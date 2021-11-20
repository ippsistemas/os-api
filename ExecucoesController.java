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

import br.com.codiub.ordemservicos.controller.service.ExecucoesService;
import br.com.codiub.ordemservicos.model.filter.ExecucoesFilter;
import br.com.codiub.ordemservicos.model.input.ExecucoesInput;
import br.com.codiub.ordemservicos.model.output.ExecucoesOutput;

@RestController
@RequestMapping("/execucoes")
public class ExecucoesController {
    
    @Autowired
    private ExecucoesService execucoesService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ExecucoesOutput create(@RequestBody @Valid ExecucoesInput execucoesInput) {
        return execucoesService.create(execucoesInput);
    }

    @GetMapping
    public Page<ExecucoesOutput> readPage(ExecucoesFilter filter, Pageable pageable) {
        return execucoesService.readPage(filter, pageable);
    }

    @GetMapping("/list")
    public List<ExecucoesOutput> readeList(ExecucoesFilter filter, Pageable pageable) {
        return execucoesService.readList(filter, pageable);
    }

    @GetMapping("/id")
    public ExecucoesOutput readById(ExecucoesFilter filter) {
        return execucoesService.readById(filter);
    }

    @PutMapping
    public ExecucoesOutput update(ExecucoesFilter filter, @RequestBody @Valid 
ExecucoesInput execucoesInput) {
        return execucoesService.update(filter, execucoesInput);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(ExecucoesFilter filter) {
        execucoesService.delete(filter);
    }


}
