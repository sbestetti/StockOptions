package main.net.bestetti.mb;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import main.net.bestetti.dao.OperationDao;
import main.net.bestetti.model.Operation;
import main.net.bestetti.model.OperationCost;
import main.net.bestetti.util.OperationCalculator;

@ManagedBean
@ViewScoped
public class OperationBean {
	
	@Inject
	private LoginBean loginBean;
	@Inject
	private OperationDao dao;
	private OperationCalculator calculator = new OperationCalculator();
	private Operation op = new Operation();
	private OperationCost oc = new OperationCost();
	private boolean showConfirmation = false;
		
	public void addOperation() {
		op.setUser(loginBean.getUser());
		op = calculator.calculateOperationTotal(op);
		oc = calculator.getFinalOC();
		showConfirmation = true;
	}
	
	public String confirmAdding() {
		dao.add(op, oc);
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