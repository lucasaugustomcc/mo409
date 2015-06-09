package br.unicamp.ic.mo409.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.unicamp.ic.mo409.model.Usuario;

@Repository("UsuarioDAO")
public class UsuarioDAO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3922406143939849621L;
	
	@PersistenceContext(unitName = "persistenceUnit")
	protected EntityManager entityManager;

	public Usuario find(Integer id) 
	{
		return entityManager.find(Usuario.class, id);
	}

	@Transactional
	public void persist(Usuario usuario) {
		entityManager.persist(usuario);
		entityManager.flush();
	}

	public void merge(Usuario Usuario) 
	{
		entityManager.merge(Usuario);
	}

	@Transactional
	public void remove(Usuario Usuario) {
		entityManager.remove(Usuario);
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> findAll() {		
		return entityManager.createQuery("SELECT c FROM Usuario c")
				.getResultList();
	}

	public boolean existeUsuario(Usuario usuario) {
		return entityManager.find(Usuario.class, usuario.getIdUsuario()) != null;
	}

	public boolean isUsuarioValido(String usuario, String password) {
	    Query query = entityManager
	            .createQuery("SELECT u FROM Usuario u WHERE u.nome=:usuarionameparam AND u.senha=:passwordparam");
	    query.setParameter("usuarionameparam", usuario);
	    query.setParameter("passwordparam", password);

	    try 
	    {
	    	query.getSingleResult();
	    	return true;
	    }
	    catch (NoResultException e)
    	{
    		return false;
    	}	    
	}
}
