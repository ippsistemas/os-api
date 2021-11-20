package br.com.codiub.ordemservicos.controller.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.codiub.ordemservicos.controller.service.EquipesService;
import br.com.codiub.ordemservicos.model.filter.EquipesFilter;
import br.com.codiub.ordemservicos.model.input.EquipesInput;
import br.com.codiub.ordemservicos.model.output.EquipesOutput;

@RestController
@RequestMapping("/equipes")
public class EquipesController {
    
    @Autowired
    private EquipesService equipesService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EquipesOutput create(@RequestBody @Valid EquipesInput equipesInput) {
        return equipesService.create(equipesInput);
    }

    @GetMapping
    public Page<EquipesOutput> readPage(EquipesFilter filter, Pageable pageable) {
        return equipesService.readPage(filter, pageable);
    }

    @GetMapping("/list")
    public List<EquipesOutput> readeList(EquipesFilter filter, Pageable pageable) {
        return equipesService.readList(filter, pageable);
    }

    @GetMapping("/id")
    public EquipesOutput readById(EquipesFilter filter) {
        return equipesService.readById(filter);
    }

    @PutMapping
    public EquipesOutput update(EquipesFilter filter, @RequestBody @Valid 
EquipesInput equipesInput) {
        return equipesService.update(filter, equipesInput);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(EquipesFilter filter) {
        equipesService.delete(filter);
    }


}
