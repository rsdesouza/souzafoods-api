package br.com.rodolfo.souzafoods.domain.repository;

import java.util.List;

import br.com.rodolfo.souzafoods.domain.model.Restaurante;


public interface RestauranteRepository {

	List<Restaurante> listar();
	Restaurante buscar(Long id);
	Restaurante salvar (Restaurante restaurante);
	void remover(Restaurante restaurante);
	
}
