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

import br.com.codiub.ordemservicos.controller.service.SituacoesService;
import br.com.codiub.ordemservicos.model.filter.SituacoesFilter;
import br.com.codiub.ordemservicos.model.input.SituacoesInput;
import br.com.codiub.ordemservicos.model.output.SituacoesOutput;

@RestController
@RequestMapping("/situacoes")
public class SituacoesController {
    
    @Autowired
    private SituacoesService situacoesService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SituacoesOutput create(@RequestBody @Valid SituacoesInput situacoesInput) {
        return situacoesService.create(situacoesInput);
    }

    @GetMapping
    public Page<SituacoesOutput> readPage(SituacoesFilter filter, Pageable pageable) {
        return situacoesService.readPage(filter, pageable);
    }

    @GetMapping("/list")
    public List<SituacoesOutput> readeList(SituacoesFilter filter, Pageable pageable) {
        return situacoesService.readList(filter, pageable);
    }

    @GetMapping("/id")
    public SituacoesOutput readById(SituacoesFilter filter) {
        return situacoesService.readById(filter);
    }

    @PutMapping
    public SituacoesOutput update(SituacoesFilter filter, @RequestBody @Valid 
SituacoesInput situacoesInput) {
        return situacoesService.update(filter, situacoesInput);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(SituacoesFilter filter) {
        situacoesService.delete(filter);
    }


}
