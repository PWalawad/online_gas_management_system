package com.app.dao;

import java.util.List;


import com.app.pojos.Bill;


public interface ICustomerDao 
{
	public List<Bill> allBills(Integer id);
	public List<Bill> myPendingaBills(Integer id);
	public List<Bill> myPaidBills(Integer id);
	
}
