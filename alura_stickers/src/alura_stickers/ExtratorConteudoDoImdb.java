package alura_stickers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtratorConteudoDoImdb implements ExtratorConteudo {

	public List<Conteudo> extraiConteudo(String json) {

		// extrair os dados (titulo, imagem, classificação)
		JsonParser parser = new JsonParser();
		List<Map<String, String>> listaDeAtributos = parser.parse(json);

		List<Conteudo> conteudos = new ArrayList<>();

		// popular lista de conteudos
		listaDeAtributos.forEach(atributos -> {
			String titulo = atributos.get("title");
			String urlImagem = atributos.get("image");

			Conteudo conteudo = new Conteudo(titulo, urlImagem);
			conteudos.add(conteudo);

		});
		return conteudos;
	}
}