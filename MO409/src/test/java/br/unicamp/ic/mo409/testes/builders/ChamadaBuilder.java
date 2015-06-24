package br.unicamp.ic.mo409.testes.builders;

import java.sql.Time;
import java.util.Date;

import br.unicamp.ic.mo409.model.Chamada;
import br.unicamp.ic.mo409.model.Professor;
import br.unicamp.ic.mo409.model.Turma;

@SuppressWarnings("deprecation")
public class ChamadaBuilder implements Builder<Chamada>{
	
	private Date dataChamada = new Date(2015,6,5);
	private Time horaFim = new Time(22,0,0);
	private Professor professor;
	private Turma turma;
	private int idChamada = 1;
	private Time horaInicio = new Time(20,0,0);

	public ChamadaBuilder() {}

    public ChamadaBuilder withHoraFim(Time horaFim) {
        this.horaFim = horaFim;
        return this;
    }
    
    public ChamadaBuilder withDataChamada(Date dataChamada) {
        this.dataChamada = dataChamada;
        return this;
    }
    
    public ChamadaBuilder withHoraInicio(Time horaInicio) {
        this.horaInicio = horaInicio;
        return this;
    }

    public ChamadaBuilder withProfessor(Professor professor) {
        this.professor = professor;
        return this;
    } 
    
	public ChamadaBuilder withTurma(Turma turma) {
		this.turma = turma;
		return this;
	}
	
	public ChamadaBuilder withIdChamada(int idChamada) {
		this.idChamada = idChamada;
		return this;
	}

    public Chamada build() {
    	Chamada chamada = new Chamada();    	    
    	chamada.setDataChamada(dataChamada);
    	chamada.setHoraFim(horaFim);
    	chamada.setProfessor(professor);
    	chamada.setTurma(turma);
    	chamada.setIdChamada(idChamada);
    	chamada.setHoraInicio(horaInicio);
        return chamada;
    }


}
