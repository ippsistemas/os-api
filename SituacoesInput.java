package br.com.codiub.ordemservicos.model.input;

import javax.validation.constraints.NotNull;
import br.com.codiub.ordemservicos.model.entity.Situacoes;
import lombok.Data;

@Data
public class SituacoesInput {

private Long id;
private String situacao;

    
   

    public Situacoes inputToEntity() {
        Situacoes situacoes = new Situacoes();

situacoes.setId(id);
situacoes.setSituacao(situacao);


        return situacoes;

    }
    

    public Situacoes mergeInputToEntity(Situacoes situacoes) {

        if (!nome.equals(situacoes.getNome())) {
            situacoes.setNome(nome);
        }

        if (!tipoSituacoes.toUpperCase().equals(situacoes.getTipoSituacoes().name())) {
            situacoes.setTipoSituacoes(TipoSituacoesEnum.valueOf
(tipoSituacoes.toUpperCase()));
        }

        return situacoes;
     }

}
