package br.unicamp.ic.mo409.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tb_presenca database table.
 * 
 */
@Entity
@Table(name="tb_presenca")
@NamedQuery(name="Presenca.findAll", query="SELECT p FROM Presenca p")
public class Presenca implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_presenca", unique=true, nullable=false)
	private int idPresenca;

	@Column(name="is_presente")
	private boolean isPresente;

	@Column(name="num_ticks", nullable=false)
	private int numTicks;

	//bi-directional many-to-one association to Aluno
	@ManyToOne
	@JoinColumn(name="tb_aluno_ra_aluno", nullable=false)
	private Aluno aluno;

	//bi-directional many-to-one association to Aula
	@ManyToOne
	@JoinColumn(name="tb_aula_id_aula", nullable=false)
	private Chamada chamada;

	public Presenca() {
	}

	public int getIdPresenca() {
		return this.idPresenca;
	}

	public void setIdPresenca(int idPresenca) {
		this.idPresenca = idPresenca;
	}

	public boolean getIsPresente() {
		return this.isPresente;
	}

	public void setIsPresente(boolean isPresente) {
		this.isPresente = isPresente;
	}

	public int getNumTicks() {
		return this.numTicks;
	}

	public void setNumTicks(int numTicks) {
		this.numTicks = numTicks;
	}

	public Aluno getAluno() {
		return this.aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Chamada getChamada() {
		return this.chamada;
	}

	public void setChamada(Chamada chamada) {
		this.chamada = chamada;
	}

}