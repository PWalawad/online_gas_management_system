package com.app.model;

public class BillModel {
	private Integer billNo;
	private float amount;
	private String startDate;
	private String endDate;
	private String dueDate;
	private String status;
	
	
	public BillModel() {
		
	}

	  
	public BillModel(Integer billNo, float amount, String startDate, String endDate, String dueDate, String status) {
		super();
		this.billNo = billNo;
		this.amount = amount;
		this.startDate = startDate;
		this.endDate = endDate;
		this.dueDate = dueDate;
		this.status = status;
	}

	public Integer getBillNo() {
		return billNo;
	}
	public void setBillNo(Integer billNo) {
		this.billNo = billNo;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	


}
