package com.app.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dao.ICustomerDao;

import com.app.pojos.Bill;


@CrossOrigin
@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private ICustomerDao dao1;
	
	public CustomerController() 
	{
		
	}
	@GetMapping("/mybills/{id}")
	public List<Bill> allBills(@PathVariable("id") Integer id)
	{
		return dao1.allBills(id);	
	}
	@GetMapping("/mypendingbills/{id}")
	public List<Bill> myPendingaBills(@PathVariable("id") Integer id)
	{
		return dao1.myPendingaBills(id);	
	}
	@GetMapping("/mypaidbills/{id}")
	public List<Bill> myPaidbills(@PathVariable("id") Integer id)
	{
		return dao1.myPaidBills(id);	
	}
	

}
