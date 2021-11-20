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


import br.com.codiub.ordemservicos.model.entity.KitMateriais;
import br.com.codiub.ordemservicos.model.exception.BusinessException;
import br.com.codiub.ordemservicos.model.exception.EntityInUseException;
import br.com.codiub.ordemservicos.model.exception.EntityNotFoundException;
import br.com.codiub.ordemservicos.model.filter.KitMateriaisFilter;
import br.com.codiub.ordemservicos.model.input.KitMateriaisInput;
import br.com.codiub.ordemservicos.model.output.KitMateriaisOutput;
import br.com.codiub.ordemservicos.model.repository.KitMateriaisRepository;
import br.com.codiub.ordemservicos.model.repository.spec.KitMateriaisSpec;

@Service
public class KitMateriaisService {

    @Autowired
    private KitMateriaisRepository kitmateriaisRepository;

    private KitMateriaisOutput kitmateriaisOutput = new KitMateriaisOutput();

    private static final String MSG_KIT_MATERIAIS_EM_USO = "KitMateriais de ID: %d não pode ser 
removido,"
			+ " pois está em uso!";

	private static final String MSG_KIT_MATERIAIS_INCONSISTENTE = "Não permitido alterar 
o id pois,"
			+ " pode causar inconsistência de dados! ID original: %d - ID 
destino: %d";

	private static final String MSG_KIT_MATERIAIS_NOT_FOUND = "KitMateriais de ID: %d 
não encontrada!";
	

    @Transactional
    public KitMateriaisOutput create(KitMateriaisInput kitmateriaisInput) {
        return kitmateriaisOutput.entityToOutput(kitmateriaisRepository.save
(kitmateriaisInput.inputToEntity()));
    }

    
    public Page<KitMateriaisOutput> readPage(KitMateriaisFilter filter, Pageable pageable) {
        Specification<KitMateriais> kitmateriaisSpec = KitMateriaisSpec.usingFilter(filter, 
pageable);
        Page<KitMateriais> kitmateriaisPage = kitmateriaisRepository.findAll(kitmateriaisSpec, 
pageable);
        List<KitMateriaisOutput> kitmateriaisOutputList = 
kitmateriaisOutput.entityListToOutputList(kitmateriaisPage.getContent());
        return new PageImpl<>(kitmateriaisOutputList, pageable, 
kitmateriaisPage.getTotalElements());
    }

    public List<KitMateriaisOutput> readList(KitMateriaisFilter filter, Pageable pageable) {
        Specification<KitMateriais> kitmateriaisSpec = KitMateriaisSpec.usingFilter(filter, 
pageable);
        List<KitMateriais> kitmateriaisList = kitmateriaisRepository.findAll(kitmateriaisSpec);
        return kitmateriaisOutput.entityListToOutputList(kitmateriaisList);
    }

    public KitMateriaisOutput readById(KitMateriaisFilter filter) {
        if (filter.getId() == null) {
            throw new EntityNotFoundException(String.format(MSG_KIT_MATERIAIS_NOT_FOUND, 
filter.getId()));
        }

        return kitmateriaisOutput.entityToOutput(kitmateriaisRepository.findById(filter.getId())
            .orElseThrow(() -> new EntityNotFoundException(
                String.format(MSG_KIT_MATERIAIS_NOT_FOUND, filter.getId()))));
    }

    
    @Transactional
    public KitMateriaisOutput update(KitMateriaisFilter filter, KitMateriaisInput kitmateriaisInput) 
{
        if (filter.getId() == null) {
            throw new BusinessException("ID inválido!");
        }

        if (!filter.getId().equals(kitmateriaisInput.getId())) {
            throw new BusinessException(String.format(MSG_KIT_MATERIAIS_INCONSISTENTE, 
filter.getId(), kitmateriaisInput.getId()));
        }

        KitMateriais kitmateriais = kitmateriaisRepository.findById(filter.getId())
        .orElseThrow(() -> new EntityNotFoundException(
            String.format("KitMateriais de ID %d não encontrada!", filter.getId())));

        kitmateriais = kitmateriaisInput.mergeInputToEntity(kitmateriais);

        return kitmateriaisOutput.entityToOutput(kitmateriais);
    }
    
    @Transactional
    public void delete(KitMateriaisFilter filter) {
        try {
            if (filter.getId() == null) {
                throw new BusinessException("ID inválido!");
            }
            kitmateriaisRepository.deleteById(filter.getId());
            kitmateriaisRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(
                String.format(String.format(MSG_KIT_MATERIAIS_NOT_FOUND, filter.getId())));
        } catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(String.format
(MSG_KIT_MATERIAIS_EM_USO, filter.getId()));
        }
    }

}
