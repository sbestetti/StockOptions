package main.net.bestetti.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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

}