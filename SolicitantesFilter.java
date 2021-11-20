package br.com.codiub.ordemservicos.model.filter;

import lombok.Data;

@Data
public class SolicitantesFilter {

private Long id;
private String nome;
private String cpfCnpj;
private Long solicitanteId;
private String celular;
private String telefone;
private Long relacaoProprietario;
private String tipoSolicitante;


}
