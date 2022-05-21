package br.com.rodolfo.souzafoods.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.rodolfo.souzafoods.domain.model.Restaurante;
import br.com.rodolfo.souzafoods.domain.repository.RestauranteRepository;

@Service
public class RestauranteRepositoryImpl implements RestauranteRepository{
	
	@PersistenceContext
	private EntityManager manager;
	
	public List<Restaurante> listar(){
		TypedQuery<Restaurante> query = manager.createQuery("from Restaurante", Restaurante.class);
		
		return query.getResultList();
		
	}
	
	@Override
	public Restaurante buscar (Long id) {
		return manager.find(Restaurante.class, id);
	}
	
	@Transactional
	@Override
	public Restaurante salvar(Restaurante restaurante) {
		return manager.merge(restaurante);
	}
	
	@Transactional
	@Override
	public void remover(Restaurante restaurante) {
		restaurante = buscar(restaurante.getId());
		manager.remove(restaurante);
	}

}
