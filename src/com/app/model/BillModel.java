package com.app.model;

import java.time.LocalDate;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.app.pojos.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BillModel {
	private Integer billNo;
	private float amount;
	private LocalDate startDate;
	private LocalDate endDate;
	private LocalDate dueDate;
	private String status;

	public Integer getBillNo() {
		return billNo;
	}

	public float getAmount() {
		return amount;
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
	

	public String getStatus() {
		return status;
	}


	/*
	 * ========================================= Setters
	 * ===========================================
	 */

	public void setBillNo(Integer billNo) {
		this.billNo = billNo;
	}

	public BillModel() {
		
	}

	public BillModel(Integer billNo, float amount, LocalDate startDate, LocalDate endDate, LocalDate dueDate,
			String status) {
		super();
		this.billNo = billNo;
		this.amount = amount;
		this.startDate = startDate;
		this.endDate = endDate;
		this.dueDate = dueDate;
		this.status = status;
	}

	public void setAmount(float amount) {
		this.amount = amount;
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

	public void setStatus(String status) {
		this.status = status;
	}


}
