/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.cardapio.classes;

/**
 *
 * @author valdir.santos
 */
public record ItemCardapio(Long id, String nome, String descricao, double preco, double precoComDesconto, CategoriaCardapio categoria) {

    public enum CategoriaCardapio {
        ENTRADA,PRATOS_PRINCIPAIS,SOBREMESAS,BEBIDAS,LANCHES;
    }
}
