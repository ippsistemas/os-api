package br.com.codiub.ordemservicos.controller.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import br.com.codiub.ordemservicos.model.entity.TiposVeiculos;
import br.com.codiub.ordemservicos.model.exception.BusinessException;
import br.com.codiub.ordemservicos.model.exception.EntityInUseException;
import br.com.codiub.ordemservicos.model.exception.EntityNotFoundException;
import br.com.codiub.ordemservicos.model.filter.TiposVeiculosFilter;
import br.com.codiub.ordemservicos.model.input.TiposVeiculosInput;
import br.com.codiub.ordemservicos.model.output.TiposVeiculosOutput;
import br.com.codiub.ordemservicos.model.repository.TiposVeiculosRepository;
import br.com.codiub.ordemservicos.model.repository.spec.TiposVeiculosSpec;

@Service
public class TiposVeiculosService {

    @Autowired
    private TiposVeiculosRepository tiposveiculosRepository;

    private TiposVeiculosOutput tiposveiculosOutput = new TiposVeiculosOutput();

    private static final String MSG_TIPOS_VEICULOS_EM_USO = "TiposVeiculos de ID: %d não pode ser 
removido,"
			+ " pois está em uso!";

	private static final String MSG_TIPOS_VEICULOS_INCONSISTENTE = "Não permitido alterar 
o id pois,"
			+ " pode causar inconsistência de dados! ID original: %d - ID 
destino: %d";

	private static final String MSG_TIPOS_VEICULOS_NOT_FOUND = "TiposVeiculos de ID: %d 
não encontrada!";
	

    @Transactional
    public TiposVeiculosOutput create(TiposVeiculosInput tiposveiculosInput) {
        return tiposveiculosOutput.entityToOutput(tiposveiculosRepository.save
(tiposveiculosInput.inputToEntity()));
    }

    
    public Page<TiposVeiculosOutput> readPage(TiposVeiculosFilter filter, Pageable pageable) {
        Specification<TiposVeiculos> tiposveiculosSpec = TiposVeiculosSpec.usingFilter(filter, 
pageable);
        Page<TiposVeiculos> tiposveiculosPage = tiposveiculosRepository.findAll(tiposveiculosSpec, 
pageable);
        List<TiposVeiculosOutput> tiposveiculosOutputList = 
tiposveiculosOutput.entityListToOutputList(tiposveiculosPage.getContent());
        return new PageImpl<>(tiposveiculosOutputList, pageable, 
tiposveiculosPage.getTotalElements());
    }

    public List<TiposVeiculosOutput> readList(TiposVeiculosFilter filter, Pageable pageable) {
        Specification<TiposVeiculos> tiposveiculosSpec = TiposVeiculosSpec.usingFilter(filter, 
pageable);
        List<TiposVeiculos> tiposveiculosList = tiposveiculosRepository.findAll(tiposveiculosSpec);
        return tiposveiculosOutput.entityListToOutputList(tiposveiculosList);
    }

    public TiposVeiculosOutput readById(TiposVeiculosFilter filter) {
        if (filter.getId() == null) {
            throw new EntityNotFoundException(String.format(MSG_TIPOS_VEICULOS_NOT_FOUND, 
filter.getId()));
        }

        return tiposveiculosOutput.entityToOutput(tiposveiculosRepository.findById(filter.getId())
            .orElseThrow(() -> new EntityNotFoundException(
                String.format(MSG_TIPOS_VEICULOS_NOT_FOUND, filter.getId()))));
    }

    
    @Transactional
    public TiposVeiculosOutput update(TiposVeiculosFilter filter, TiposVeiculosInput tiposveiculosInput) 
{
        if (filter.getId() == null) {
            throw new BusinessException("ID inválido!");
        }

        if (!filter.getId().equals(tiposveiculosInput.getId())) {
            throw new BusinessException(String.format(MSG_TIPOS_VEICULOS_INCONSISTENTE, 
filter.getId(), tiposveiculosInput.getId()));
        }

        TiposVeiculos tiposveiculos = tiposveiculosRepository.findById(filter.getId())
        .orElseThrow(() -> new EntityNotFoundException(
            String.format("TiposVeiculos de ID %d não encontrada!", filter.getId())));

        tiposveiculos = tiposveiculosInput.mergeInputToEntity(tiposveiculos);

        return tiposveiculosOutput.entityToOutput(tiposveiculos);
    }
    
    @Transactional
    public void delete(TiposVeiculosFilter filter) {
        try {
            if (filter.getId() == null) {
                throw new BusinessException("ID inválido!");
            }
            tiposveiculosRepository.deleteById(filter.getId());
            tiposveiculosRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(
                String.format(String.format(MSG_TIPOS_VEICULOS_NOT_FOUND, filter.getId())));
        } catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(String.format
(MSG_TIPOS_VEICULOS_EM_USO, filter.getId()));
        }
    }

}
