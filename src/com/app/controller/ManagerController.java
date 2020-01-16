package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dao.IManagerDao;

@CrossOrigin
@RestController
@RequestMapping("/manager")
public class ManagerController {

	@Autowired
	private IManagerDao dao;
	public ManagerController()
	{
		
	}

}
