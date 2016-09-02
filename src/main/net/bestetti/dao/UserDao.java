package main.net.bestetti.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import main.net.bestetti.model.User;

@Stateless
public class UserDao {
	
	@PersistenceContext
	private EntityManager em;
	
	public int add(User user) {
		try {
			em.persist(user);
			return 0;
		} catch (PersistenceException e) {
			System.out.println("Error adding user! " + user.getEmail() + " already exists.");
			return 1;
		}
	}
	
	public List<User> getUsersOnly() {
		TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);
		return query.getResultList();
	}

}
