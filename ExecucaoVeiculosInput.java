package br.com.codiub.ordemservicos.model.input;

import javax.validation.constraints.NotNull;
import br.com.codiub.ordemservicos.model.entity.ExecucaoVeiculos;
import lombok.Data;

@Data
public class ExecucaoVeiculosInput {

private Long id;
private Long execucao;
private Long veiculo;

    
   

    public ExecucaoVeiculos inputToEntity() {
        ExecucaoVeiculos execucaoveiculos = new ExecucaoVeiculos();

execucaoveiculos.setId(id);
execucaoveiculos.setExecucao(execucao);
execucaoveiculos.setVeiculo(veiculo);


        return execucaoveiculos;

    }
    

    public ExecucaoVeiculos mergeInputToEntity(ExecucaoVeiculos execucaoveiculos) {

        if (!nome.equals(execucaoveiculos.getNome())) {
            execucaoveiculos.setNome(nome);
        }

        if (!tipoExecucaoVeiculos.toUpperCase().equals(execucaoveiculos.getTipoExecucaoVeiculos().name())) {
            execucaoveiculos.setTipoExecucaoVeiculos(TipoExecucaoVeiculosEnum.valueOf
(tipoExecucaoVeiculos.toUpperCase()));
        }

        return execucaoveiculos;
     }

}
