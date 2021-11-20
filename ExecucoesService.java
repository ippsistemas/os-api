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


import br.com.codiub.ordemservicos.model.entity.Execucoes;
import br.com.codiub.ordemservicos.model.exception.BusinessException;
import br.com.codiub.ordemservicos.model.exception.EntityInUseException;
import br.com.codiub.ordemservicos.model.exception.EntityNotFoundException;
import br.com.codiub.ordemservicos.model.filter.ExecucoesFilter;
import br.com.codiub.ordemservicos.model.input.ExecucoesInput;
import br.com.codiub.ordemservicos.model.output.ExecucoesOutput;
import br.com.codiub.ordemservicos.model.repository.ExecucoesRepository;
import br.com.codiub.ordemservicos.model.repository.spec.ExecucoesSpec;

@Service
public class ExecucoesService {

    @Autowired
    private ExecucoesRepository execucoesRepository;

    private ExecucoesOutput execucoesOutput = new ExecucoesOutput();

    private static final String MSG_EXECUCOES_EM_USO = "Execucoes de ID: %d não pode ser 
removido,"
			+ " pois está em uso!";

	private static final String MSG_EXECUCOES_INCONSISTENTE = "Não permitido alterar 
o id pois,"
			+ " pode causar inconsistência de dados! ID original: %d - ID 
destino: %d";

	private static final String MSG_EXECUCOES_NOT_FOUND = "Execucoes de ID: %d 
não encontrada!";
	

    @Transactional
    public ExecucoesOutput create(ExecucoesInput execucoesInput) {
        return execucoesOutput.entityToOutput(execucoesRepository.save
(execucoesInput.inputToEntity()));
    }

    
    public Page<ExecucoesOutput> readPage(ExecucoesFilter filter, Pageable pageable) {
        Specification<Execucoes> execucoesSpec = ExecucoesSpec.usingFilter(filter, 
pageable);
        Page<Execucoes> execucoesPage = execucoesRepository.findAll(execucoesSpec, 
pageable);
        List<ExecucoesOutput> execucoesOutputList = 
execucoesOutput.entityListToOutputList(execucoesPage.getContent());
        return new PageImpl<>(execucoesOutputList, pageable, 
execucoesPage.getTotalElements());
    }

    public List<ExecucoesOutput> readList(ExecucoesFilter filter, Pageable pageable) {
        Specification<Execucoes> execucoesSpec = ExecucoesSpec.usingFilter(filter, 
pageable);
        List<Execucoes> execucoesList = execucoesRepository.findAll(execucoesSpec);
        return execucoesOutput.entityListToOutputList(execucoesList);
    }

    public ExecucoesOutput readById(ExecucoesFilter filter) {
        if (filter.getId() == null) {
            throw new EntityNotFoundException(String.format(MSG_EXECUCOES_NOT_FOUND, 
filter.getId()));
        }

        return execucoesOutput.entityToOutput(execucoesRepository.findById(filter.getId())
            .orElseThrow(() -> new EntityNotFoundException(
                String.format(MSG_EXECUCOES_NOT_FOUND, filter.getId()))));
    }

    
    @Transactional
    public ExecucoesOutput update(ExecucoesFilter filter, ExecucoesInput execucoesInput) 
{
        if (filter.getId() == null) {
            throw new BusinessException("ID inválido!");
        }

        if (!filter.getId().equals(execucoesInput.getId())) {
            throw new BusinessException(String.format(MSG_EXECUCOES_INCONSISTENTE, 
filter.getId(), execucoesInput.getId()));
        }

        Execucoes execucoes = execucoesRepository.findById(filter.getId())
        .orElseThrow(() -> new EntityNotFoundException(
            String.format("Execucoes de ID %d não encontrada!", filter.getId())));

        execucoes = execucoesInput.mergeInputToEntity(execucoes);

        return execucoesOutput.entityToOutput(execucoes);
    }
    
    @Transactional
    public void delete(ExecucoesFilter filter) {
        try {
            if (filter.getId() == null) {
                throw new BusinessException("ID inválido!");
            }
            execucoesRepository.deleteById(filter.getId());
            execucoesRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(
                String.format(String.format(MSG_EXECUCOES_NOT_FOUND, filter.getId())));
        } catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(String.format
(MSG_EXECUCOES_EM_USO, filter.getId()));
        }
    }

}
