package br.com.codiub.ordemservicos.model.input;

import javax.validation.constraints.NotNull;
import br.com.codiub.ordemservicos.model.entity.TiposArquivos;
import lombok.Data;

@Data
public class TiposArquivosInput {

private Long id;
private String tipoArquivo;

    
   

    public TiposArquivos inputToEntity() {
        TiposArquivos tiposarquivos = new TiposArquivos();

tiposarquivos.setId(id);
tiposarquivos.setTipoArquivo(tipoArquivo);


        return tiposarquivos;

    }
    

    public TiposArquivos mergeInputToEntity(TiposArquivos tiposarquivos) {

        if (!nome.equals(tiposarquivos.getNome())) {
            tiposarquivos.setNome(nome);
        }

        if (!tipoTiposArquivos.toUpperCase().equals(tiposarquivos.getTipoTiposArquivos().name())) {
            tiposarquivos.setTipoTiposArquivos(TipoTiposArquivosEnum.valueOf
(tipoTiposArquivos.toUpperCase()));
        }

        return tiposarquivos;
     }

}
