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


import br.com.codiub.ordemservicos.model.entity.OrdensServicos;
import br.com.codiub.ordemservicos.model.exception.BusinessException;
import br.com.codiub.ordemservicos.model.exception.EntityInUseException;
import br.com.codiub.ordemservicos.model.exception.EntityNotFoundException;
import br.com.codiub.ordemservicos.model.filter.OrdensServicosFilter;
import br.com.codiub.ordemservicos.model.input.OrdensServicosInput;
import br.com.codiub.ordemservicos.model.output.OrdensServicosOutput;
import br.com.codiub.ordemservicos.model.repository.OrdensServicosRepository;
import br.com.codiub.ordemservicos.model.repository.spec.OrdensServicosSpec;

@Service
public class OrdensServicosService {

    @Autowired
    private OrdensServicosRepository ordensservicosRepository;

    private OrdensServicosOutput ordensservicosOutput = new OrdensServicosOutput();

    private static final String MSG_ORDENS_SERVICOS_EM_USO = "OrdensServicos de ID: %d não pode ser 
removido,"
			+ " pois está em uso!";

	private static final String MSG_ORDENS_SERVICOS_INCONSISTENTE = "Não permitido alterar 
o id pois,"
			+ " pode causar inconsistência de dados! ID original: %d - ID 
destino: %d";

	private static final String MSG_ORDENS_SERVICOS_NOT_FOUND = "OrdensServicos de ID: %d 
não encontrada!";
	

    @Transactional
    public OrdensServicosOutput create(OrdensServicosInput ordensservicosInput) {
        return ordensservicosOutput.entityToOutput(ordensservicosRepository.save
(ordensservicosInput.inputToEntity()));
    }

    
    public Page<OrdensServicosOutput> readPage(OrdensServicosFilter filter, Pageable pageable) {
        Specification<OrdensServicos> ordensservicosSpec = OrdensServicosSpec.usingFilter(filter, 
pageable);
        Page<OrdensServicos> ordensservicosPage = ordensservicosRepository.findAll(ordensservicosSpec, 
pageable);
        List<OrdensServicosOutput> ordensservicosOutputList = 
ordensservicosOutput.entityListToOutputList(ordensservicosPage.getContent());
        return new PageImpl<>(ordensservicosOutputList, pageable, 
ordensservicosPage.getTotalElements());
    }

    public List<OrdensServicosOutput> readList(OrdensServicosFilter filter, Pageable pageable) {
        Specification<OrdensServicos> ordensservicosSpec = OrdensServicosSpec.usingFilter(filter, 
pageable);
        List<OrdensServicos> ordensservicosList = ordensservicosRepository.findAll(ordensservicosSpec);
        return ordensservicosOutput.entityListToOutputList(ordensservicosList);
    }

    public OrdensServicosOutput readById(OrdensServicosFilter filter) {
        if (filter.getId() == null) {
            throw new EntityNotFoundException(String.format(MSG_ORDENS_SERVICOS_NOT_FOUND, 
filter.getId()));
        }

        return ordensservicosOutput.entityToOutput(ordensservicosRepository.findById(filter.getId())
            .orElseThrow(() -> new EntityNotFoundException(
                String.format(MSG_ORDENS_SERVICOS_NOT_FOUND, filter.getId()))));
    }

    
    @Transactional
    public OrdensServicosOutput update(OrdensServicosFilter filter, OrdensServicosInput ordensservicosInput) 
{
        if (filter.getId() == null) {
            throw new BusinessException("ID inválido!");
        }

        if (!filter.getId().equals(ordensservicosInput.getId())) {
            throw new BusinessException(String.format(MSG_ORDENS_SERVICOS_INCONSISTENTE, 
filter.getId(), ordensservicosInput.getId()));
        }

        OrdensServicos ordensservicos = ordensservicosRepository.findById(filter.getId())
        .orElseThrow(() -> new EntityNotFoundException(
            String.format("OrdensServicos de ID %d não encontrada!", filter.getId())));

        ordensservicos = ordensservicosInput.mergeInputToEntity(ordensservicos);

        return ordensservicosOutput.entityToOutput(ordensservicos);
    }
    
    @Transactional
    public void delete(OrdensServicosFilter filter) {
        try {
            if (filter.getId() == null) {
                throw new BusinessException("ID inválido!");
            }
            ordensservicosRepository.deleteById(filter.getId());
            ordensservicosRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(
                String.format(String.format(MSG_ORDENS_SERVICOS_NOT_FOUND, filter.getId())));
        } catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(String.format
(MSG_ORDENS_SERVICOS_EM_USO, filter.getId()));
        }
    }

}
