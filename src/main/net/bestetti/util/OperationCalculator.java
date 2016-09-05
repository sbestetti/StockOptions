package main.net.bestetti.util;

import java.math.BigDecimal;
import javax.inject.Inject;
import main.net.bestetti.model.Operation;
import main.net.bestetti.model.OperationCost;

public class OperationCalculator {
	
	@Inject
	private static double tax = 0.15;
	private static double fee = 0.03;
	private static double maintenance = 0.0325;
	private OperationCost finalOC = new OperationCost();
	
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
	
	//Getters & Setters
	public OperationCost getFinalOC() {
		return finalOC;
	}
	
}