package br.com.codiub.ordemservicos.model.input;

import javax.validation.constraints.NotNull;
import br.com.codiub.ordemservicos.model.entity.KitMateriais;
import lombok.Data;

@Data
public class KitMateriaisInput {

private Long id;
private Long material;
private Long kit;

    
   

    public KitMateriais inputToEntity() {
        KitMateriais kitmateriais = new KitMateriais();

kitmateriais.setId(id);
kitmateriais.setMaterial(material);
kitmateriais.setKit(kit);


        return kitmateriais;

    }
    

    public KitMateriais mergeInputToEntity(KitMateriais kitmateriais) {

        if (!nome.equals(kitmateriais.getNome())) {
            kitmateriais.setNome(nome);
        }

        if (!tipoKitMateriais.toUpperCase().equals(kitmateriais.getTipoKitMateriais().name())) {
            kitmateriais.setTipoKitMateriais(TipoKitMateriaisEnum.valueOf
(tipoKitMateriais.toUpperCase()));
        }

        return kitmateriais;
     }

}
