package br.unicamp.ic.mo409.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


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
	
	@Column(nullable=false, length=10)
	private String papel;

	//bi-directional one-to-one association to Aluno
	@OneToOne(mappedBy="usuario")
	private Aluno aluno;

	//bi-directional one-to-one association to Professor
	@OneToOne(mappedBy="usuario")
	private Professor professor;

	public Usuario() {
	}

	public int getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	public Integer getLogin() {
		if (aluno != null)
			return aluno.getRaAluno();
		return professor.getRaProfessor();		
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

	public Aluno getAluno() {
		return this.aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Professor getProfessor() {
		return this.professor;
	}

	public void setProfessores(Professor professor) {
		this.professor = professor;
	}

	public String getPapel() {
		return papel;
	}

	public void setPapel(String papel) {
		this.papel = papel;
	}

	public String[] getRoles() {
		String roles[] = papel.split(",");
		return roles;
	}
}