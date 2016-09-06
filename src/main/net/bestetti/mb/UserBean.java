package main.net.bestetti.mb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import main.net.bestetti.dao.UserDao;
import main.net.bestetti.model.User;

@Named
@ViewScoped
public class UserBean {
	
	@Inject
	private UserDao dao;
	
	private User user = new User();
	private String passwordCheck;
	private boolean showPasswordMessage = false;
	private boolean showEmailMessage = false;
	private List<User> users;
	
	@PostConstruct
	public void notice() {
		System.out.println("UserBean: " + this.toString());
	}
	
	public String add() {
		showPasswordMessage = false;
		showEmailMessage = false;
		if (user.getPassword().equals(passwordCheck)) {
			if (dao.add(user) == 0) {
				return "/index.xhtml?faces-redirect=true";
			} else {
				showEmailMessage = true;
				return null;				
			}
		} else {
			showPasswordMessage = true;
			return null;
		}
	}			
	
	public List<User> getUsers() {
		if (users == null) {
			this.users = dao.getUsersOnly();
		}
		
		return users;		
	}
	
	//Getters & Setters
	public boolean isShowPasswordMessage() {
		return showPasswordMessage;
	}

	public void setShowPasswordMessage(boolean showPasswordMessage) {
		this.showPasswordMessage = showPasswordMessage;
	}

	public boolean isShowEmailMessage() {
		return showEmailMessage;
	}

	public void setShowEmailMessage(boolean showEmailMessage) {
		this.showEmailMessage = showEmailMessage;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getPasswordCheck() {
		return passwordCheck;
	}

	public void setPasswordCheck(String passwordCheck) {
		this.passwordCheck = passwordCheck;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}	

}