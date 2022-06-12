package br.com.rodolfo.souzafoods.api.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.rodolfo.souzafoods.domain.model.Cozinha;
import br.com.rodolfo.souzafoods.domain.model.Restaurante;
import br.com.rodolfo.souzafoods.domain.repository.CozinhaRepository;
import br.com.rodolfo.souzafoods.domain.repository.RestauranteRepository;

@RestController
@RequestMapping("/teste")
public class TesteController {
	
	@Autowired
	CozinhaRepository cozinhaRepository;
	
	@Autowired
	RestauranteRepository restauranteRepository;
	
	@GetMapping("/cozinhas/por-nome")
	public List <Cozinha> cozinhasPorNome(String nome){
		return cozinhaRepository.findTodasByNome(nome);
	}
	
	@GetMapping("/cozinhas/unica-por-nome")
	public Optional <Cozinha> cozinhaPorNome(String nome){
		return cozinhaRepository.findByNome(nome);
	}
	
	@GetMapping("/restaurantes/por-taxa-frete")
	public List <Restaurante> restaurantesPorTaxaFrete(BigDecimal taxaInicial, 
			BigDecimal taxaFinal){
		return restauranteRepository.queryByTaxaFreteBetween(taxaInicial, taxaFinal);
	}
	
	@GetMapping("/restaurantes/por-nome")
	public List <Restaurante> restaurantesPorNome(String nome, Long cozinhaId){
		return restauranteRepository.consultaPorNome(nome, cozinhaId);
	}
	
	@GetMapping("/restaurantes/por-primeiro-nome")
	public Optional<Restaurante> restaurantesPorPrimeiroNome(String nome){
		return restauranteRepository.findFirstRestauranteByNomeContaining(nome);
	}
}
