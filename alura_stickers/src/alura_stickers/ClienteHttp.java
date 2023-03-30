package alura_stickers;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class ClienteHttp {

	public String buscaDados(String url) {

		// fazer uma conexão HTTP e buscar os dados da API
		try {
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder(URI.create(url)).GET().build();
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			String json = response.body();
			return json;

		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}

	}
}
