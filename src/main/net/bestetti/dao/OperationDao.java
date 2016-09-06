package main.net.bestetti.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import main.net.bestetti.model.Operation;
import main.net.bestetti.model.OperationCost;
import main.net.bestetti.model.User;

@Named
public class OperationDao implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	private EntityManager em;
	
	@PostConstruct
	public void notice() {
		System.out.println("OperationDao: " + this.toString());
	}
	
	public OperationDao() {
		System.out.println("OperationDao created");
	}
	
	@Transactional
	public void add (Operation op, OperationCost oc) {
		op.setCreated(new Date());
		oc.setOperation(op);
		op.setOc(oc);
		em.persist(op);
		em.persist(oc);
	}
	
	public List<Operation> getOperationsByUser (User user) {
		String jpql = "SELECT o FROM Operation o WHERE o.user.id = :pUser";
		TypedQuery<Operation> query = em.createQuery(jpql, Operation.class);
		query.setParameter("pUser", user.getId());
		return query.getResultList();		
	}

}
