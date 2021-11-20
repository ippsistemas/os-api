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


import br.com.codiub.ordemservicos.model.entity.Situacoes;
import br.com.codiub.ordemservicos.model.exception.BusinessException;
import br.com.codiub.ordemservicos.model.exception.EntityInUseException;
import br.com.codiub.ordemservicos.model.exception.EntityNotFoundException;
import br.com.codiub.ordemservicos.model.filter.SituacoesFilter;
import br.com.codiub.ordemservicos.model.input.SituacoesInput;
import br.com.codiub.ordemservicos.model.output.SituacoesOutput;
import br.com.codiub.ordemservicos.model.repository.SituacoesRepository;
import br.com.codiub.ordemservicos.model.repository.spec.SituacoesSpec;

@Service
public class SituacoesService {

    @Autowired
    private SituacoesRepository situacoesRepository;

    private SituacoesOutput situacoesOutput = new SituacoesOutput();

    private static final String MSG_SITUACOES_EM_USO = "Situacoes de ID: %d não pode ser 
removido,"
			+ " pois está em uso!";

	private static final String MSG_SITUACOES_INCONSISTENTE = "Não permitido alterar 
o id pois,"
			+ " pode causar inconsistência de dados! ID original: %d - ID 
destino: %d";

	private static final String MSG_SITUACOES_NOT_FOUND = "Situacoes de ID: %d 
não encontrada!";
	

    @Transactional
    public SituacoesOutput create(SituacoesInput situacoesInput) {
        return situacoesOutput.entityToOutput(situacoesRepository.save
(situacoesInput.inputToEntity()));
    }

    
    public Page<SituacoesOutput> readPage(SituacoesFilter filter, Pageable pageable) {
        Specification<Situacoes> situacoesSpec = SituacoesSpec.usingFilter(filter, 
pageable);
        Page<Situacoes> situacoesPage = situacoesRepository.findAll(situacoesSpec, 
pageable);
        List<SituacoesOutput> situacoesOutputList = 
situacoesOutput.entityListToOutputList(situacoesPage.getContent());
        return new PageImpl<>(situacoesOutputList, pageable, 
situacoesPage.getTotalElements());
    }

    public List<SituacoesOutput> readList(SituacoesFilter filter, Pageable pageable) {
        Specification<Situacoes> situacoesSpec = SituacoesSpec.usingFilter(filter, 
pageable);
        List<Situacoes> situacoesList = situacoesRepository.findAll(situacoesSpec);
        return situacoesOutput.entityListToOutputList(situacoesList);
    }

    public SituacoesOutput readById(SituacoesFilter filter) {
        if (filter.getId() == null) {
            throw new EntityNotFoundException(String.format(MSG_SITUACOES_NOT_FOUND, 
filter.getId()));
        }

        return situacoesOutput.entityToOutput(situacoesRepository.findById(filter.getId())
            .orElseThrow(() -> new EntityNotFoundException(
                String.format(MSG_SITUACOES_NOT_FOUND, filter.getId()))));
    }

    
    @Transactional
    public SituacoesOutput update(SituacoesFilter filter, SituacoesInput situacoesInput) 
{
        if (filter.getId() == null) {
            throw new BusinessException("ID inválido!");
        }

        if (!filter.getId().equals(situacoesInput.getId())) {
            throw new BusinessException(String.format(MSG_SITUACOES_INCONSISTENTE, 
filter.getId(), situacoesInput.getId()));
        }

        Situacoes situacoes = situacoesRepository.findById(filter.getId())
        .orElseThrow(() -> new EntityNotFoundException(
            String.format("Situacoes de ID %d não encontrada!", filter.getId())));

        situacoes = situacoesInput.mergeInputToEntity(situacoes);

        return situacoesOutput.entityToOutput(situacoes);
    }
    
    @Transactional
    public void delete(SituacoesFilter filter) {
        try {
            if (filter.getId() == null) {
                throw new BusinessException("ID inválido!");
            }
            situacoesRepository.deleteById(filter.getId());
            situacoesRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(
                String.format(String.format(MSG_SITUACOES_NOT_FOUND, filter.getId())));
        } catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(String.format
(MSG_SITUACOES_EM_USO, filter.getId()));
        }
    }

}
