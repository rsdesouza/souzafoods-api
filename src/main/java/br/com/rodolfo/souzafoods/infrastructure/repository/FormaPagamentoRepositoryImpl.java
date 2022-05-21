package br.com.rodolfo.souzafoods.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Transactional;

import br.com.rodolfo.souzafoods.domain.model.FormaPagamento;
import br.com.rodolfo.souzafoods.domain.repository.FormaPagamentoRepository;

public class FormaPagamentoRepositoryImpl implements FormaPagamentoRepository{
	
	@PersistenceContext
	private EntityManager manager;
	
	public List<FormaPagamento> listar(){
		TypedQuery<FormaPagamento> query = manager.createQuery("from FormaPagamento", FormaPagamento.class);
		
		return query.getResultList();
		
	}
	
	@Override
	public FormaPagamento buscar (Long id) {
		return manager.find(FormaPagamento.class, id);
	}
	
	@Transactional
	@Override
	public FormaPagamento salvar(FormaPagamento formaPagamento) {
		return manager.merge(formaPagamento);
	}
	
	@Transactional
	@Override
	public void remover(FormaPagamento formaPagamento) {
		formaPagamento = buscar(formaPagamento.getId());
		manager.remove(formaPagamento);
	}


}
