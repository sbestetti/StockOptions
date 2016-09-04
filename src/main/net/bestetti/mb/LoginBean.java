package main.net.bestetti.mb;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import main.net.bestetti.dao.UserDao;
import main.net.bestetti.model.User;

@SessionScoped
@ManagedBean
public class LoginBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private UserDao dao;
	private String email;
	private String password;
	private boolean showErrorMessages = false;
	private User loggedUser = new User();
	private boolean logged = false;
	
	public LoginBean() {
		System.out.println("Login bean created");
	}
	
	public String login() {
		if (dao.checkLogin(email, password)) {
			loggedUser = dao.getUserByEmail(email).get(0);
			logged = true;
			return "/menu.xhtml?faces-redirect=true";
		}
		showErrorMessages = true;
		email = "";
		password = "";
		return null;
	}
	
	public boolean notLogged() {
//		System.out.println("notLogged return: " + !loggedUser.getEmail().isEmpty());
		return !loggedUser.getEmail().isEmpty();
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

	public User getLoggedUser() {
		return loggedUser;
	}

	public void setLoggedUser(User loggedUser) {
		this.loggedUser = loggedUser;
	}

	public boolean isLogged() {
		System.out.println("isLogged = " + logged);
		return logged;
	}

	public void setLogged(boolean logged) {
		this.logged = logged;
	}

}