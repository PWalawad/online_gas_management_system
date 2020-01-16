package com.app.dao;

import com.app.pojos.User;

public interface IHomeDao 
{
	public User signIn(User u);

	public String logout(User u);
  
}
