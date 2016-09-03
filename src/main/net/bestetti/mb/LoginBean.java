package main.net.bestetti.mb;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import main.net.bestetti.dao.UserDao;

@ManagedBean
@ViewScoped
public class LoginBean {
	
	@Inject
	private UserDao dao;
	private String email;
	private String password;
	private boolean showErrorMessages = false;
	
	public String login() {
		if (dao.checkLogin(email, password)) {
			return "/menu.xhtml?faces-redirect=true";
		}
		showErrorMessages = true;
		email = "";
		password = "";
		return null;
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
	
	

}
