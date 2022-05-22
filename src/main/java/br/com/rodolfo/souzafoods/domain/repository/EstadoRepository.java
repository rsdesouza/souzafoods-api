package br.com.rodolfo.souzafoods.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.rodolfo.souzafoods.domain.model.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long>{

}
