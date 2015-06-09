package br.unicamp.ic.mo409.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the tb_chamada database table.
 * 
 */
@Entity
@Table(name = "tb_chamada")
@NamedQuery(name = "Chamada.findAll", query = "SELECT a FROM Chamada a")
public class Chamada implements Serializable {
	private static final long serialVersionUID = 1L;

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
	}

	public boolean abrirChamada() {
		return false;
	}

	public boolean encerrarChamada() {
		return false;
	}

	public Tick receberTick() {
		return null;
	}
	
	public List<Presenca> calcularPresencas() {
		return null;
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