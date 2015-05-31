package br.unicamp.ic.mo409.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the TURMA database table.
 * 
 */
@Embeddable
public class TurmaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(unique=true, nullable=false)
	private int idTURMA;

	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private int DISCIPLINA_idDISCIPLINA;

	public TurmaPK() {
	}
	public int getIdTURMA() {
		return this.idTURMA;
	}
	public void setIdTURMA(int idTURMA) {
		this.idTURMA = idTURMA;
	}
	public int getDISCIPLINA_idDISCIPLINA() {
		return this.DISCIPLINA_idDISCIPLINA;
	}
	public void setDISCIPLINA_idDISCIPLINA(int DISCIPLINA_idDISCIPLINA) {
		this.DISCIPLINA_idDISCIPLINA = DISCIPLINA_idDISCIPLINA;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TurmaPK)) {
			return false;
		}
		TurmaPK castOther = (TurmaPK)other;
		return 
			(this.idTURMA == castOther.idTURMA)
			&& (this.DISCIPLINA_idDISCIPLINA == castOther.DISCIPLINA_idDISCIPLINA);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idTURMA;
		hash = hash * prime + this.DISCIPLINA_idDISCIPLINA;
		
		return hash;
	}
}