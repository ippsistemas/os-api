package br.com.codiub.ordemservicos.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
@Table(name = "EXECUCOES")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Execucoes implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

@GenericGenerator(name = "SEQ_EXECUCOES", strategy = 
"org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = @Parameter(name = 
"sequence", value = "SEQ_EXECUCOES"))

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
