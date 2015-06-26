package br.unicamp.ic.mo409.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.unicamp.ic.mo409.model.Turma;

@Repository("TurmaDAO")
public class TurmaDAO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3922406143939849621L;
	
	@PersistenceContext(unitName = "persistenceUnit")
	protected EntityManager entityManager;

	public Turma find(Integer id) 
	{
		return entityManager.find(Turma.class, id);
	}

	@Transactional
	public void persist(Turma turma) {
		entityManager.persist(turma);
		entityManager.flush();
	}

	public void merge(Turma turma) 
	{
		entityManager.merge(turma);
	}

	@SuppressWarnings("unchecked")
	public List<Turma> listarTurmasProfessor(Integer raProfessor) 
	{		
		return entityManager.createQuery("SELECT t FROM Turma t join t.professores p WHERE t.periodo = 1 AND p.raProfessor = :RaProfessor")
				.setParameter("RaProfessor", raProfessor)
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Turma> findAll() {		
		return entityManager.createQuery("SELECT c FROM Turma c")
				.getResultList();
	}
}
