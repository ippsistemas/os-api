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

import br.com.codiub.ordemservicos.controller.service.EquipeFuncionarioService;
import br.com.codiub.ordemservicos.model.filter.EquipeFuncionarioFilter;
import br.com.codiub.ordemservicos.model.input.EquipeFuncionarioInput;
import br.com.codiub.ordemservicos.model.output.EquipeFuncionarioOutput;

@RestController
@RequestMapping("/equipefuncionario")
public class EquipeFuncionarioController {
    
    @Autowired
    private EquipeFuncionarioService equipefuncionarioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EquipeFuncionarioOutput create(@RequestBody @Valid EquipeFuncionarioInput equipefuncionarioInput) {
        return equipefuncionarioService.create(equipefuncionarioInput);
    }

    @GetMapping
    public Page<EquipeFuncionarioOutput> readPage(EquipeFuncionarioFilter filter, Pageable pageable) {
        return equipefuncionarioService.readPage(filter, pageable);
    }

    @GetMapping("/list")
    public List<EquipeFuncionarioOutput> readeList(EquipeFuncionarioFilter filter, Pageable pageable) {
        return equipefuncionarioService.readList(filter, pageable);
    }

    @GetMapping("/id")
    public EquipeFuncionarioOutput readById(EquipeFuncionarioFilter filter) {
        return equipefuncionarioService.readById(filter);
    }

    @PutMapping
    public EquipeFuncionarioOutput update(EquipeFuncionarioFilter filter, @RequestBody @Valid 
EquipeFuncionarioInput equipefuncionarioInput) {
        return equipefuncionarioService.update(filter, equipefuncionarioInput);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(EquipeFuncionarioFilter filter) {
        equipefuncionarioService.delete(filter);
    }


}
