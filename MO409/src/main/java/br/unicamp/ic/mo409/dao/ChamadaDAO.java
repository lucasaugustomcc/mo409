package br.unicamp.ic.mo409.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.unicamp.ic.mo409.model.Chamada;

@Repository("ChamadaDAO")
public class ChamadaDAO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3922406143939849621L;
	
	@PersistenceContext(unitName = "persistenceUnit")
	protected EntityManager entityManager;

	public Chamada find(Integer id) 
	{
		return entityManager.find(Chamada.class, id);
	}

	@Transactional
	public void persist(Chamada chamada) {
		entityManager.persist(chamada);
		entityManager.flush();
	}

	public void merge(Chamada chamada) 
	{
		entityManager.merge(chamada);
	}

	@Transactional
	public void remove(Chamada chamada) {
		entityManager.remove(chamada);
	}

	@SuppressWarnings("unchecked")
	public List<Chamada> findAll() {		
		return entityManager.createQuery("SELECT c FROM Aula c")
				.getResultList();
	}
}
