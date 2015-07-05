package br.unicamp.ic.mo409.testes.builders;

import java.sql.Time;

import br.unicamp.ic.mo409.model.Aluno;
import br.unicamp.ic.mo409.model.Chamada;
import br.unicamp.ic.mo409.model.ChamadaState;
import br.unicamp.ic.mo409.model.Presenca;
import br.unicamp.ic.mo409.model.PresencaState;
import br.unicamp.ic.mo409.model.Turma;

@SuppressWarnings("deprecation")
public class PresencaBuilder implements Builder<Presenca>{
	
	private Time horaFim = new Time(12,0,0);
	private int idPresenca = 1;
	private Time horaInicio = new Time(10,0,0);
	private Chamada chamada = new ChamadaBuilder()
									.withChamadaState(ChamadaState.aberta)
									.withTurma(
											new TurmaBuilder().build()
									)
									.build();
	private boolean isPresente = false;
	private int numTicks = 0;
	private PresencaState presencaState = PresencaState.em_branco;
	private Aluno aluno;

	public PresencaBuilder() {}

    public PresencaBuilder withHoraFim(Time horaFim) {
        this.horaFim = horaFim;
        return this;
    }
    
    public PresencaBuilder withNumTicks(Integer numTicks) {
        this.numTicks = numTicks;
        return this;
    }
    
    public PresencaBuilder withHoraInicio(Time horaInicio) {
        this.horaInicio = horaInicio;
        return this;
    }

    public PresencaBuilder withIsPresente(Boolean isPresente) {
        this.isPresente = isPresente;
        return this;
    } 
    
	public PresencaBuilder withChamada(Chamada chamada) {
		this.chamada = chamada;
		return this;
	}
	
	public PresencaBuilder withIdPresenca(int idPresenca) {
		this.idPresenca = idPresenca;
		return this;
	}

    public PresencaBuilder withPresencaState(PresencaState presencaState)
	{
		this.presencaState = presencaState;
		return this;
	}
    
    public PresencaBuilder withAluno(Aluno aluno)
	{
		this.aluno = aluno;
		return this;
	}

	public Presenca build() {
    	Presenca presenca = new Presenca();    	    
    	presenca.setHoraFim(horaFim);
    	presenca.setIdPresenca(idPresenca);
    	presenca.setHoraInicio(horaInicio);
    	presenca.setChamada(chamada);
    	presenca.setIsPresente(isPresente);
    	presenca.setNumTicks(numTicks);
    	presenca.setState(presencaState);
    	presenca.setAluno(aluno);
        return presenca;
    }


}
