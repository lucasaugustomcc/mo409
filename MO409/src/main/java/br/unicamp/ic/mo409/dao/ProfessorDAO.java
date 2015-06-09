package br.unicamp.ic.mo409.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.unicamp.ic.mo409.model.Professor;
import br.unicamp.ic.mo409.model.Turma;

@Repository("ProfessorDAO")
public class ProfessorDAO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3922406143939849621L;
	
	@PersistenceContext(unitName = "persistenceUnit")
	protected EntityManager entityManager;

	public Professor find(Integer id) 
	{
		return entityManager.find(Professor.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Turma> listTurmasByProfessor(Professor professor) 
	{		
		return entityManager.createQuery("SELECT t FROM Turma t join t.professores p WHERE t.periodo = 1 AND p.raProfessor = :RaProfessor")
				.setParameter("RaProfessor", professor.getRaProfessor())
				.getResultList();
	}
}
