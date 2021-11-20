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

import br.com.codiub.ordemservicos.controller.service.KitMateriaisService;
import br.com.codiub.ordemservicos.model.filter.KitMateriaisFilter;
import br.com.codiub.ordemservicos.model.input.KitMateriaisInput;
import br.com.codiub.ordemservicos.model.output.KitMateriaisOutput;

@RestController
@RequestMapping("/kitmateriais")
public class KitMateriaisController {
    
    @Autowired
    private KitMateriaisService kitmateriaisService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public KitMateriaisOutput create(@RequestBody @Valid KitMateriaisInput kitmateriaisInput) {
        return kitmateriaisService.create(kitmateriaisInput);
    }

    @GetMapping
    public Page<KitMateriaisOutput> readPage(KitMateriaisFilter filter, Pageable pageable) {
        return kitmateriaisService.readPage(filter, pageable);
    }

    @GetMapping("/list")
    public List<KitMateriaisOutput> readeList(KitMateriaisFilter filter, Pageable pageable) {
        return kitmateriaisService.readList(filter, pageable);
    }

    @GetMapping("/id")
    public KitMateriaisOutput readById(KitMateriaisFilter filter) {
        return kitmateriaisService.readById(filter);
    }

    @PutMapping
    public KitMateriaisOutput update(KitMateriaisFilter filter, @RequestBody @Valid 
KitMateriaisInput kitmateriaisInput) {
        return kitmateriaisService.update(filter, kitmateriaisInput);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(KitMateriaisFilter filter) {
        kitmateriaisService.delete(filter);
    }


}
