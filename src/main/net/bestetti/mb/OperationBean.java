package main.net.bestetti.mb;

import javax.inject.Inject;
import javax.inject.Named;

import main.net.bestetti.dao.OperationDao;
import main.net.bestetti.model.Operation;
import main.net.bestetti.model.OperationCost;
import main.net.bestetti.util.OperationCalculator;

@Named
public class OperationBean {
	
	@Inject
	private LoginBean loginBean;
	@Inject
	private OperationDao dao;
	@Inject
	private OperationCalculator calculator;
	private Operation op = new Operation();
	private OperationCost oc = new OperationCost();
	private boolean showConfirmation = false;
	
	public OperationBean() {
		System.out.println("OperationBean created");
	}
		
	public void addOperation() {
		System.out.println("OperationBean's addOperation method called");
		op.setUser(loginBean.getUser());
		op = calculator.calculateOperationTotal(op);
		oc = calculator.getFinalOC();
		showConfirmation = true;
	}
	
	public String confirmAdding() {
		dao.add(op, oc);
		loginBean.updateUserBalance();
		return "/menu.xhtml?faces-redirect=true";
	}
	
	public String cancel() {
		return "/menu.xhtml?faces-redirect=true";
	}
	
	//Getters & Setters
	public boolean isShowConfirmation() {
		return showConfirmation;
	}
	
	public Operation getOp() {
		return op;
	}

	public void setOp(Operation op) {
		this.op = op;
	}

	public OperationCost getOc() {
		return oc;
	}

	public void setOc(OperationCost oc) {
		this.oc = oc;
	}
	
}