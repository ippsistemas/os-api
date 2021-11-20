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

import br.com.codiub.ordemservicos.controller.service.OrdensServicosService;
import br.com.codiub.ordemservicos.model.filter.OrdensServicosFilter;
import br.com.codiub.ordemservicos.model.input.OrdensServicosInput;
import br.com.codiub.ordemservicos.model.output.OrdensServicosOutput;

@RestController
@RequestMapping("/ordensservicos")
public class OrdensServicosController {
    
    @Autowired
    private OrdensServicosService ordensservicosService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrdensServicosOutput create(@RequestBody @Valid OrdensServicosInput ordensservicosInput) {
        return ordensservicosService.create(ordensservicosInput);
    }

    @GetMapping
    public Page<OrdensServicosOutput> readPage(OrdensServicosFilter filter, Pageable pageable) {
        return ordensservicosService.readPage(filter, pageable);
    }

    @GetMapping("/list")
    public List<OrdensServicosOutput> readeList(OrdensServicosFilter filter, Pageable pageable) {
        return ordensservicosService.readList(filter, pageable);
    }

    @GetMapping("/id")
    public OrdensServicosOutput readById(OrdensServicosFilter filter) {
        return ordensservicosService.readById(filter);
    }

    @PutMapping
    public OrdensServicosOutput update(OrdensServicosFilter filter, @RequestBody @Valid 
OrdensServicosInput ordensservicosInput) {
        return ordensservicosService.update(filter, ordensservicosInput);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(OrdensServicosFilter filter) {
        ordensservicosService.delete(filter);
    }


}
