package br.com.codiub.ordemservicos.model.input;

import javax.validation.constraints.NotNull;
import br.com.codiub.ordemservicos.model.entity.OsServico;
import lombok.Data;

@Data
public class OsServicoInput {

private Long id;
private Long ordemServico;
private Long servico;
private Long equipe;
private String observacao;

    
   

    public OsServico inputToEntity() {
        OsServico osservico = new OsServico();

osservico.setId(id);
osservico.setOrdemServico(ordemServico);
osservico.setServico(servico);
osservico.setEquipe(equipe);
osservico.setObservacao(observacao);


        return osservico;

    }
    

    public OsServico mergeInputToEntity(OsServico osservico) {

        if (!nome.equals(osservico.getNome())) {
            osservico.setNome(nome);
        }

        if (!tipoOsServico.toUpperCase().equals(osservico.getTipoOsServico().name())) {
            osservico.setTipoOsServico(TipoOsServicoEnum.valueOf
(tipoOsServico.toUpperCase()));
        }

        return osservico;
     }

}
