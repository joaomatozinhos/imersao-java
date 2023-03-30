package alura_stickers;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class GeradorDeStickers {

	public void cria(InputStream inputStream, String nomeImagem, String textoImagem) throws Exception {

		// leitura da imagem
		// InputStream inputStream = new FileInputStream(new File("entrada/filme.jpg"));
		// InputStream inputStream = new URL(
		// "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies_2.jpg").openStream();
		BufferedImage imagemOriginal = ImageIO.read(inputStream);

		// criar nova imagem em memória com transparência e com tamanho novo
		int largura = imagemOriginal.getWidth();
		int altura = imagemOriginal.getHeight();
		int novaAltura = altura + 200; // em pixel
		BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

		// copiar imagem original pra nova imagem (em memória)
		Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
		graphics.drawImage(imagemOriginal, 0, 0, null);

		// configurar a fonte
		Font fonte = new Font("Impact", Font.BOLD, 100);
		graphics.setColor(Color.YELLOW);
		graphics.setFont(fonte);

		// definir e centralizar texto no eixo X
		String texto = textoImagem;
		FontMetrics fontMetrics = graphics.getFontMetrics();
		Rectangle2D retangulo = fontMetrics.getStringBounds(texto, graphics);
		int larguraTexto = (int) retangulo.getWidth();
		int posicaoXTexto = (largura - larguraTexto) / 2;
		int posicaoYTexto = novaAltura - 100;

		// inserir texto na nova imagem
		graphics.drawString(texto, posicaoXTexto, posicaoYTexto);

		// criar contorno (outline) no texto
		FontRenderContext fontRenderContext = graphics.getFontRenderContext();
		TextLayout textLayout = new TextLayout(texto, fonte, fontRenderContext);

		Shape outline = textLayout.getOutline(null);
		AffineTransform transform = graphics.getTransform();
		transform.translate(posicaoXTexto, posicaoYTexto);
		graphics.setTransform(transform);

		BasicStroke outlineStroke = new BasicStroke(largura * 0.004f);
		graphics.setStroke(outlineStroke);

		graphics.setColor(Color.BLACK);
		graphics.draw(outline);
		graphics.setClip(outline);

		// escrever a nova imagem em um arquivo
		ImageIO.write(novaImagem, "png", new File("stickers/" + nomeImagem + ".png"));
	}
}
