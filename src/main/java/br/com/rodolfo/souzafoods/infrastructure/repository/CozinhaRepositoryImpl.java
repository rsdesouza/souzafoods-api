package br.com.rodolfo.souzafoods.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.rodolfo.souzafoods.domain.model.Cozinha;
import br.com.rodolfo.souzafoods.domain.repository.CozinhaRepository;

@Service
public class CozinhaRepositoryImpl implements CozinhaRepository{

	@PersistenceContext
	private EntityManager manager;
	
	public List<Cozinha> listar(){
		TypedQuery<Cozinha> query = manager.createQuery("from Cozinha", Cozinha.class);
		
		return query.getResultList();
		
	}
	
	@Override
	public Cozinha buscar (Long id) {
		return manager.find(Cozinha.class, id);
	}
	
	@Transactional
	@Override
	public Cozinha salvar(Cozinha cozinha) {
		return manager.merge(cozinha);
	}
	
	@Transactional
	@Override
	public void remover(Long id) {
		Cozinha cozinha = buscar(id);
		if(cozinha == null) {
			throw new EmptyResultDataAccessException(1);
			
		}
		manager.remove(cozinha);
	}

}
