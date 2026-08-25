/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.cardapio.mains;

import static java.lang.IO.println;

import com.example.cardapio.classes.Database2;
import com.example.cardapio.classes.HistoricoVisualizacao;

/**
 *
 * @author valdi
 */
public class HistoricoMain {

    Database2 database = new Database2();

    void main() throws InterruptedException {
        HistoricoVisualizacao historico = new HistoricoVisualizacao(database);
        historico.registrarVisualizacao(1L);
        historico.registrarVisualizacao(2L);
        historico.registrarVisualizacao(4L);
        historico.registrarVisualizacao(18L);
        
        
        println("\n=============testando todas as op��es de lambda==========");
        database.itensPorId.forEach((chave,valor) -> println( "chave: "+chave + " valor: "+valor));
        //println(database.itensPorId);
        Long idParaRemover = 1L;
        var removido = database.removerItemCardpio(idParaRemover);
        println(removido);
        database.itensDoCardapio().forEach(System.out::println);
        println("================LISTAR HISTORICO=================");
        println("Solicitando o GC....");
        System.gc();
        Thread.sleep(500);
        
        historico.contadorRegistroHistorico();
        historico.registroHistorico();
    }

}
