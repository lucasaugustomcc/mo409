package br.unicamp.ic.mo409.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.unicamp.ic.mo409.model.Aluno;

@Repository("AlunoDAO")
public class AlunoDAO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3922406143939849621L;
	
	@PersistenceContext(unitName = "persistenceUnit")
	protected EntityManager entityManager;

	public Aluno find(Integer id) 
	{
		return entityManager.find(Aluno.class, id);
	}
	
	public List<Aluno> listTurmasByAluno(Aluno aluno) 
	{		
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Aluno> findAll() {		
		return entityManager.createQuery("SELECT c FROM Aluno c")
				.getResultList();
	}
}
