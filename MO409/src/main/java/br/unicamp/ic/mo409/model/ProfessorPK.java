package br.unicamp.ic.mo409.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the tb_professor database table.
 * 
 */
@Embeddable
public class ProfessorPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ra_professor", unique=true, nullable=false)
	private int raProfessor;

	@Column(name="tb_usuario_id_usuario", insertable=false, updatable=false, unique=true, nullable=false)
	private int tbUsuarioIdUsuario;

	public ProfessorPK() {
	}
	public int getRaProfessor() {
		return this.raProfessor;
	}
	public void setRaProfessor(int raProfessor) {
		this.raProfessor = raProfessor;
	}
	public int getTbUsuarioIdUsuario() {
		return this.tbUsuarioIdUsuario;
	}
	public void setTbUsuarioIdUsuario(int tbUsuarioIdUsuario) {
		this.tbUsuarioIdUsuario = tbUsuarioIdUsuario;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ProfessorPK)) {
			return false;
		}
		ProfessorPK castOther = (ProfessorPK)other;
		return 
			(this.raProfessor == castOther.raProfessor)
			&& (this.tbUsuarioIdUsuario == castOther.tbUsuarioIdUsuario);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.raProfessor;
		hash = hash * prime + this.tbUsuarioIdUsuario;
		
		return hash;
	}
}