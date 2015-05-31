package br.unicamp.ic.mo409.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the PRESENCA database table.
 * 
 */
@Embeddable
public class ALUNO_has_TURMAPK implements Serializable{
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable = false, updatable = false, unique = true, nullable = false)
	private int ALUNO_ra;

	public ALUNO_has_TURMAPK() {
	}
	
	@Column(unique=true, nullable=false)
	private int TURMA_idTURMA;
	
	public int getTURMA_IdTURMA() {
		return this.TURMA_idTURMA;
	}
	public void setTURMA_IdTURMA(int idTURMA) {
		this.TURMA_idTURMA = idTURMA;
	}

	public int getALUNO_ra() {
		return this.ALUNO_ra;
	}

	public void setALUNO_ra(int ALUNO_ra) {
		this.ALUNO_ra = ALUNO_ra;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ALUNO_has_TURMAPK)) {
			return false;
		}
		ALUNO_has_TURMAPK castOther = (ALUNO_has_TURMAPK) other;
		return (this.TURMA_idTURMA == castOther.TURMA_idTURMA)
				&& (this.ALUNO_ra == castOther.ALUNO_ra);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.TURMA_idTURMA;
		hash = hash * prime + this.ALUNO_ra;

		return hash;
	}
}
