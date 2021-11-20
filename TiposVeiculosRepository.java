package br.com.codiub.ordemservicos.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import br.com.codiub.ordemservicos.model.entity.TiposVeiculos;

@Repository
public interface TiposVeiculosRepository extends JpaRepository<TiposVeiculos, Long>, 
JpaSpecificationExecutor<TiposVeiculos> {
  
}
