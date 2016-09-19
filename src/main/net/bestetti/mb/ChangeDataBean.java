package main.net.bestetti.mb;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import main.net.bestetti.dao.UserDao;
import main.net.bestetti.model.User;

@Named @SessionScoped
public class ChangeDataBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private LoginBean loginBean;
	
	@Inject
	private UserDao dao;
	
	private User user = new User();
	private String currentPassword;
	private String newPassword;
	private String confirmNewPassword;
	private Double operationCost;
	
	@PostConstruct
	public void getUserFromBean() {
		this.user = loginBean.getUser();
		
	}
	
	public String userUpdate() {
		System.out.println("userUpdate called");
		if (dao.updateUser(this.user)) {
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
	public String getCurrentPassword() {
		return currentPassword;
	}
	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getConfirmNewPassword() {
		return confirmNewPassword;
	}
	public void setConfirmNewPassword(String confirmNewPassword) {
		this.confirmNewPassword = confirmNewPassword;
	}
	public Double getOperationCost() {
		return operationCost;
	}
	public void setOperationCost(Double operationCost) {
		this.operationCost = operationCost;
	}
	
	

}
