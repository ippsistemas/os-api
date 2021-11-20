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
@Table(name = "EQUIPE_FUNCIONARIO")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class EquipeFuncionario implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

@GenericGenerator(name = "SEQ_EQUIPE_FUNCIONARIO", strategy = 
"org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = @Parameter(name = 
"sequence", value = "SEQ_EQUIPE_FUNCIONARIO"))

private Long id;
private Long equipe;
private Long empresa;
private Long matricula;
private String ocupacao;


	
    
}
