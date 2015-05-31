package br.unicamp.ic.mo409.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class TURMA_has_PROFESSORPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(unique = true, nullable = false)
	private int PROFESSOR_ra;

	@Column(insertable = false, updatable = false, unique = true, nullable = false)
	private int TURMA_idTURMA;

	public TURMA_has_PROFESSORPK() {
	}

	public int getPROFESSOR_ra() {
		return this.PROFESSOR_ra;
	}

	public void setIdTURMA(int idTURMA) {
		this.PROFESSOR_ra = idTURMA;
	}

	public int getDISCIPLINA_idDISCIPLINA() {
		return this.TURMA_idTURMA;
	}

	public void setDISCIPLINA_idDISCIPLINA(int DISCIPLINA_idDISCIPLINA) {
		this.TURMA_idTURMA = DISCIPLINA_idDISCIPLINA;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TurmaPK)) {
			return false;
		}
		TURMA_has_PROFESSORPK castOther = (TURMA_has_PROFESSORPK) other;
		return (this.PROFESSOR_ra == castOther.PROFESSOR_ra)
				&& (this.TURMA_idTURMA == castOther.TURMA_idTURMA);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.PROFESSOR_ra;
		hash = hash * prime + this.TURMA_idTURMA;

		return hash;
	}
}