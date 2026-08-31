package com.example.cardapio.classes;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class ServidorCardapioSocket {
    private static final int PORTA = 8081;
    private final Gson gson = new Gson();
    BancoDados database = new SQLDatabase();
    private final List<ItemCardapio> itens = new CopyOnWriteArrayList<>(database.itensDoCardapio());

    public static void main(String[] args) throws IOException {
        new ServidorCardapioSocket().iniciar();
    }

    private void iniciar() throws IOException {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        try (ServerSocket servidor = new ServerSocket(PORTA)) {
            System.out.println("Servidor iniciado na porta " + PORTA);
            while (!servidor.isClosed()) {
                Socket cliente = servidor.accept();
                executor.execute(() -> atender(cliente));
            }
        } finally {
            executor.shutdown();
        }
    }

    private void atender(Socket cliente) {
        try (cliente) {
            Requisicao requisicao = lerRequisicao(cliente.getInputStream());
            Resposta resposta = processar(requisicao);
            enviarResposta(cliente.getOutputStream(), resposta);
        } catch (IOException erro) {
            System.err.println("Erro ao atender cliente: " + erro.getMessage());
        }
    }

    private Requisicao lerRequisicao(InputStream entrada) throws IOException {
        ByteArrayOutputStream cabecalho = new ByteArrayOutputStream();
        int anterior = -1;
        int atual;
        while ((atual = entrada.read()) != -1) {
            cabecalho.write(atual);
            if (anterior == '\r' && atual == '\n') {
                byte[] bytes = cabecalho.toByteArray();
                int tamanho = bytes.length;
                if (tamanho >= 4 && bytes[tamanho - 4] == '\r' && bytes[tamanho - 3] == '\n'
                        && bytes[tamanho - 2] == '\r' && bytes[tamanho - 1] == '\n') {
                    break;
                }
            }
            anterior = atual;
        }

        String textoCabecalho = cabecalho.toString(StandardCharsets.UTF_8);
        String[] linhas = textoCabecalho.split("\\r\\n");
        String[] primeiraLinha = linhas[0].split(" ");
        int tamanhoCorpo = 0;
        for (String linha : linhas) {
            if (linha.toLowerCase().startsWith("content-length:")) {
                tamanhoCorpo = Integer.parseInt(linha.substring(linha.indexOf(':') + 1).trim());
            }
        }

        byte[] corpo = entrada.readNBytes(tamanhoCorpo);
        return new Requisicao(primeiraLinha[0], primeiraLinha[1], new String(corpo, StandardCharsets.UTF_8));
    }

    private Resposta processar(Requisicao requisicao) {
        if ("GET".equals(requisicao.metodo()) && "/itens-cardapio".equals(requisicao.caminho())) {
            return new Resposta(200, gson.toJson(itens));
        }
        if ("GET".equals(requisicao.metodo()) && "/itens-cardapio/total".equals(requisicao.caminho())) {
            return new Resposta(200, gson.toJson(itens.size()));
        }
        if ("POST".equals(requisicao.metodo()) && "/itens-cardapio".equals(requisicao.caminho())) {
            try {
                ItemCardapio item = gson.fromJson(requisicao.corpo(), ItemCardapio.class);
                itens.add(item);
                return new Resposta(201, gson.toJson(item));
            } catch (JsonSyntaxException erro) {
                return new Resposta(400, "{\"erro\":\"JSON invalido\"}");
            }
        }
        return new Resposta(404, "{\"erro\":\"Endpoint nao encontrado\"}");
    }

    private void enviarResposta(OutputStream saida, Resposta resposta) throws IOException {
        byte[] corpo = resposta.corpo().getBytes(StandardCharsets.UTF_8);
        String cabecalho = "HTTP/1.1 " + resposta.status() + " " + textoStatus(resposta.status()) + "\r\n"
                + "Content-Type: application/json; charset=UTF-8\r\n"
                + "Content-Length: " + corpo.length + "\r\n"
                + "Connection: close\r\n\r\n";
        saida.write(cabecalho.getBytes(StandardCharsets.UTF_8));
        saida.write(corpo);
        saida.flush();
    }

    private String textoStatus(int status) {
        return status == 201 ? "Created" : status == 400 ? "Bad Request" : status == 404 ? "Not Found" : "OK";
    }

    private record Requisicao(String metodo, String caminho, String corpo) {
    }

    private record Resposta(int status, String corpo) {
    }
}