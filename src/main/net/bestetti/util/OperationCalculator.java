package main.net.bestetti.util;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import main.net.bestetti.dao.OperationDao;
import main.net.bestetti.model.Operation;
import main.net.bestetti.model.OperationCost;
import main.net.bestetti.model.User;

@Named @RequestScoped
public class OperationCalculator implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private OperationDao dao;
	
	private static double tax = 0.15;
	private static double fee = 0.03;
	private static double maintenance = 0.0325;
	private OperationCost finalOC = new OperationCost();
	
	
	//This method returns the Operation itself but with all it's fields calculated
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
			op.setAmount(1);
			op.setTotal(op.getUnitPrice());
			OperationCost oc = this.calculateOperationCosts(op);
			op.setTotalPlusCosts(op.getTotal());
		}		
		return op;
	}
	
	//This method creates and calculates an OperationCosts object for the given Operation
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
	
	//For security reasons the User's balance is never stored but is re-calculated by this method every time it changes 
	public BigDecimal getUserBalance(User user) {
		BigDecimal result = BigDecimal.ZERO;
		List<Operation> operations = dao.getOperationsByUser(user);
		for (Operation each : operations) {
			if (each.getType().equals("BUY") || each.getType().equals("WITHDRAWL")) {
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