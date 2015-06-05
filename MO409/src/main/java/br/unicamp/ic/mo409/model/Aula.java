package br.unicamp.ic.mo409.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the tb_aula database table.
 * 
 */
@Entity
@Table(name="tb_aula")
@NamedQuery(name="Aula.findAll", query="SELECT a FROM Aula a")
public class Aula implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_aula", unique=true, nullable=false)
	private int idAula;

	@Temporal(TemporalType.DATE)
	@Column(name="data_aula")
	private Date dataAula;

	@Column(name="hora_fim")
	private Time horaFim;

	@Column(name="hora_inicio")
	private Time horaInicio;

	//bi-directional many-to-one association to Professor
	@ManyToOne
	@JoinColumn(name="tb_professor_ra_professor", nullable=false)
	private Professor professor;

	//bi-directional many-to-one association to Turma
	@ManyToOne
	@JoinColumn(name="tb_turma_id_turma", nullable=false)
	private Turma turma;

	//bi-directional many-to-one association to Presenca
	@OneToMany(mappedBy="aula")
	private List<Presenca> presencas;

	//bi-directional many-to-one association to Tick
	@OneToMany(mappedBy="aula")
	private List<Tick> ticks;

	public Aula() {
	}

	public int getIdAula() {
		return this.idAula;
	}

	public void setIdAula(int idAula) {
		this.idAula = idAula;
	}

	public Date getDataAula() {
		return this.dataAula;
	}

	public void setDataAula(Date dataAula) {
		this.dataAula = dataAula;
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
		presenca.setAula(this);

		return presenca;
	}

	public Presenca removePresenca(Presenca presenca) {
		getPresencas().remove(presenca);
		presenca.setAula(null);

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
		tick.setAula(this);

		return tick;
	}

	public Tick removeTick(Tick tick) {
		getTicks().remove(tick);
		tick.setAula(null);

		return tick;
	}

}