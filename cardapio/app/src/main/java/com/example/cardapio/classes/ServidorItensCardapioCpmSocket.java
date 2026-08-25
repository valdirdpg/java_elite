package com.example.cardapio.classes;

import static java.lang.IO.println;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;

public class ServidorItensCardapioCpmSocket {
    void main() throws Exception {
        // ServidorItensCardapio servidor = new ServidorItensCardapio();
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            println("Servidor iniciado na porta 8080");
            try (Socket clienteSocket = serverSocket.accept()) {
                InputStream inputStream = clienteSocket.getInputStream();

                StringBuilder requestBuilder = new StringBuilder();
                int data;
                while ((data = inputStream.read()) != -1) {
                    requestBuilder.append((char) data);
                }
                String request = requestBuilder.toString();
                println("Requisição recebida:\n" + request);

                Path path = Path.of("itensCardapio.json"); 
                String content = Files.readString(path);

                OutputStream clienteOutputStream = clienteSocket.getOutputStream();
                PrintStream printStream = new PrintStream(clienteOutputStream);
                
                String response = "HTTP/1.1 200 OK" ;
                        // "Content-Type: application/json\r\n" +
                        // "\r\n" + content;
                printStream.println(response);
                printStream.println("Content-Type: application/json\r\n");
                printStream.println();
                
                printStream.println(content);
                
                // printStream.flush();
                // printStream.close();
                // inputStream.close();




                println("Cliente conectado: " + clienteSocket.getInetAddress().getHostAddress());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
