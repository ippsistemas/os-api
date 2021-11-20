package br.com.codiub.ordemservicos.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@Data
@Entity
@Table(name = "EQUIPES")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Equipes implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

@GenericGenerator(name = "SEQ_EQUIPES", strategy = 
"org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = @Parameter(name = 
"sequence", value = "SEQ_EQUIPES"))

private Long id;
private String equipe;
private Long subSecao;


	
    
}
