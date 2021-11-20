package br.com.codiub.ordemservicos.model.output;

import java.util.ArrayList;
import java.util.List;

import br.com.codiub.ordemservicos.model.entity.Equipes;
import lombok.Data;

@Data
public class EquipesOutput {
    
private Long id;
private String equipe;
private Long subSecao;


   

    public EquipesOutput entityToOutput(Equipes equipes) {
        EquipesOutput equipesOutput = new EquipesOutput();

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
equipesOutput.setId(equipes.getId());
equipesOutput.setEquipe(equipes.getEquipe());
equipesOutput.setSubSecao(equipes.getSubSecao());
equipesOutput.setId(equipes.getId());
equipesOutput.setEquipe(equipes.getEquipe());
equipesOutput.setSubSecao(equipes.getSubSecao());
equipesOutput.setId(equipes.getId());
equipesOutput.setEquipe(equipes.getEquipe());
equipesOutput.setSubSecao(equipes.getSubSecao());
equipesOutput.setId(equipes.getId());
equipesOutput.setEquipe(equipes.getEquipe());
equipesOutput.setSubSecao(equipes.getSubSecao());
equipesOutput.setId(equipes.getId());
equipesOutput.setEquipe(equipes.getEquipe());
equipesOutput.setSubSecao(equipes.getSubSecao());

        
 
        return equipesOutput;
    }


    public List<EquipesOutput> entityListToOutputList(List<Equipes> equipesList) {
        List<EquipesOutput> equipesOutputList = new ArrayList<>();

        equipesList.forEach(equipes -> 
            equipesOutputList.add(entityToOutput(equipes))
        );

        return equipesOutputList;
    }

}
