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
@Table(name = "SOLICITANTES")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Solicitantes implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

@GenericGenerator(name = "SEQ_SOLICITANTES", strategy = 
"org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = @Parameter(name = 
"sequence", value = "SEQ_SOLICITANTES"))

private Long id;
private String nome;
private String cpfCnpj;
private Long solicitanteId;
private String celular;
private String telefone;
private Long relacaoProprietario;
private String tipoSolicitante;


	
    
}
