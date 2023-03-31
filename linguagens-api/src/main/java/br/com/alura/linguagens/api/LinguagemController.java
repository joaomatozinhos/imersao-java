package br.com.alura.linguagens.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class LinguagemController {

	@Autowired
	private LinguagemRepository repositorio;

	@GetMapping("/linguagens")
	public List<Linguagem> obterLinguagens() {
		List<Linguagem> linguagens = repositorio.findByOrderByRanking();
		return linguagens;
	}

	@GetMapping("/linguagens/{id}")
	public Linguagem obterLinguagensPorRanking(@PathVariable String id) {
		Linguagem linguagem = repositorio.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		return linguagem;
	}

	@PostMapping("/linguagens")
	public ResponseEntity<Linguagem> cadastrarLinguagem(@RequestBody Linguagem linguagem) {
		Linguagem linguagemSalva = repositorio.save(linguagem);
		return new ResponseEntity<>(linguagemSalva, HttpStatus.CREATED);
	}

	@PutMapping("/linguagens/{id}")
	public Linguagem editarLinguagem(@PathVariable String id, @RequestBody Linguagem linguagem) {
		if (!repositorio.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		linguagem.setId(id);
		Linguagem linguagemSalva = repositorio.save(linguagem);
		return linguagemSalva;
	}

	@DeleteMapping("/linguagens/{id}")
	public void excluirLinguagem(@PathVariable String id) {
		repositorio.deleteById(id);
	}
}
