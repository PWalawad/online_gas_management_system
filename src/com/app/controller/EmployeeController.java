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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dao.IEmployeeDao;
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

	@GetMapping("/default")
	public List<User> dueDateExpiredCustomers() {
		
		
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd");

	        String date = "2019-06-05";

	        LocalDate localDate = LocalDate.parse(date, formatter);
			return dao.displayDueDateExpiredCustomers(localDate);
			
	}
	/*
	 public String processLoginForm(@RequestParam String email, 
			@RequestParam(name = "password") String pass, Model map,
			HttpSession hs, RedirectAttributes flashMap) {
		
		System.out.println("in process login form " + email + " " + pass);
		
		try {
			
			Vendor v = dao.validateUser(email, pass);
			
			flashMap.addFlashAttribute("mesg", v.getUserRole() + " : Login Successful");
			
			hs.setAttribute("user_dtls", v);// entire session

			if (v.getUserRole().equals(Role.ADMIN))
				return "redirect:/admin/list";
			
			return "redirect:/vendor/details";

		} catch (RuntimeException e) {
			
			map.addAttribute("mesg", "Invalid Login , Pls retry ...");
			return "/user/login";

		}

	}
		
		
		
		
	}
	*/
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getCustomerdeatils(@PathVariable int id)
	{
		User u=dao.displaySingleCustomer(id);
		if(u==null)
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		return new  ResponseEntity<User>(u,HttpStatus.OK);
	}
	
	@PostMapping
	public void addNextMonthBillOfCustomer(@RequestBody Bill b)
	{
		//User u=dao.displaySingleCustomer(id);
		dao.InsertNextMonthBill(b);
	}
	
}
