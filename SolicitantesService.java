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


import br.com.codiub.ordemservicos.model.entity.Solicitantes;
import br.com.codiub.ordemservicos.model.exception.BusinessException;
import br.com.codiub.ordemservicos.model.exception.EntityInUseException;
import br.com.codiub.ordemservicos.model.exception.EntityNotFoundException;
import br.com.codiub.ordemservicos.model.filter.SolicitantesFilter;
import br.com.codiub.ordemservicos.model.input.SolicitantesInput;
import br.com.codiub.ordemservicos.model.output.SolicitantesOutput;
import br.com.codiub.ordemservicos.model.repository.SolicitantesRepository;
import br.com.codiub.ordemservicos.model.repository.spec.SolicitantesSpec;

@Service
public class SolicitantesService {

    @Autowired
    private SolicitantesRepository solicitantesRepository;

    private SolicitantesOutput solicitantesOutput = new SolicitantesOutput();

    private static final String MSG_SOLICITANTES_EM_USO = "Solicitantes de ID: %d não pode ser 
removido,"
			+ " pois está em uso!";

	private static final String MSG_SOLICITANTES_INCONSISTENTE = "Não permitido alterar 
o id pois,"
			+ " pode causar inconsistência de dados! ID original: %d - ID 
destino: %d";

	private static final String MSG_SOLICITANTES_NOT_FOUND = "Solicitantes de ID: %d 
não encontrada!";
	

    @Transactional
    public SolicitantesOutput create(SolicitantesInput solicitantesInput) {
        return solicitantesOutput.entityToOutput(solicitantesRepository.save
(solicitantesInput.inputToEntity()));
    }

    
    public Page<SolicitantesOutput> readPage(SolicitantesFilter filter, Pageable pageable) {
        Specification<Solicitantes> solicitantesSpec = SolicitantesSpec.usingFilter(filter, 
pageable);
        Page<Solicitantes> solicitantesPage = solicitantesRepository.findAll(solicitantesSpec, 
pageable);
        List<SolicitantesOutput> solicitantesOutputList = 
solicitantesOutput.entityListToOutputList(solicitantesPage.getContent());
        return new PageImpl<>(solicitantesOutputList, pageable, 
solicitantesPage.getTotalElements());
    }

    public List<SolicitantesOutput> readList(SolicitantesFilter filter, Pageable pageable) {
        Specification<Solicitantes> solicitantesSpec = SolicitantesSpec.usingFilter(filter, 
pageable);
        List<Solicitantes> solicitantesList = solicitantesRepository.findAll(solicitantesSpec);
        return solicitantesOutput.entityListToOutputList(solicitantesList);
    }

    public SolicitantesOutput readById(SolicitantesFilter filter) {
        if (filter.getId() == null) {
            throw new EntityNotFoundException(String.format(MSG_SOLICITANTES_NOT_FOUND, 
filter.getId()));
        }

        return solicitantesOutput.entityToOutput(solicitantesRepository.findById(filter.getId())
            .orElseThrow(() -> new EntityNotFoundException(
                String.format(MSG_SOLICITANTES_NOT_FOUND, filter.getId()))));
    }

    
    @Transactional
    public SolicitantesOutput update(SolicitantesFilter filter, SolicitantesInput solicitantesInput) 
{
        if (filter.getId() == null) {
            throw new BusinessException("ID inválido!");
        }

        if (!filter.getId().equals(solicitantesInput.getId())) {
            throw new BusinessException(String.format(MSG_SOLICITANTES_INCONSISTENTE, 
filter.getId(), solicitantesInput.getId()));
        }

        Solicitantes solicitantes = solicitantesRepository.findById(filter.getId())
        .orElseThrow(() -> new EntityNotFoundException(
            String.format("Solicitantes de ID %d não encontrada!", filter.getId())));

        solicitantes = solicitantesInput.mergeInputToEntity(solicitantes);

        return solicitantesOutput.entityToOutput(solicitantes);
    }
    
    @Transactional
    public void delete(SolicitantesFilter filter) {
        try {
            if (filter.getId() == null) {
                throw new BusinessException("ID inválido!");
            }
            solicitantesRepository.deleteById(filter.getId());
            solicitantesRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(
                String.format(String.format(MSG_SOLICITANTES_NOT_FOUND, filter.getId())));
        } catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(String.format
(MSG_SOLICITANTES_EM_USO, filter.getId()));
        }
    }

}
