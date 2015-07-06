package br.unicamp.ic.mo409.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * The persistent class for the tb_turma database table.
 * 
 */
@Entity
@Table(name="tb_turma")
@NamedQuery(name="Turma.findAll", query="SELECT t FROM Turma t")
public class Turma implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_turma", unique=true, nullable=false)
	private int idTurma;

	@Column(nullable=false)
	private int ano;

	@Column(name="cod_turma", length=2)
	private String codTurma;

	@Column(nullable=false)
	private int periodo;

	//bi-directional many-to-one association to Aula
	@OneToMany(mappedBy="turma")
	private List<Chamada> chamadas;

	//bi-directional many-to-many association to Professor
	@ManyToMany(mappedBy="turmas", fetch = FetchType.EAGER)
	private List<Professor> professores;

	//bi-directional many-to-one association to Disciplina
	@ManyToOne(cascade={CascadeType.REMOVE}, fetch = FetchType.EAGER)
	@JoinColumn(name="tb_disciplina_id_disciplina", nullable=false)
	private Disciplina disciplina;

	//bi-directional many-to-many association to Aluno
	@ManyToMany(cascade={CascadeType.REMOVE}, fetch = FetchType.EAGER)
	@JoinTable(
		name="tb_turma_aluno"
		, joinColumns={
			@JoinColumn(name="tb_turma_id_turma", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="tb_aluno_ra_aluno", nullable=false)
			}
		)
	private List<Aluno> alunos;
	
	@OneToOne(optional=true, fetch = FetchType.EAGER)
	@JoinColumn(name="tb_parametro_id_parametro")
	private Parametro parametro;

	public Turma() {
	}

	public int getIdTurma() {
		return this.idTurma;
	}

	public void setIdTurma(int idTurma) {
		this.idTurma = idTurma;
	}

	public int getAno() {
		return this.ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public String getCodTurma() {
		return this.codTurma;
	}

	public void setCodTurma(String codTurma) {
		this.codTurma = codTurma;
	}

	public int getPeriodo() {
		return this.periodo;
	}

	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}

	public List<Chamada> getChamadas() {
		return this.chamadas;
	}

	public void setChamadas(List<Chamada> chamadas) {
		this.chamadas = chamadas;
	}

	public Chamada addChamada(Chamada aula) {
		getChamadas().add(aula);
		aula.setTurma(this);

		return aula;
	}

	public Chamada removeAula(Chamada aula) {
		getChamadas().remove(aula);
		aula.setTurma(null);

		return aula;
	}

	public List<Professor> getProfessores() {
		return this.professores;
	}

	public void setProfessores(List<Professor> professores) {
		this.professores = professores;
	}

	public Disciplina getDisciplina() {
		return this.disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public List<Aluno> getAlunos() {
		return this.alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	public Parametro getParametro()
	{
		return parametro;
	}

	public void setParametro(Parametro parametro)
	{
		this.parametro = parametro;
	}

}