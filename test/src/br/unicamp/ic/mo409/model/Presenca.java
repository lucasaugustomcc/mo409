package br.unicamp.ic.mo409.model;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Presenca {

	public PresencaState state;
	public Integer minTicks;
	public Integer numTicks;
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

	public void checkInPresenca(Integer idTurma, Integer raAluno, Date dataAula, Time horaInicio) {
		state = PresencaState.em_branco;  ///??
	}

	public void checkOutPresenca(Integer idTurma, Integer raAluno, Date dataAula, Time horaInicio) {
		state = PresencaState.em_aula;    ///??
	}
	
////////
	
	public void handleEvent(Object... in_colObject) {
		if (in_colObject.length > 0) 
		{
			String sEventName = (String) in_colObject[0];
			if ((state == PresencaState.em_branco)
					&& (sEventName.compareTo("checkInPresencaEvent") == 0)) 
			{
				Integer tempIDTurma    = (Integer) in_colObject[1];
				Integer idTurma;
				Integer tempRAAluno    = (Integer) in_colObject[2];
				Integer raAluno;
				Integer tempDataAula   = (Integer) in_colObject[3];
				Date    dataAula;
				Integer tempHoraInicio = (Integer) in_colObject[4];
				Time    horaInicio;
				
				if (tempIDTurma > 0)
				{
				    idTurma = 1;
				}
				else
				{
					idTurma = 2;
				}
				if (tempRAAluno > 0)
				{
				    raAluno = 1;
				}
				else
				{
					raAluno = 2;
				}
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
				checkInPresenca(idTurma, raAluno, dataAula, horaInicio);
			}
			
			if ((state == PresencaState.em_aula)
					&& (sEventName.compareTo("checkOutPresencaEvent") == 0)) 
			{
				Integer tempIDTurma    = (Integer) in_colObject[1];
				Integer idTurma;
				Integer tempRAAluno    = (Integer) in_colObject[2];
				Integer raAluno;
				Integer tempDataAula   = (Integer) in_colObject[3];
				Date    dataAula;
				Integer tempHoraInicio = (Integer) in_colObject[4];
				Time    horaInicio;
				
				if (tempIDTurma > 0)
				{
				    idTurma = 1;
				}
				else
				{
					idTurma = 2;
				}
				if (tempRAAluno > 0)
				{
				    raAluno = 1;
				}
				else
				{
					raAluno = 2;
				}
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
				checkOutPresenca(idTurma, raAluno, dataAula, horaInicio);
			}
			
			if ((state == PresencaState.em_branco) 
					&& sEventName.compareTo("calcularPresencaEvent") == 0 ) 
			{
				Integer tempIDTurma    = (Integer) in_colObject[1];
				Integer idTurma;
				Integer tempRAAluno    = (Integer) in_colObject[2];
				Integer raAluno;
				Integer tempDataAula   = (Integer) in_colObject[3];
				Date    dataAula;
				Integer tempHoraInicio = (Integer) in_colObject[4];
				Time    horaInicio;
				
				if (tempIDTurma > 0)
				{
				    idTurma = 1;
				}
				else
				{
					idTurma = 2;
				}
				if (tempRAAluno > 0)
				{
				    raAluno = 1;
				}
				else
				{
					raAluno = 2;
				}
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
				calcularPresenca(idTurma, raAluno, dataAula, horaInicio, numTicks);
			}
			
			if ((state == PresencaState.em_aula) 
					&& (sEventName.compareTo("calcularPresencaEvent") == 0))
			{
				Integer tempIDTurma    = (Integer) in_colObject[1];
				Integer idTurma;
				Integer tempRAAluno    = (Integer) in_colObject[2];
				Integer raAluno;
				Integer tempDataAula   = (Integer) in_colObject[3];
				Date    dataAula;
				Integer tempHoraInicio = (Integer) in_colObject[4];
				Time    horaInicio;
				
				if (tempIDTurma > 0)
				{
				    idTurma = 1;
				}
				else
				{
					idTurma = 2;
				}
				if (tempRAAluno > 0)
				{
				    raAluno = 1;
				}
				else
				{
					raAluno = 2;
				}
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
				calcularPresenca(idTurma, raAluno, dataAula, horaInicio, numTicks);
			}
			
			if ((state == PresencaState.fora_de_aula)
					&& (sEventName.compareTo("calcularPresencaEvent") == 0)) 
			{
				Integer tempIDTurma    = (Integer) in_colObject[1];
				Integer idTurma;
				Integer tempRAAluno    = (Integer) in_colObject[2];
				Integer raAluno;
				Integer tempDataAula   = (Integer) in_colObject[3];
				Date    dataAula;
				Integer tempHoraInicio = (Integer) in_colObject[4];
				Time    horaInicio;
				
				if (tempIDTurma > 0)
				{
				    idTurma = 1;
				}
				else
				{
					idTurma = 2;
				}
				if (tempRAAluno > 0)
				{
				    raAluno = 1;
				}
				else
				{
					raAluno = 2;
				}
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
				calcularPresenca(idTurma, raAluno, dataAula, horaInicio, numTicks);
			}
			
			if ((state == PresencaState.calculando) 
					&& (sEventName.compareTo("calcularPresencaEvent") == 0)) 
			{
				Integer tempIDTurma    = (Integer) in_colObject[1];
				Integer idTurma;
				Integer tempRAAluno    = (Integer) in_colObject[2];
				Integer raAluno;
				Integer tempDataAula   = (Integer) in_colObject[3];
				Date    dataAula;
				Integer tempHoraInicio = (Integer) in_colObject[4];
				Time    horaInicio;
				
				if (tempIDTurma > 0)
				{
				    idTurma = 1;
				}
				else
				{
					idTurma = 2;
				}
				if (tempRAAluno > 0)
				{
				    raAluno = 1;
				}
				else
				{
					raAluno = 2;
				}
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
				calcularPresenca(idTurma, raAluno, dataAula, horaInicio, numTicks);
			}
			
//          Se estiver no estado "calculando", independentemente de acao de disparo, vai para:
//			"presente" se numTicks >= minTicks   ou
//			"ausente"  caso contrario
//          Deixei o esqueleto de codigo logo a seguir apenas para caso seja necessario 
//			tratar aqui estas condicoes.
//			
//			if ((state == PresencaState.calculando) 
//				&& (????))			
//		    {
//		    	
//		    }

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
