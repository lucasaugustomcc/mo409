package br.unicamp.ic.mo409.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the PRESENCA database table.
 * 
 */
@Entity
@Table(name="PRESENCA")
@NamedQuery(name="Presenca.findAll", query="SELECT p FROM Presenca p")
public class Presenca implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PresencaPK id;

	private byte aprovado;

	@Column(name="numero_ticks")
	private int numeroTicks;

	//bi-directional many-to-one association to Aluno
	@ManyToOne
	@JoinColumn(name="ALUNO_ra", nullable=false, insertable=false, updatable=false)
	private Aluno aluno;

	//bi-directional many-to-one association to Tick
	@OneToMany(mappedBy="presenca")
	private List<Tick> ticks;

	public Presenca() {
	}

	public PresencaPK getId() {
		return this.id;
	}

	public void setId(PresencaPK id) {
		this.id = id;
	}

	public byte getAprovado() {
		return this.aprovado;
	}

	public void setAprovado(byte aprovado) {
		this.aprovado = aprovado;
	}

	public int getNumeroTicks() {
		return this.numeroTicks;
	}

	public void setNumeroTicks(int numeroTicks) {
		this.numeroTicks = numeroTicks;
	}

	public Aluno getAluno() {
		return this.aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public List<Tick> getTicks() {
		return this.ticks;
	}

	public void setTicks(List<Tick> ticks) {
		this.ticks = ticks;
	}

	public Tick addTick(Tick tick) {
		getTicks().add(tick);
		tick.setPresenca(this);

		return tick;
	}

	public Tick removeTick(Tick tick) {
		getTicks().remove(tick);
		tick.setPresenca(null);

		return tick;
	}

}