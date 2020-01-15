package com.app.pojos;


import java.time.LocalDate;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "bill")
public class Bill {

	private Integer billNo;
	private float amount;
	private LocalDate startDate;
	private LocalDate endDate;
	private LocalDate dueDate;
	private String status;
	private LocalDate paymentDoneDate;
	
	//mapping 
    private User userBillDeatils;
		
    
		
		public LocalDate getPaymentDoneDate() {
		return paymentDoneDate;
	}

		@ManyToOne
		@JoinColumn(name = "id")
		@JsonIgnore
		public User getUserBillDeatils() {
			return userBillDeatils;
		}


		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@JsonProperty(value = "no")
	public Integer getBillNo() {
		return billNo;
	}


	public float getAmount() {
		return amount;
	}

	public String getStatus() {
		return status;
	}

	
	public LocalDate getStartDate() {
		return startDate;
	}

	
	public LocalDate getEndDate() {
		return endDate;
	}

	
	public LocalDate getDueDate() {
		return dueDate;
	}

/*
 * =========================================
 * Setters
 * ===========================================
 */
	
	
	

	public void setBillNo(Integer billNo) {
		this.billNo = billNo;
	}



	public void setAmount(float amount) {
		this.amount = amount;
	}
	public void setPaymentDoneDate(LocalDate paymentDoneDate) {
		this.paymentDoneDate = paymentDoneDate;
	}



	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}



	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}



	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}



	public void setUserBillDeatils(User userBillDeatils) {
		this.userBillDeatils = userBillDeatils;
	}
	


	public Bill() 
	{
		
	}



	public Bill(Integer billNo, float amount, LocalDate startDate, LocalDate endDate, LocalDate dueDate) {
		super();
		this.billNo = billNo;
		this.amount = amount;
		this.startDate = startDate;
		this.endDate = endDate;
		this.dueDate = dueDate;
		
	}



	@Override
	public String toString() {
		return "Bill [billNo=" + billNo + ", amount=" + amount + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", dueDate=" + dueDate + ", userBillDeatils=" + userBillDeatils + ", getUserBillDeatils()="
				+ getUserBillDeatils() + ", getBillNo()=" + getBillNo() + ", getAmount()=" + getAmount()
				+ ", getStartDate()=" + getStartDate() + ", getEndDate()=" + getEndDate() + ", getDueDate()="
				+ getDueDate() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}



	public void setStatus(String status) {
		this.status = status;
	}


	

}
