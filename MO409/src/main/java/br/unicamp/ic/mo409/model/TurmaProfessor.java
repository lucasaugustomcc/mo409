package br.unicamp.ic.mo409.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tb_turma_professor database table.
 * 
 */
@Entity
@Table(name="tb_turma_professor")
@NamedQuery(name="TurmaProfessor.findAll", query="SELECT t FROM TurmaProfessor t")
public class TurmaProfessor implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TurmaProfessorPK id;

	public TurmaProfessor() {
	}

	public TurmaProfessorPK getId() {
		return this.id;
	}

	public void setId(TurmaProfessorPK id) {
		this.id = id;
	}

}