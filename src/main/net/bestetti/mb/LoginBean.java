package main.net.bestetti.mb;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import main.net.bestetti.dao.UserDao;
import main.net.bestetti.model.User;

@Named
@RequestScoped
public class LoginBean {
	
	@Inject
	private UserDao dao;
	@Inject
	LoggedUserBean loggedUser;
	private User user = new User();
	
	@PostConstruct
	public void notice() {
		System.out.println("LoginBean: " + this.toString());
	}
	
	public String login() {
		if (dao.login(user) != null) {
			loggedUser.setLoggedUser(dao.getUserByEmail(this.user));
			return "/menu.xhtml?faces-redirect=true";
		}
		return null;
	}

	//Getters & Setters
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}	
	
}