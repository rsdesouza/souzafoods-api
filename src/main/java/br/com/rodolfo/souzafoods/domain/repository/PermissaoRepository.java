package br.com.rodolfo.souzafoods.domain.repository;

import java.util.List;

import br.com.rodolfo.souzafoods.domain.model.Permissao;

public interface PermissaoRepository {
	
	List<Permissao> listar();
	Permissao buscar(Long id);
	Permissao salvar (Permissao permissao);
	void remover(Permissao permissao);

}
