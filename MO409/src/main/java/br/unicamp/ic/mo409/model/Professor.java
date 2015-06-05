package br.unicamp.ic.mo409.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the tb_professor database table.
 * 
 */
@Entity
@Table(name="tb_professor")
@NamedQuery(name="Professor.findAll", query="SELECT p FROM Professor p")
public class Professor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ra_professor", unique=true, nullable=false)
	private int raProfessor;

	//bi-directional many-to-one association to Aula
	@OneToMany(mappedBy="professor")
	private List<Aula> aulas;

	//bi-directional many-to-many association to Turma
	@ManyToMany
	@JoinTable(
		name="tb_turma_professor"
		, joinColumns={
			@JoinColumn(name="tb_professor_ra_professor", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="tb_turma_id_turma", nullable=false)
			}
		)
	private List<Turma> turmas;

	//bi-directional one-to-one association to Usuario
	@OneToOne
	@JoinColumn(name="tb_usuario_id_usuario", nullable=false)
	private Usuario usuario;

	public Professor() {
	}

	public int getRaProfessor() {
		return this.raProfessor;
	}

	public void setRaProfessor(int raProfessor) {
		this.raProfessor = raProfessor;
	}

	public List<Aula> getAulas() {
		return this.aulas;
	}

	public void setAulas(List<Aula> aulas) {
		this.aulas = aulas;
	}

	public Aula addAula(Aula aula) {
		getAulas().add(aula);
		aula.setProfessor(this);

		return aula;
	}

	public Aula removeAula(Aula aula) {
		getAulas().remove(aula);
		aula.setProfessor(null);

		return aula;
	}

	public List<Turma> getTurmas() {
		return this.turmas;
	}

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}