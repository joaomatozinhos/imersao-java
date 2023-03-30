package alura_stickers;

public enum API {
	IMDB_TOP_MOVIES("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json",
			new ExtratorConteudoDoImdb()),
	IMDB_TOP_SERIES("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopTVs.json",
			new ExtratorConteudoDoImdb()),
	NASA("https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2023-03-10&end_date=2023-03-15",
			new ExtratorConteudoDaNasa());

	private String url;
	private ExtratorConteudo extrator;

	API(String url, ExtratorConteudo extrator) {
		this.url = url;
		this.extrator = extrator;
	}

	public String getUrl() {
		return url;
	}

	public ExtratorConteudo getExtrator() {
		return extrator;
	}

}
