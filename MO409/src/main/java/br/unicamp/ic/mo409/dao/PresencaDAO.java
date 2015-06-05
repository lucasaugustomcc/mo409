package br.unicamp.ic.mo409.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.unicamp.ic.mo409.model.Presenca;

@Repository("PresencaDAO")
public class PresencaDAO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3922406143939849621L;
	
	@PersistenceContext(unitName = "persistenceUnit")
	protected EntityManager entityManager;

	public Presenca find(Integer id) 
	{
		return entityManager.find(Presenca.class, id);
	}

	@Transactional
	public void persist(Presenca Presenca) {
		entityManager.persist(Presenca);
		entityManager.flush();
	}

	public void merge(Presenca Presenca) 
	{
		entityManager.merge(Presenca);
	}

	@Transactional
	public void remove(Presenca Presenca) {
		entityManager.remove(Presenca);
	}

	@SuppressWarnings("unchecked")
	public List<Presenca> findAll() {		
		return entityManager.createQuery("SELECT c FROM Presenca c")
				.getResultList();
	}
}
