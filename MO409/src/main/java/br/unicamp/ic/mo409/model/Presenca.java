package br.unicamp.ic.mo409.model;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the tb_presenca database table.
 * 
 */
@Entity
@Table(name = "tb_presenca")
@NamedQuery(name = "Presenca.findAll", query = "SELECT p FROM Presenca p")
public class Presenca implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_presenca", unique = true, nullable = false)
	private int idPresenca;

	@Column(name = "is_presente")
	private boolean isPresente;

	@Enumerated(EnumType.STRING)
	@Column(name = "state", nullable = false, length = 30)
	PresencaState state;

	@Column(name = "num_ticks", nullable = false)
	private int numTicks;

	// bi-directional many-to-one association to Aluno
	@ManyToOne
	@JoinColumn(name = "tb_aluno_ra_aluno", nullable = false)
	private Aluno aluno;

	// bi-directional many-to-one association to Aula
	@ManyToOne
	@JoinColumn(name = "tb_chamada_id_chamada", nullable = false)
	private Chamada chamada;

	@Column(name = "hora_fim")
	private Time horaFim;

	@Column(name = "hora_inicio")
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
				Integer tempLatitude = (Integer) in_colObject[1];
				float latitude;
				Integer tempLongitude = (Integer) in_colObject[2];
				float longitude;
				
				if (tempLatitude > 0)
				{
					latitude = -20.100232232f;					
				}
				else
				{
					latitude = 10.100232232f;	
				}
				
				if (tempLongitude > 0)
				{
					longitude = -20.100232232f;					
				}
				else
				{
					longitude = 10.100232232f;	
				}
				receberTick(latitude, longitude);
			}

			if ((state == PresencaState.em_aula && chamada.state == ChamadaState.aberta)
					&& (sEventName.compareTo("checkOutPresencaEvent") == 0))
			{
				checkOutPresenca();
			}

			chamada.setState(ChamadaState.encerrada);
			if (state == PresencaState.em_branco 
					&& chamada.state == ChamadaState.encerrada
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
				calcularPresenca(numMinTicks);
			}

			if ((state == PresencaState.em_aula || state == PresencaState.fora_de_aula)
					&& chamada.state == ChamadaState.encerrada
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
				calcularPresenca(numTicks);
			}
			
			if ((state == PresencaState.ausente || state == PresencaState.presente)
					&& chamada.state == ChamadaState.encerrada
					&& sEventName.compareTo("visualizarPresencaEvent") == 0)
			{
				visualizarPresenca();
			}	
		}
	}

	private PresencaState visualizarPresenca()
	{
		if (chamada.getState() == ChamadaState.encerrada && 
				(state == PresencaState.presente || state == PresencaState.ausente))
		{
			return state;
		}
		else
		{
			throw new IllegalStateException("Estado não permite solicitar o cálculo da presença.");
		}
		
	}
	
	public void calcularPresenca(Integer numTicksMinino)
	{
		if (chamada.getState() == ChamadaState.encerrada && 
				(state == PresencaState.em_branco || state == PresencaState.em_aula || state == PresencaState.fora_de_aula))
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
		else
		{
			throw new IllegalStateException("Estado não permite solicitar o cálculo da presença.");
		}
	}
	
	private float calcularDistancia(float latitudeAluno, float longitudeAluno)
	{
		double d2r = (180 / Math.PI);
		float distance = 0;

		try{
		    double dlong = (Float.valueOf(chamada.getLongitude()) - longitudeAluno) * d2r;
		    double dlat = (Float.valueOf(chamada.getLatitude()) - latitudeAluno) * d2r;
		    double a =
		        Math.pow(Math.sin(dlat / 2.0), 2)
		            + Math.cos(Float.valueOf(chamada.getLatitude()) * d2r)
		            * Math.cos(latitudeAluno * d2r)
		            * Math.pow(Math.sin(dlong / 2.0), 2);
		    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		    distance = (float) (6367 * c);

		    return distance;

		} catch(Exception e){
		    e.printStackTrace();
		}
		return distance;
	}

	/**
	 * Recebe um tick do aluno
	 * @param longitude 
	 * @param latitude 
	 * 
	 * @return objeto tick populado
	 */
	public Tick receberTick(float latitude, float longitude) throws IllegalStateException
	{
		if (state == PresencaState.em_aula
				&& chamada.getState() == ChamadaState.aberta)
		{
			float distancia = calcularDistancia(latitude, longitude);
			Tick tick = new Tick();
			tick.setAluno(this.getAluno());
			tick.setDistancia(Math.round(distancia));
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
		if (state == PresencaState.em_aula
				&& chamada.getState() == ChamadaState.aberta)
		{
			setHoraFim(new Time(System.currentTimeMillis()));
			state = PresencaState.fora_de_aula;
		}
		else
		{
			throw new IllegalStateException(
					"Você não está na aula ou aula já encerrada.");
		}
	}

	/**
	 * Altera o estado da presença e define o horário de início da presença.
	 */
	public void checkInPresenca()
	{
		if (state == PresencaState.em_branco 
				&& chamada.getState() == ChamadaState.aberta)
		{
			setHoraInicio(new Time(System.currentTimeMillis()));
			state = PresencaState.em_aula;
		} else
		{
			throw new IllegalStateException("Chamada não aberta ou aluno já fez checkin!");
		}
	}

}