package br.com.rodolfo.souzafoods.api.controller;

import java.util.List;
import java.util.Optional;

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
import br.com.rodolfo.souzafoods.domain.model.Estado;
import br.com.rodolfo.souzafoods.domain.repository.EstadoRepository;
import br.com.rodolfo.souzafoods.domain.service.CadastroEstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoController {
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CadastroEstadoService service;
	
	@GetMapping
	public List<Estado> listar(){
		return estadoRepository.findAll();
	}
	@GetMapping("/{id}")
	public ResponseEntity<Estado> buscar(@PathVariable Long id) {
		Optional<Estado> estado = estadoRepository.findById(id);

		if (estado.isPresent()) {
			return ResponseEntity.ok(estado.get());
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Estado adicionar(@RequestBody Estado estado) {
		return service.salvar(estado);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Estado> atualizar(@PathVariable Long id, @RequestBody Estado estado) {
		Optional<Estado> estadoAtual = estadoRepository.findById(id);

		if (estadoAtual.isPresent()) {

			BeanUtils.copyProperties(estado, estadoAtual.get(), "id");
			
			Estado estadoSalvo = service.salvar(estado);
			return ResponseEntity.ok(estadoSalvo);
		}

		return ResponseEntity.notFound().build();

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Estado> remover(@PathVariable Long id) {

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
