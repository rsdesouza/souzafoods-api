package br.com.rodolfo.souzafoods.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.rodolfo.souzafoods.domain.exception.EntidadeEmUsoException;
import br.com.rodolfo.souzafoods.domain.exception.EntidadeNaoEncontradaException;
import br.com.rodolfo.souzafoods.domain.model.Cidade;
import br.com.rodolfo.souzafoods.domain.repository.CidadeRepository;

@Service
public class CadastroCidadeService {
	
	@Autowired
	private CidadeRepository cidadeRepository;

	public Cidade salvar(Cidade cidade) {
		return cidadeRepository.salvar(cidade);
	}
	
	public void excluir(Long id) {
		try {
			cidadeRepository.remover(id);
		} catch (EmptyResultDataAccessException e){
			throw new EntidadeNaoEncontradaException (
					String.format("Não existe um cadastro de cidade com código %d", id));
			
		} catch (DataIntegrityViolationException e){
			throw new EntidadeEmUsoException (
					String.format("Cidade de código %d não pode ser removida, pois está em uso", id));
		}
		
	}


}
