package br.unicamp.ic.mo409.model;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Presenca {

	public PresencaState state;
	public Integer minTicks;
	public Date hora_inicio;
	public Date hora_fim;
	public Date data_aula;

	public Presenca()
	{
		state = PresencaState.calculando;
	}

	public void calcularPresenca(Integer idTurma, Integer raAluno, Date dataAula, Time horaInicio, Integer numTicks) {
		if (numTicks >= minTicks)
		{
			state = PresencaState.presente;
		}
		else
		{
			state = PresencaState.ausente;
		}
	}

	public void encerrarPresenca(Time horaFim) {
		state = ChamadaState.encerrada;
	}

////////
	
	public void handleEvent(Object... in_colObject) {
		if (in_colObject.length > 0) 
		{
			String sEventName = (String) in_colObject[0];
			if ((state == ChamadaState.nao_aberta)
					&& (sEventName.compareTo("abrirChamadaEvent") == 0)) 
			{
				Integer tempIDTurma     = (Integer) in_colObject[1];
				Integer idTurma;
				Integer tempRAProfessor = (Integer) in_colObject[2];
				Integer raProfessor;
				Integer tempDataAula    = (Integer) in_colObject[3];
				Date    dataAula;
				Integer tempHoraInicio = (Integer) in_colObject[4];
				Time    horaInicio;
				
				if (tempDataAula > 0)
				{
				    dataAula = new Date(2015,6,1);
				}
				else
				{
					dataAula = new Date(2010,5,1);
				}
				if (tempHoraInicio > 0)
				{
				    horaInicio = new Time(10,0,0);
				}
				else
				{
					horaInicio = new Time(16,0,0);
				}
				if (tempIDTurma > 0)
				{
				    idTurma = 1;
				}
				else
				{
					idTurma = 2;
				}
				if (tempRAProfessor > 0)
				{
				    raProfessor = 1;
				}
				else
				{
					raProfessor = 2;
				}
				abrirChamada(idTurma, raProfessor, dataAula, horaInicio);
			}
			
			if (sEventName.compareTo("encerrarChamadaEvent") == 0 && state == ChamadaState.aberta) 
			{
				Integer tempHoraFim = (Integer) in_colObject[1];
				Time    horaFim;
				
				if (tempHoraFim > 0)
				{
				    horaFim = new Time(10,0,0);
				}
				else
				{
					horaFim = new Time(16,0,0);
				}
				encerrarChamada(horaFim);
			}
			
			if (sEventName.compareTo("calcularPresencaEvent") == 0 && state == ChamadaState.encerrada) 
			{
				Integer tempListaPresencas = (Integer) in_colObject[1];
				List<Presenca>    listaPresencas = new ArrayList<Presenca>();;
				
				if (tempListaPresencas > 0)
				{
					listaPresencas.add(new Presenca());
				}
				else
				{
					listaPresencas.add(new Presenca());
				}
				calcularPresenca(listaPresencas);
			}
		}
	}

	public void setNumTicks(Integer numTicks) {
		this.numTicks = numTicks;
	}

	public Integer getNumTicks() {
		return numTicks;
	}

	public void setHoraInicio(Date hora_inicio) {
		this.hora_inicio = hora_inicio;
	}

	public Date getHoraInicio() {
		return hora_inicio;
	}

	public void setHoraFim(Date hora_fim) {
		this.hora_fim = hora_fim;
	}

	public Date getHoraFim() {
		return hora_fim;
	}
	
	
	
}
