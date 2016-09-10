package main.net.bestetti.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import main.net.bestetti.model.User;

@Named @RequestScoped
public class UserDao implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext(unitName = "StockOptions")
	private EntityManager em;
	
	//	List of returns:
	//	0 - User added OK
	//	1 - Null fields
	//	2 - E-mail already exists
	@Transactional
	public int add(User user) {
		user.setBalance(BigDecimal.ZERO);
		user.setLastUpdate(new Date());
		if (this.getUserByEmail(user) == null) {
			try {
				em.persist(user);
				return 0;
			} catch (PersistenceException e) {
				return 1;
			}
		} else {
			return 2;
		}		
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