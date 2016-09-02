package main.net.bestetti.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import main.net.bestetti.model.User;

@Stateless
public class UserDao {
	
	@PersistenceContext
	private EntityManager em;
	
	public int add(User user) {
		if (this.getUserByEmail(user).isEmpty()) {
			em.persist(user);
			return 0;
		}
		return 1;		
	}
	
	public List<User> getUsersOnly() {
		TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);
		return query.getResultList();
	}
	
	public List<User> getUserByEmail(User user) {
		String jpql = "SELECT u FROM User u WHERE u.email = :email";
		TypedQuery<User> query = em.createQuery(jpql, User.class);
		query.setParameter("email", user.getEmail());
		return query.getResultList();
	}

}