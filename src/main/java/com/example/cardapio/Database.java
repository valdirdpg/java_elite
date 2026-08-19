/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.cardapio;

import static com.example.cardapio.ItemCardapio.CategoriaCardapio.BEBIDAS;
import static com.example.cardapio.ItemCardapio.CategoriaCardapio.PRATOS_PRINCIPAIS;
import static com.example.cardapio.ItemCardapio.CategoriaCardapio.SOBREMESAS;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author valdir.santos
 */
public class Database {
    public static List<ItemCardapio> itensDoCardapio() {
        List<ItemCardapio> itens = new ArrayList<>();

        var refrescoDoChaves = new ItemCardapio(1L, "Refresco do Chaves",
                "Suco de limão que parece de tamarindo e tem gosto de groselha.", 2.99, 0, BEBIDAS);
        itens.add(refrescoDoChaves);

        var sanduicheDoChaves = new ItemCardapio(2L, "Sanduíche de Presunto do Chaves",
                "Sanduíche de presunto simples, mas feito com muito amor.", 3.50, 0, PRATOS_PRINCIPAIS);
        itens.add(sanduicheDoChaves);

        var tortaDeFrango = new ItemCardapio(3L, "Torta de Frango da Dona Florinda",
                "Torta de frango com recheio cremoso e massa crocante.", 12.99, 0, PRATOS_PRINCIPAIS);
        itens.add(tortaDeFrango);

        var pipocaDoQuico = new ItemCardapio(4L, "Pipoca do Quico",
                "Balde de pipoca preparado com carinho pelo Quico.", 4.99, 0, PRATOS_PRINCIPAIS);
        itens.add(pipocaDoQuico);

        var aguaJamaica = new ItemCardapio(5L, "Água de Jamaica",
                "Água aromatizada com hibisco e toque de açúcar.", 2.5, 0, BEBIDAS);
        itens.add(aguaJamaica);

        var churrosDoChaves = new ItemCardapio(6L, "Churros do Chaves",
                "Churros recheados com doce de leite, clássicos e irresistíveis.", 4.99, 0, SOBREMESAS);
        itens.add(churrosDoChaves);

        var tacosDeCarnitas = new ItemCardapio(7L, "Tacos de Carnitas",
                "Tacos recheados com carne tenra", 25.9, 0, PRATOS_PRINCIPAIS);
        itens.add(tacosDeCarnitas);

        return itens;
    }
}
