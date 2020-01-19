package com.app.dao;

import java.time.LocalDate;
import java.util.List;

import com.app.pojos.Bill;
import com.app.pojos.User;

public interface IManagerDao 
{

	
	public List<User> billsBetweenParticularDateSpan(LocalDate startDate1,LocalDate startDate2);

	public List<User> billsBetweenParticularDateSpanPaidBill(LocalDate startDate1, LocalDate startDate2);
	
	public List<User> billsBetweenParticularDateSpanPendingBill(LocalDate startDate1, LocalDate startDate2);
	
	public void sendMailTodefaultCustomer(Integer id);
	
	
	
	
	
	
	
	
	
	
	
	//public Number highestPaidBillsForCurrentMOnth(LocalDate startDate1,LocalDate startDate2);
	//,String toDay
	
	
	/* "SELECT e FROM Employee e WHERE e.joinDate BETWEEN '1990-01-01' AND '2010-05-01' order "
	//                      + "by e.joinDate");

	 * List<Employee> resultList = query.getResultList();
	 * =======================================================================
           "SELECT e FROM Employee e WHERE e.joinDate BETWEEN :startDate AND :endDate order "
                      + "by e.joinDate");
      query.setParameter("startDate", localToTimeStamp(LocalDate.of(2005,1,1)));
      query.setParameter("endDate", localToTimeStamp(LocalDate.of(2018,1,1)));
      List<Employee> resultList = query.getResultList();
      
                  Query query = em.createQuery(
              "SELECT e FROM Employee e WHERE e.joinDate BETWEEN '1990-01-01' AND '2010-05-01' order "
                      + "by e.joinDate");
      List<Employee> resultList = query.getResultList();
      ==============================================================
      "SELECT e FROM Employee e WHERE e.joinDate BETWEEN :startDate AND :endDate order "
                      + "by e.joinDate");
      query.setParameter("startDate", localToTimeStamp(LocalDate.of(2005,1,1)));
      query.setParameter("endDate", localToTimeStamp(LocalDate.of(2018,1,1)));
      List<Employee> resultList = query.getResultList();
        ================================================================
        
                       Query query = em.createQuery(
              "SELECT e FROM Employee e WHERE e.joinDate NOT BETWEEN :startDate AND :endDate order "
                      + "by e.joinDate");
      query.setParameter("startDate", localToTimeStamp(LocalDate.of(2005,1,1)));
      query.setParameter("endDate", localToTimeStamp(LocalDate.of(2018,1,1)));
      List<Employee> resultList = query.getResultList();
	 */
	
}
