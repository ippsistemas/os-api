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

import br.com.codiub.ordemservicos.controller.service.VeiculosService;
import br.com.codiub.ordemservicos.model.filter.VeiculosFilter;
import br.com.codiub.ordemservicos.model.input.VeiculosInput;
import br.com.codiub.ordemservicos.model.output.VeiculosOutput;

@RestController
@RequestMapping("/veiculos")
public class VeiculosController {
    
    @Autowired
    private VeiculosService veiculosService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VeiculosOutput create(@RequestBody @Valid VeiculosInput veiculosInput) {
        return veiculosService.create(veiculosInput);
    }

    @GetMapping
    public Page<VeiculosOutput> readPage(VeiculosFilter filter, Pageable pageable) {
        return veiculosService.readPage(filter, pageable);
    }

    @GetMapping("/list")
    public List<VeiculosOutput> readeList(VeiculosFilter filter, Pageable pageable) {
        return veiculosService.readList(filter, pageable);
    }

    @GetMapping("/id")
    public VeiculosOutput readById(VeiculosFilter filter) {
        return veiculosService.readById(filter);
    }

    @PutMapping
    public VeiculosOutput update(VeiculosFilter filter, @RequestBody @Valid 
VeiculosInput veiculosInput) {
        return veiculosService.update(filter, veiculosInput);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(VeiculosFilter filter) {
        veiculosService.delete(filter);
    }


}
