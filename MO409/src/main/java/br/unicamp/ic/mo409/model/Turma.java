package br.unicamp.ic.mo409.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TURMA database table.
 * 
 */
@Entity
@Table(name="TURMA")
@NamedQuery(name="Turma.findAll", query="SELECT t FROM Turma t")
public class Turma implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TurmaPK id;

	@Column(nullable=false, length=45)
	private String nome;

	//bi-directional many-to-one association to Disciplina
	@ManyToOne
	@JoinColumn(name="DISCIPLINA_idDISCIPLINA", nullable=false, insertable=false, updatable=false)
	private Disciplina disciplina;

	public Turma() {
	}

	public TurmaPK getId() {
		return this.id;
	}

	public void setId(TurmaPK id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Disciplina getDisciplina() {
		return this.disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

}