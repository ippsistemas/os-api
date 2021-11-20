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

import br.com.codiub.ordemservicos.controller.service.SolicitantesService;
import br.com.codiub.ordemservicos.model.filter.SolicitantesFilter;
import br.com.codiub.ordemservicos.model.input.SolicitantesInput;
import br.com.codiub.ordemservicos.model.output.SolicitantesOutput;

@RestController
@RequestMapping("/solicitantes")
public class SolicitantesController {
    
    @Autowired
    private SolicitantesService solicitantesService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SolicitantesOutput create(@RequestBody @Valid SolicitantesInput solicitantesInput) {
        return solicitantesService.create(solicitantesInput);
    }

    @GetMapping
    public Page<SolicitantesOutput> readPage(SolicitantesFilter filter, Pageable pageable) {
        return solicitantesService.readPage(filter, pageable);
    }

    @GetMapping("/list")
    public List<SolicitantesOutput> readeList(SolicitantesFilter filter, Pageable pageable) {
        return solicitantesService.readList(filter, pageable);
    }

    @GetMapping("/id")
    public SolicitantesOutput readById(SolicitantesFilter filter) {
        return solicitantesService.readById(filter);
    }

    @PutMapping
    public SolicitantesOutput update(SolicitantesFilter filter, @RequestBody @Valid 
SolicitantesInput solicitantesInput) {
        return solicitantesService.update(filter, solicitantesInput);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(SolicitantesFilter filter) {
        solicitantesService.delete(filter);
    }


}
