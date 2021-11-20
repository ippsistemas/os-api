package br.com.codiub.ordemservicos.model.output;

import java.util.ArrayList;
import java.util.List;

import br.com.codiub.ordemservicos.model.entity.EquipeFuncionario;
import lombok.Data;

@Data
public class EquipeFuncionarioOutput {
    
private Long id;
private Long equipe;
private Long empresa;
private Long matricula;
private String ocupacao;


   

    public EquipeFuncionarioOutput entityToOutput(EquipeFuncionario equipefuncionario) {
        EquipeFuncionarioOutput equipefuncionarioOutput = new EquipeFuncionarioOutput();

Memo5
equipefuncionarioOutput.setId(equipefuncionario.getId());
equipefuncionarioOutput.setEquipe(equipefuncionario.getEquipe());
equipefuncionarioOutput.setEmpresa(equipefuncionario.getEmpresa());
equipefuncionarioOutput.setMatricula(equipefuncionario.getMatricula());
equipefuncionarioOutput.setOcupacao(equipefuncionario.getOcupacao());
equipefuncionarioOutput.setId(equipefuncionario.getId());
equipefuncionarioOutput.setEquipe(equipefuncionario.getEquipe());
equipefuncionarioOutput.setEmpresa(equipefuncionario.getEmpresa());
equipefuncionarioOutput.setMatricula(equipefuncionario.getMatricula());
equipefuncionarioOutput.setOcupacao(equipefuncionario.getOcupacao());
equipefuncionarioOutput.setId(equipefuncionario.getId());
equipefuncionarioOutput.setEquipe(equipefuncionario.getEquipe());
equipefuncionarioOutput.setEmpresa(equipefuncionario.getEmpresa());
equipefuncionarioOutput.setMatricula(equipefuncionario.getMatricula());
equipefuncionarioOutput.setOcupacao(equipefuncionario.getOcupacao());
equipefuncionarioOutput.setId(equipefuncionario.getId());
equipefuncionarioOutput.setEquipe(equipefuncionario.getEquipe());
equipefuncionarioOutput.setEmpresa(equipefuncionario.getEmpresa());
equipefuncionarioOutput.setMatricula(equipefuncionario.getMatricula());
equipefuncionarioOutput.setOcupacao(equipefuncionario.getOcupacao());
equipefuncionarioOutput.setId(equipefuncionario.getId());
equipefuncionarioOutput.setEquipe(equipefuncionario.getEquipe());
equipefuncionarioOutput.setEmpresa(equipefuncionario.getEmpresa());
equipefuncionarioOutput.setMatricula(equipefuncionario.getMatricula());
equipefuncionarioOutput.setOcupacao(equipefuncionario.getOcupacao());

        
 
        return equipefuncionarioOutput;
    }


    public List<EquipeFuncionarioOutput> entityListToOutputList(List<EquipeFuncionario> equipefuncionarioList) {
        List<EquipeFuncionarioOutput> equipefuncionarioOutputList = new ArrayList<>();

        equipefuncionarioList.forEach(equipefuncionario -> 
            equipefuncionarioOutputList.add(entityToOutput(equipefuncionario))
        );

        return equipefuncionarioOutputList;
    }

}
