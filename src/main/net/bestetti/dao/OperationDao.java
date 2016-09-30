package main.net.bestetti.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import main.net.bestetti.mb.LoginBean;
import main.net.bestetti.model.Operation;
import main.net.bestetti.model.OperationCost;
import main.net.bestetti.model.User;

@Named @RequestScoped
public class OperationDao implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext(unitName = "StockOptions")
	private EntityManager em;
	
	@Inject
	private LoginBean loginBean;
	
	@Transactional
	public void add (Operation op, OperationCost oc) {
		op.setCreated(new Date());
		oc.setOperation(op);
		op.setOc(oc);
		op.setUser(loginBean.getUser());
		em.persist(op);
		em.persist(oc);
	}
	
	public List<Operation> getOperationsByUser (User user) {
		String jpql = "SELECT o FROM Operation o WHERE o.user.id = :pUser";
		TypedQuery<Operation> query = em.createQuery(jpql, Operation.class);
		query.setParameter("pUser", user.getId());
		return query.getResultList();		
	}
	
	public Integer operationCount (User user) {
		String jpql = "SELECT COUNT(o) FROM Operation o WHERE o.user.id = :pUser";
		TypedQuery<Long> query = em.createQuery(jpql, Long.class);
		query.setParameter("pUser", user.getId());
		return query.getSingleResult().intValue();
	}
	
	public List<Operation> getPagedResult (User user, int first, int pageSize) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Operation> criteria = builder.createQuery(Operation.class);
		criteria.from(Operation.class);
		Root<Operation> root = criteria.from(Operation.class);
		criteria.select(root);
		Path<User> path = root.<User>get("user");
		Predicate userQuery = builder.equal(path, user);
		criteria.where(userQuery);
		criteria.distinct(true);
		TypedQuery<Operation> criteriaQuery = em.createQuery(criteria);
		criteriaQuery.setFirstResult(first);
		criteriaQuery.setMaxResults(pageSize);
		return criteriaQuery.getResultList();
	}
	
	public Operation getOperationById(Long id) {
		String jpql = "SELECT o FROM Operation o where id = :pid";
		TypedQuery<Operation> query = em.createQuery(jpql, Operation.class);
		query.setParameter("pid", id);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}		
	}

}