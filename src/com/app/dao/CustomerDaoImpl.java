package com.app.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.pojos.Address;
import com.app.pojos.Bankdeatils;
import com.app.pojos.Bill;
import com.app.pojos.User;
import com.app.pojos.UserType;

@Repository
@Transactional
public class CustomerDaoImpl implements ICustomerDao {

	@Autowired
	private SessionFactory sf;

	@Override
	public List<Bill> allBills(Integer id) 
	{
		String jpql="select u from  User u where u.id = :id ";
		User u=sf.getCurrentSession().createQuery(jpql, User.class).setParameter("id", id).getSingleResult();
		return u.getBills();
		
	}

	@Override
	public List<Bill> myPendingaBills(Integer id)
	{

		String jpql = "select b from Bill b where b.status = 'pending'";
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

	@Override
	public List<Bill> myPaidBills(Integer id) {
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

	@Override
	public void registerMeOnline(User newUser)
	{
		newUser.setRoll(UserType.customer);
		sf.getCurrentSession().persist(newUser);
	}

	@Override
	public void addbankdetails(Bankdeatils b,Integer id) 
	{
		String jpql="select u from User u where u.id = :id";
		User newUser=sf.getCurrentSession().createQuery(jpql, User.class).setParameter("id", id).getSingleResult();
		newUser.setBank(b);
		
	}

	@Override
	public void addAddress(Address a, Integer id) 
	{
		String jpql="select u from User u where u.id = :id";
		User newUser=sf.getCurrentSession().createQuery(jpql, User.class).setParameter("id", id).getSingleResult();
		newUser.addAddress(a);
		
		
	}

	

}
