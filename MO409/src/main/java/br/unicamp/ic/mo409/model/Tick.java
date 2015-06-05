package br.unicamp.ic.mo409.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tb_tick database table.
 * 
 */
@Entity
@Table(name="tb_tick")
@NamedQuery(name="Tick.findAll", query="SELECT t FROM Tick t")
public class Tick implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_tick", unique=true, nullable=false)
	private int idTick;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_hora")
	private Date dataHora;

	private int distancia;

	@Column(name="ra_aluno")
	private int raAluno;

	//bi-directional many-to-one association to Aluno
	@ManyToOne
	@JoinColumn(name="tb_aluno_ra_aluno", nullable=false)
	private Aluno aluno;

	//bi-directional many-to-one association to Aula
	@ManyToOne
	@JoinColumn(name="tb_aula_id_aula", nullable=false)
	private Aula aula;

	public Tick() {
	}

	public int getIdTick() {
		return this.idTick;
	}

	public void setIdTick(int idTick) {
		this.idTick = idTick;
	}

	public Date getDataHora() {
		return this.dataHora;
	}

	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}

	public int getDistancia() {
		return this.distancia;
	}

	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}

	public int getRaAluno() {
		return this.raAluno;
	}

	public void setRaAluno(int raAluno) {
		this.raAluno = raAluno;
	}

	public Aluno getAluno() {
		return this.aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Aula getAula() {
		return this.aula;
	}

	public void setAula(Aula aula) {
		this.aula = aula;
	}

}