package br.unicamp.ic.mo409.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

import br.unicamp.ic.mo409.model.User;

@Repository("UserDAO")
public class UserDAO {

	@PersistenceContext(unitName = "persistenceUnit")
	protected EntityManager entityManager;

	public User find(Integer id) {

		return entityManager.find(User.class, id);

	}

	@Transactional
	public void persist(User user) {
		entityManager.persist(user);
		entityManager.flush();
	}

	public void merge(User User) {

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

	public User findUserByEmail(String email) {
		// TODO Auto-generated method stub
		return entityManager.find(User.class, 1);
	}

}
