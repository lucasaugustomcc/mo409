package br.unicamp.ic.mo409.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the TICKS database table.
 * 
 */
@Entity
@Table(name="TICKS")
@NamedQuery(name="Tick.findAll", query="SELECT t FROM Tick t")
public class Tick implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TickPK id;

	@Column(nullable=false)
	private int distancia;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date hora;

	//bi-directional many-to-one association to Aluno
	@ManyToOne
	@JoinColumn(name="ALUNO_ra", nullable=false, insertable=false, updatable=false)
	private Aluno aluno;

	//bi-directional many-to-one association to Presenca
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="PRESENCA_ALUNO_ra", referencedColumnName="ALUNO_ra", nullable=false, insertable=false, updatable=false),
		@JoinColumn(name="PRESENCA_idPRESENCA", referencedColumnName="idPRESENCA", nullable=false, insertable=false, updatable=false)
		})
	private Presenca presenca;

	public Tick() {
	}

	public TickPK getId() {
		return this.id;
	}

	public void setId(TickPK id) {
		this.id = id;
	}

	public int getDistancia() {
		return this.distancia;
	}

	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}

	public Date getHora() {
		return this.hora;
	}

	public void setHora(Date hora) {
		this.hora = hora;
	}

	public Aluno getAluno() {
		return this.aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Presenca getPresenca() {
		return this.presenca;
	}

	public void setPresenca(Presenca presenca) {
		this.presenca = presenca;
	}

}