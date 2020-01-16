package com.app.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class ManagerDaoImpl implements IManagerDao
{
  @Autowired
  private SessionFactory sf;
  
}
