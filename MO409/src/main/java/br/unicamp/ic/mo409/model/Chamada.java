package br.unicamp.ic.mo409.model;

import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * The persistent class for the tb_chamada database table.
 * 
 */
@Entity
@Table(name = "tb_chamada")
public class Chamada implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Enumerated(EnumType.STRING)
	@Column(name="state", nullable=false, length=30)
	ChamadaState state;

	@Transient
	private static final Integer numTicksMinino = 20;

	@Transient
	private static final Integer tempoTicks = 5;

	@Transient
	private EntityManager entityManager;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_chamada", unique = true, nullable = false)
	private int idChamada;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_chamada")
	private Date dataChamada;

	@Column(name = "hora_fim")
	private Time horaFim;

	@Column(name = "hora_inicio")
	private Time horaInicio;

	// bi-directional many-to-one association to Professor
	@ManyToOne
	@JoinColumn(name = "tb_professor_ra_professor", nullable = false)
	private Professor professor;

	// bi-directional many-to-one association to Turma
	@ManyToOne
	@JoinColumn(name = "tb_turma_id_turma", nullable = false)
	private Turma turma;

	// bi-directional many-to-one association to Presenca
	@OneToMany(mappedBy = "chamada", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	private List<Presenca> presencas;

	// bi-directional many-to-one association to Tick
	@OneToMany(mappedBy = "chamada")
	private List<Tick> ticks;

	private String latitude;

	private String longitude;

	public Chamada()
	{
		state = ChamadaState.nao_aberta;
	}

	public Chamada(EntityManager em)
	{
		state = ChamadaState.nao_aberta;
		this.entityManager = em;
	}

	@SuppressWarnings("deprecation")
	public void handleEvent(Object... in_colObject)
	{
		
		if (in_colObject.length > 0)
		{
			String sEventName = (String) in_colObject[0];
			if ((state == ChamadaState.atribuindo_localizacao)
					&& (sEventName.compareTo("abrirChamadaEvent") == 0))
			{
				Integer tempIDTurma = (Integer) in_colObject[1];
				Integer idTurma;
				Integer tempRAProfessor = (Integer) in_colObject[2];
				Integer raProfessor;
				Integer tempDataChamada = (Integer) in_colObject[3];
				Date dataChamada;
				Integer tempHoraInicio = (Integer) in_colObject[4];
				Time horaInicio;

				if (tempDataChamada > 0)
				{
					dataChamada = new Date(2015, 6, 1);
				} 
				else
				{
					dataChamada = new Date(2010, 5, 1);
				}
				if (tempHoraInicio > 0)
				{
					horaInicio = new Time(10, 0, 0);
				} 
				else
				{
					horaInicio = new Time(16, 0, 0);
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
				Turma turma = entityManager.getReference(Turma.class, idTurma);
				Professor professor = entityManager.getReference(
						Professor.class, raProfessor);
				abrirChamada(turma, professor, dataChamada, horaInicio);
			}
			
			if (sEventName.compareTo("visualizarParametrosEvent") == 0
					&& state == ChamadaState.nao_aberta)
			{				
				visualizarParametros();
			}
			
			if (sEventName.compareTo("atribuirParametrosEvent") == 0
					&& state == ChamadaState.visualizando_parametros)
			{			
				Integer tempPorcentagem = (Integer) in_colObject[1];
				Float porcentagem;
				Integer tempDuracao = (Integer) in_colObject[2];
				Integer duracao;
				
				if (tempPorcentagem > 0)
				{
					porcentagem = 71.544434234234f;
				}
				else
				{
					porcentagem = 50f;
				}
				
				if (tempDuracao > 0)
				{
					duracao = 50;
				}
				else
				{
					duracao = 60;
				}
				
				atribuirParametros(duracao, porcentagem);
			}
			
			if (sEventName.compareTo("atribuirLocalizacaoProfessorEvent") == 0
					&& (state == ChamadaState.nao_aberta 
					|| state == ChamadaState.visualizando_parametros 
					|| state == ChamadaState.atribuindo_parametros))
			{				
				Integer tempLatitude = (Integer) in_colObject[1];
				String latitude;
				Integer tempLongitude = (Integer) in_colObject[2];
				String longitude;
				
				if (tempLatitude > 0)
				{
					latitude = "-20.100232232";					
				}
				else
				{
					latitude = "10.100232232";	
				}
				
				if (tempLongitude > 0)
				{
					longitude = "-20.100232232";					
				}
				else
				{
					longitude = "10.100232232";	
				}
				
				atribuirLocalizacao(latitude, longitude);
			}

			if (sEventName.compareTo("encerrarChamadaEvent") == 0
					&& state == ChamadaState.aberta)
			{
				Integer tempHoraFim = (Integer) in_colObject[1];
				Time horaFim;

				if (tempHoraFim > 0)
				{
					horaFim = new Time(10, 0, 0);
				} else
				{
					horaFim = new Time(16, 0, 0);
				}
				encerrarChamada(horaFim);
			}

			if (sEventName.compareTo("calcularPresencaEvent") == 0
					&& state == ChamadaState.encerrada)
			{
				Integer tempListaPresencas = (Integer) in_colObject[1];
				List<Presenca> listaPresencas = new ArrayList<Presenca>();

				if (tempListaPresencas > 0)
				{
					listaPresencas.add(new Presenca());
				} else
				{
					listaPresencas.add(new Presenca());
				}
				calcularPresenca();
			}
		}
	}

	private void atribuirParametros(Integer duracao, Float porcentagem)
	{
//		if (state != ChamadaState.visualizando_parametros)
//		{
//			throw new IllegalStateException(
//					"Chamada não disponível para atribuir localização.");
//		}
//		state = ChamadaState.atribuindo_parametros;
		
	}

	private void atribuirLocalizacao(String latitude, String longitude)
	{
		if (state == ChamadaState.aberta 
				&& state == ChamadaState.encerrada)
		{
			throw new IllegalStateException(
					"Chamada não disponível para atribuir localização.");
		}
		state = ChamadaState.atribuindo_localizacao;
		this.setLatitude(latitude);
		this.setLongitude(longitude);
	}

	private void visualizarParametros()
	{
		//state = ChamadaState.visualizando_parametros;
	}

	public List<Presenca> abrirChamada(Turma turma, Professor professor,
			Date dataChamada, Time horaInicio)
	{
		if (state == ChamadaState.atribuindo_localizacao)
		{
			this.setProfessor(professor);
			this.setTurma(turma);
			this.setDataChamada(dataChamada);
			this.setHoraInicio(horaInicio);
			state = ChamadaState.aberta;

			List<Aluno> alunos = getTurma().getAlunos();
			List<Presenca> presencas = new ArrayList<Presenca>();
			for (Aluno aluno : alunos)
			{
				Presenca presenca = new Presenca();
				presenca.setAluno(aluno);
				presenca.setChamada(this);
				presencas.add(presenca);				
			}
			this.setPresencas(presencas);
			return presencas;
		} else
		{
			throw new IllegalStateException(
					"Chamada não disponível para abertura.");
		}
	}

	/**
	 * Encerra a chamada e altera o estado da chamada e de todas as presenças
	 * associadas a esta chamada.
	 * 
	 * @param horaFim
	 *            : horário do fim da chamada
	 */

	public void encerrarChamada(Time horaFim)
	{
		if (state == ChamadaState.aberta)
		{
			this.setHoraFim(horaFim);
			this.setState(ChamadaState.encerrada);

			// marcar alunos como fora da aula
			List<Presenca> presencas = getPresencas();
			for (Presenca presenca : presencas)
			{
				presenca.setState(PresencaState.fora_de_aula);
				if (presenca.getHoraFim() == null)
				{
					presenca.setHoraFim(horaFim);
				}
			}
		} else
		{
			throw new IllegalStateException("Chamada já encerrada");
		}
	}

	/**
	 * Invoca o método para calcular o resultado da presença para cada aluno da turma.
	 */
	public void calcularPresenca()
	{
		if (state == ChamadaState.encerrada)
		{
			for (Presenca presenca : getPresencas())
			{
				presenca.calcularPresenca(numTicksMinino);
			}
		} else
		{
			throw new IllegalStateException("Chamada ainda aberta.");
		}
	}

	public int getIdChamada()
	{
		return this.idChamada;
	}

	public void setIdChamada(int idChamada)
	{
		this.idChamada = idChamada;
	}

	public Date getDataChamada()
	{
		return this.dataChamada;
	}

	public void setDataChamada(Date dataChamada)
	{
		this.dataChamada = dataChamada;
	}

	public Time getHoraFim()
	{
		return this.horaFim;
	}

	public void setHoraFim(Time horaFim)
	{
		this.horaFim = horaFim;
	}

	public Time getHoraInicio()
	{
		return this.horaInicio;
	}

	public void setHoraInicio(Time horaInicio)
	{
		this.horaInicio = horaInicio;
	}

	public Professor getProfessor()
	{
		return this.professor;
	}

	public void setProfessor(Professor professor)
	{
		this.professor = professor;
	}

	public Turma getTurma()
	{
		return this.turma;
	}

	public void setTurma(Turma turma)
	{
		this.turma = turma;
	}

	public List<Presenca> getPresencas()
	{
		return this.presencas;
	}

	public void setPresencas(List<Presenca> presencas)
	{
		this.presencas = presencas;
	}

	public Presenca addPresenca(Presenca presenca)
	{
		getPresencas().add(presenca);
		presenca.setChamada(this);

		return presenca;
	}

	public Presenca removePresenca(Presenca presenca)
	{
		getPresencas().remove(presenca);
		presenca.setChamada(null);

		return presenca;
	}

	public List<Tick> getTicks()
	{
		return this.ticks;
	}

	public void setTicks(List<Tick> ticks)
	{
		this.ticks = ticks;
	}

	public Tick addTick(Tick tick)
	{
		getTicks().add(tick);
		tick.setChamada(this);

		return tick;
	}

	public Tick removeTick(Tick tick)
	{
		getTicks().remove(tick);
		tick.setChamada(null);

		return tick;
	}

	public ChamadaState getState()
	{
		return state;
	}

	public void setState(ChamadaState state)
	{
		this.state = state;
	}

	public String getLongitude()
	{
		return longitude;
	}

	public void setLongitude(String longitude)
	{
		this.longitude = longitude;
	}

	public String getLatitude()
	{
		return latitude;
	}

	public void setLatitude(String latitude)
	{
		this.latitude = latitude;
	}

}