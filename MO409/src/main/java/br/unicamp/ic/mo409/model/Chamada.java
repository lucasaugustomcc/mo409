package br.unicamp.ic.mo409.model;

import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the tb_chamada database table.
 * 
 */
@Entity
@Table(name = "tb_chamada")
public class Chamada implements Serializable {
	private static final long serialVersionUID = 1L;
	
	ChamadaState state;
	private static final Integer numTicksMinino = 20; 
	private static final Integer tempoTicks = 5; 
	
	@PersistenceContext(unitName = "persistenceUnit")
	protected EntityManager entityManager;
	
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
	@OneToMany(mappedBy = "chamada")
	private List<Presenca> presencas;

	// bi-directional many-to-one association to Tick
	@OneToMany(mappedBy = "chamada")
	private List<Tick> ticks;

	public Chamada() {
		state = ChamadaState.nao_aberta;
	}
	
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
				Integer tempDataChamada    = (Integer) in_colObject[3];
				Date    dataChamada;
				Integer tempHoraInicio = (Integer) in_colObject[4];
				Time    horaInicio;
				
				if (tempDataChamada > 0)
				{
				    dataChamada = new Date(2015,6,1);
				}
				else
				{
					dataChamada = new Date(2010,5,1);
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
				Turma turma = entityManager.getReference(Turma.class, idTurma);
				Professor professor = entityManager.getReference(Professor.class, raProfessor);
				abrirChamada(turma, professor, dataChamada, horaInicio);
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

	public void abrirChamada(Turma turma, Professor professor, Date dataChamada, Time horaInicio) {		
		this.setProfessor(professor);
		this.setTurma(turma);
		this.setDataChamada(dataChamada);
		this.setHoraInicio(horaInicio);
		state = ChamadaState.aberta;
	}

	public void encerrarChamada(Time horaFim) {
		this.setHoraFim(horaFim);
		state = ChamadaState.encerrada;
	}
	
	public void calcularPresenca(List<Presenca> presencas) {
		for (Presenca presenca : presencas)
		{
			presenca.calcularPresenca(numTicksMinino);
		}
		state = ChamadaState.encerrada;		
	}

	public int getIdChamada() {
		return this.idChamada;
	}

	public void setIdChamada(int idChamada) {
		this.idChamada = idChamada;
	}

	public Date getDataChamada() {
		return this.dataChamada;
	}

	public void setDataChamada(Date dataChamada) {
		this.dataChamada = dataChamada;
	}

	public Time getHoraFim() {
		return this.horaFim;
	}

	public void setHoraFim(Time horaFim) {
		this.horaFim = horaFim;
	}

	public Time getHoraInicio() {
		return this.horaInicio;
	}

	public void setHoraInicio(Time horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Professor getProfessor() {
		return this.professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Turma getTurma() {
		return this.turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public List<Presenca> getPresencas() {
		return this.presencas;
	}

	public void setPresencas(List<Presenca> presencas) {
		this.presencas = presencas;
	}

	public Presenca addPresenca(Presenca presenca) {
		getPresencas().add(presenca);
		presenca.setChamada(this);

		return presenca;
	}

	public Presenca removePresenca(Presenca presenca) {
		getPresencas().remove(presenca);
		presenca.setChamada(null);

		return presenca;
	}

	public List<Tick> getTicks() {
		return this.ticks;
	}

	public void setTicks(List<Tick> ticks) {
		this.ticks = ticks;
	}

	public Tick addTick(Tick tick) {
		getTicks().add(tick);
		tick.setChamada(this);

		return tick;
	}

	public Tick removeTick(Tick tick) {
		getTicks().remove(tick);
		tick.setChamada(null);

		return tick;
	}

}