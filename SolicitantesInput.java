package br.com.codiub.ordemservicos.model.input;

import javax.validation.constraints.NotNull;
import br.com.codiub.ordemservicos.model.entity.Solicitantes;
import lombok.Data;

@Data
public class SolicitantesInput {

private Long id;
private String nome;
private String cpfCnpj;
private Long solicitanteId;
private String celular;
private String telefone;
private Long relacaoProprietario;
private String tipoSolicitante;

    
   

    public Solicitantes inputToEntity() {
        Solicitantes solicitantes = new Solicitantes();

solicitantes.setId(id);
solicitantes.setNome(nome);
solicitantes.setCpfCnpj(cpfCnpj);
solicitantes.setSolicitanteId(solicitanteId);
solicitantes.setCelular(celular);
solicitantes.setTelefone(telefone);
solicitantes.setRelacaoProprietario(relacaoProprietario);
solicitantes.setTipoSolicitante(tipoSolicitante);


        return solicitantes;

    }
    

    public Solicitantes mergeInputToEntity(Solicitantes solicitantes) {

        if (!nome.equals(solicitantes.getNome())) {
            solicitantes.setNome(nome);
        }

        if (!tipoSolicitantes.toUpperCase().equals(solicitantes.getTipoSolicitantes().name())) {
            solicitantes.setTipoSolicitantes(TipoSolicitantesEnum.valueOf
(tipoSolicitantes.toUpperCase()));
        }

        return solicitantes;
     }

}
