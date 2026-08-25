/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.cardapio.mains;

import com.example.cardapio.classes.Database;
import com.example.cardapio.classes.ItemCardapio;
import com.google.gson.Gson;
import static java.lang.IO.println;


/**
 *
 * @author valdir.santos
 */
public class MainClasse {   

        void main() {
        Gson gson = new Gson();
        String json = "";
        
        var lista = Database.itensDoCardapio();
        var novaLista = lista.remove(0);
        
        for (ItemCardapio item : lista){
          json = gson.toJson(item);
          println(json);
          
        }
        var tamanhoLista = lista.size();
        String filtro = lista.get(5).toString();
        println(filtro);
        
        
        println(novaLista);
        println(tamanhoLista);
        println("=======================FOR SIMPLIFICADO STREAM=====================");
        lista.forEach(System.out::println);
        
        /**
        Resultado com ArrayList
        ItemCardapio{id=7, nome=Tacos de Carnitas, descricao=Tacos recheados com carne tenra, preco=25.9, pre�oComDesconto=0.0, cardapio=PRATOS_PRINCIPAIS}
        ItemCardapio{id=1, nome=Refresco do Chaves, descricao=Suco de lim�o que parece de tamarindo e tem gosto de groselha., preco=2.99, pre�oComDesconto=0.0, cardapio=BEBIDAS}
        7
        **/     
        

    }
}
