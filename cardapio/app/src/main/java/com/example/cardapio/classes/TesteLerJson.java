package com.example.cardapio.classes;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Logger;

import static com.mysql.cj.conf.PropertyKey.logger;
import static java.lang.IO.println;

public class TesteLerJson {
    private static final Gson gson = new Gson();
    Logger logger = Logger.getLogger(TesteLerJson.class.getName());

    void main() throws IOException {
        Path path = Path.of("itensCardapio.json");
        String json = Files.readString(path, StandardCharsets.UTF_8);
        String arquivo = "";
        try {
            if (!Files.exists(path)) {
                logger.warning("Arquivo não encontrado: " + path);
                //return new ServidorCardapioSktHtml.Resposta(404, "{\"erro\":\"Arquivo não encontrado\"}");
            }

            logger.fine("Requisição GET para /itensCardapio.json");
            logger.info("Retornando conteúdo do arquivo JSON: " + json.length() + " caracteres");
            arquivo = Files.writeString(path, json).toString();
            //return new ServidorCardapioSktHtml.Resposta(200, gson.toJson(json));
        } catch (Exception e) {
            logger.severe("Erro ao processar requisição: " + e.getMessage());
            //eturn new ServidorCardapioSktHtml.Resposta(500, "{\"erro\":\"Erro interno do servidor\"}");
            throw new RuntimeException(e);
        }
        logger.info(() -> "Arquivo JSON criado com sucesso!" + path);
        logger.info(() -> "Conteúdo do arquivo JSON: " + json);
        String finalArquivo = arquivo;
        logger.info(() -> "Texto do writeString: " + finalArquivo);
        println(arquivo);
        println(gson.toJson(json));
    }
}
