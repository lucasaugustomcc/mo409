package br.unicamp.ic.mo409.testes.builders;

import java.sql.Time;
import java.util.Date;

import br.unicamp.ic.mo409.model.Chamada;
import br.unicamp.ic.mo409.model.ChamadaState;
import br.unicamp.ic.mo409.model.Parametro;
import br.unicamp.ic.mo409.model.Professor;
import br.unicamp.ic.mo409.model.Turma;

@SuppressWarnings("deprecation")
public class ChamadaBuilder implements Builder<Chamada>{
	
	private Date dataChamada = new Date(115,6,5);
	private Time horaFim = new Time(12,0,0);
	private Professor professor;
	private Turma turma = new TurmaBuilder().build();
	private int idChamada = 1;
	private Time horaInicio = new Time(10,0,0);
	private ChamadaState chamadaState = ChamadaState.nao_aberta;
	private Parametro parametro = new Parametro(50, 50);
	private String latitude = "0";
	private String longitude = "0";

	public ChamadaBuilder() {}

    public ChamadaBuilder withHoraFim(Time horaFim) {
        this.horaFim = horaFim;
        return this;
    }
    
    public ChamadaBuilder withDataChamada(Date dataChamada) {
        this.dataChamada = dataChamada;
        return this;
    }
    
    public ChamadaBuilder withParametro(Parametro parametro) {
        this.parametro = parametro;
        return this;
    }
    
    public ChamadaBuilder withLatitude(float latitude) {
        this.latitude = String.valueOf(latitude);
        return this;
    }
    
    public ChamadaBuilder withLongitude(float longitude) {
        this.longitude = String.valueOf(longitude);
        return this;
    }
    
    public ChamadaBuilder withParametro(Integer duracao, float porcentagem) {
        this.parametro = new Parametro();
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
	
	public ChamadaBuilder withChamadaState(ChamadaState chamadaState) {
		this.chamadaState = chamadaState;
		return this;
	}

    public Chamada build() {
    	Chamada chamada = new Chamada(turma, professor);    	    
    	chamada.setDataChamada(dataChamada);
    	chamada.setHoraFim(horaFim);
    	chamada.setParametro(parametro);
    	chamada.setIdChamada(idChamada);
    	chamada.setHoraInicio(horaInicio);
    	chamada.setState(chamadaState);
    	chamada.setLatitude(latitude);
    	chamada.setLongitude(longitude);
        return chamada;
    }


}
