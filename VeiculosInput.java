package br.com.codiub.ordemservicos.model.input;

import javax.validation.constraints.NotNull;
import br.com.codiub.ordemservicos.model.entity.Veiculos;
import lombok.Data;

@Data
public class VeiculosInput {

private Long id;
private Long tipoVeiculo;
private String veiculo;
private String prefixo;
private Long codigo;
private String placa;

    
   

    public Veiculos inputToEntity() {
        Veiculos veiculos = new Veiculos();

veiculos.setId(id);
veiculos.setTipoVeiculo(tipoVeiculo);
veiculos.setVeiculo(veiculo);
veiculos.setPrefixo(prefixo);
veiculos.setCodigo(codigo);
veiculos.setPlaca(placa);


        return veiculos;

    }
    

    public Veiculos mergeInputToEntity(Veiculos veiculos) {

        if (!nome.equals(veiculos.getNome())) {
            veiculos.setNome(nome);
        }

        if (!tipoVeiculos.toUpperCase().equals(veiculos.getTipoVeiculos().name())) {
            veiculos.setTipoVeiculos(TipoVeiculosEnum.valueOf
(tipoVeiculos.toUpperCase()));
        }

        return veiculos;
     }

}
