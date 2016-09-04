package main.net.bestetti.mb;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import main.net.bestetti.dao.UserDao;
import main.net.bestetti.model.User;

@SessionScoped
@Named
public class LoginBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private UserDao dao;
	private String email;
	private String password;
	private boolean showErrorMessages = false;
	private User user = new User();
	private User loggedUser = new User();
	private boolean logged;
	
	public String login() {
		if (dao.loginOk(user)) {
			loggedUser = dao.getUserByEmail(user);
			this.logged = true;
			return "menu?faces-redirect=true";
		}
		user = new User();
		showErrorMessages = true;
		return null;			
	}
	
	public String logoff() {
		loggedUser = null;
		logged = false;
		return "/index.htmlx?faces-redirect=true";
		
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

	public boolean isLogged() {
		return logged;
	}

	public void setLogged(boolean logged) {
		this.logged = logged;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getLoggedUser() {
		return loggedUser;
	}

	public void setLoggedUser(User loggedUser) {
		this.loggedUser = loggedUser;
	}

}