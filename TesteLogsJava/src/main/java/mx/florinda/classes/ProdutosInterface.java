package mx.florinda.classes;

import java.sql.SQLException;

public interface ProdutosInterface {
    Produtos inserirProduto(long id, String nome, double preco) throws SQLException;

    Produtos atualizarProduto(long id, String nome, double preco);

    boolean removerProduto(long id);

    boolean buscarProduto(long id);
}
