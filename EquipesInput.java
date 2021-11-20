package br.com.codiub.ordemservicos.model.input;

import javax.validation.constraints.NotNull;
import br.com.codiub.ordemservicos.model.entity.Equipes;
import lombok.Data;

@Data
public class EquipesInput {

private Long id;
private String equipe;
private Long subSecao;

    
   

    public Equipes inputToEntity() {
        Equipes equipes = new Equipes();

equipes.setId(id);
equipes.setEquipe(equipe);
equipes.setSubSecao(subSecao);


        return equipes;

    }
    

    public Equipes mergeInputToEntity(Equipes equipes) {

        if (!nome.equals(equipes.getNome())) {
            equipes.setNome(nome);
        }

        if (!tipoEquipes.toUpperCase().equals(equipes.getTipoEquipes().name())) {
            equipes.setTipoEquipes(TipoEquipesEnum.valueOf
(tipoEquipes.toUpperCase()));
        }

        return equipes;
     }

}
