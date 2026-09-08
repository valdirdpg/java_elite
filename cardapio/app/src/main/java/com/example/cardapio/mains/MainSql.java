package com.example.cardapio.mains;

import com.example.cardapio.classes.BancoDados;
import com.example.cardapio.classes.InMemoryDatabase;
import com.example.cardapio.classes.ItemCardapio;
import com.example.cardapio.classes.SQLDatabase;

import java.util.List;

import static java.lang.IO.println;

public class MainSql {
    void main() {
        BancoDados database = new SQLDatabase();
        // database.adcionarItemCardapio(10L,"X-tudo","Hamburguer com tudo",25.9,23.90, ItemCardapio.CategoriaCardapio.ENTRADA);
        List<ItemCardapio> listaItens;
        listaItens = database.itensDoCardapio();
        listaItens.forEach(System.out::println);
        System.out.println("Total de itens " + database.totalItemCardapio());
        println(database.itemCardapioPorId(5L));
        var resultado = database.removerItemCardpio(7L);
        println(resultado);
        System.out.println("Total de itens " + database.totalItemCardapio());
    }
}
