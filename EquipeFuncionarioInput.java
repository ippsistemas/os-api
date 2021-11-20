package br.com.codiub.ordemservicos.model.input;

import javax.validation.constraints.NotNull;
import br.com.codiub.ordemservicos.model.entity.EquipeFuncionario;
import lombok.Data;

@Data
public class EquipeFuncionarioInput {

private Long id;
private Long equipe;
private Long empresa;
private Long matricula;
private String ocupacao;

    
   

    public EquipeFuncionario inputToEntity() {
        EquipeFuncionario equipefuncionario = new EquipeFuncionario();

equipefuncionario.setId(id);
equipefuncionario.setEquipe(equipe);
equipefuncionario.setEmpresa(empresa);
equipefuncionario.setMatricula(matricula);
equipefuncionario.setOcupacao(ocupacao);


        return equipefuncionario;

    }
    

    public EquipeFuncionario mergeInputToEntity(EquipeFuncionario equipefuncionario) {

        if (!nome.equals(equipefuncionario.getNome())) {
            equipefuncionario.setNome(nome);
        }

        if (!tipoEquipeFuncionario.toUpperCase().equals(equipefuncionario.getTipoEquipeFuncionario().name())) {
            equipefuncionario.setTipoEquipeFuncionario(TipoEquipeFuncionarioEnum.valueOf
(tipoEquipeFuncionario.toUpperCase()));
        }

        return equipefuncionario;
     }

}
