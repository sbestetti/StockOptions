package main.net.bestetti.dao;

import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import main.net.bestetti.model.Operation;
import main.net.bestetti.model.OperationCost;

@Stateless
public class OperationDao {
	
	@PersistenceContext
	private EntityManager em;
	
	public void add (Operation op, OperationCost oc) {
		op.setCreated(new Date());
		oc.setOperation(op);
		op.setOc(oc);
		em.persist(op);
		em.persist(oc);
	}

}
