package br.com.rodolfo.souzafoods.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Transactional;

import br.com.rodolfo.souzafoods.domain.model.Permissao;
import br.com.rodolfo.souzafoods.domain.repository.PermissaoRepository;

public class PermissaoRepositoryImpl implements PermissaoRepository{
	
	@PersistenceContext
	private EntityManager manager;
	
	public List<Permissao> listar(){
		TypedQuery<Permissao> query = manager.createQuery("from Permissao", Permissao.class);
		
		return query.getResultList();
		
	}
	
	@Override
	public Permissao buscar (Long id) {
		return manager.find(Permissao.class, id);
	}
	
	@Transactional
	@Override
	public Permissao salvar(Permissao permissao) {
		return manager.merge(permissao);
	}
	
	@Transactional
	@Override
	public void remover(Permissao permissao) {
		permissao = buscar(permissao.getId());
		manager.remove(permissao);
	}


}
