package br.com.codiub.ordemservicos.model.input;

import javax.validation.constraints.NotNull;
import br.com.codiub.ordemservicos.model.entity.TiposVeiculos;
import lombok.Data;

@Data
public class TiposVeiculosInput {

private Long id;
private String tipoVeiculo;

    
   

    public TiposVeiculos inputToEntity() {
        TiposVeiculos tiposveiculos = new TiposVeiculos();

tiposveiculos.setId(id);
tiposveiculos.setTipoVeiculo(tipoVeiculo);


        return tiposveiculos;

    }
    

    public TiposVeiculos mergeInputToEntity(TiposVeiculos tiposveiculos) {

        if (!nome.equals(tiposveiculos.getNome())) {
            tiposveiculos.setNome(nome);
        }

        if (!tipoTiposVeiculos.toUpperCase().equals(tiposveiculos.getTipoTiposVeiculos().name())) {
            tiposveiculos.setTipoTiposVeiculos(TipoTiposVeiculosEnum.valueOf
(tipoTiposVeiculos.toUpperCase()));
        }

        return tiposveiculos;
     }

}
