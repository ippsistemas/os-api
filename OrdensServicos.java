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
@Table(name = "ORDENS_SERVICOS")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class OrdensServicos implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

@GenericGenerator(name = "SEQ_ORDENS_SERVICOS", strategy = 
"org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = @Parameter(name = 
"sequence", value = "SEQ_ORDENS_SERVICOS"))

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
