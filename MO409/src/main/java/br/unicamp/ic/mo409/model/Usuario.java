package br.unicamp.ic.mo409.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tb_usuario database table.
 * 
 */
@Entity
@Table(name="tb_usuario")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_usuario", unique=true, nullable=false)
	private int idUsuario;

	@Column(length=45)
	private String email;

	@Column(nullable=false, length=40)
	private String nome;

	@Column(nullable=false, length=20)
	private String senha;

	//bi-directional many-to-one association to Aluno
	@OneToMany(mappedBy="tbUsuario")
	private List<Aluno> tbAlunos;

	//bi-directional many-to-one association to Professor
	@OneToMany(mappedBy="tbUsuario")
	private List<Professor> tbProfessors;

	public Usuario() {
	}

	public int getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Aluno> getTbAlunos() {
		return this.tbAlunos;
	}

	public void setTbAlunos(List<Aluno> tbAlunos) {
		this.tbAlunos = tbAlunos;
	}

	public Aluno addTbAluno(Aluno tbAluno) {
		getTbAlunos().add(tbAluno);
		tbAluno.setTbUsuario(this);

		return tbAluno;
	}

	public Aluno removeTbAluno(Aluno tbAluno) {
		getTbAlunos().remove(tbAluno);
		tbAluno.setTbUsuario(null);

		return tbAluno;
	}

	public List<Professor> getTbProfessors() {
		return this.tbProfessors;
	}

	public void setTbProfessors(List<Professor> tbProfessors) {
		this.tbProfessors = tbProfessors;
	}

	public Professor addTbProfessor(Professor tbProfessor) {
		getTbProfessors().add(tbProfessor);
		tbProfessor.setTbUsuario(this);

		return tbProfessor;
	}

	public Professor removeTbProfessor(Professor tbProfessor) {
		getTbProfessors().remove(tbProfessor);
		tbProfessor.setTbUsuario(null);

		return tbProfessor;
	}

}