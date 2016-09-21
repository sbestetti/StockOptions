package main.net.bestetti.mb;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import main.net.bestetti.dao.UserDao;
import main.net.bestetti.model.User;

@Named @SessionScoped
public class AddUserBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Inject
	UserDao dao;
	
	private User user = new User();
	private boolean requiredFieldsMessage = false;
	private boolean emailInUseMessage = false;
	private boolean passwordMismatchMessage = false;
	private String passwordCheck;

	public String addUser() {
		if (user.getPassword().equals(passwordCheck)) {
			int result = dao.add(user);
			switch (result) {
				case 0:
					return "/index.htmlx?faces-redirect=true";
				case 1:
					requiredFieldsMessage = true;
					return null;
				case 2:
					emailInUseMessage = true;
					return null;
				default:
					return null;
			}
		} else {
			passwordMismatchMessage = true;
			return null;
		}		
	}
	
	//Getters & Setters
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isRequiredFieldsMessage() {
		return requiredFieldsMessage;
	}

	public void setRequiredFieldsMessage(boolean requiredFieldsMessage) {
		this.requiredFieldsMessage = requiredFieldsMessage;
	}

	public boolean isEmailInUseMessage() {
		return emailInUseMessage;
	}

	public void setEmailInUseMessage(boolean emailInUseMessage) {
		this.emailInUseMessage = emailInUseMessage;
	}

	public boolean isPasswordMismatchMessage() {
		return passwordMismatchMessage;
	}

	public void setPasswordMismatchMessage(boolean passwordMismatchMessage) {
		this.passwordMismatchMessage = passwordMismatchMessage;
	}

	public String getPasswordCheck() {
		return passwordCheck;
	}

	public void setPasswordCheck(String passwordCheck) {
		this.passwordCheck = passwordCheck;
	}
}