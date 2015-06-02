package br.unicamp.ic.mo409.model;

import java.io.Serializable;

import javax.persistence.*;


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

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="tb_usuario_id_usuario", nullable=false, insertable=false, updatable=false)
	private Usuario tbUsuario;

	public Professor() {
	}

	public Usuario getTbUsuario() {
		return this.tbUsuario;
	}

	public void setTbUsuario(Usuario tbUsuario) {
		this.tbUsuario = tbUsuario;
	}

}