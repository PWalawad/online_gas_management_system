package com.app.dao;

import java.util.List;

import com.app.pojos.Address;
import com.app.pojos.Bankdeatils;
import com.app.pojos.Bill;
import com.app.pojos.User;


public interface ICustomerDao 
{
	public List<Bill> allBills(Integer id);
	public List<Bill> myPendingaBills(Integer id);
	public List<Bill> myPaidBills(Integer id);
	public User registerMeOnline(User newUser);
	public Bankdeatils addbankdetails(Bankdeatils b,Integer id);
	public Address addAddress(Address a, Integer id);
	public Integer paybill(Integer id);
	
}
