# Explicação do `ServidorCardapioSocket`

A classe `ServidorCardapioSocket` funciona como um pequeno servidor HTTP feito manualmente usando `ServerSocket`. Ele escuta requisições na porta **8081**.

## 1. De onde vêm os dados?

Nesta linha:

```java
BancoDados database = new SQLDatabase();
```

o programa usa a classe `SQLDatabase`, que conecta ao MySQL:

```text
jdbc:mysql://localhost:3306/cardapio
```

Os dados são obtidos da tabela `cardapio.item_cardapio` usando:

```sql
SELECT * FROM cardapio.item_cardapio
```

Ao iniciar, os itens são carregados do MySQL para a lista `itens`:

```java
private final List<ItemCardapio> itens =
    new CopyOnWriteArrayList<>(database.itensDoCardapio());
```

**Importante:** depois disso, as requisições usam a lista em memória, e não consultam o banco novamente.

## 2. Como o servidor começa?

O método `main` executa:

```java
new ServidorCardapioSocket().iniciar();
```

Depois o servidor abre a porta:

```java
new ServerSocket(8081)
```

Quando um cliente faz uma requisição, o servidor aceita a conexão e cria uma tarefa para atendê-la:

```java
Socket cliente = servidor.accept();
executor.execute(() -> atender(cliente));
```

São permitidos até **10 clientes simultaneamente**, porque foi criado um pool com 10 threads.

## 3. Como ele lê a requisição?

O método:

```java
lerRequisicao(cliente.getInputStream())
```

lê a requisição HTTP enviada pelo navegador, Postman ou outra aplicação.

Uma requisição poderia ser:

```http
GET /itens-cardapio HTTP/1.1
Host: localhost:8081
```

O código identifica:

```text
GET
/itens-cardapio
```

Ele também procura o cabeçalho `Content-Length`, que informa o tamanho do corpo da requisição. Esse cabeçalho é usado principalmente no `POST`.

A requisição é transformada neste record:

```java
new Requisicao(metodo, caminho, corpo)
```

## 4. Quais requisições funcionam?

O método `processar` verifica o método HTTP e o caminho.

### Buscar todos os itens

```http
GET http://localhost:8081/itens-cardapio
```

Executa:

```java
return new Resposta(200, gson.toJson(itens));
```

Retorna os itens da lista em memória como JSON. O `Gson` converte os objetos Java para JSON.

### Consultar o total

```http
GET http://localhost:8081/itens-cardapio/total
```

Executa:

```java
gson.toJson(itens.size())
```

Retorna somente a quantidade de itens, por exemplo:

```json
8
```

Esse valor vem de `itens.size()`, e não do método `totalItemCardapio()` do banco.

### Adicionar um item

```http
POST http://localhost:8081/itens-cardapio
Content-Type: application/json
```

Corpo:

```json
{
  "id": 9,
  "nome": "Hambúrguer",
  "descricao": "Hambúrguer artesanal",
  "preco": 20.0,
  "precoComDesconto": 18.0,
  "categoria": "LANCHES"
}
```

O JSON é convertido para `ItemCardapio`:

```java
ItemCardapio item =
    gson.fromJson(requisicao.corpo(), ItemCardapio.class);
```

Depois é adicionado à lista:

```java
itens.add(item);
```

O item é devolvido com status `201 Created`.

**Atenção:** esse `POST` não salva o item no MySQL. Ele salva apenas na memória. Se o servidor for encerrado, o item será perdido. Ao reiniciar, a lista será carregada novamente do banco.

## 5. Como a resposta volta ao cliente?

O método:

```java
enviarResposta(cliente.getOutputStream(), resposta);
```

monta uma resposta HTTP manualmente:

```http
HTTP/1.1 200 OK
Content-Type: application/json; charset=UTF-8
Content-Length: ...
Connection: close
```

Depois envia o JSON:

```java
saida.write(corpo);
saida.flush();
```

O cliente recebe o status e o conteúdo.

## 6. O que acontece com uma rota inválida?

