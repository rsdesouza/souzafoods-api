package br.com.rodolfo.souzafoods.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.rodolfo.souzafoods.domain.exception.EntidadeNaoEncontradaException;
import br.com.rodolfo.souzafoods.domain.model.Restaurante;
import br.com.rodolfo.souzafoods.domain.repository.RestauranteRepository;
import br.com.rodolfo.souzafoods.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CadastroRestauranteService service;

	@GetMapping
	public List<Restaurante> listar() {
		return restauranteRepository.listar();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Restaurante> buscar(@PathVariable Long id) {
		Restaurante restaurante = restauranteRepository.buscar(id);

		if (restaurante != null) {
			return ResponseEntity.ok(restaurante);
		}

		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Restaurante> atualizar(@PathVariable Long id, 
			@RequestBody Restaurante restaurante) {
		try {
			Restaurante restauranteAtual = restauranteRepository.buscar(id);
			if (restauranteAtual != null) {
				BeanUtils.copyProperties(restaurante, restauranteAtual, "id");
				service.salvar(restaurante);
				return ResponseEntity.ok(restauranteAtual);
			}
			return ResponseEntity.notFound().build();
			
		}catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante) {
		try {
			restaurante =  service.salvar(restaurante);
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(restaurante);
		}catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
			
		}
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<?> atualizaParcial(@PathVariable Long id, 
			@RequestBody Map<String, Object> campos){
		Restaurante restauranteAtual = restauranteRepository.buscar(id);
		
		if (restauranteAtual==null) {
			return ResponseEntity.notFound().build();
		}
		
		merge(campos, restauranteAtual);
		
		return atualizar(id, restauranteAtual);
	}

	private void merge(Map<String, Object> camposOrigem, Restaurante restauranteDestino  ) {
		ObjectMapper objectMapper = new ObjectMapper();
		Restaurante restauranteOrigem = objectMapper.convertValue(camposOrigem, Restaurante.class);
		
		camposOrigem.forEach((nomePropriedade, valorPropriedade) -> {
			Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
			field.setAccessible(true);
			
			Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);
			
			//System.out.println(nomePropriedade + " = " + novoValor);
			
			ReflectionUtils.setField(field, restauranteDestino, novoValor);
		});
	}

}
