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

import br.com.codiub.ordemservicos.controller.service.OsServicoService;
import br.com.codiub.ordemservicos.model.filter.OsServicoFilter;
import br.com.codiub.ordemservicos.model.input.OsServicoInput;
import br.com.codiub.ordemservicos.model.output.OsServicoOutput;

@RestController
@RequestMapping("/osservico")
public class OsServicoController {
    
    @Autowired
    private OsServicoService osservicoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OsServicoOutput create(@RequestBody @Valid OsServicoInput osservicoInput) {
        return osservicoService.create(osservicoInput);
    }

    @GetMapping
    public Page<OsServicoOutput> readPage(OsServicoFilter filter, Pageable pageable) {
        return osservicoService.readPage(filter, pageable);
    }

    @GetMapping("/list")
    public List<OsServicoOutput> readeList(OsServicoFilter filter, Pageable pageable) {
        return osservicoService.readList(filter, pageable);
    }

    @GetMapping("/id")
    public OsServicoOutput readById(OsServicoFilter filter) {
        return osservicoService.readById(filter);
    }

    @PutMapping
    public OsServicoOutput update(OsServicoFilter filter, @RequestBody @Valid 
OsServicoInput osservicoInput) {
        return osservicoService.update(filter, osservicoInput);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(OsServicoFilter filter) {
        osservicoService.delete(filter);
    }


}
