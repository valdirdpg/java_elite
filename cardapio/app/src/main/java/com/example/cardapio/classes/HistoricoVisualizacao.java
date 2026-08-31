/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.cardapio.classes;

import java.io.PrintStream;

import static java.lang.IO.println;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.WeakHashMap;

/**
 *
 * @author valdi
 */
public class HistoricoVisualizacao {
    private final BancoDados database;
    private final Map<ItemCardapio, LocalDateTime> visualizacoes = new WeakHashMap<>();
    public HistoricoVisualizacao(BancoDados database) {
        this.database = database;
    }
    
    public void registrarVisualizacao(Long itemId){
        Optional<ItemCardapio> optionalItemCardapio = database.itemCardapioPorId(itemId);
        
        if(optionalItemCardapio.isEmpty()){
            println("Item não encontrado " + itemId);
            return;
        }
        ItemCardapio itemCardapio = optionalItemCardapio.get();
        var data = LocalDateTime.now();
        visualizacoes.put(itemCardapio, data);
        System.out.printf("'%s' visualizado em '%s'\n", itemCardapio.nome(),data);
    }
    
    public void contadorRegistroHistorico(){
        var contador = visualizacoes.size();
        println("Quantidade de visualizações: " + contador);
    }
    
    public void registroHistorico(){
        if (visualizacoes.isEmpty()){
            println("Nenhum item foi visualizado ainda.\n");
            return;
        }
        visualizacoes.forEach((item,hora)-> System.out
                .printf("\n* %s em %s", item.nome(), hora));
        
    }
    
    
    
    
}
