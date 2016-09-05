package main.net.bestetti.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import main.net.bestetti.model.User;

@Named
public class UserDao implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	private EntityManager em;
	
	public UserDao() {
		System.out.println("UserDao created");
	}
	
	public int add(User user) {
		if (this.getUserByEmail(user) == null) {
			em.persist(user);
			return 0;
		}
		return 1;		
	}
	
	public List<User> getUsersOnly() {
		TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);
		return query.getResultList();
	}
	
	public User getUserByEmail(User user) {
		String jpql = "SELECT u FROM User u WHERE u.email = :email";
		TypedQuery<User> query = em.createQuery(jpql, User.class);
		query.setParameter("email", user.getEmail());
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}		
	}
	
	public User getUserByEmail (String email) {
		User toCheck = new User();
		toCheck.setEmail(email);
		return this.getUserByEmail(toCheck);
	}
	
	public User getUserById (Long id) {
		return em.find(User.class, id);
	}
	
	public User userLogin (String email, String password) {
		System.out.println("UserDao's userLogin method called");
		String jpql = "SELECT u FROM User u WHERE u.email = :email AND u.password = :password";
		TypedQuery<User> query = em.createQuery(jpql, User.class);
		query.setParameter("email", email);
		query.setParameter("password", password);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}