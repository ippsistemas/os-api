package br.com.codiub.ordemservicos.model.filter;

import lombok.Data;

@Data
public class ExecucoesFilter {

private Long id;
private String tipoExecucao;
private Long osServico;
private Long situacaoExecucao;
private String turno;
private String setorExecucao;
private String dataExecucao;
private String horaioInicio;
private String horaioSaida;
private String horaioTermino;
private String observacao;
private String dataHora;
private String leitura;
private String motivo;
private Long tipoCorte;


}
