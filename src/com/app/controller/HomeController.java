package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.app.dao.IHomeDao;
import com.app.pojos.User;


@CrossOrigin
@RestController
@RequestMapping("/home")
public class HomeController 
{
	 @Autowired 
	    private IHomeDao dao;
	 
	 @PostMapping("/login")
	 public User logIn(@RequestBody User u)
	 {
		User findUser=dao.signIn(u) ;
		return findUser;	 
	 }
	
}
