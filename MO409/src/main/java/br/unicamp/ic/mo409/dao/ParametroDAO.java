package br.unicamp.ic.mo409.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.unicamp.ic.mo409.model.Parametro;

@Repository("ParametroDAO")
public class ParametroDAO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3922406143939849621L;
	
	@PersistenceContext(unitName = "persistenceUnit")
	protected EntityManager entityManager;

	public Parametro find(Integer id) 
	{
		return entityManager.find(Parametro.class, id);
	}

	@Transactional
	public void persist(Parametro Parametro) {
		entityManager.persist(Parametro);
		entityManager.flush();
	}

	public void merge(Parametro Parametro) 
	{
		entityManager.merge(Parametro);
	}

	@Transactional
	public void remove(Parametro Parametro) {
		entityManager.remove(Parametro);
	}

	@SuppressWarnings("unchecked")
	public List<Parametro> findAll() {		
		return entityManager.createQuery("SELECT c FROM Parametro c")
				.getResultList();
	}

}
