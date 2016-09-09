package main.net.bestetti.mb;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

import main.net.bestetti.dao.OperationDao;
import main.net.bestetti.model.Operation;
import main.net.bestetti.model.OperationCost;
import main.net.bestetti.util.OperationCalculator;

@Named @SessionScoped
public class OperationBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private LoginBean loginBean;
	
	@Inject
	private OperationDao dao;
	
	@Inject
	private OperationCalculator calculator;
	
	private boolean showConfirmation = false;
	private Operation op = new Operation();
	private OperationCost oc = new OperationCost();
	private List<Operation> operations;
	private boolean tickerEnabled = true;
		
	public List<Operation> getOperations() {
		operations = dao.getOperationsByUser(loginBean.getUser());
		return operations;
	}

	public void cleanBean () {
		this.oc = new OperationCost();
		this.op = new Operation();
		this.showConfirmation = false;
	}
		
	public void addOperation() {
		op.setUser(loginBean.getUser());
		op = calculator.calculateOperationTotal(op);
		oc = calculator.getFinalOC();
		showConfirmation = true;
	}
	
	public String confirmAdding() {
		dao.add(op, oc);
		loginBean.setBalance(calculator.getUserBalance(loginBean.getUser()));
		return "/menu.xhtml?faces-redirect=true";
	}
	
	public String cancel() {
		return "/menu.xhtml?faces-redirect=true";
	}
	
	//Validators
	public void volumeValidator(FacesContext fc, UIComponent component, Object value) throws ValidatorException {
		BigDecimal valueToCheck = new BigDecimal(value.toString());
		if (valueToCheck.compareTo(BigDecimal.ONE) == -1) {
			throw new ValidatorException(new FacesMessage("Must be at least 0.01"));			
		}	
	}
	
	public void priceValidator(FacesContext fc, UIComponent component, Object value) throws ValidatorException {
		BigDecimal valueToCheck = new BigDecimal(value.toString());
		if (valueToCheck.compareTo(BigDecimal.ZERO) == -1 || valueToCheck.compareTo(BigDecimal.ZERO) == 0) {
			throw new ValidatorException(new FacesMessage("Must be at least 0.01"));			
		}		
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

	public boolean isTickerEnabled() {
		return tickerEnabled;
	}

	public void setTickerEnabled(boolean tickerEnabled) {
		this.tickerEnabled = tickerEnabled;
	}
	
}