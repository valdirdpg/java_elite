/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.cardapio.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import static com.example.cardapio.classes.ItemCardapio.CategoriaCardapio.*;

/**
 *
 * @author valdir.santos
 */
public class InMemoryDatabase implements BancoDados {
    public final Map<Long, ItemCardapio> itensPorId = new ConcurrentHashMap<>();
    //public Map<Long, ItemCardapio> itensPorId = new HashMap<>();

    public InMemoryDatabase() {

        var refrescoDoChaves = new ItemCardapio(1L, "Refresco do Chaves",
                "Suco de limão que parece de tamarindo e tem gosto de groselha.", 2.99, 0, BEBIDAS);
        itensPorId.put(1L, refrescoDoChaves);

        var sanduicheDoChaves = new ItemCardapio(2L, "Sanduíche de Presunto do Chaves",
                "Sanduíche de presunto simples, mas feito com muito amor.", 3.50, 0, PRATO_PRINCIPAL);
        itensPorId.put(2L, sanduicheDoChaves);

        var tortaDeFrango = new ItemCardapio(3L, "Torta de Frango da Dona Florinda",
                "Torta de frango com recheio cremoso e massa crocante.", 12.99, 0, ENTRADA);
        itensPorId.put(3L, tortaDeFrango);

        var pipocaDoQuico = new ItemCardapio(4L, "Pipoca do Quico",
                "Balde de pipoca preparado com carinho pelo Quico.", 4.99, 0, LANCHES);
        itensPorId.put(4L, pipocaDoQuico);

        var aguaJamaica = new ItemCardapio(5L, "Água de Jamaica",
                "Água aromatizada com hibisco e toque de açúcar.", 2.5, 0, BEBIDAS);
        itensPorId.put(5L, aguaJamaica);

        var churrosDoChaves = new ItemCardapio(6L, "Churros do Chaves",
                "Churros recheados com doce de leite, clássicos e irresistíveis.", 4.99, 0, SOBREMESA);
        itensPorId.put(6L, churrosDoChaves);

        var tacosDeCarnitas = new ItemCardapio(7L, "Tacos de Carnitas",
                "Tacos recheados com carne tenra", 25.9, 0, PRATO_PRINCIPAL);
        itensPorId.put(7L, tacosDeCarnitas);
        var batataDoce = new ItemCardapio(8L, "Batata Doce",
                "Batata fatiada com caramelo", 15.9, 0, PRATO_PRINCIPAL);
        itensPorId.put(8L, batataDoce);

    }

    @Override
    public List<ItemCardapio> itensDoCardapio() {
        return new ArrayList<>(itensPorId.values());
    }

    @Override
    public Optional<ItemCardapio> itemCardapioPorId(Long itemId) {
        ItemCardapio itemCardapio = itensPorId.get(itemId);
        return Optional.ofNullable(itemCardapio);
    }

    @Override
    public boolean removerItemCardpio(Long idParaRemover) {
        var item = itensPorId.remove(idParaRemover);
        return item != null;
    }

    @Override
    public int totalItemCardapio() {
        return itensPorId.size();
    }

    @Override
    public boolean alterarPrecoItemCardapio(Long idSelecionado, Double novoPreco) {
        var item = itensPorId.get(idSelecionado);
        if (item != null) {
            item.criarNovoItemCardapio(idSelecionado, item.nome(), item.descricao(), novoPreco,
                    item.precoComDesconto(), item.categoria());
            return true;
        }
        return false;
    }
    @Override
    public ItemCardapio adcionarItemCardapio(Long id, String nome, String descricao, double preco,
                                             double preco_promocional, ItemCardapio.CategoriaCardapio categoria){
        var item = new ItemCardapio(id,nome,descricao,preco,0,categoria);
        itensPorId.put(id,item);
        return item;
    }

    public String historicoDeVisualizacao(Long id) {
        return "Item visualizado: " + itensPorId.get(id).nome()
                + itensPorId.get(id).preco() + itensPorId.get(id).categoria().toString();
    }

    @Override
    public String toString() {
        return "Banco de Dados{" + "itensPorId=" + itensPorId + '}' + "\n";
    }
}