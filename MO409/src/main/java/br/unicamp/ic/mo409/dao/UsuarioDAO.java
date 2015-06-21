package br.unicamp.ic.mo409.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.unicamp.ic.mo409.model.Chamada;
import br.unicamp.ic.mo409.model.Usuario;

@Repository("UsuarioDAO")
public class UsuarioDAO implements UserDetailsService {

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
	
	public Usuario loadUsuarioByUsername(String usuario) {
		System.out.println(usuario);
	    Query query = entityManager
	            //.createQuery("SELECT u FROM Usuario u  where p.raprofessor=:usuarionameparam OR a.raAluno=:usuarionameparam");
	    		.createQuery("SELECT u FROM Usuario u  where u.id=:usuarionameparam");
	    query.setParameter("usuarionameparam", Integer.valueOf(usuario));

	    try 
	    {
	    	return (Usuario) query.getSingleResult();
	    }
	    catch (NoResultException e)
    	{
    		throw new UsernameNotFoundException("not found");
    	}	    
	}

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		System.out.println(username);
		return (UserDetails) loadUsuarioByUsername(username);
	}
}