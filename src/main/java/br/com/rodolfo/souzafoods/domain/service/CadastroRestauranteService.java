package br.com.rodolfo.souzafoods.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rodolfo.souzafoods.domain.exception.EntidadeNaoEncontradaException;
import br.com.rodolfo.souzafoods.domain.model.Cozinha;
import br.com.rodolfo.souzafoods.domain.model.Restaurante;
import br.com.rodolfo.souzafoods.domain.repository.CozinhaRepository;
import br.com.rodolfo.souzafoods.domain.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	public Restaurante salvar(Restaurante restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId();
		Cozinha cozinha = cozinhaRepository.buscar(cozinhaId);
		
		if(cozinha == null) {
			throw new EntidadeNaoEncontradaException(
					String.format("Não existe cadstri de cozinha com o código %d", cozinhaId));
			
		}
		restaurante.setCozinha(cozinha);
		return restauranteRepository.salvar(restaurante);
	}

}
