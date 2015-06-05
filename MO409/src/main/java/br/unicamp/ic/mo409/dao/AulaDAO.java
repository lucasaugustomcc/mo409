package br.unicamp.ic.mo409.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.unicamp.ic.mo409.model.Aula;

@Repository("AulaDAO")
public class AulaDAO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3922406143939849621L;
	
	@PersistenceContext(unitName = "persistenceUnit")
	protected EntityManager entityManager;

	public Aula find(Integer id) 
	{
		return entityManager.find(Aula.class, id);
	}

	@Transactional
	public void persist(Aula aula) {
		entityManager.persist(aula);
		entityManager.flush();
	}

	public void merge(Aula aula) 
	{
		entityManager.merge(aula);
	}

	@Transactional
	public void remove(Aula aula) {
		entityManager.remove(aula);
	}

	@SuppressWarnings("unchecked")
	public List<Aula> findAll() {		
		return entityManager.createQuery("SELECT c FROM Aula c")
				.getResultList();
	}
}
