package com.example.cardapio.mains;

import com.example.cardapio.classes.BancoDados;
import com.example.cardapio.classes.InMemoryDatabase;
import com.example.cardapio.classes.ItemCardapio;
import com.example.cardapio.classes.SQLDatabase;

import java.util.List;

public class MainSql {
    void main(){
        BancoDados database = new SQLDatabase();
        database.adcionarItemCardapio(10L,"X-tudo","Hamburguer com tudo",25.9,23.90, ItemCardapio.CategoriaCardapio.ENTRADA);
        List<ItemCardapio> listaItens;
        listaItens = database.itensDoCardapio();
        listaItens.forEach(System.out::println);
        System.out.println("Total de itens "+database.totalItemCardapio());


    }
}
