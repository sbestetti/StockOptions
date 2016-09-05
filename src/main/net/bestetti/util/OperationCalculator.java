package main.net.bestetti.util;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import main.net.bestetti.dao.OperationDao;
import main.net.bestetti.model.Operation;
import main.net.bestetti.model.OperationCost;
import main.net.bestetti.model.User;

@Named
public class OperationCalculator implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	OperationDao dao;
	private static double tax = 0.15;
	private static double fee = 0.03;
	private static double maintenance = 0.0325;
	private OperationCost finalOC = new OperationCost();
	
	public OperationCalculator() {
		System.out.println("OperationCalculator created");
	}
	
	public Operation calculateOperationTotal (Operation op) {
		if (op.getType().equals("BUY")) {
			op.setTotal(op.getUnitPrice().multiply(new BigDecimal(op.getAmount())));
			OperationCost oc = this.calculateOperationCosts(op);
			op.setTotalPlusCosts(op.getTotal().add(oc.getTotal()));
		} else if (op.getType().equals("SELL")) {
			op.setTotal(op.getUnitPrice().multiply(new BigDecimal(op.getAmount())));
			OperationCost oc = this.calculateOperationCosts(op);
			op.setTotalPlusCosts(op.getTotal().subtract(oc.getFee().add(oc.getMaintenance().add(oc.getTax()))));
		} else {
			op.setTotal(op.getUnitPrice());
			OperationCost oc = this.calculateOperationCosts(op);
			op.setTotalPlusCosts(op.getTotal());
		}		
		return op;
	}
	
	public OperationCost calculateOperationCosts (Operation op) {
		
		OperationCost oc = new OperationCost();
				
		//Check the operation's type and calculate the values accordingly
		if (op.getType().equals("BUY")) {
			oc.setTax(BigDecimal.ZERO);
			oc.setFee(op.getTotal().multiply(new BigDecimal(OperationCalculator.fee)));
			oc.setMaintenance(op.getTotal().multiply(new BigDecimal(OperationCalculator.maintenance)));
		} else if (op.getType().equals("SELL")) {
			oc.setTax(op.getTotal().multiply(new BigDecimal(OperationCalculator.tax)));
			oc.setFee(op.getTotal().multiply(new BigDecimal(OperationCalculator.fee)));
			oc.setMaintenance(op.getTotal().multiply(new BigDecimal(OperationCalculator.maintenance)));
		} else {
			oc.setTax(BigDecimal.ZERO);
			oc.setFee(BigDecimal.ZERO);
			oc.setMaintenance(BigDecimal.ZERO);
		}
		
		//Save all Operation Cost's values
		oc.setTotal((oc.getTax().add(oc.getFee()).add(oc.getMaintenance())));
		finalOC = oc;		
				
		return oc;
	}
	
	public BigDecimal getUserBalance(User user) {
		BigDecimal result = BigDecimal.ZERO;
		List<Operation> operations = dao.getOperationsByUser(user);
		for (Operation each : operations) {
			String type = each.getType();
			if (type.equals("BUY") || type.equals("WITHDRAWL")) {
				result = result.subtract(each.getTotalPlusCosts());
			} else {
				result = result.add(each.getTotalPlusCosts());
			}
		}
		return result;
	}
	
	//Getters & Setters
	public OperationCost getFinalOC() {
		return finalOC;
	}
	
}