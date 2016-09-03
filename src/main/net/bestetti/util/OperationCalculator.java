package main.net.bestetti.util;

import java.math.BigDecimal;
import javax.ejb.Singleton;
import javax.inject.Inject;
import main.net.bestetti.dao.OperationDao;
import main.net.bestetti.model.Operation;
import main.net.bestetti.model.OperationCost;

@Singleton
public class OperationCalculator {
	
	@Inject
	private OperationDao dao;	
	private static double tax = 0.15;
	private static double fee = 0.03;
	private static double maintenance = 0.0325;
	
	public void addOperation (Operation op) {
		
		//Calculate operation's Total
		op.setTotal(op.getUnitPrice().multiply(new BigDecimal(op.getAmount())));
		
		//Create a new Operation Cost and bind each other
		OperationCost oc = new OperationCost();
		oc.setOperation(op);
		op.setOc(oc);
		
		//Calculate and save all Operation Cost's values
		oc.setTax(op.getTotal().multiply(new BigDecimal(OperationCalculator.tax)));
		oc.setFee(op.getTotal().multiply(new BigDecimal(OperationCalculator.fee)));
		oc.setMaintenance(op.getTotal().multiply(new BigDecimal(OperationCalculator.maintenance)));
		oc.setTotal((oc.getTax().add(oc.getFee()).add(oc.getMaintenance())));
		
		//Set Operation's Total + Costs property
		op.setTotalPlusCosts(oc.getTotal().add(op.getTotal()));
		
		//Call Dao to persist both
		dao.add(op, oc);		
	}

}