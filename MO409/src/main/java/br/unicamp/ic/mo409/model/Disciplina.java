package br.unicamp.ic.mo409.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the DISCIPLINA database table.
 * 
 */
@Entity
@Table(name="DISCIPLINA")
@NamedQuery(name="Disciplina.findAll", query="SELECT d FROM Disciplina d")
public class Disciplina implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int idDISCIPLINA;

	//bi-directional many-to-many association to Professor
	@ManyToMany
	@JoinTable(
		name="PROFESSOR_has_DISCIPLINA"
		, joinColumns={
			@JoinColumn(name="DISCIPLINA_idDISCIPLINA", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="PROFESSOR_ra", nullable=false)
			}
		)
	private List<Professor> professors;

	//bi-directional many-to-one association to Turma
	@OneToMany(mappedBy="disciplina")
	private List<Turma> turmas;

	public Disciplina() {
	}

	public int getIdDISCIPLINA() {
		return this.idDISCIPLINA;
	}

	public void setIdDISCIPLINA(int idDISCIPLINA) {
		this.idDISCIPLINA = idDISCIPLINA;
	}

	public List<Professor> getProfessors() {
		return this.professors;
	}

	public void setProfessors(List<Professor> professors) {
		this.professors = professors;
	}

	public List<Turma> getTurmas() {
		return this.turmas;
	}

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}

	public Turma addTurma(Turma turma) {
		getTurmas().add(turma);
		turma.setDisciplina(this);

		return turma;
	}

	public Turma removeTurma(Turma turma) {
		getTurmas().remove(turma);
		turma.setDisciplina(null);

		return turma;
	}

}