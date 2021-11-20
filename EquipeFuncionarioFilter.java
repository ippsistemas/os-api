package br.com.codiub.ordemservicos.model.filter;

import lombok.Data;

@Data
public class EquipeFuncionarioFilter {

private Long id;
private Long equipe;
private Long empresa;
private Long matricula;
private String ocupacao;


}
