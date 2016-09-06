package main.net.bestetti.mb;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import main.net.bestetti.model.User;

@Named
@SessionScoped
public class LoggedUserBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	User loggedUser;
	
	@PostConstruct
	public void notice() {
		System.out.println("LoggedUserBean: " + this.toString());
	}
	
	public boolean isLogged() {
		if (loggedUser == null) {
			return false;
		}
		return true;
	}

	//Getters & Setters
	public User getLoggedUser() {
		return loggedUser;
	}

	public void setLoggedUser(User loggedUser) {
		this.loggedUser = loggedUser;
	}
	
	
	
	

}
