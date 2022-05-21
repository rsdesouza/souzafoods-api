package br.com.rodolfo.souzafoods.domain.repository;

import java.util.List;

import br.com.rodolfo.souzafoods.domain.model.Cidade;

public interface CidadeRepository {
	
	List<Cidade> listar();
	Cidade buscar(Long id);
	Cidade salvar (Cidade cidade);
	void remover(Long id);

}
