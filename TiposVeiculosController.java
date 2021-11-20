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

import br.com.codiub.ordemservicos.controller.service.TiposVeiculosService;
import br.com.codiub.ordemservicos.model.filter.TiposVeiculosFilter;
import br.com.codiub.ordemservicos.model.input.TiposVeiculosInput;
import br.com.codiub.ordemservicos.model.output.TiposVeiculosOutput;

@RestController
@RequestMapping("/tiposveiculos")
public class TiposVeiculosController {
    
    @Autowired
    private TiposVeiculosService tiposveiculosService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TiposVeiculosOutput create(@RequestBody @Valid TiposVeiculosInput tiposveiculosInput) {
        return tiposveiculosService.create(tiposveiculosInput);
    }

    @GetMapping
    public Page<TiposVeiculosOutput> readPage(TiposVeiculosFilter filter, Pageable pageable) {
        return tiposveiculosService.readPage(filter, pageable);
    }

    @GetMapping("/list")
    public List<TiposVeiculosOutput> readeList(TiposVeiculosFilter filter, Pageable pageable) {
        return tiposveiculosService.readList(filter, pageable);
    }

    @GetMapping("/id")
    public TiposVeiculosOutput readById(TiposVeiculosFilter filter) {
        return tiposveiculosService.readById(filter);
    }

    @PutMapping
    public TiposVeiculosOutput update(TiposVeiculosFilter filter, @RequestBody @Valid 
TiposVeiculosInput tiposveiculosInput) {
        return tiposveiculosService.update(filter, tiposveiculosInput);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(TiposVeiculosFilter filter) {
        tiposveiculosService.delete(filter);
    }


}
