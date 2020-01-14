package com.app.pojos;

import javax.persistence.Embeddable;


@Embeddable
public class Bankdeatils {

	private String bankName;
	private Integer AccountNo;
	
	

	public Bankdeatils() {
		
	}

	@Override
	public String toString() {
		return "Bankdeatils [bankName=" + bankName + ", AccountNo=" + AccountNo + ", getBankName()=" + getBankName()
				+ ", getAccountNo()=" + getAccountNo() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

	public Bankdeatils(String bankName, Integer accountNo) {
		super();
		this.bankName = bankName;
		AccountNo = accountNo;
	}

	public String getBankName() {
		return bankName;
	}
	public Integer getAccountNo() {
		return AccountNo;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public void setAccountNo(Integer accountNo) {
		AccountNo = accountNo;
	}

}
