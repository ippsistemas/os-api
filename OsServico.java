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
@Table(name = "OS_SERVICO")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class OsServico implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

@GenericGenerator(name = "SEQ_OS_SERVICO", strategy = 
"org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = @Parameter(name = 
"sequence", value = "SEQ_OS_SERVICO"))

private Long id;
private Long ordemServico;
private Long servico;
private Long equipe;
private String observacao;


	
    
}
