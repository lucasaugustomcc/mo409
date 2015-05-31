package br.unicamp.ic.mo409.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TURMA_has_PROFESSOR database table.
 * 
 */
@Entity
@Table(name="TURMA_has_PROFESSOR")
@NamedQuery(name="TURMA_has_PROFESSOR.findAll", query="SELECT t FROM TURMA_has_PROFESSOR t")
public class TURMA_has_PROFESSOR implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TURMA_has_PROFESSORPK id;

	public TURMA_has_PROFESSOR() {
	}

	public TURMA_has_PROFESSORPK getId() {
		return this.id;
	}

	public void setId(TURMA_has_PROFESSORPK id) {
		this.id = id;
	}

}