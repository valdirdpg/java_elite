package com.example.cardapio.classes;

import java.util.List;
import static java.lang.IO.println;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.google.gson.Gson;

public record GeradorItensCardapioJSON() {
    
    void main() throws IOException{
        Database2 database = new Database2();
        List<ItemCardapio> listarItensCardapio = database.itensDoCardapio();
        Gson gson = new Gson();
        String json = gson.toJson(listarItensCardapio);
        println("\n"+json+"\n");

        Path path = Path.of("itensCardapio.json");
        Files.writeString(path, json);
    }
}
