package br.com.rodolfo.souzafoods.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.rodolfo.souzafoods.domain.model.Estado;
import br.com.rodolfo.souzafoods.domain.repository.EstadoRepository;

@Service
public class EstadoRepositoryImpl implements EstadoRepository{
	
	@PersistenceContext
	private EntityManager manager;
	
	public List<Estado> listar(){
		TypedQuery<Estado> query = manager.createQuery("from Estado", Estado.class);
		
		return query.getResultList();
		
	}
	
	@Override
	public Estado buscar (Long id) {
		return manager.find(Estado.class, id);
	}
	
	@Transactional
	@Override
	public Estado salvar(Estado estado) {
		return manager.merge(estado);
	}
	
	@Transactional
	@Override
	public void remover(Long id) {
		Estado estado = buscar(id);
		if(estado == null) {
			throw new EmptyResultDataAccessException(1);
			
		}
		manager.remove(estado);
	}

}
