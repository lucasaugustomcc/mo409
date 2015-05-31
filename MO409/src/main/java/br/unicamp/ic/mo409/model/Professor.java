package br.unicamp.ic.mo409.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the PROFESSOR database table.
 * 
 */
@Entity
@Table(name="PROFESSOR")
@NamedQuery(name="Professor.findAll", query="SELECT p FROM Professor p")
public class Professor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int ra;

	//bi-directional many-to-many association to Disciplina
	@ManyToMany(mappedBy="professors")
	private List<Disciplina> disciplinas;

	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="professor")
	private List<Usuario> usuarios;

	public Professor() {
	}

	public int getRa() {
		return this.ra;
	}

	public void setRa(int ra) {
		this.ra = ra;
	}

	public List<Disciplina> getDisciplinas() {
		return this.disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario addUsuario(Usuario usuario) {
		getUsuarios().add(usuario);
		usuario.setProfessor(this);

		return usuario;
	}

	public Usuario removeUsuario(Usuario usuario) {
		getUsuarios().remove(usuario);
		usuario.setProfessor(null);

		return usuario;
	}

}