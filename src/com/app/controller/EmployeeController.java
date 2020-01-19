package com.app.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.dao.IEmployeeDao;
import com.app.model.BillModel;
import com.app.pojos.Bill;
import com.app.pojos.User;

@CrossOrigin
@RestController
@RequestMapping("/employee")
public class EmployeeController {
  @Autowired 
    private IEmployeeDao dao;
  
	public EmployeeController() 
	{
	  	
	}
	@GetMapping("/customers")
	public List<User> allCustomers() {
		return dao.displayAllCustomers();
	}
	

	/*@GetMapping("/default")
	public List<User> dueDateExpiredCustomers() 
	{
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	        String date = "2019-06-05";

	        LocalDate localDate = LocalDate.parse(date, formatter);
			return dao.displayDueDateExpiredCustomers(localDate);
			
	}*/
	
	  @RequestMapping(value="/default/{searchByDate}",method=RequestMethod.GET)
	public List<User> dueDateExpiredCustomers(@PathVariable String searchByDate) 
	{
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	        LocalDate localDate = LocalDate.parse(searchByDate, formatter);
			return dao.displayDueDateExpiredCustomers(localDate);
			
	}
	  @RequestMapping(value="/{id}",method=RequestMethod.GET)
	  
		  public ResponseEntity<?> getCustomerdeatils(@PathVariable("id") int id)
			{
				User u=dao.displaySingleCustomer(id);
				if(u==null)
					return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
				return new  ResponseEntity<User>(u,HttpStatus.OK);
			}
	  
	  /*
		 * @RequestMapping(value = "/{name}", method = RequestMethod.GET)
	public List<Brand> getBrand(@PathVariable String name) {
	    return brandService.getSome(name);
	}
		 */
	/*
	@GetMapping("/{id}")
	public ResponseEntity<?> getCustomerdeatils(@PathVariable int id)
	{
		User u=dao.displaySingleCustomer(id);
		if(u==null)
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		return new  ResponseEntity<User>(u,HttpStatus.OK);
	}
	*/
	  
	@PostMapping("/{id}")
	public Bill addNextMonthBillOfCustomer(@RequestBody BillModel b,@PathVariable("id") Integer id)
	{
		//User u=dao.displaySingleCustomer(id);

		Bill entity = new Bill( b.getAmount(), getLocalDate(b.getStartDate()),
				getLocalDate(b.getEndDate()), getLocalDate(b.getDueDate()),b.getStatus());
		User persistedUser = dao.displaySingleCustomer(id);
		persistedUser.addBill(entity);
		dao.insertNextMonthBill(persistedUser);
		return entity;
	
		}
	@GetMapping("delete/{id}")
	public Integer deleteCustomer(@PathVariable("id") Integer id)
	{
		dao.deleteCustomer(id);
		return 1;
		
	}
	public LocalDate getLocalDate(String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		return LocalDate.parse(date, formatter);
	}
	
	@GetMapping("/allbills/{id}")
	public List<Bill> allBills(@PathVariable("id") Integer id)
	{
		return dao.allBills(id);	
	}
	@GetMapping("/pendingbills/{id}")
	public List<Bill> myPendingaBills(@PathVariable("id") Integer id)
	{
		return dao.pendingaBills(id);	
	}
	@GetMapping("/allpaidbills/{id}")
	public List<Bill> myPaidbills(@PathVariable("id") Integer id)
	{
		return dao.paidBills(id);	
	}
	
	
}
