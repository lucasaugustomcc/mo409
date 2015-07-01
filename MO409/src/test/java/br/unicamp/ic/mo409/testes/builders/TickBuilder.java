package br.unicamp.ic.mo409.testes.builders;

import java.util.Date;

import br.unicamp.ic.mo409.model.Aluno;
import br.unicamp.ic.mo409.model.Chamada;
import br.unicamp.ic.mo409.model.Tick;

@SuppressWarnings("deprecation")
public class TickBuilder implements Builder<Tick>{
	
	private int idTick = 1;
	private Date dataHora = new Date(115, 10, 10, 10,0,0);
	private Chamada chamada;
	private Aluno aluno;
	private int distancia = 0;

	public TickBuilder() {}     
    
	public TickBuilder withChamada(Chamada chamada) {
		this.chamada = chamada;
		return this;
	}
	
	public TickBuilder withIdTick(int idTick) {
		this.idTick = idTick;
		return this;
	}

    public TickBuilder withDistancia(int distancia)
	{
		this.distancia = distancia;
		return this;
	}
    
    public TickBuilder withAluno(Aluno aluno)
	{
		this.aluno = aluno;
		return this;
	}

	public Tick build() {
    	Tick tick = new Tick();    	    
    	tick.setDataHora(dataHora);
    	tick.setIdTick(idTick);
    	tick.setChamada(chamada);
    	tick.setDistancia(distancia);
    	tick.setAluno(aluno);
        return tick;
    }


}
