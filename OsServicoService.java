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


import br.com.codiub.ordemservicos.model.entity.OsServico;
import br.com.codiub.ordemservicos.model.exception.BusinessException;
import br.com.codiub.ordemservicos.model.exception.EntityInUseException;
import br.com.codiub.ordemservicos.model.exception.EntityNotFoundException;
import br.com.codiub.ordemservicos.model.filter.OsServicoFilter;
import br.com.codiub.ordemservicos.model.input.OsServicoInput;
import br.com.codiub.ordemservicos.model.output.OsServicoOutput;
import br.com.codiub.ordemservicos.model.repository.OsServicoRepository;
import br.com.codiub.ordemservicos.model.repository.spec.OsServicoSpec;

@Service
public class OsServicoService {

    @Autowired
    private OsServicoRepository osservicoRepository;

    private OsServicoOutput osservicoOutput = new OsServicoOutput();

    private static final String MSG_OS_SERVICO_EM_USO = "OsServico de ID: %d não pode ser 
removido,"
			+ " pois está em uso!";

	private static final String MSG_OS_SERVICO_INCONSISTENTE = "Não permitido alterar 
o id pois,"
			+ " pode causar inconsistência de dados! ID original: %d - ID 
destino: %d";

	private static final String MSG_OS_SERVICO_NOT_FOUND = "OsServico de ID: %d 
não encontrada!";
	

    @Transactional
    public OsServicoOutput create(OsServicoInput osservicoInput) {
        return osservicoOutput.entityToOutput(osservicoRepository.save
(osservicoInput.inputToEntity()));
    }

    
    public Page<OsServicoOutput> readPage(OsServicoFilter filter, Pageable pageable) {
        Specification<OsServico> osservicoSpec = OsServicoSpec.usingFilter(filter, 
pageable);
        Page<OsServico> osservicoPage = osservicoRepository.findAll(osservicoSpec, 
pageable);
        List<OsServicoOutput> osservicoOutputList = 
osservicoOutput.entityListToOutputList(osservicoPage.getContent());
        return new PageImpl<>(osservicoOutputList, pageable, 
osservicoPage.getTotalElements());
    }

    public List<OsServicoOutput> readList(OsServicoFilter filter, Pageable pageable) {
        Specification<OsServico> osservicoSpec = OsServicoSpec.usingFilter(filter, 
pageable);
        List<OsServico> osservicoList = osservicoRepository.findAll(osservicoSpec);
        return osservicoOutput.entityListToOutputList(osservicoList);
    }

    public OsServicoOutput readById(OsServicoFilter filter) {
        if (filter.getId() == null) {
            throw new EntityNotFoundException(String.format(MSG_OS_SERVICO_NOT_FOUND, 
filter.getId()));
        }

        return osservicoOutput.entityToOutput(osservicoRepository.findById(filter.getId())
            .orElseThrow(() -> new EntityNotFoundException(
                String.format(MSG_OS_SERVICO_NOT_FOUND, filter.getId()))));
    }

    
    @Transactional
    public OsServicoOutput update(OsServicoFilter filter, OsServicoInput osservicoInput) 
{
        if (filter.getId() == null) {
            throw new BusinessException("ID inválido!");
        }

        if (!filter.getId().equals(osservicoInput.getId())) {
            throw new BusinessException(String.format(MSG_OS_SERVICO_INCONSISTENTE, 
filter.getId(), osservicoInput.getId()));
        }

        OsServico osservico = osservicoRepository.findById(filter.getId())
        .orElseThrow(() -> new EntityNotFoundException(
            String.format("OsServico de ID %d não encontrada!", filter.getId())));

        osservico = osservicoInput.mergeInputToEntity(osservico);

        return osservicoOutput.entityToOutput(osservico);
    }
    
    @Transactional
    public void delete(OsServicoFilter filter) {
        try {
            if (filter.getId() == null) {
                throw new BusinessException("ID inválido!");
            }
            osservicoRepository.deleteById(filter.getId());
            osservicoRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(
                String.format(String.format(MSG_OS_SERVICO_NOT_FOUND, filter.getId())));
        } catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(String.format
(MSG_OS_SERVICO_EM_USO, filter.getId()));
        }
    }

}
