package br.com.codiub.ordemservicos.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import br.com.codiub.ordemservicos.model.entity.ExecucaoVeiculos;

@Repository
public interface ExecucaoVeiculosRepository extends JpaRepository<ExecucaoVeiculos, Long>, 
JpaSpecificationExecutor<ExecucaoVeiculos> {
  
}
