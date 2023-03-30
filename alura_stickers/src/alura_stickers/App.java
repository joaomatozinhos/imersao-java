package alura_stickers;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.text.Normalizer;
import java.util.List;

public class App {
	public static void main(String[] args) throws Exception {

		// API api = API.IMDB_TOP_MOVIES;
		// API api = API.IMDB_TOP_SERIES;
		API api = API.NASA;
		String url = api.getUrl();

		ExtratorConteudo extrator = api.getExtrator();

		ClienteHttp cliente = new ClienteHttp();
		String json = cliente.buscaDados(url);

		// criar diretorio onde vai salvar os arquivos
		File diretorio = new File("stickers/");
		diretorio.mkdir();

		// exibir e manipular os dados
		List<Conteudo> conteudos = extrator.extraiConteudo(json);

		GeradorDeStickers gerador = new GeradorDeStickers();

		for (Conteudo conteudo : conteudos) {

			InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();

			String nomeArquivo = Normalizer.normalize(conteudo.getTitulo(), Normalizer.Form.NFD)
					.replaceAll("[^\\p{ASCII}\\w\\s]", "").replaceAll("\\s+", "_").replaceAll(":", "");

			String textoImagem = "TOPZERA";

			gerador.cria(inputStream, nomeArquivo, textoImagem);
		}
	}
}
