package com.app.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.app.pojos.Bill;
import com.app.pojos.User;
@Repository
@Transactional
public class EmployeeDao implements IEmployeeDao {
  
	@Autowired
	private SessionFactory sf;
	
	public EmployeeDao()  {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<User> displayAllCustomers() {
		String jpql="select u from User u where u.roll='customer'";
		
		return sf.getCurrentSession().createQuery(jpql, User.class).getResultList();
	}

	@Override
	public List<User> displayDueDateExpiredCustomers(LocalDate endDate) 
	{
		
		String jpql="select b Bill b where b.endDate >:endDate";
		List<Bill> user_id=sf.getCurrentSession().createQuery(jpql, Bill.class).setParameter("endDate", endDate).getResultList();
		List<User> expiredUser=new ArrayList<>();
		for (Bill bills : user_id)
		{
		  String jpql2="select u from User u where u.bills=:bills";
		  User u=sf.getCurrentSession().createQuery(jpql2,User.class).setParameter("bills", bills).getSingleResult();
		  expiredUser.add(u);
         }
		return expiredUser;
	}

	@Override
	public User displaySingleCustomer(int id) {
		String jpql="select u from User u where u.id=:id";
		User u=sf.getCurrentSession().createQuery(jpql,User.class).setParameter("id", id).getSingleResult();
		return u;
	}

	@Override
	public int InsertNextMonthBill(Bill b) {
		
		
		sf.getCurrentSession().persist(b);
		return 1;
	}
	
	@Override
	public void SendEmailToCustomers() {
		
		
	}

}
