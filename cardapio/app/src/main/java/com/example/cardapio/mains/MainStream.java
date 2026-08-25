/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.cardapio.mains;

import static java.lang.IO.println;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

import com.example.cardapio.classes.Database;
import com.example.cardapio.classes.ItemCardapio;

/**
 *
 * @author valdi
 */
public class MainStream {

    void main() {
        Database database = new Database();
        List<ItemCardapio> itens = database.itensDoCardapio();
        Set<ItemCardapio.CategoriaCardapio> categorias = new LinkedHashSet<>();
        for (ItemCardapio item : itens) {
            ItemCardapio.CategoriaCardapio categoria = item.categoria();
            categorias.add(categoria);
        }
        for (ItemCardapio.CategoriaCardapio categoria : categorias) {
            println(categoria);
        }
        println("============UTILIZANDO STREAM PARAFAZER OMESMO========");
        itens.stream().map(ItemCardapio::categoria)
                .collect(Collectors.toSet())
                .forEach(System.out::println);

        println("------------------------QUANTIDADE DE ÍTENS POR CATEGORIA-----------------------------");
        itens.stream().collect(Collectors.groupingBy(ItemCardapio::categoria,
                TreeMap::new,
                Collectors.counting())).forEach((chave,valor)->println(chave+"=>"+valor));
       
    }   
}
