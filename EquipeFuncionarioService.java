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


import br.com.codiub.ordemservicos.model.entity.EquipeFuncionario;
import br.com.codiub.ordemservicos.model.exception.BusinessException;
import br.com.codiub.ordemservicos.model.exception.EntityInUseException;
import br.com.codiub.ordemservicos.model.exception.EntityNotFoundException;
import br.com.codiub.ordemservicos.model.filter.EquipeFuncionarioFilter;
import br.com.codiub.ordemservicos.model.input.EquipeFuncionarioInput;
import br.com.codiub.ordemservicos.model.output.EquipeFuncionarioOutput;
import br.com.codiub.ordemservicos.model.repository.EquipeFuncionarioRepository;
import br.com.codiub.ordemservicos.model.repository.spec.EquipeFuncionarioSpec;

@Service
public class EquipeFuncionarioService {

    @Autowired
    private EquipeFuncionarioRepository equipefuncionarioRepository;

    private EquipeFuncionarioOutput equipefuncionarioOutput = new EquipeFuncionarioOutput();

    private static final String MSG_EQUIPE_FUNCIONARIO_EM_USO = "EquipeFuncionario de ID: %d não pode ser 
removido,"
			+ " pois está em uso!";

	private static final String MSG_EQUIPE_FUNCIONARIO_INCONSISTENTE = "Não permitido alterar 
o id pois,"
			+ " pode causar inconsistência de dados! ID original: %d - ID 
destino: %d";

	private static final String MSG_EQUIPE_FUNCIONARIO_NOT_FOUND = "EquipeFuncionario de ID: %d 
não encontrada!";
	

    @Transactional
    public EquipeFuncionarioOutput create(EquipeFuncionarioInput equipefuncionarioInput) {
        return equipefuncionarioOutput.entityToOutput(equipefuncionarioRepository.save
(equipefuncionarioInput.inputToEntity()));
    }

    
    public Page<EquipeFuncionarioOutput> readPage(EquipeFuncionarioFilter filter, Pageable pageable) {
        Specification<EquipeFuncionario> equipefuncionarioSpec = EquipeFuncionarioSpec.usingFilter(filter, 
pageable);
        Page<EquipeFuncionario> equipefuncionarioPage = equipefuncionarioRepository.findAll(equipefuncionarioSpec, 
pageable);
        List<EquipeFuncionarioOutput> equipefuncionarioOutputList = 
equipefuncionarioOutput.entityListToOutputList(equipefuncionarioPage.getContent());
        return new PageImpl<>(equipefuncionarioOutputList, pageable, 
equipefuncionarioPage.getTotalElements());
    }

    public List<EquipeFuncionarioOutput> readList(EquipeFuncionarioFilter filter, Pageable pageable) {
        Specification<EquipeFuncionario> equipefuncionarioSpec = EquipeFuncionarioSpec.usingFilter(filter, 
pageable);
        List<EquipeFuncionario> equipefuncionarioList = equipefuncionarioRepository.findAll(equipefuncionarioSpec);
        return equipefuncionarioOutput.entityListToOutputList(equipefuncionarioList);
    }

    public EquipeFuncionarioOutput readById(EquipeFuncionarioFilter filter) {
        if (filter.getId() == null) {
            throw new EntityNotFoundException(String.format(MSG_EQUIPE_FUNCIONARIO_NOT_FOUND, 
filter.getId()));
        }

        return equipefuncionarioOutput.entityToOutput(equipefuncionarioRepository.findById(filter.getId())
            .orElseThrow(() -> new EntityNotFoundException(
                String.format(MSG_EQUIPE_FUNCIONARIO_NOT_FOUND, filter.getId()))));
    }

    
    @Transactional
    public EquipeFuncionarioOutput update(EquipeFuncionarioFilter filter, EquipeFuncionarioInput equipefuncionarioInput) 
{
        if (filter.getId() == null) {
            throw new BusinessException("ID inválido!");
        }

        if (!filter.getId().equals(equipefuncionarioInput.getId())) {
            throw new BusinessException(String.format(MSG_EQUIPE_FUNCIONARIO_INCONSISTENTE, 
filter.getId(), equipefuncionarioInput.getId()));
        }

        EquipeFuncionario equipefuncionario = equipefuncionarioRepository.findById(filter.getId())
        .orElseThrow(() -> new EntityNotFoundException(
            String.format("EquipeFuncionario de ID %d não encontrada!", filter.getId())));

        equipefuncionario = equipefuncionarioInput.mergeInputToEntity(equipefuncionario);

        return equipefuncionarioOutput.entityToOutput(equipefuncionario);
    }
    
    @Transactional
    public void delete(EquipeFuncionarioFilter filter) {
        try {
            if (filter.getId() == null) {
                throw new BusinessException("ID inválido!");
            }
            equipefuncionarioRepository.deleteById(filter.getId());
            equipefuncionarioRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(
                String.format(String.format(MSG_EQUIPE_FUNCIONARIO_NOT_FOUND, filter.getId())));
        } catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(String.format
(MSG_EQUIPE_FUNCIONARIO_EM_USO, filter.getId()));
        }
    }

}
