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


import br.com.codiub.ordemservicos.model.entity.Veiculos;
import br.com.codiub.ordemservicos.model.exception.BusinessException;
import br.com.codiub.ordemservicos.model.exception.EntityInUseException;
import br.com.codiub.ordemservicos.model.exception.EntityNotFoundException;
import br.com.codiub.ordemservicos.model.filter.VeiculosFilter;
import br.com.codiub.ordemservicos.model.input.VeiculosInput;
import br.com.codiub.ordemservicos.model.output.VeiculosOutput;
import br.com.codiub.ordemservicos.model.repository.VeiculosRepository;
import br.com.codiub.ordemservicos.model.repository.spec.VeiculosSpec;

@Service
public class VeiculosService {

    @Autowired
    private VeiculosRepository veiculosRepository;

    private VeiculosOutput veiculosOutput = new VeiculosOutput();

    private static final String MSG_VEICULOS_EM_USO = "Veiculos de ID: %d não pode ser 
removido,"
			+ " pois está em uso!";

	private static final String MSG_VEICULOS_INCONSISTENTE = "Não permitido alterar 
o id pois,"
			+ " pode causar inconsistência de dados! ID original: %d - ID 
destino: %d";

	private static final String MSG_VEICULOS_NOT_FOUND = "Veiculos de ID: %d 
não encontrada!";
	

    @Transactional
    public VeiculosOutput create(VeiculosInput veiculosInput) {
        return veiculosOutput.entityToOutput(veiculosRepository.save
(veiculosInput.inputToEntity()));
    }

    
    public Page<VeiculosOutput> readPage(VeiculosFilter filter, Pageable pageable) {
        Specification<Veiculos> veiculosSpec = VeiculosSpec.usingFilter(filter, 
pageable);
        Page<Veiculos> veiculosPage = veiculosRepository.findAll(veiculosSpec, 
pageable);
        List<VeiculosOutput> veiculosOutputList = 
veiculosOutput.entityListToOutputList(veiculosPage.getContent());
        return new PageImpl<>(veiculosOutputList, pageable, 
veiculosPage.getTotalElements());
    }

    public List<VeiculosOutput> readList(VeiculosFilter filter, Pageable pageable) {
        Specification<Veiculos> veiculosSpec = VeiculosSpec.usingFilter(filter, 
pageable);
        List<Veiculos> veiculosList = veiculosRepository.findAll(veiculosSpec);
        return veiculosOutput.entityListToOutputList(veiculosList);
    }

    public VeiculosOutput readById(VeiculosFilter filter) {
        if (filter.getId() == null) {
            throw new EntityNotFoundException(String.format(MSG_VEICULOS_NOT_FOUND, 
filter.getId()));
        }

        return veiculosOutput.entityToOutput(veiculosRepository.findById(filter.getId())
            .orElseThrow(() -> new EntityNotFoundException(
                String.format(MSG_VEICULOS_NOT_FOUND, filter.getId()))));
    }

    
    @Transactional
    public VeiculosOutput update(VeiculosFilter filter, VeiculosInput veiculosInput) 
{
        if (filter.getId() == null) {
            throw new BusinessException("ID inválido!");
        }

        if (!filter.getId().equals(veiculosInput.getId())) {
            throw new BusinessException(String.format(MSG_VEICULOS_INCONSISTENTE, 
filter.getId(), veiculosInput.getId()));
        }

        Veiculos veiculos = veiculosRepository.findById(filter.getId())
        .orElseThrow(() -> new EntityNotFoundException(
            String.format("Veiculos de ID %d não encontrada!", filter.getId())));

        veiculos = veiculosInput.mergeInputToEntity(veiculos);

        return veiculosOutput.entityToOutput(veiculos);
    }
    
    @Transactional
    public void delete(VeiculosFilter filter) {
        try {
            if (filter.getId() == null) {
                throw new BusinessException("ID inválido!");
            }
            veiculosRepository.deleteById(filter.getId());
            veiculosRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(
                String.format(String.format(MSG_VEICULOS_NOT_FOUND, filter.getId())));
        } catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(String.format
(MSG_VEICULOS_EM_USO, filter.getId()));
        }
    }

}
