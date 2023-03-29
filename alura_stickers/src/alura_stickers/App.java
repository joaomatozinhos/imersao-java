package alura_stickers;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.text.Normalizer;
import java.util.List;
import java.util.Map;

public class App {
	public static void main(String[] args) throws Exception {

		// fazer uma conexão HTTP e buscar os dados da API
		String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder(URI.create(url)).GET().build();
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		String json = response.body();

		// extrair os dados (titulo, poster, classificação)
		JsonParser parser = new JsonParser();
		List<Map<String, String>> listaDeFilmes = parser.parse(json);

		// criar diretorio onde vai salvar os arquivos
		File diretorio = new File("stickers/");
		diretorio.mkdir();

		// exibir e manipular os dados
		GeradorDeStickers gerador = new GeradorDeStickers();
		for (Map<String, String> filme : listaDeFilmes) {
			System.out.println(filme.get("title"));
			System.out.println(filme.get("image"));
			System.out.println(filme.get("imDbRating"));
			System.out.println();

			String imagem = filme.get("image");
			InputStream inputStream = new URL(imagem).openStream();

			String titulo = filme.get("title");
			String nomeImagem = Normalizer.normalize(titulo, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}\\w\\s]", "")
					.replaceAll("\\s+", "_").replaceAll(":", "");
			System.out.println(nomeImagem);

			double nota = Double.parseDouble(filme.get("imDbRating"));

			String textoImagem = "";
			if (nota >= 9) {
				textoImagem = "TOPZERA";
			} else {
				textoImagem = "legal em fera";
			}

			gerador.cria(inputStream, nomeImagem, textoImagem);
		}
	}
}
