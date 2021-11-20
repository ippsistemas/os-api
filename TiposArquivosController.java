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

import br.com.codiub.ordemservicos.controller.service.TiposArquivosService;
import br.com.codiub.ordemservicos.model.filter.TiposArquivosFilter;
import br.com.codiub.ordemservicos.model.input.TiposArquivosInput;
import br.com.codiub.ordemservicos.model.output.TiposArquivosOutput;

@RestController
@RequestMapping("/tiposarquivos")
public class TiposArquivosController {
    
    @Autowired
    private TiposArquivosService tiposarquivosService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TiposArquivosOutput create(@RequestBody @Valid TiposArquivosInput tiposarquivosInput) {
        return tiposarquivosService.create(tiposarquivosInput);
    }

    @GetMapping
    public Page<TiposArquivosOutput> readPage(TiposArquivosFilter filter, Pageable pageable) {
        return tiposarquivosService.readPage(filter, pageable);
    }

    @GetMapping("/list")
    public List<TiposArquivosOutput> readeList(TiposArquivosFilter filter, Pageable pageable) {
        return tiposarquivosService.readList(filter, pageable);
    }

    @GetMapping("/id")
    public TiposArquivosOutput readById(TiposArquivosFilter filter) {
        return tiposarquivosService.readById(filter);
    }

    @PutMapping
    public TiposArquivosOutput update(TiposArquivosFilter filter, @RequestBody @Valid 
TiposArquivosInput tiposarquivosInput) {
        return tiposarquivosService.update(filter, tiposarquivosInput);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(TiposArquivosFilter filter) {
        tiposarquivosService.delete(filter);
    }


}
