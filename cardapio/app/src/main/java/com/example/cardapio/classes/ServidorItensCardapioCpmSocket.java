package com.example.cardapio.classes;

import static java.lang.IO.println;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class ServidorItensCardapioCpmSocket {
    void main() throws Exception {
        // ServidorItensCardapio servidor = new ServidorItensCardapio();
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            println("Servidor iniciado na porta 8080");
            while (true) {
                Socket clienteSocket = serverSocket.accept();{
                    Thread thread = new Thread(() -> {
                        try {
                            processaRequisicao(clienteSocket);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                    thread.start();
                    //processaRequisicao(clienteSocket);
                }
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void processaRequisicao(Socket clienteSocket) throws IOException {
        try (clienteSocket) {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(clienteSocket.getInputStream(), StandardCharsets.UTF_8));
            StringBuilder requestBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null && !line.isEmpty()) {
                requestBuilder.append(line).append("\r\n");
            }
            String request = requestBuilder.toString();
            println("Requisição recebida:\n" + request);

            Path path = Path.of("cardapio/itensCardapio.json");
            String content = Files.readString(path);
            byte[] contentBytes = content.getBytes(StandardCharsets.UTF_8);

            OutputStream clienteOutputStream = clienteSocket.getOutputStream();
            PrintStream printStream = new PrintStream(clienteOutputStream, false, StandardCharsets.UTF_8);

            printStream.print("HTTP/1.1 200 OK\r\n");
            printStream.print("Content-Type: application/json; charset=UTF-8\r\n");
            printStream.print("Content-Length: " + contentBytes.length + "\r\n");
            printStream.print("Connection: close\r\n");
            printStream.print("\r\n");
            printStream.flush();
            clienteOutputStream.write(contentBytes);
            clienteOutputStream.flush();

            println("Cliente conectado: " + clienteSocket.getInetAddress().getHostAddress());
        }finally {
            clienteSocket.close();
        }
    }
}
