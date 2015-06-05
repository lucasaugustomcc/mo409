package br.unicamp.ic.mo409.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.unicamp.ic.mo409.model.Disciplina;

@Repository("DisciplinaDAO")
public class DisciplinaDAO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3922406143939849621L;
	
	@PersistenceContext(unitName = "persistenceUnit")
	protected EntityManager entityManager;

	public Disciplina find(Integer id) 
	{
		return entityManager.find(Disciplina.class, id);
	}

	@Transactional
	public void persist(Disciplina Disciplina) {
		entityManager.persist(Disciplina);
		entityManager.flush();
	}

	public void merge(Disciplina Disciplina) 
	{
		entityManager.merge(Disciplina);
	}

	@Transactional
	public void remove(Disciplina Disciplina) {
		entityManager.remove(Disciplina);
	}

	@SuppressWarnings("unchecked")
	public List<Disciplina> findAll() {		
		return entityManager.createQuery("SELECT c FROM Disciplina c")
				.getResultList();
	}

}
