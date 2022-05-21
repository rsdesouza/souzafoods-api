package br.com.rodolfo.souzafoods.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.rodolfo.souzafoods.domain.exception.EntidadeEmUsoException;
import br.com.rodolfo.souzafoods.domain.exception.EntidadeNaoEncontradaException;
import br.com.rodolfo.souzafoods.domain.model.Estado;
import br.com.rodolfo.souzafoods.domain.repository.EstadoRepository;

@Service
public class CadastroEstadoService {
	
	@Autowired
	private EstadoRepository estadoRepository;

	public Estado salvar(Estado estado) {
		return estadoRepository.salvar(estado);
	}
	
	public void excluir(Long id) {
		try {
			estadoRepository.remover(id);
		} catch (EmptyResultDataAccessException e){
			throw new EntidadeNaoEncontradaException (
					String.format("Não existe um cadastro de estado com código %d", id));
			
		} catch (DataIntegrityViolationException e){
			throw new EntidadeEmUsoException (
					String.format("Estado de código %d não pode ser removida, pois está em uso", id));
		}
		
	}


}
