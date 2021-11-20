package br.com.codiub.ordemservicos.model.input;

import javax.validation.constraints.NotNull;
import br.com.codiub.ordemservicos.model.entity.OrdensServicos;
import lombok.Data;

@Data
public class OrdensServicosInput {

private Long id;
private Long solicitante;
private Long identificador;
private Long situacao;
private Long dataEmissao;
private String latitude;
private String longitude;
private Long quantidadeReclamada;
private String solicitacao;
private Long ordemServicoId;

    
   

    public OrdensServicos inputToEntity() {
        OrdensServicos ordensservicos = new OrdensServicos();

ordensservicos.setId(id);
ordensservicos.setSolicitante(solicitante);
ordensservicos.setIdentificador(identificador);
ordensservicos.setSituacao(situacao);
ordensservicos.setDataEmissao(dataEmissao);
ordensservicos.setLatitude(latitude);
ordensservicos.setLongitude(longitude);
ordensservicos.setQuantidadeReclamada(quantidadeReclamada);
ordensservicos.setSolicitacao(solicitacao);
ordensservicos.setOrdemServicoId(ordemServicoId);


        return ordensservicos;

    }
    

    public OrdensServicos mergeInputToEntity(OrdensServicos ordensservicos) {

        if (!nome.equals(ordensservicos.getNome())) {
            ordensservicos.setNome(nome);
        }

        if (!tipoOrdensServicos.toUpperCase().equals(ordensservicos.getTipoOrdensServicos().name())) {
            ordensservicos.setTipoOrdensServicos(TipoOrdensServicosEnum.valueOf
(tipoOrdensServicos.toUpperCase()));
        }

        return ordensservicos;
     }

}
