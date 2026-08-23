package org.example.classes;

public class TesteClass {
    String nome;
    int idade;

    public TesteClass(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    @Override
    public String toString() {
        return "TesteClass [nome=" + nome + ", idade=" + idade + "]";
    }

    
}
