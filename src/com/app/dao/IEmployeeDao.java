package com.app.dao;
import java.time.LocalDate;
import java.util.List;

import com.app.pojos.*;;

public interface IEmployeeDao
{
  public List<User> displayAllCustomers();
  public List<User> displayDueDateExpiredCustomers(LocalDate endDate);
  public User displaySingleCustomer(int id);
  public int insertNextMonthBill(User b);
  public void sendEmailToCustomers();
  public User deleteCustomer(Integer id);
 public  List<Bill> paidBills(Integer id);
 public List<Bill> pendingaBills(Integer id);
 public List<Bill> allBills(Integer id);
}
