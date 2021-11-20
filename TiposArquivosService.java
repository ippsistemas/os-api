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


import br.com.codiub.ordemservicos.model.entity.TiposArquivos;
import br.com.codiub.ordemservicos.model.exception.BusinessException;
import br.com.codiub.ordemservicos.model.exception.EntityInUseException;
import br.com.codiub.ordemservicos.model.exception.EntityNotFoundException;
import br.com.codiub.ordemservicos.model.filter.TiposArquivosFilter;
import br.com.codiub.ordemservicos.model.input.TiposArquivosInput;
import br.com.codiub.ordemservicos.model.output.TiposArquivosOutput;
import br.com.codiub.ordemservicos.model.repository.TiposArquivosRepository;
import br.com.codiub.ordemservicos.model.repository.spec.TiposArquivosSpec;

@Service
public class TiposArquivosService {

    @Autowired
    private TiposArquivosRepository tiposarquivosRepository;

    private TiposArquivosOutput tiposarquivosOutput = new TiposArquivosOutput();

    private static final String MSG_TIPOS_ARQUIVOS_EM_USO = "TiposArquivos de ID: %d não pode ser 
removido,"
			+ " pois está em uso!";

	private static final String MSG_TIPOS_ARQUIVOS_INCONSISTENTE = "Não permitido alterar 
o id pois,"
			+ " pode causar inconsistência de dados! ID original: %d - ID 
destino: %d";

	private static final String MSG_TIPOS_ARQUIVOS_NOT_FOUND = "TiposArquivos de ID: %d 
não encontrada!";
	

    @Transactional
    public TiposArquivosOutput create(TiposArquivosInput tiposarquivosInput) {
        return tiposarquivosOutput.entityToOutput(tiposarquivosRepository.save
(tiposarquivosInput.inputToEntity()));
    }

    
    public Page<TiposArquivosOutput> readPage(TiposArquivosFilter filter, Pageable pageable) {
        Specification<TiposArquivos> tiposarquivosSpec = TiposArquivosSpec.usingFilter(filter, 
pageable);
        Page<TiposArquivos> tiposarquivosPage = tiposarquivosRepository.findAll(tiposarquivosSpec, 
pageable);
        List<TiposArquivosOutput> tiposarquivosOutputList = 
tiposarquivosOutput.entityListToOutputList(tiposarquivosPage.getContent());
        return new PageImpl<>(tiposarquivosOutputList, pageable, 
tiposarquivosPage.getTotalElements());
    }

    public List<TiposArquivosOutput> readList(TiposArquivosFilter filter, Pageable pageable) {
        Specification<TiposArquivos> tiposarquivosSpec = TiposArquivosSpec.usingFilter(filter, 
pageable);
        List<TiposArquivos> tiposarquivosList = tiposarquivosRepository.findAll(tiposarquivosSpec);
        return tiposarquivosOutput.entityListToOutputList(tiposarquivosList);
    }

    public TiposArquivosOutput readById(TiposArquivosFilter filter) {
        if (filter.getId() == null) {
            throw new EntityNotFoundException(String.format(MSG_TIPOS_ARQUIVOS_NOT_FOUND, 
filter.getId()));
        }

        return tiposarquivosOutput.entityToOutput(tiposarquivosRepository.findById(filter.getId())
            .orElseThrow(() -> new EntityNotFoundException(
                String.format(MSG_TIPOS_ARQUIVOS_NOT_FOUND, filter.getId()))));
    }

    
    @Transactional
    public TiposArquivosOutput update(TiposArquivosFilter filter, TiposArquivosInput tiposarquivosInput) 
{
        if (filter.getId() == null) {
            throw new BusinessException("ID inválido!");
        }

        if (!filter.getId().equals(tiposarquivosInput.getId())) {
            throw new BusinessException(String.format(MSG_TIPOS_ARQUIVOS_INCONSISTENTE, 
filter.getId(), tiposarquivosInput.getId()));
        }

        TiposArquivos tiposarquivos = tiposarquivosRepository.findById(filter.getId())
        .orElseThrow(() -> new EntityNotFoundException(
            String.format("TiposArquivos de ID %d não encontrada!", filter.getId())));

        tiposarquivos = tiposarquivosInput.mergeInputToEntity(tiposarquivos);

        return tiposarquivosOutput.entityToOutput(tiposarquivos);
    }
    
    @Transactional
    public void delete(TiposArquivosFilter filter) {
        try {
            if (filter.getId() == null) {
                throw new BusinessException("ID inválido!");
            }
            tiposarquivosRepository.deleteById(filter.getId());
            tiposarquivosRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(
                String.format(String.format(MSG_TIPOS_ARQUIVOS_NOT_FOUND, filter.getId())));
        } catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(String.format
(MSG_TIPOS_ARQUIVOS_EM_USO, filter.getId()));
        }
    }

}
