package mx.florinda.classes;
import java.sql.*;
//import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BancoDeDados implements ProdutosInterface {
    Logger logger = Logger.getLogger(BancoDeDados.class.getName());
    Conexao conexao = new Conexao();

    public BancoDeDados() throws SQLException {
    }

    @Override
    public Produtos inserirProduto(long id, String nome, double preco) throws SQLException {
        logger.info("Inserindo produto no banco de dados");
        String sql = "INSERT INTO produtos.produto(id, nome, preco) VALUES (?, ?, ?)";
        try (Connection con = conexao.conexaoSql();
                PreparedStatement statement = con.prepareStatement(sql);) {
            statement.setLong(1, id);
            statement.setString(2, nome);
            statement.setDouble(3, preco);
            statement.execute();
            return new Produtos(id, nome, preco);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);

        }
    }
    @Override
    public Produtos atualizarProduto(long id, String nome, double preco) {
        logger.info("Atualizando produto no banco de dados");
        String sql = "UPDATE produtos.produto SET nome=?, preco=? WHERE id=?";
        try(Connection con = conexao.conexaoSql();
        PreparedStatement stmt = con.prepareStatement(sql);){
            stmt.setString(1, nome);
            stmt.setDouble(2, preco);
            stmt.setLong(3, id);
            stmt.execute();
            return new Produtos(id, nome, preco);
        }catch (SQLException ex) {
            logger.log(Level.FINER, "Falha ao atualizar produto!", ex);
            throw new RuntimeException(ex);
        }

    }

    @Override
    public boolean removerProduto(long id) {
        int updateRows = 0;
        logger.fine("Removendo produto no banco de dados" + id);
        String sql = "DELETE FROM produtos.produto WHERE id=?";
        try(Connection con = conexao.conexaoSql();
        PreparedStatement stmt = con.prepareStatement(sql);){
            stmt.setLong(1, id);
            updateRows=stmt.executeUpdate();
            if(updateRows > 0) {
                logger.info("Produto removido com sucesso!");
                return true;
            }else {
                logger.info("Produto não encontrado!");
                return false;
            }
        }catch (SQLException ex) {
            logger.log(Level.FINER, "Falha ao remover produto!", ex);
            throw new RuntimeException(ex);
        }

    }

    @Override
    public boolean buscarProduto(long id) {
        return false;
    }
    public List<Produtos> listarProdutos() {
        logger.info("Listando produtos do banco de dados");
        Produtos produtos = null;
        List<Produtos> produto = new ArrayList<>();
        try (Connection con = conexao.conexaoSql();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM produtos.produto");) {
            while (rs.next()) {
                long id = rs.getLong("id");
                String nome = rs.getString("nome");
                double preco = rs.getDouble("preco");
                //System.out.println("ID: " + id + " Nome: " + nome + " Preço: " + preco);
                produtos = new Produtos(id, nome, preco);
                produto.add(produtos);
            }
            return produto;
        } catch (SQLException ex) {
            logger.log(Level.FINER, "Falha ao listar produtos!", ex);
        }
        return produto;
    }
}
