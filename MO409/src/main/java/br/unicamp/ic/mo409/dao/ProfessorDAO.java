package br.unicamp.ic.mo409.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.unicamp.ic.mo409.model.Professor;

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

	@Transactional
	public void persist(Professor professor) {
		entityManager.persist(professor);
		entityManager.flush();
	}

	public void merge(Professor professor) 
	{
		entityManager.merge(professor);
	}

	@Transactional
	public void remove(Professor professor) {
		entityManager.remove(professor);
	}

	@SuppressWarnings("unchecked")
	public List<Professor> findAll() {		
		return entityManager.createQuery("SELECT c FROM Professor c")
				.getResultList();
	}
}
