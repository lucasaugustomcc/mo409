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

import br.unicamp.ic.mo409.model.Usuario;

@Repository("UsuarioDAO")
public class UsuarioDAO implements UserDetailsService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3922406143939849621L;
	
	@PersistenceContext(unitName = "persistenceUnit")
	protected EntityManager entityManager;

	public boolean existeUsuario(Usuario usuario) {
		return entityManager.find(Usuario.class, usuario.getIdUsuario()) != null;
	}

	public boolean isUsuarioValido(String usuario, String password) {
	    Query query = entityManager
	            .createQuery("SELECT u FROM Usuario u left join u.professor p left join u.aluno a WHERE (p.raProfessor=:usuarionameparam OR a.raAluno=:usuarionameparam) AND u.senha=:passwordparam");
	    query.setParameter("usuarionameparam", Integer.valueOf(usuario));
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