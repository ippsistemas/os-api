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

import br.com.codiub.ordemservicos.controller.service.ExecucaoVeiculosService;
import br.com.codiub.ordemservicos.model.filter.ExecucaoVeiculosFilter;
import br.com.codiub.ordemservicos.model.input.ExecucaoVeiculosInput;
import br.com.codiub.ordemservicos.model.output.ExecucaoVeiculosOutput;

@RestController
@RequestMapping("/execucaoveiculos")
public class ExecucaoVeiculosController {
    
    @Autowired
    private ExecucaoVeiculosService execucaoveiculosService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ExecucaoVeiculosOutput create(@RequestBody @Valid ExecucaoVeiculosInput execucaoveiculosInput) {
        return execucaoveiculosService.create(execucaoveiculosInput);
    }

    @GetMapping
    public Page<ExecucaoVeiculosOutput> readPage(ExecucaoVeiculosFilter filter, Pageable pageable) {
        return execucaoveiculosService.readPage(filter, pageable);
    }

    @GetMapping("/list")
    public List<ExecucaoVeiculosOutput> readeList(ExecucaoVeiculosFilter filter, Pageable pageable) {
        return execucaoveiculosService.readList(filter, pageable);
    }

    @GetMapping("/id")
    public ExecucaoVeiculosOutput readById(ExecucaoVeiculosFilter filter) {
        return execucaoveiculosService.readById(filter);
    }

    @PutMapping
    public ExecucaoVeiculosOutput update(ExecucaoVeiculosFilter filter, @RequestBody @Valid 
ExecucaoVeiculosInput execucaoveiculosInput) {
        return execucaoveiculosService.update(filter, execucaoveiculosInput);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(ExecucaoVeiculosFilter filter) {
        execucaoveiculosService.delete(filter);
    }


}
