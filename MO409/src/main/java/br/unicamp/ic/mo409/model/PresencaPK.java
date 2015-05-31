package br.unicamp.ic.mo409.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the PRESENCA database table.
 * 
 */
@Embeddable
public class PresencaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(unique=true, nullable=false)
	private int idPRESENCA;

	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private int ALUNO_ra;

	public PresencaPK() {
	}
	public int getIdPRESENCA() {
		return this.idPRESENCA;
	}
	public void setIdPRESENCA(int idPRESENCA) {
		this.idPRESENCA = idPRESENCA;
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
		if (!(other instanceof PresencaPK)) {
			return false;
		}
		PresencaPK castOther = (PresencaPK)other;
		return 
			(this.idPRESENCA == castOther.idPRESENCA)
			&& (this.ALUNO_ra == castOther.ALUNO_ra);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idPRESENCA;
		hash = hash * prime + this.ALUNO_ra;
		
		return hash;
	}
}