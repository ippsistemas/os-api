package br.com.codiub.ordemservicos.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import br.com.codiub.ordemservicos.model.entity.Solicitantes;

@Repository
public interface SolicitantesRepository extends JpaRepository<Solicitantes, Long>, 
JpaSpecificationExecutor<Solicitantes> {
  
}
