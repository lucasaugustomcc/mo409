package br.unicamp.ic.mo409.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


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
	private byte periodo;

	//bi-directional many-to-many association to Aluno
	@ManyToMany
	@JoinTable(
		name="tb_aluno_turma"
		, joinColumns={
			@JoinColumn(name="tb_turma_id_turma", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="tb_aluno_ra_aluno", nullable=false)
			}
		)
	private List<Aluno> tbAlunos;

	//bi-directional many-to-one association to Disciplina
	@ManyToOne(cascade={CascadeType.REMOVE})
	@JoinColumn(name="tb_disciplina_id_disciplina", nullable=false)
	private Disciplina tbDisciplina;

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

	public byte getPeriodo() {
		return this.periodo;
	}

	public void setPeriodo(byte periodo) {
		this.periodo = periodo;
	}

	public List<Aluno> getTbAlunos() {
		return this.tbAlunos;
	}

	public void setTbAlunos(List<Aluno> tbAlunos) {
		this.tbAlunos = tbAlunos;
	}

	public Disciplina getTbDisciplina() {
		return this.tbDisciplina;
	}

	public void setTbDisciplina(Disciplina tbDisciplina) {
		this.tbDisciplina = tbDisciplina;
	}

}