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

	public EmployeeDao() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<User> displayAllCustomers() {
		String jpql = "select u from User u where u.roll='customer'";

		return sf.getCurrentSession().createQuery(jpql, User.class).getResultList();
	}

	@Override
	public List<User> displayDueDateExpiredCustomers(LocalDate dueDate) {

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
		String jpql = "select u from User u where u.id=:id";
		User u = sf.getCurrentSession().createQuery(jpql, User.class).setParameter("id", id).getSingleResult();
		return u;
	}

	@Override
	public int insertNextMonthBill(User u) {

		sf.getCurrentSession().persist(u);
		return 1;
	}

	@Override
	public void sendEmailToCustomers() {

	}

	
}
