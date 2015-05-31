package br.unicamp.ic.mo409.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the TICKS database table.
 * 
 */
@Embeddable
public class TickPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(unique=true, nullable=false)
	private int idTICKS;

	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private int ALUNO_ra;

	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private int PRESENCA_idPRESENCA;

	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private int PRESENCA_ALUNO_ra;

	public TickPK() {
	}
	public int getIdTICKS() {
		return this.idTICKS;
	}
	public void setIdTICKS(int idTICKS) {
		this.idTICKS = idTICKS;
	}
	public int getALUNO_ra() {
		return this.ALUNO_ra;
	}
	public void setALUNO_ra(int ALUNO_ra) {
		this.ALUNO_ra = ALUNO_ra;
	}
	public int getPRESENCA_idPRESENCA() {
		return this.PRESENCA_idPRESENCA;
	}
	public void setPRESENCA_idPRESENCA(int PRESENCA_idPRESENCA) {
		this.PRESENCA_idPRESENCA = PRESENCA_idPRESENCA;
	}
	public int getPRESENCA_ALUNO_ra() {
		return this.PRESENCA_ALUNO_ra;
	}
	public void setPRESENCA_ALUNO_ra(int PRESENCA_ALUNO_ra) {
		this.PRESENCA_ALUNO_ra = PRESENCA_ALUNO_ra;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TickPK)) {
			return false;
		}
		TickPK castOther = (TickPK)other;
		return 
			(this.idTICKS == castOther.idTICKS)
			&& (this.ALUNO_ra == castOther.ALUNO_ra)
			&& (this.PRESENCA_idPRESENCA == castOther.PRESENCA_idPRESENCA)
			&& (this.PRESENCA_ALUNO_ra == castOther.PRESENCA_ALUNO_ra);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idTICKS;
		hash = hash * prime + this.ALUNO_ra;
		hash = hash * prime + this.PRESENCA_idPRESENCA;
		hash = hash * prime + this.PRESENCA_ALUNO_ra;
		
		return hash;
	}
}