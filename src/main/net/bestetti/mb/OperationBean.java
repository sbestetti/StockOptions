package main.net.bestetti.mb;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import main.net.bestetti.model.Operation;
import main.net.bestetti.model.OperationCost;
import main.net.bestetti.util.OperationCalculator;

@RequestScoped
@Named
public class OperationBean {
	
	@Inject
	private LoggedUserBean loggedUserBean;
	
	@Inject
	private OperationCalculator calculator;
	
	@Inject
	private OperationConfirmation confirmationBean;
	
	private Operation op = new Operation();
	private OperationCost oc = new OperationCost();
	private boolean showConfirmation = false;
	
	@PostConstruct
	public void notice() {
		System.out.println("OperationBean: " + this.toString());
	}
	
	public String addOperation() {
		op.setUser(loggedUserBean.getLoggedUser());
		op = calculator.calculateOperationTotal(op);
		oc = calculator.calculateOperationCosts(op);
		confirmationBean.setOp(op);
		confirmationBean.setOc(oc);
		confirmationBean.setUser(op.getUser());
		return "/operationconfirmation.xhtml";
	}

	//Getters & Setters
	public Operation getOp() {
		return op;
	}

	public void setOp(Operation op) {
		this.op = op;
	}

	public boolean isShowConfirmation() {
		return showConfirmation;
	}

	public void setShowConfirmation(boolean showConfirmation) {
		this.showConfirmation = showConfirmation;
	}

	public OperationCost getOc() {
		return oc;
	}

	public void setOc(OperationCost oc) {
		this.oc = oc;
	}	
	
}