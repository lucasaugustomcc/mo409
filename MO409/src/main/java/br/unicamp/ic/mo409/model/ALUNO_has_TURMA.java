package br.unicamp.ic.mo409.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the ALUNO_has_TURMA database table.
 * 
 */
@Entity
@Table(name="ALUNO_has_TURMA")
@NamedQuery(name="ALUNO_has_TURMA.findAll", query="SELECT a FROM ALUNO_has_TURMA a")
public class ALUNO_has_TURMA implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ALUNO_has_TURMAPK id;

	public ALUNO_has_TURMA() {
	}

	public ALUNO_has_TURMAPK getId() {
		return this.id;
	}

	public void setId(ALUNO_has_TURMAPK id) {
		this.id = id;
	}

}