package br.com.codiub.ordemservicos.model.input;

import javax.validation.constraints.NotNull;
import br.com.codiub.ordemservicos.model.entity.Execucoes;
import lombok.Data;

@Data
public class ExecucoesInput {

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

    
   

    public Execucoes inputToEntity() {
        Execucoes execucoes = new Execucoes();

execucoes.setId(id);
execucoes.setTipoExecucao(tipoExecucao);
execucoes.setOsServico(osServico);
execucoes.setSituacaoExecucao(situacaoExecucao);
execucoes.setTurno(turno);
execucoes.setSetorExecucao(setorExecucao);
execucoes.setDataExecucao(dataExecucao);
execucoes.setHoraioInicio(horaioInicio);
execucoes.setHoraioSaida(horaioSaida);
execucoes.setHoraioTermino(horaioTermino);
execucoes.setObservacao(observacao);
execucoes.setDataHora(dataHora);
execucoes.setLeitura(leitura);
execucoes.setMotivo(motivo);
execucoes.setTipoCorte(tipoCorte);


        return execucoes;

    }
    

    public Execucoes mergeInputToEntity(Execucoes execucoes) {

        if (!nome.equals(execucoes.getNome())) {
            execucoes.setNome(nome);
        }

        if (!tipoExecucoes.toUpperCase().equals(execucoes.getTipoExecucoes().name())) {
            execucoes.setTipoExecucoes(TipoExecucoesEnum.valueOf
(tipoExecucoes.toUpperCase()));
        }

        return execucoes;
     }

}
