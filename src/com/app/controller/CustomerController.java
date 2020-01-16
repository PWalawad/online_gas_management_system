package com.app.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dao.ICustomerDao;
import com.app.model.BillModel;
import com.app.pojos.Address;
import com.app.pojos.Bankdeatils;
import com.app.pojos.Bill;
import com.app.pojos.User;
import com.app.pojos.UserType;


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
	
	//========================================================
	@PostMapping
	public Integer registerMeOnline(@RequestBody User b,@PathVariable("id") Integer id)
	{
		
		dao1.registerMeOnline(b);
		return null;
		
	}
	@PostMapping("/addbankdetails/{id}")
	public Integer addbankdetails(@RequestBody Bankdeatils b,@PathVariable("id") Integer id)
	{
		
		dao1.addbankdetails(b,id);
		return 1;
		
	}
	
	@PostMapping("/addAddress/{id}")
	public Integer addAddress(@RequestBody Address a,@PathVariable("id") Integer id)
	{
		
		dao1.addAddress(a,id);
		return 1;
		
	}
	@GetMapping("/paybill/{id}")
	public String paybill(@PathVariable("id") Integer id)
	{
		Integer result=dao1.paybill(id);
		if(result.equals(0))
			return "You dont have any pending bill";
		else if(result.equals(404))
		   return "You are not registred customer";
		else
		   return dao1.paybill(id).toString();
	}
}
