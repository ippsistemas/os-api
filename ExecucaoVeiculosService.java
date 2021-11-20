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


import br.com.codiub.ordemservicos.model.entity.ExecucaoVeiculos;
import br.com.codiub.ordemservicos.model.exception.BusinessException;
import br.com.codiub.ordemservicos.model.exception.EntityInUseException;
import br.com.codiub.ordemservicos.model.exception.EntityNotFoundException;
import br.com.codiub.ordemservicos.model.filter.ExecucaoVeiculosFilter;
import br.com.codiub.ordemservicos.model.input.ExecucaoVeiculosInput;
import br.com.codiub.ordemservicos.model.output.ExecucaoVeiculosOutput;
import br.com.codiub.ordemservicos.model.repository.ExecucaoVeiculosRepository;
import br.com.codiub.ordemservicos.model.repository.spec.ExecucaoVeiculosSpec;

@Service
public class ExecucaoVeiculosService {

    @Autowired
    private ExecucaoVeiculosRepository execucaoveiculosRepository;

    private ExecucaoVeiculosOutput execucaoveiculosOutput = new ExecucaoVeiculosOutput();

    private static final String MSG_EXECUCAO_VEICULOS_EM_USO = "ExecucaoVeiculos de ID: %d não pode ser 
removido,"
			+ " pois está em uso!";

	private static final String MSG_EXECUCAO_VEICULOS_INCONSISTENTE = "Não permitido alterar 
o id pois,"
			+ " pode causar inconsistência de dados! ID original: %d - ID 
destino: %d";

	private static final String MSG_EXECUCAO_VEICULOS_NOT_FOUND = "ExecucaoVeiculos de ID: %d 
não encontrada!";
	

    @Transactional
    public ExecucaoVeiculosOutput create(ExecucaoVeiculosInput execucaoveiculosInput) {
        return execucaoveiculosOutput.entityToOutput(execucaoveiculosRepository.save
(execucaoveiculosInput.inputToEntity()));
    }

    
    public Page<ExecucaoVeiculosOutput> readPage(ExecucaoVeiculosFilter filter, Pageable pageable) {
        Specification<ExecucaoVeiculos> execucaoveiculosSpec = ExecucaoVeiculosSpec.usingFilter(filter, 
pageable);
        Page<ExecucaoVeiculos> execucaoveiculosPage = execucaoveiculosRepository.findAll(execucaoveiculosSpec, 
pageable);
        List<ExecucaoVeiculosOutput> execucaoveiculosOutputList = 
execucaoveiculosOutput.entityListToOutputList(execucaoveiculosPage.getContent());
        return new PageImpl<>(execucaoveiculosOutputList, pageable, 
execucaoveiculosPage.getTotalElements());
    }

    public List<ExecucaoVeiculosOutput> readList(ExecucaoVeiculosFilter filter, Pageable pageable) {
        Specification<ExecucaoVeiculos> execucaoveiculosSpec = ExecucaoVeiculosSpec.usingFilter(filter, 
pageable);
        List<ExecucaoVeiculos> execucaoveiculosList = execucaoveiculosRepository.findAll(execucaoveiculosSpec);
        return execucaoveiculosOutput.entityListToOutputList(execucaoveiculosList);
    }

    public ExecucaoVeiculosOutput readById(ExecucaoVeiculosFilter filter) {
        if (filter.getId() == null) {
            throw new EntityNotFoundException(String.format(MSG_EXECUCAO_VEICULOS_NOT_FOUND, 
filter.getId()));
        }

        return execucaoveiculosOutput.entityToOutput(execucaoveiculosRepository.findById(filter.getId())
            .orElseThrow(() -> new EntityNotFoundException(
                String.format(MSG_EXECUCAO_VEICULOS_NOT_FOUND, filter.getId()))));
    }

    
    @Transactional
    public ExecucaoVeiculosOutput update(ExecucaoVeiculosFilter filter, ExecucaoVeiculosInput execucaoveiculosInput) 
{
        if (filter.getId() == null) {
            throw new BusinessException("ID inválido!");
        }

        if (!filter.getId().equals(execucaoveiculosInput.getId())) {
            throw new BusinessException(String.format(MSG_EXECUCAO_VEICULOS_INCONSISTENTE, 
filter.getId(), execucaoveiculosInput.getId()));
        }

        ExecucaoVeiculos execucaoveiculos = execucaoveiculosRepository.findById(filter.getId())
        .orElseThrow(() -> new EntityNotFoundException(
            String.format("ExecucaoVeiculos de ID %d não encontrada!", filter.getId())));

        execucaoveiculos = execucaoveiculosInput.mergeInputToEntity(execucaoveiculos);

        return execucaoveiculosOutput.entityToOutput(execucaoveiculos);
    }
    
    @Transactional
    public void delete(ExecucaoVeiculosFilter filter) {
        try {
            if (filter.getId() == null) {
                throw new BusinessException("ID inválido!");
            }
            execucaoveiculosRepository.deleteById(filter.getId());
            execucaoveiculosRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(
                String.format(String.format(MSG_EXECUCAO_VEICULOS_NOT_FOUND, filter.getId())));
        } catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(String.format
(MSG_EXECUCAO_VEICULOS_EM_USO, filter.getId()));
        }
    }

}
