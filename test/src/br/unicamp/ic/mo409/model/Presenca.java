package br.unicamp.ic.mo409.model;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;



/**
 * The persistent class for the tb_presenca database table.
 * 
 */

public class Presenca implements Serializable
{
	private static final long serialVersionUID = 1L;


	private int idPresenca;


	private boolean isPresente;


	PresencaState state;

	private int numTicks;

	// bi-directional many-to-one association to Aluno
	private Aluno aluno;

	// bi-directional many-to-one association to Aula
	private Chamada chamada;

	private Time horaFim;

	private Time horaInicio;

	public Presenca()
	{
		state = PresencaState.em_branco;
	}

	public int getIdPresenca()
	{
		return this.idPresenca;
	}

	public void setIdPresenca(int idPresenca)
	{
		this.idPresenca = idPresenca;
	}

	public boolean getIsPresente()
	{
		return this.isPresente;
	}

	public void setIsPresente(boolean isPresente)
	{
		this.isPresente = isPresente;
	}

	public int getNumTicks()
	{
		return this.numTicks;
	}

	public void setNumTicks(int numTicks)
	{
		this.numTicks = numTicks;
	}

	public Aluno getAluno()
	{
		return this.aluno;
	}

	public void setAluno(Aluno aluno)
	{
		this.aluno = aluno;
	}

	public Chamada getChamada()
	{
		return this.chamada;
	}

	public void setChamada(Chamada chamada)
	{
		this.chamada = chamada;
	}

	public Time getHoraFim()
	{
		return horaFim;
	}

	public void setHoraFim(Time horaFim)
	{
		this.horaFim = horaFim;
	}

	public Time getHoraInicio()
	{
		return horaInicio;
	}

	public void setHoraInicio(Time horaInicio)
	{
		this.horaInicio = horaInicio;
	}

	public PresencaState getState()
	{
		return state;
	}

	public void setState(PresencaState state)
	{
		this.state = state;
	}

	public void calcularPresenca(Integer numTicksMinino)
	{
		state = PresencaState.calculando;

		if (numTicks >= numTicksMinino)
		{
			state = PresencaState.presente;
		} else
		{
			state = PresencaState.ausente;
		}
	}

	public void handleEvent(Object... in_colObject)
	{
		if (in_colObject.length > 0)
		{
			Chamada chamada = new Chamada();
			setChamada(chamada);
			chamada.setState(ChamadaState.aberta);
			
			String sEventName = (String) in_colObject[0];
			if ((state == PresencaState.em_branco && chamada.state == ChamadaState.aberta)
					&& (sEventName.compareTo("checkInPresencaEvent") == 0))
			{
				checkInPresenca();
			}

			if ((state == PresencaState.em_aula && chamada.state == ChamadaState.aberta)
					&& (sEventName.compareTo("receberTickEvent") == 0))
			{
				Integer tempNumTicks = (Integer) in_colObject[1];
				Integer numMinTicks;

				if (tempNumTicks > 0)
				{
					numMinTicks = 1;
				} else
				{
					numMinTicks = 2;
				}
				setNumTicks(5);
				receberTick();
			}

			if ((state == PresencaState.em_aula 
			    && chamada.state == ChamadaState.aberta)
					&& (sEventName.compareTo("checkOutPresencaEvent") == 0))
			{
				checkOutPresenca();
			}

			chamada.setState(ChamadaState.encerrada);
			if ((state == PresencaState.em_branco && chamada.state == ChamadaState.encerrada)
					&& sEventName.compareTo("calcularPresencaEvent") == 0)
			{
				Integer tempNumTicks = (Integer) in_colObject[1];
				Integer numMinTicks;

				if (tempNumTicks > 0)
				{
					numMinTicks = 1;
				} else
				{
					numMinTicks = 2;
				}
				setNumTicks(tempNumTicks);
				calcularPresenca(numMinTicks);
			}

			if ((state == PresencaState.em_aula && chamada.state == ChamadaState.encerrada)
					&& (sEventName.compareTo("calcularPresencaEvent") == 0))
			{
				Integer tempNumTicks = (Integer) in_colObject[1];
				Integer numMinTicks;

				if (tempNumTicks > 0)
				{
					numMinTicks = 1;
				} else
				{
					numMinTicks = 2;
				}
				setNumTicks(5);
				calcularPresenca(numMinTicks);
			}
			
			
			if ((state == PresencaState.fora_de_aula && chamada.state == ChamadaState.encerrada)
					&& (sEventName.compareTo("calcularPresencaEvent") == 0))
			{
				Integer tempNumTicks = (Integer) in_colObject[1];
				Integer numTicks;

				if (tempNumTicks > 0)
				{
					numTicks = 1;
				} else
				{
					numTicks = 2;
				}
				setNumTicks(5);
				calcularPresenca(numTicks);
			}

			if ((state == PresencaState.calculando && chamada.state == ChamadaState.encerrada)
					&& (sEventName.compareTo("calcularPresencaEvent") == 0))
			{
				Integer tempNumTicks = (Integer) in_colObject[1];
				Integer numTicks;

				if (tempNumTicks > 0)
				{
					numTicks = 1;
				} else
				{
					numTicks = 2;
				}
				setNumTicks(5);
				calcularPresenca(numTicks);
			}
		}
	}

	/**
	 * Recebe um tick do aluno
	 * @return objeto tick populado
	 */
	public Tick receberTick() throws IllegalStateException
	{
		if (state == PresencaState.em_aula
				&& chamada.getState() == ChamadaState.aberta)
		{
			Tick tick = new Tick();
			tick.setAluno(this.getAluno());
			tick.setChamada(chamada);
			tick.setDataHora(new Date(System.currentTimeMillis()));
			setNumTicks(getNumTicks() + 1);
			return tick;
		} else
		{
			throw new IllegalStateException(
					"Chamada já fechada ou você não está na aula.");
		}
	}

	/**
	 * Altera o estado da presença e define o horário final da presença.
	 */
	public void checkOutPresenca()
	{
		if (state == PresencaState.em_aula)
		{
			setHoraFim(new Time(System.currentTimeMillis()));
			state = PresencaState.fora_de_aula;
		}
	}

	/**
	 * Altera o estado da presença e define o horário de início da presença.
	 */
	public void checkInPresenca()
	{
		if (chamada.state == ChamadaState.aberta)
		{
			setHoraInicio(new Time(System.currentTimeMillis()));
			state = PresencaState.em_aula;
		} else
		{
			throw new IllegalStateException("Chamada não aberta!");
		}
	}

}