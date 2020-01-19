package com.app.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import com.app.pojos.Bill;
import com.app.pojos.User;

@Repository
@Transactional
public class EmployeeDao implements IEmployeeDao {

	@Autowired
	private SessionFactory sf;

	public EmployeeDao() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<User> displayAllCustomers() {
		try{String jpql = "select u from User u where u.roll='customer'";

		return sf.getCurrentSession().createQuery(jpql, User.class).getResultList();}
		catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<User> displayDueDateExpiredCustomers(LocalDate dueDate) {

		try
			{
				String jpql = "select b from Bill b where b.dueDate < :dueDate and b.status = 'pending'";
			List<Bill> user_id = sf.getCurrentSession().createQuery(jpql, Bill.class).setParameter("dueDate", dueDate)
					.getResultList();
			List<User> expiredUser = new ArrayList<>();
	
			for (Bill bills : user_id) 
			{
				expiredUser.add(bills.getUserBillDeatils());
			}
			return expiredUser;
	  }
		catch (Exception e) {
			return null;
		}
		
	}
	/*
	 * @Override
	public List<User> displayDueDateExpiredCustomers(LocalDate dueDate) {

		String jpql = "select b from Bill b where b.dueDate < :dueDate";
		List<Bill> user_id = sf.getCurrentSession().createQuery(jpql, Bill.class).setParameter("dueDate", dueDate)
				.getResultList();
		List<User> expiredUser = new ArrayList<>();

		for (Bill bills : user_id) 
		{
			expiredUser.add(bills.getUserBillDeatils());
		}
		return expiredUser;
	}
	 */

	@Override
	public User displaySingleCustomer(int id) {
	
		try{
			String jpql = "select u from User u where u.id=:id";
		
		User u = sf.getCurrentSession().createQuery(jpql, User.class).setParameter("id", id).getSingleResult();
		return u;
		}
		catch (Exception e) {
			return null;
		}
	}

	@Override
	public int insertNextMonthBill(User u) {

		sf.getCurrentSession().update(u);
		return 1;
	}

	@Override
	public void sendEmailToCustomers() {

	}

	@Override
	public User deleteCustomer(Integer id) {
		try {
			String jpql = "select u from User u where u.id=:id";
			User u = sf.getCurrentSession().createQuery(jpql, User.class).
					setParameter("id", id).getSingleResult();
			sf.getCurrentSession().delete(u);
			return u;
		}
		catch (Exception e) {
			return null;
		}
		
	}
//////////////////////////////////////////////////////
	
	@Override
	public List<Bill> allBills(Integer id) 
	{
       try 
		{
    	   
    	   String jpql="select u from  User u where u.id = :id ";
		User u=sf.getCurrentSession().createQuery(jpql, User.class).setParameter("id", id).getSingleResult();
		return u.getBills();
			
		}
		catch (NoResultException nre ) 
		{
	        return null;
			
		}
		
		
	}

	@Override
	public List<Bill> pendingaBills(Integer id)
	{
try {

		String jpql = "select b from Bill b where b.status ='pending'";
		List<Bill> user_id = sf.getCurrentSession().createQuery(jpql, Bill.class).getResultList();
		List<Bill> allPendingBills=new ArrayList<>();

		for (Bill bills : user_id) 
		{
			Integer fingId=bills.getUserBillDeatils().getId();
			System.out.println("id="+id);
				if(fingId.equals(id))
					    allPendingBills.add(bills);
				else
					continue;
		}
		return allPendingBills;
   }
	catch (NoResultException nre ) 
	{
	    return null;
		
	}
}

	@Override
	public List<Bill> paidBills(Integer id) {
		try
		{
			
			String jpql = "select b from Bill b where b.status = 'done'";
			List<Bill> user_id = sf.getCurrentSession().createQuery(jpql, Bill.class).getResultList();
			List<Bill> allDoneBills=new ArrayList<>();
	
			for (Bill bills : user_id) 
			{
				Integer fingId=bills.getUserBillDeatils().getId();
				System.out.println("id="+id);
					if(fingId.equals(id))
						allDoneBills.add(bills);
					else
						continue;
			}
			return allDoneBills;
	    }
		catch (NoResultException nre ) 
		{
	        return null;
			
		}
	}
	
	
	
}
