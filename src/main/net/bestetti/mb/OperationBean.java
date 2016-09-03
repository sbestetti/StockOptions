package main.net.bestetti.mb;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import main.net.bestetti.dao.UserDao;
import main.net.bestetti.model.Operation;
import main.net.bestetti.model.User;
import main.net.bestetti.util.OperationCalculator;

@ManagedBean
@ViewScoped
public class OperationBean {
	
	@Inject
	private UserDao userDao;
	@Inject
	private OperationCalculator calculator;
	private Operation operation = new Operation();
	private User user = new User();
	private long userId;
	
	public void addOperation() {
		user = userDao.getUserById(userId);
		operation.setUser(user);
		calculator.addOperation(operation);
		operation = new Operation();
	}
	
	//Getters & Setters
	public Operation getOperation() {
		return operation;
	}
	public void setOperation(Operation operation) {
		this.operation = operation;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}

}