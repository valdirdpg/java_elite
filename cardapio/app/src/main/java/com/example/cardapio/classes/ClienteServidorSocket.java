package com.example.cardapio.classes;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ClienteServidorSocket {
    public static void main(String[] args) {
        try {
            ServerSocket servidor = new ServerSocket(8080);
            System.out.println("Servidor ouvindo na porta 8080...");

            Socket socket = servidor.accept();

            BufferedReader entrada = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );

            PrintWriter saida = new PrintWriter(socket.getOutputStream(), true);

            String mensagemCliente = entrada.readLine();
            System.out.println("Cliente disse: " + mensagemCliente);

            saida.println("Olá, cliente! Recebi sua mensagem.");

            socket.close();
            servidor.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
