package br.com.codiub.ordemservicos.model.filter;

import lombok.Data;

@Data
public class OrdensServicosFilter {

private Long id;
private Long solicitante;
private Long identificador;
private Long situacao;
private Long dataEmissao;
private String latitude;
private String longitude;
private Long quantidadeReclamada;
private String solicitacao;
private Long ordemServicoId;


}
