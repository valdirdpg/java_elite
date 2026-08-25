/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.cardapio.classes;

/**
 *
 * @author valdir.santos
 */
public class ItemCardapioOLD {
     public enum CategoriaCardapio {
        ENTRADA, PRATOS_PRINCIPAIS, SOBREMESAS, BEBIDAS, LANCHES;        
    }
     
    private Long id;
    private String nome;
    private String descricao;
    private double preco;
    private double precoComDesconto;
    private CategoriaCardapio cardapio;

    public ItemCardapioOLD(Long id, String nome, String descricao, double preco, double precoComDesconto, CategoriaCardapio cardapio) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.precoComDesconto = precoComDesconto;
        this.cardapio = cardapio;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getPreco() {
        return preco;
    }

    public double getPrecoComDesconto() {
        return precoComDesconto;
    }

    public CategoriaCardapio getCardapio() {
        return cardapio;
    }

    @Override
    public String toString() {
        return "ItemCardapio{" + "id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", preco=" + preco + ", pre\u00e7oComDesconto=" + precoComDesconto + ", cardapio=" + cardapio + '}';
    }
    
    
    
}
