/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.cardapio.classes;

import static com.example.cardapio.classes.ItemCardapio.CategoriaCardapio.*;

import java.util.ArrayList;
import java.util.HashMap;


import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 *
 * @author valdir.santos
 */
public class Database2 {

    public Map<Long, ItemCardapio> itensPorId = new HashMap<>();

    public Database2() {

        var refrescoDoChaves = new ItemCardapio(1L, "Refresco do Chaves",
                "Suco de limão que parece de tamarindo e tem gosto de groselha.", 2.99, 0, BEBIDAS);
        itensPorId.put(1L, refrescoDoChaves);

        var sanduicheDoChaves = new ItemCardapio(2L, "Sanduíche de Presunto do Chaves",
                "Sanduíche de presunto simples, mas feito com muito amor.", 3.50, 0, PRATOS_PRINCIPAIS);
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
                "Churros recheados com doce de leite, clássicos e irresistíveis.", 4.99, 0, SOBREMESAS);
        itensPorId.put(6L, churrosDoChaves);

        var tacosDeCarnitas = new ItemCardapio(7L, "Tacos de Carnitas",
                "Tacos recheados com carne tenra", 25.9, 0, PRATOS_PRINCIPAIS);
        itensPorId.put(7L, tacosDeCarnitas);
        var batataDoce = new ItemCardapio(8L, "Batata Doce",
                "Batata fatiada com caramelo", 15.9, 0, PRATOS_PRINCIPAIS);
        itensPorId.put(8L, batataDoce);

    }

    public  List<ItemCardapio> itensDoCardapio() {
        return new ArrayList<>(itensPorId.values());
    }
    
    public Optional<ItemCardapio> itemCardapioPorId(Long itemId){
        ItemCardapio itemCardapio = itensPorId.get(itemId);
        return Optional.ofNullable(itemCardapio);
    }
    public boolean removerItemCardpio(Long idParaRemover) {        
        var item = itensPorId.remove(idParaRemover);
        return item != null;
    }

    @Override
    public String toString() {
        return "Database2{" + "itensPorId=" + itensPorId + '}'+"\n";
    }

    
}
