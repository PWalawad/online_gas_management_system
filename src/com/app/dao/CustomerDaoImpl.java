package com.app.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

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
	public List<Bill> myPendingaBills(Integer id)
	{
try {

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
	catch (NoResultException nre ) 
	{
	    return null;
		
	}
}

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

	@Override
	public User registerMeOnline(User newUser)
	{
		try
		{
			newUser.setRoll(UserType.customer);
			sf.getCurrentSession().persist(newUser);
			return newUser;
		}
		
		catch (NoResultException nre ) 
		{
	        return null;
			
		}
	}

	@Override
	public Bankdeatils addbankdetails(Bankdeatils b,Integer id) 
	{
		
		try{String jpql="select u from User u where u.id = :id";
		User newUser=sf.getCurrentSession().createQuery(jpql, User.class).setParameter("id", id).getSingleResult();
		newUser.setBank(b);
		return b;
	}
		catch (NoResultException nre ) 
		{
	        return null;
			
		}
		
	}

	@Override
	public Address addAddress(Address a, Integer id) 
	{
			try{
				String jpql="select u from User u where u.id = :id";
			User newUser=sf.getCurrentSession().createQuery(jpql, User.class).setParameter("id", id).getSingleResult();
			newUser.addAddress(a);
			return a;
			
		}
		catch (NoResultException nre ) 
		{
	        return null;
			
		}
		
	}

	@Override
	public Integer paybill(Integer id)
	{
		String jpql="select u from User u where u.id = :id";
		try
		{
			User user=sf.getCurrentSession().createQuery(jpql, User.class).setParameter("id", id).getSingleResult();
			   
			
		           List<Bill> unpaidBill=this.myPendingaBills(id);
					int Total=0;
					for(Bill singleUnPaid : unpaidBill)
					{
						Total=(int) (Total+singleUnPaid.getAmount());
						
					}
					 if(Total==0)
						return 0;
					 else
					    return Total;
				
		}
		catch(NoResultException nre)
		{
			return 404;
		}
			
	
	}

	

}
