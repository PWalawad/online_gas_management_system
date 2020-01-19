package com.app.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.pojos.Bill;
import com.app.pojos.User;
@Repository
@Transactional
public class IManagerDaoImpl implements IManagerDao
{
	@Autowired
	SessionFactory sf;

	@Override
	public List<User> billsBetweenParticularDateSpan(LocalDate startDate1,LocalDate startDate2) 
	{
		String jpql="select b from Bill b where b.startDate between :startDate1 and :startDate2 order by b.amount";
	   List<Bill> monthlyBill=sf.getCurrentSession().createQuery(jpql, Bill.class).setParameter("startDate1", startDate1)
			   .setParameter("startDate2", startDate2).getResultList();
	   List<User> User = new ArrayList<>();
		
		for (Bill bills : monthlyBill) 
		{
			User.add(bills.getUserBillDeatils());
		}
		return User;
		
	}
	@Override
	public List<User> billsBetweenParticularDateSpanPaidBill(LocalDate startDate1,LocalDate startDate2) 
	{
		String jpql="select b from Bill b where  b.status = 'done' and b.startDate between :startDate1 and :startDate2";
	   List<Bill> monthlyBill=sf.getCurrentSession().createQuery(jpql, Bill.class).setParameter("startDate1", startDate1)
			   .setParameter("startDate2", startDate2).getResultList();
	   List<User> User = new ArrayList<>();
		
		for (Bill bills : monthlyBill) 
		{
			User.add(bills.getUserBillDeatils());
		}
		return User;
		
	}
	@Override
	public List<User> billsBetweenParticularDateSpanPendingBill(LocalDate startDate1, LocalDate startDate2) {
		String jpql="select b from Bill b where  b.status = 'pending' and b.startDate between :startDate1 and :startDate2";
		   List<Bill> monthlyBill=sf.getCurrentSession().createQuery(jpql, Bill.class).setParameter("startDate1", startDate1)
				   .setParameter("startDate2", startDate2).getResultList();
		   //return monthlyBill;
		   
		   List<User> User = new ArrayList<>();
			
			for (Bill bills : monthlyBill) 
			{
				User.add(bills.getUserBillDeatils());
			}
			return User;
	}
	@Override
	public void sendMailTodefaultCustomer(Integer id) 
	{
		
		
	}

	
	
}
