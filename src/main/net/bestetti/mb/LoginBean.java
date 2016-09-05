package main.net.bestetti.mb;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import main.net.bestetti.dao.UserDao;
import main.net.bestetti.model.User;
import main.net.bestetti.util.OperationCalculator;

@SessionScoped
@Named
public class LoginBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private UserDao dao;
	
	@Inject
	private OperationCalculator calculator;
	
	private String email;
	private String password;
	private boolean showErrorMessages = false;
	private User user;
	private boolean logged = false;
	private BigDecimal balance = BigDecimal.ZERO;
	
	public LoginBean() {
		System.out.println("LoginBean created");
	}
	
	public String login() {
		System.out.println("loginBean.login method called");
		this.user = dao.userLogin(email, password);
		if (user != null) {
			logged = true;
			this.updateUserBalance();
			return "menu?faces-redirect=true";
		}
		user = new User();
		showErrorMessages = true;
		return null;			
	}
	
	public String logoff() {
		this.user = new User();
		logged = false;
		return "/index.htmlx?faces-redirect=true";		
	}
	
	public void updateUserBalance() {
		this.balance = calculator.getUserBalance(user);
	}
	
	//Getters & Setters
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isShowErrorMessages() {
		return showErrorMessages;
	}

	public void setShowErrorMessages(boolean showErrorMessages) {
		this.showErrorMessages = showErrorMessages;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isLogged() {
		return logged;
	}

	public void setLogged(boolean logged) {
		this.logged = logged;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

}