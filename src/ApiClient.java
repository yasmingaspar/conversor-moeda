import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiClient{
    private final String API_KEY = "INSIRA_SUA_CHAVE_AQUI";

    private final String BASE_URL = "https://v6.exchangerate-api.com/v6/";

    public String buscaTaxa(String moedaBase, String moedaAlvo) {
        // Consulta por pares
        String url = BASE_URL + API_KEY + "/pair/" + moedaBase + "/" + moedaAlvo;

        try {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                return response.body();
            } else {
                System.err.println("Erro HTTP: Código de status " + response.statusCode());
                System.err.println("Corpo da resposta: " + response.body());
                return null;
            }

        } catch (IOException e) {
            System.err.println("Erro de IO (conexão): " + e.getMessage());
            return null;
        } catch (InterruptedException e) {
            System.err.println("A requisição foi interrompida: " + e.getMessage());

            Thread.currentThread().interrupt();
            return null;
        }
    }
}
