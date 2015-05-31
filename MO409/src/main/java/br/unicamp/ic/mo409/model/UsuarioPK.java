package br.unicamp.ic.mo409.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the USUARIO database table.
 * 
 */
@Embeddable
public class UsuarioPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(unique=true, nullable=false)
	private int idUSUARIO;

	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private int ALUNO_ra;

	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private int PROFESSOR_ra;

	public UsuarioPK() {
	}
	public int getIdUSUARIO() {
		return this.idUSUARIO;
	}
	public void setIdUSUARIO(int idUSUARIO) {
		this.idUSUARIO = idUSUARIO;
	}
	public int getALUNO_ra() {
		return this.ALUNO_ra;
	}
	public void setALUNO_ra(int ALUNO_ra) {
		this.ALUNO_ra = ALUNO_ra;
	}
	public int getPROFESSOR_ra() {
		return this.PROFESSOR_ra;
	}
	public void setPROFESSOR_ra(int PROFESSOR_ra) {
		this.PROFESSOR_ra = PROFESSOR_ra;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof UsuarioPK)) {
			return false;
		}
		UsuarioPK castOther = (UsuarioPK)other;
		return 
			(this.idUSUARIO == castOther.idUSUARIO)
			&& (this.ALUNO_ra == castOther.ALUNO_ra)
			&& (this.PROFESSOR_ra == castOther.PROFESSOR_ra);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idUSUARIO;
		hash = hash * prime + this.ALUNO_ra;
		hash = hash * prime + this.PROFESSOR_ra;
		
		return hash;
	}
}