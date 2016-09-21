package main.net.bestetti.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class User {
	
	@Id @GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String firstName;
	
	@NotNull
	private String lastName;
	
	@Column (unique = true) @NotNull
	private String email;
	
	@NotNull
	private String password;
	
	@NotNull
	private Date lastUpdate = new Date();
	
	@NotNull
	private BigDecimal balance;
	
	@NotNull
	private Double taxPercentage;
	
	@NotNull
	private Double operationCost;
	
	@NotNull
	private Boolean operationCostIsPercentage;
	
	@OneToMany (mappedBy = "user")
	private List<Operation> operations;
	
	//Getters & Setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
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
	public Date getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public List<Operation> getOperations() {
		return operations;
	}
	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}
	public Double getTaxPercentage() {
		return taxPercentage;
	}
	public void setTaxPercentage(Double taxPercentage) {
		this.taxPercentage = taxPercentage;
	}
	public Double getOperationCost() {
		return operationCost;
	}
	public void setOperationCost(Double operationCost) {
		this.operationCost = operationCost;
	}
	public Boolean getOperationCostIsPercentage() {
		return operationCostIsPercentage;
	}
	public void setOperationCostIsPercentage(Boolean operationCostIsPercentage) {
		this.operationCostIsPercentage = operationCostIsPercentage;
	}	

}