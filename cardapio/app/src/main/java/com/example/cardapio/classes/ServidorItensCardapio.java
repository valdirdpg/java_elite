package com.example.cardapio.classes;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.file.Path;
import java.nio.file.Files;

import com.sun.net.httpserver.HttpServer;

public class ServidorItensCardapio {

    public static void main(String[] args) throws IOException {

        HttpServer server = HttpServer.create(new InetSocketAddress(8001), 0);

        server.createContext("/itensCardapio.json", exchange -> {

            Path path = Path.of("itensCardapio.json"); // ajuste o caminho conforme seu projeto

            String content = Files.readString(path);
            byte[] bytes = content.getBytes();

            exchange.getResponseHeaders().add("Content-Type", "application/json");

            exchange.sendResponseHeaders(200, bytes.length);

            try (OutputStream os = exchange.getResponseBody()) {
                os.write(bytes);
            }
        });

        System.out.println("Servidor iniciado na porta 8001");
        server.start();
    }
}
