package mx.florinda.classes;

public class Produtos implements ProdutosInterface {
    private long id;
    private String nome;
    private double preco;

    public Produtos(long id, String nome, double preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }
    @Override
    public Produtos inserirProduto(long id, String nome, double preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        return new Produtos(id, nome, preco);
    }
    @Override
    public Produtos atualizarProduto(long id, String nome, double preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        return new Produtos(id, nome, preco);
    }
    @Override
    public boolean removerProduto(long id) {
        if (this.id == id) {
            this.id = 0;
            this.nome = null;
            this.preco = 0.0;
            return true;
        }
        return false;
    }
    @Override
    public boolean buscarProduto(long id) {
        if (this.id == id) {
            return true;
        }
        return false;
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    @Override
    public String toString() {
        return "Produtos{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", preco=" + preco +
                '}';
    }
}
