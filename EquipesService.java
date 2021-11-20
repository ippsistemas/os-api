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


import br.com.codiub.ordemservicos.model.entity.Equipes;
import br.com.codiub.ordemservicos.model.exception.BusinessException;
import br.com.codiub.ordemservicos.model.exception.EntityInUseException;
import br.com.codiub.ordemservicos.model.exception.EntityNotFoundException;
import br.com.codiub.ordemservicos.model.filter.EquipesFilter;
import br.com.codiub.ordemservicos.model.input.EquipesInput;
import br.com.codiub.ordemservicos.model.output.EquipesOutput;
import br.com.codiub.ordemservicos.model.repository.EquipesRepository;
import br.com.codiub.ordemservicos.model.repository.spec.EquipesSpec;

@Service
public class EquipesService {

    @Autowired
    private EquipesRepository equipesRepository;

    private EquipesOutput equipesOutput = new EquipesOutput();

    private static final String MSG_EQUIPES_EM_USO = "Equipes de ID: %d não pode ser 
removido,"
			+ " pois está em uso!";

	private static final String MSG_EQUIPES_INCONSISTENTE = "Não permitido alterar 
o id pois,"
			+ " pode causar inconsistência de dados! ID original: %d - ID 
destino: %d";

	private static final String MSG_EQUIPES_NOT_FOUND = "Equipes de ID: %d 
não encontrada!";
	

    @Transactional
    public EquipesOutput create(EquipesInput equipesInput) {
        return equipesOutput.entityToOutput(equipesRepository.save
(equipesInput.inputToEntity()));
    }

    
    public Page<EquipesOutput> readPage(EquipesFilter filter, Pageable pageable) {
        Specification<Equipes> equipesSpec = EquipesSpec.usingFilter(filter, 
pageable);
        Page<Equipes> equipesPage = equipesRepository.findAll(equipesSpec, 
pageable);
        List<EquipesOutput> equipesOutputList = 
equipesOutput.entityListToOutputList(equipesPage.getContent());
        return new PageImpl<>(equipesOutputList, pageable, 
equipesPage.getTotalElements());
    }

    public List<EquipesOutput> readList(EquipesFilter filter, Pageable pageable) {
        Specification<Equipes> equipesSpec = EquipesSpec.usingFilter(filter, 
pageable);
        List<Equipes> equipesList = equipesRepository.findAll(equipesSpec);
        return equipesOutput.entityListToOutputList(equipesList);
    }

    public EquipesOutput readById(EquipesFilter filter) {
        if (filter.getId() == null) {
            throw new EntityNotFoundException(String.format(MSG_EQUIPES_NOT_FOUND, 
filter.getId()));
        }

        return equipesOutput.entityToOutput(equipesRepository.findById(filter.getId())
            .orElseThrow(() -> new EntityNotFoundException(
                String.format(MSG_EQUIPES_NOT_FOUND, filter.getId()))));
    }

    
    @Transactional
    public EquipesOutput update(EquipesFilter filter, EquipesInput equipesInput) 
{
        if (filter.getId() == null) {
            throw new BusinessException("ID inválido!");
        }

        if (!filter.getId().equals(equipesInput.getId())) {
            throw new BusinessException(String.format(MSG_EQUIPES_INCONSISTENTE, 
filter.getId(), equipesInput.getId()));
        }

        Equipes equipes = equipesRepository.findById(filter.getId())
        .orElseThrow(() -> new EntityNotFoundException(
            String.format("Equipes de ID %d não encontrada!", filter.getId())));

        equipes = equipesInput.mergeInputToEntity(equipes);

        return equipesOutput.entityToOutput(equipes);
    }
    
    @Transactional
    public void delete(EquipesFilter filter) {
        try {
            if (filter.getId() == null) {
                throw new BusinessException("ID inválido!");
            }
            equipesRepository.deleteById(filter.getId());
            equipesRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(
                String.format(String.format(MSG_EQUIPES_NOT_FOUND, filter.getId())));
        } catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(String.format
(MSG_EQUIPES_EM_USO, filter.getId()));
        }
    }

}
