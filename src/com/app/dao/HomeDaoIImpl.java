package com.app.dao;

import javax.persistence.NoResultException;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.pojos.User;
import com.app.pojos.UserType;

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
			String password=u.getPassword();
			String jpql="select u from User u where u.consumer_Employee_No =:consumer_Employee_No and u.password=:password";
			User findUser=sf.getCurrentSession().createQuery(jpql, User.class).setParameter("consumer_Employee_No", consumer_Employee_No).setParameter("password",password).getSingleResult();
			System.out.println(findUser.getRoll());
			
				return findUser;
			
		}
		catch (NoResultException nre ) {
			System.out.println("null yetey yaar fucking value");
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
