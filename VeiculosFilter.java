package br.com.codiub.ordemservicos.model.filter;

import lombok.Data;

@Data
public class VeiculosFilter {

private Long id;
private Long tipoVeiculo;
private String veiculo;
private String prefixo;
private Long codigo;
private String placa;


}
