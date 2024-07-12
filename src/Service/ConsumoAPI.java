package Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoAPI {
    public String ObtenerDatos(String url) throws IOException, InterruptedException {
        HttpClient client= HttpClient.newHttpClient();
        HttpRequest request= HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response= client.send(request, HttpResponse.BodyHandlers.ofString());

        String json= response.body();

        return json;
    }
}
