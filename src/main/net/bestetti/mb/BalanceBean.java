package main.net.bestetti.mb;

import java.math.BigDecimal;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import main.net.bestetti.util.OperationCalculator;

@Named
@RequestScoped
public class BalanceBean {
	
	@Inject
	private OperationCalculator calculator;
	@Inject
	private LoggedUserBean loggedUserBean;
		
	@PostConstruct
	public void notice() {
		System.out.println("BalanceBean: " + this.toString());
	}
	
	public BigDecimal getBalance() {
		return calculator.getUserBalance(loggedUserBean.getLoggedUser());		
	}

}
