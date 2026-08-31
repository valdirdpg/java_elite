package com.example.cardapio.classes;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SQLDatabase implements BancoDados {
    @Override
    public List<ItemCardapio> itensDoCardapio() {
        List<ItemCardapio> itens = new ArrayList<>();
        String sql = "SELECT * FROM cardapio.item_cardapio";
        // executar a query e retornar uma lista de itens do cardápio
        try (Connection conexao =
                     DriverManager.getConnection("jdbc:mysql://localhost:3" +
                                     "306/cardapio", "root",
                             "root123456");
             PreparedStatement statement = conexao.prepareStatement(sql);
             ResultSet rs = statement.executeQuery();){

            while (rs.next()){
                long id = rs.getLong("id");
                String nome = rs.getString("nome");
                String descricao = rs.getString("descricao");
                double preco = rs.getDouble("preco");
                double precoComDesconto = rs.getDouble("preco_promocional");
                ItemCardapio.CategoriaCardapio categoria = ItemCardapio.CategoriaCardapio.valueOf(rs.getString("categoria"));
                var itemCardapio = new ItemCardapio(id,nome,descricao,preco,precoComDesconto,categoria);
                itens.add(itemCardapio);
                }
            return itens;
        } catch (SQLException e) {
            throw  new RuntimeException(e);
        }
    }

    @Override
    public Optional<ItemCardapio> itemCardapioPorId(Long itemId) {
        return Optional.empty();
    }

    @Override
    public boolean removerItemCardpio(Long idParaRemover) {
        return false;
    }

    @Override
    public int totalItemCardapio() {
        String sql = "SELECT COUNT(*) FROM cardapio.item_cardapio";
        try (Connection conexao =
                     DriverManager.getConnection("jdbc:mysql://localhost:3" +
                                     "306/cardapio", "root",
                             "root123456");
             PreparedStatement statement = conexao.prepareStatement(sql);
             ResultSet rs = statement.executeQuery();){
            int total = 0;
            if (rs.next()){
                total = rs.getInt(1);
                return total;
            }
            return total;
        } catch (SQLException e) {
            throw  new RuntimeException(e);
        }

    }

    @Override
    public boolean alterarPrecoItemCardapio(Long idSelecionado, Double novoPreco) {
        return false;
    }

    @Override
    public ItemCardapio adcionarItemCardapio(Long id, String nome, String descricao, double preco,
                                             double preco_promocional,
                                             ItemCardapio.CategoriaCardapio categoria) {
        String sql = "INSERT INTO cardapio.item_cardapio (id, nome, descricao, preco, preco_promocional, categoria) VALUES (?, ?, ?, ?, ?,?)";
        try (Connection conexao =
                     DriverManager.getConnection("jdbc:mysql://localhost:3" +
                                     "306/cardapio", "root",
                             "root123456");
             PreparedStatement statement = conexao.prepareStatement(sql);){

            statement.setLong(1, id);
            statement.setString(2, nome);
            statement.setString(3, descricao);
            statement.setDouble(4, preco);
            statement.setDouble(5, preco_promocional);
            statement.setString(6, categoria.name());
            statement.execute();
            return new ItemCardapio(id, nome, descricao, preco,preco_promocional, categoria);
        } catch (SQLException e) {
            throw  new RuntimeException(e);
        }

    }
}
