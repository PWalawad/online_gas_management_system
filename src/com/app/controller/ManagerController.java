package com.app.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dao.IManagerDao;
import com.app.pojos.Bill;
import com.app.pojos.User;


@CrossOrigin
@RestController
@RequestMapping("/manager")
public class ManagerController 
{
   @Autowired
   private IManagerDao dao;
   
  @GetMapping("/monthlyBills")
   public List<User> billsBetweenParticularDateSpan(@RequestParam String startDate1,@RequestParam String startDate2)
   {
	   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

       LocalDate localDate1 = LocalDate.parse(startDate1, formatter);
       LocalDate localDate2 = LocalDate.parse(startDate2, formatter);
       List<User> b= dao.billsBetweenParticularDateSpan(localDate1,localDate2);
	   return b;
   }
  //http://localhost:8080/day13.4_REST_SRVR/manager/monthlyBills?startDate1=2020-02-28&startDate2=2021-03-28
  
  @GetMapping("/monthlyPaidBills")
  public List<User> billsBetweenParticularDateSpanPaidBill(@RequestParam String startDate1,@RequestParam String startDate2)
  {
	  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

      LocalDate localDate1 = LocalDate.parse(startDate1, formatter);
      LocalDate localDate2 = LocalDate.parse(startDate2, formatter);
      List<User> b= dao.billsBetweenParticularDateSpanPaidBill(localDate1,localDate2);
	   return b;
  }
  @GetMapping("/monthlyPendingBills")
  public List<User> billsBetweenParticularDateSpanPendingBill(@RequestParam String startDate1,@RequestParam String startDate2)
  {
	  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

      LocalDate localDate1 = LocalDate.parse(startDate1, formatter);
      LocalDate localDate2 = LocalDate.parse(startDate2, formatter);
     List<User> b= dao.billsBetweenParticularDateSpanPendingBill(localDate1,localDate2);
	   return b;
  }
  @PostMapping("/sendMail/{id}")
  public void sendMailTodefaultCustomer(@PathVariable Integer id)
  {
	  dao.sendMailTodefaultCustomer(id);
  }

}
