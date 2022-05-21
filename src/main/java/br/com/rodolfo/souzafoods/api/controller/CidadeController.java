package br.com.rodolfo.souzafoods.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.rodolfo.souzafoods.domain.exception.EntidadeEmUsoException;
import br.com.rodolfo.souzafoods.domain.exception.EntidadeNaoEncontradaException;
import br.com.rodolfo.souzafoods.domain.model.Cidade;
import br.com.rodolfo.souzafoods.domain.repository.CidadeRepository;
import br.com.rodolfo.souzafoods.domain.service.CadastroCidadeService;

@RestController
@RequestMapping("/cidades")
public class CidadeController {
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private CadastroCidadeService service;
	
	@GetMapping
	public List<Cidade> listar(){
		return cidadeRepository.listar();
	}
	@GetMapping("/{id}")
	public ResponseEntity<Cidade> buscar(@PathVariable Long id) {
		Cidade cidade = cidadeRepository.buscar(id);

		if (cidade != null) {
			return ResponseEntity.ok(cidade);
		}

		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cidade adicionar(@RequestBody Cidade cidade) {
		return service.salvar(cidade);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Cidade> atualizar(@PathVariable Long id, @RequestBody Cidade cidade) {
		Cidade cidadeAtual = cidadeRepository.buscar(id);

		if (cidadeAtual != null) {

			BeanUtils.copyProperties(cidade, cidadeAtual, "id");
			service.salvar(cidade);
			return ResponseEntity.ok(cidadeAtual);
		}

		return ResponseEntity.notFound().build();

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Cidade> remover(@PathVariable Long id) {

		try {
			service.excluir(id);
			return ResponseEntity.noContent().build();

		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();

		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}

	}

}
