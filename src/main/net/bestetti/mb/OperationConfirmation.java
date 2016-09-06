package main.net.bestetti.mb;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import main.net.bestetti.dao.OperationDao;
import main.net.bestetti.model.Operation;
import main.net.bestetti.model.OperationCost;
import main.net.bestetti.model.User;

@Named
public class OperationConfirmation {
	
	@Inject
	OperationDao dao;
	
	private Operation op;
	private OperationCost oc;
	private User user;
	
	@PostConstruct
	public void notice() {
		System.out.println("OperationConfirmationBean: " + this.toString());
	}
	
	public String confirmAdding() {
		dao.add(op, oc);
		return "/menu.xhtml?faces-redirect=true";
	}
	
	//Getters & Setters
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	

}