Qualquer combinação diferente das três rotas aceitas retorna status `404`:

```java
return new Resposta(404, "{\"erro\":\"Endpoint nao encontrado\"}");
```

Exemplo:

```http
GET http://localhost:8081/produtos
```

Resposta:

```json
{
  "erro": "Endpoint nao encontrado"
}
```

## Fluxo completo

```text
Cliente
   |
   | GET /itens-cardapio
   v
ServidorSocket porta 8081
   |
   | lê método, caminho e corpo
   v
processar()
   |
   | procura a rota correta
   v
lista "itens" em memória
   |
   | converte para JSON com Gson
   v
Resposta HTTP para o cliente
```

## Diferença entre os servidores do projeto

Existe também a classe `ServidorItensCardapio`, que usa a porta **8001** e lê o arquivo `itensCardapio.json`.

Ela é diferente do `ServidorCardapioSocket`, que usa o MySQL inicialmente e a lista em memória depois.

## 7. Buscando um item pelo ID na requisição

Este código:

```java
if ("GET".equals(requisicao.metodo())
        && "/itens-cardapio/id".equals(requisicao.caminho())) {
    return new Resposta(200, gson.toJson(itens));
}
```

procura literalmente pelo caminho:

```text
/itens-cardapio/id
```

Ele não reconhece uma requisição como:

```text
/itens-cardapio/3
```

Além disso, `gson.toJson(itens)` retorna todos os itens, em vez de somente o item com o ID informado.

Uma forma de buscar o ID na lista em memória é:

```java
if ("GET".equals(requisicao.metodo())
        && requisicao.caminho().startsWith("/itens-cardapio/")) {

    String idTexto = requisicao.caminho()
            .substring("/itens-cardapio/".length());

    try {
        long id = Long.parseLong(idTexto);

        return itens.stream()
                .filter(item -> item.id() == id)
                .findFirst()
                .map(item -> new Resposta(200, gson.toJson(item)))
                .orElse(new Resposta(
                        404, "{\"erro\":\"Item nao encontrado\"}"));

    } catch (NumberFormatException erro) {
        return new Resposta(400, "{\"erro\":\"ID invalido\"}");
    }
}
```

Assim, a requisição:

```http
GET /itens-cardapio/3
```

retorna somente o item de ID `3`.

## 8. Usando uma variável em uma consulta SQL

Para passar uma variável para uma consulta, use `PreparedStatement` com o caractere `?`:

```java
String sql = "SELECT * FROM tabela WHERE id = ?";
statement.setLong(1, varId);
```

O número `1` indica que o primeiro `?` receberá o valor de `varId`.

Exemplo de implementação para buscar um item pelo ID no `SQLDatabase`:

```java
@Override
public Optional<ItemCardapio> itemCardapioPorId(Long itemId) {
    String sql = "SELECT * FROM cardapio.item_cardapio WHERE id = ?";

    try (Connection conexao = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/cardapio",
                "root",
                "root123456");
         PreparedStatement statement = conexao.prepareStatement(sql)) {

        statement.setLong(1, itemId);

        try (ResultSet rs = statement.executeQuery()) {
            if (rs.next()) {
                ItemCardapio item = new ItemCardapio(
                        rs.getLong("id"),
                        rs.getString("nome"),
                        rs.getString("descricao"),
                        rs.getDouble("preco"),
                        rs.getDouble("preco_promocional"),
                        ItemCardapio.CategoriaCardapio.valueOf(
                                rs.getString("categoria"))
                );

                return Optional.of(item);
            }

            return Optional.empty();
        }

    } catch (SQLException erro) {
        throw new RuntimeException(erro);
    }
}
```

O correto é usar `SELECT`, e não `DELECT`:

```sql
SELECT * FROM tabela WHERE id = ?
```

O `PreparedStatement` é preferível à concatenação de strings, por exemplo:

```java
// Evite:
String sql = "SELECT * FROM tabela WHERE id = " + varId;
```

Ele separa o comando SQL do valor recebido e ajuda a evitar SQL Injection.
