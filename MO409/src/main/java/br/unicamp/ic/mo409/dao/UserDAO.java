package br.unicamp.ic.mo409.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.unicamp.ic.mo409.model.User;

@Repository("UserDAO")
public class UserDAO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3922406143939849621L;
	
	@PersistenceContext(unitName = "persistenceUnit")
	protected EntityManager entityManager;

	public User find(Integer id) 
	{
		return entityManager.find(User.class, id);
	}

	@Transactional
	public void persist(User user) {
		entityManager.persist(user);
		entityManager.flush();
	}

	public void merge(User User) 
	{
		entityManager.merge(User);
	}

	@Transactional
	public void remove(User User) {
		entityManager.remove(User);
	}

	@SuppressWarnings("unchecked")
	public List<User> findAll() {		
		return entityManager.createQuery("SELECT c FROM User c")
				.getResultList();
	}

	public boolean existeUsuario(User usuario) {
		return entityManager.find(User.class, usuario.get_Id()) != null;
	}

	public boolean isValidUser(String username, String password) {
		// 1-Send query to database to see if that user exist
	    Query query = entityManager
	            .createQuery("SELECT u FROM User u WHERE u.username=:usernameparam AND u.password=:passwordparam");
	    query.setParameter("usernameparam", username);
	    query.setParameter("passwordparam", password);

	    try 
	    {
	    	User user = (User) query.getSingleResult();
	    	return true;
	    }
	    catch (NoResultException e)
    	{
    		return false;
    	}	    
	}
}
