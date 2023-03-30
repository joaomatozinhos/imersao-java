package alura_stickers;

import java.util.List;

public interface ExtratorConteudo {

	List<Conteudo> extraiConteudo(String json);
}
