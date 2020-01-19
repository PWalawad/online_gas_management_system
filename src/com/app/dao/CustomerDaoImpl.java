package com.app.dao;
import com.app.passwordencryption.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.model.BillModel;
import com.app.pojos.Address;
import com.app.pojos.Bankdeatils;
import com.app.pojos.Bill;
import com.app.pojos.User;
import com.app.pojos.UserType;
import com.app.pojos.offlineDatabaseOfUser;

@Repository
@Transactional
public class CustomerDaoImpl implements ICustomerDao {

	@Autowired
	private SessionFactory sf;
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
//////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public List<Bill> myPendingaBills(Integer id)
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
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public List<Bill> myPaidBills(Integer id) {
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
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public User registerMeOnline(User newUser)
	{
		
			Integer consumer_Employee_No=newUser.getConsumer_Employee_No();
			String oldjpql="select o from offlineDatabaseOfUser o where o.consumer_Employee_No =:consumer_Employee_No";
			offlineDatabaseOfUser oldUser=sf.getCurrentSession().createQuery(oldjpql, offlineDatabaseOfUser.class).setParameter("consumer_Employee_No",consumer_Employee_No).getSingleResult();
			System.out.println(oldUser.getConsumer_Employee_No());
			if(oldUser.getConsumer_Employee_No().equals(newUser.getConsumer_Employee_No()))
			{
				newUser.setRoll(UserType.customer);
				String saltCode=PasswordUtils.getSalt(6);
				newUser.setSaltPassword(saltCode);
				System.out.println(saltCode);
				String encyptedPassword=PasswordUtils.generateSecurePassword(newUser.getPassword(), saltCode);
	
				newUser.setPassword(encyptedPassword);
				
				sf.getCurrentSession().persist(newUser);
				return newUser;
			}
			
			else
				return null;
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public User addbankdetails(Bankdeatils b,Integer id) 
	{
		
		try{String jpql="select u from User u where u.id = :id";
		User newUser=sf.getCurrentSession().createQuery(jpql, User.class).setParameter("id", id).getSingleResult();
		newUser.setBank(b);
		return newUser;
	}
		catch (NoResultException nre ) 
		{
	        return null;
			
		}
		
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public User forGetPassword(User us)
	{
		Integer consumer_Employee_No=us.getConsumer_Employee_No();
		String email=us.getEmail();
		System.out.println(us.getPassword());
		
		String jpql="select u from User u where u.consumer_Employee_No = :consumer_Employee_No and u.email=:email";
		User newUser=sf.getCurrentSession().createQuery(jpql, User.class).setParameter("consumer_Employee_No", consumer_Employee_No).setParameter("email", email).getSingleResult();
		if(newUser!=null)
		{
			String saltCode=PasswordUtils.getSalt(6);
			newUser.setSaltPassword(saltCode);
			
			String encyptedPassword=PasswordUtils.generateSecurePassword(us.getPassword(), saltCode);
			newUser.setPassword(encyptedPassword);
			
			sf.getCurrentSession().update(newUser);
		}
		return null;
	}
	
	/////////////////////////////////////////////////////////////////////
	@Override
	public User addAddress(Address a, Integer id) 
	{
			try{
				String jpql="select u from User u where u.id = :id";
			User newUser=sf.getCurrentSession().createQuery(jpql, User.class).setParameter("id", id).getSingleResult();
			newUser.addAddress(a);
			return newUser;
			
		}
		catch (NoResultException nre ) 
		{
	        return null;
			
		}
		
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public BillModel paybill(Integer id)
	{
		String jpql="select b from Bill b where b.id = :id";
		BillModel bm=new BillModel();
		
		try
		{
			Bill unPaid=sf.getCurrentSession().createQuery(jpql, Bill.class).setParameter("id", id).getSingleResult();
		
					unPaid.setStatus("done");
					unPaid.setPaymentDoneDate(LocalDate.now());
					bm.setAmount(unPaid.getAmount());
					bm.setBillNo(unPaid.getBillNo());
					bm.setStatus(unPaid.getStatus());
					return bm;
					 
		}
		catch(NoResultException nre)
		{
			return null;
		}
			
	
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
