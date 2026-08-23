package org.example.mains;

import static java.lang.IO.println;

import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class ClienteViaCEP {
    void main() throws Exception {
        URL url = new URL("https://viacep.com.br/ws/01001020/json/");
        url.openConnection().getInputStream().transferTo(System.out);
        println(url);

        // construir com scanner utilizando o mesmo exemplo acima.
        URL url2 = new URL("https://viacep.com.br/ws/01001000/json/");

        try (Scanner scanner = new Scanner(url2.openStream())) {

            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                println(line);
            }

        }

        URI uri = new URI("https://viacep.com.br/ws/01001000/json/");
        try(HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder(uri).build();
            HttpResponse<String> response = client
            .send(request, HttpResponse.BodyHandlers.ofString());
            println(response.statusCode());
            println(response.body());
        }
    }
}