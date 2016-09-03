package main.net.bestetti.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Operation {
	
	@Id @GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private User user;
	@OneToOne (fetch = FetchType.EAGER)
	private OperationCost oc;
	private String type;
	private String ticker;
	private int amount;
	private BigDecimal unitPrice;
	private BigDecimal total;
	private BigDecimal totalPlusCosts;
	private Date created;
	
	//Getters & Setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type.toUpperCase();
	}
	public String getTicker() {
		return ticker;
	}
	public void setTicker(String ticker) {
		this.ticker = ticker.toUpperCase();
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public OperationCost getOc() {
		return oc;
	}
	public void setOc(OperationCost oc) {
		this.oc = oc;
	}
	public BigDecimal getTotalPlusCosts() {
		return totalPlusCosts;
	}
	public void setTotalPlusCosts(BigDecimal totalPlusCosts) {
		this.totalPlusCosts = totalPlusCosts;
	}	

}