/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.cardapio.mains;

import static com.example.cardapio.classes.ItemCardapio.CategoriaCardapio.*;
import static java.lang.IO.println;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import com.example.cardapio.classes.Database2;
import com.example.cardapio.classes.ItemCardapio;

/**
 *
 * @author valdi
 */
public class MainStreamMap {

    void main() {
        int cont = 0;
        Database2 database = new Database2();
        List<ItemCardapio> itens = database.itensDoCardapio();
        itens.forEach(System.out::println);

        println("------------------LISTA ESPECÍFICA--------------------------------");

        Optional<ItemCardapio> optionalItemId = database.itemCardapioPorId(8L);
        if (optionalItemId.isPresent()) {
            println(optionalItemId.get());
        } else {
            println("Item não encontrado");
        }

        println("=========================OUTRO JEITO DE FAZER O MESMO 1===================");

        String resultadoBusaca = optionalItemId.map(ItemCardapio::toString)
                .orElse("Item não encontrado");
        println(resultadoBusaca);
        println("=========================OUTRO JEITO DE FAZER O MESMO 2===================");

        // Mantém as categorias que estão em promoção
        Set<ItemCardapio.CategoriaCardapio> categoriasEmPromocao
                = EnumSet.of(
                        SOBREMESAS,
                        ENTRADA
                );

// Adiciona mais uma categoria à promoção
        categoriasEmPromocao.add(PRATOS_PRINCIPAIS);

// Exibe todas as categorias em promoção
        categoriasEmPromocao.forEach(System.out::println);

        println("=========================OUTRO JEITO DE FAZER O MESMO 3===================");

        Set<ItemCardapio.CategoriaCardapio> categoriasEmPromocao2 = Set.of(
                SOBREMESAS,
                ENTRADA
        );
        //categoriasEmPromocao2.add(LANCHES);
        categoriasEmPromocao2.forEach(System.out::println);
        println("=========================OUTRO JEITO DE FAZER O MESMO 4===================");
        Set<ItemCardapio.CategoriaCardapio> categoriasEmPromocao3 = new TreeSet<>();

        categoriasEmPromocao3.add(BEBIDAS);
        categoriasEmPromocao3.add(LANCHES);
        categoriasEmPromocao3.add(PRATOS_PRINCIPAIS);
        categoriasEmPromocao3.forEach(System.out::println);

        // PRECISO DAS DESCRICOES ASSOCIADAS AS CATEGORIAS EM PROMOCAO
        Map<ItemCardapio.CategoriaCardapio, String> promocoes
                = new EnumMap<>(ItemCardapio.CategoriaCardapio.class);
        promocoes.put(SOBREMESAS, "O doce perfeito para você!");
        promocoes.put(ENTRADA, "Comece sua refeição com um toque de sabor!");
        System.out.println(promocoes);

        Map<ItemCardapio.CategoriaCardapio, String> promocoes2 = new HashMap<>();
        println("=============FAZ A MESMA COISA SÓ QUE MAIS LENTO=============");
        promocoes2.put(ItemCardapio.CategoriaCardapio.SOBREMESAS, "O doce perfeito para você!");
        promocoes2.put(ItemCardapio.CategoriaCardapio.ENTRADA, "Comece sua refeição com um toque de sabor!");

        System.out.println(promocoes2);

    }
}
