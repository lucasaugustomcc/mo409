package br.unicamp.ic.mo409.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.unicamp.ic.mo409.model.Tick;

@Repository("TickDAO")
public class TickDAO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3922406143939849621L;
	
	@PersistenceContext(unitName = "persistenceUnit")
	protected EntityManager entityManager;

	public Tick find(Integer id) 
	{
		return entityManager.find(Tick.class, id);
	}

	@Transactional
	public void persist(Tick tick) {
		entityManager.persist(tick);
		entityManager.flush();
	}

	public void merge(Tick tick) 
	{
		entityManager.merge(tick);
	}

	@Transactional
	public void remove(Tick tick) {
		entityManager.remove(tick);
	}

	@SuppressWarnings("unchecked")
	public List<Tick> findAll() {		
		return entityManager.createQuery("SELECT c FROM Tick c")
				.getResultList();
	}
}
