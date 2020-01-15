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

	/*
	 * ========================================= Setters
	 * ===========================================
	 */

	public void setBillNo(Integer billNo) {
		this.billNo = billNo;
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

}
