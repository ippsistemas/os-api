package br.com.codiub.ordemservicos.model.filter;

import lombok.Data;

@Data
public class OsServicoFilter {

private Long id;
private Long ordemServico;
private Long servico;
private Long equipe;
private String observacao;


}
