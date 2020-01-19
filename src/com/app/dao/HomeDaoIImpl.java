package com.app.dao;

import javax.persistence.NoResultException;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.passwordencryption.PasswordUtils;
import com.app.pojos.User;
import com.app.pojos.UserType;

import sun.security.util.Password;

@Repository
@Transactional
public class HomeDaoIImpl implements IHomeDao
{
	@Autowired
	private SessionFactory sf;

	@Override
	public User signIn(User u) 
	{
		try {
			Integer consumer_Employee_No=u.getConsumer_Employee_No();
			
		
			String jpql="select u from User u where u.consumer_Employee_No =:consumer_Employee_No";
			User findUser=sf.getCurrentSession().createQuery(jpql, User.class).setParameter("consumer_Employee_No", consumer_Employee_No).getSingleResult();
			String saltCode=findUser.getSaltPassword();
			String toVerifypassword=u.getPassword();
			System.out.println(saltCode+""+toVerifypassword);
			String encrPassFromDatabase=PasswordUtils.generateSecurePassword(toVerifypassword, saltCode);
			if(encrPassFromDatabase.equals(findUser.getPassword()))
				return findUser;
			else
				return	null;
			
		}
		catch (NoResultException nre ) {
			
			return null;
			
		}
		
		
	}

	@Override
	public String logout(User u) {
		try {
			
			Integer consumer_Employee_No =u.getConsumer_Employee_No();
			String jpql="select u from User u where u.consumer_Employee_No =:consumer_Employee_No";
			User findUser=sf.getCurrentSession().createQuery(jpql, User.class).setParameter("consumer_Employee_No", consumer_Employee_No).getSingleResult();
			   return "Logout  successfull"+findUser.getName();
		}
		catch (NoResultException nre ) {
			return "Invalid Login";
		}
		
	}

}
