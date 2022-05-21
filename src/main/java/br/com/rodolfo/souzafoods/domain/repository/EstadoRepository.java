package br.com.rodolfo.souzafoods.domain.repository;

import java.util.List;

import br.com.rodolfo.souzafoods.domain.model.Estado;

public interface EstadoRepository {
	
	List<Estado> listar();
	Estado buscar(Long id);
	Estado salvar (Estado estado);
	void remover(Long id);

}
