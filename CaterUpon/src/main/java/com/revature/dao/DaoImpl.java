package com.revature.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.*;
import com.revature.util.dao.HibernateUtil;
import com.revature.util.dao.Util;
import com.revature.domain.*;
import com.revature.enums.Cuisines;
import com.revature.enums.States;

public class DaoImpl implements Dao {	
	List<User> AllUsers;

	public DaoImpl() {
		super();
	}

	@SuppressWarnings("unchecked")
	@Override
	public User getUserByUsername(String userName) {
		User user = null;
		List<User> users = new ArrayList<User>();
		Session sesh = HibernateUtil.getSession();
		users = sesh.createQuery("from User where USER_USERNAME = :var").setString("var", userName).list();
		if (!users.isEmpty()) {
			user = users.get(0);
		}
		return user;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public User getUserById(int id) {
		User user = null;
		List<User> users = new ArrayList<User>();
		Session sesh = HibernateUtil.getSession();
		users = sesh.createQuery("from User where id = :id").setInteger("id", id).list();
		if (!users.isEmpty()) {
			user = users.get(0);
		}
		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Order getOrderById(int id) {
		Order order = null;
		List<Order> orders = new ArrayList<Order>();
		Session sesh = HibernateUtil.getSession();
		orders = sesh.createQuery("from User where id = :id").setInteger("id", id).list();
		if (!orders.isEmpty()) {
			order = orders.get(0);
		}
		return order;
	}

	@Override
	public int saveUser(User user) {
		Session sesh = HibernateUtil.getSession();
		int result = (int) sesh.save(user);
		return result;
	}

	@Override
	public int saveOrder(Order order) {
		Session sesh = HibernateUtil.getSession();
		int result = (int) sesh.save(order);
		return result;
	}

	@Override
	public void persistUser(User user) {
		Session sesh = HibernateUtil.getSession();
		Transaction tx = sesh.beginTransaction();
		sesh.persist(user);
		tx.commit();
		sesh.close();
	}

	@Override
	public void persistOrder(Order order) {
		Session sesh = HibernateUtil.getSession();
		Transaction tx = sesh.beginTransaction();
		sesh.persist(order);
		tx.commit();
		sesh.close();
	}

	public void persistState(State state) {
		Session sesh = HibernateUtil.getSession();
		Transaction tx = sesh.beginTransaction();
		sesh.persist(state);
		tx.commit();
		sesh.close();
	}

	@Override
	public int saveCaterer(Caterer caterer) {
		Session sesh = HibernateUtil.getSession();
		int result = (int) sesh.save(caterer);
		return result;
	}

	@Override
	public int saveReview(Review review) {
		Session sesh = HibernateUtil.getSession();
		int result = (int) sesh.save(review);
		return result;
	}

	@Override
	public int saveCuisine(Cuisine cuisine) {
		Session sesh = HibernateUtil.getSession();
		int result = (int) sesh.save(cuisine);
		return result;
	}

	@Override
	public int saveState(State state) {
		Session sesh = HibernateUtil.getSession();
		int result = (int) sesh.save(state);
		return result;
	}

	public void saveAllStates() {
		int stateCounter = 0;
		Session sesh = HibernateUtil.getSession();
		Transaction tx = sesh.beginTransaction();

		for (States state : States.values()) {
			State newState = new State();
			newState.setState_Name(state);
			newState.setState_Id(stateCounter);
			stateCounter++;
			System.out.println(newState.getState_Name());
			sesh.save(newState);
		}
		tx.commit();
		sesh.close();
	}
/*
	public void saveAllCuisines() {
		int cuisinesCounter = 0;
		Session sesh = HibernateUtil.getSession();
		Transaction tx = sesh.beginTransaction();

		for (Cuisines cuisine : Cuisines.values()) {
			Cuisine newCuisine = new Cuisine();
			newCuisine.setCuisine_Type(cuisine);
			newCuisine.setCaterer_Id(cuisinesCounter);
			cuisinesCounter++;
			System.out.println(newCuisine.getCuisine_Type());
			sesh.save(newCuisine);
		}
		tx.commit();
		sesh.close();
	}
*/
	@Override
	public int saveStatusType(StatusType statusType) {
		Session sesh = HibernateUtil.getSession();
		int result = (int) sesh.save(statusType);
		return result;
	}

	@Override
	public int saveUserType(UserType userType) {
		Session sesh = HibernateUtil.getSession();
		int result = (int) sesh.save(userType);
		return result;
	}

	@Override
	public void persistReview(Review review) {
		Session sesh = HibernateUtil.getSession();
		Transaction tx = sesh.beginTransaction();
		sesh.persist(review);
		tx.commit();
		sesh.close();
	}

	@Override
	public void persistCaterer(Caterer caterer) {
		Session sesh = HibernateUtil.getSession();
		Transaction tx = sesh.beginTransaction();
		sesh.save(caterer);
		sesh.persist(caterer);
		tx.commit();
		sesh.close();

	}

	@Override
	public void persistCuisine(Cuisine cuisine) {
		Session sesh = HibernateUtil.getSession();
		Transaction tx = sesh.beginTransaction();
		sesh.persist(cuisine);
		tx.commit();
		sesh.close();

	}

	@Override
	public void persistStatusType(StatusType statusType) {
		Session sesh = HibernateUtil.getSession();
		Transaction tx = sesh.beginTransaction();
		sesh.persist(statusType);
		tx.commit();
		sesh.close();

	}

	@Override
	public void persistUserType(UserType userType) {
		Session sesh = HibernateUtil.getSession();
		Transaction tx = sesh.beginTransaction();
		sesh.persist(userType);
		tx.commit();
		sesh.close();

	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean login(User user) {
		Session sesh = HibernateUtil.getSession();
		List<User> users = sesh.createQuery("from User where USER_USERNAME = :username and USER_PASSWORD = :password").
				setString("username", user.getUser_Username()).setString("password", user.getUser_Password()).list();

		if(!users.isEmpty())
		{
			user = users.get(0);
			return true;
		}
		
		sesh.close();
		return false;
	}

	@Override
	public List<Caterer> findAllCatererByCity(String City) {
		List<Caterer> availableCaterer = new ArrayList<Caterer>();
		Session sesh = HibernateUtil.getSession();
		
		List<Caterer> caterers = sesh.createQuery("from Caterer").list();
		Util util = new Util();
		
		for(int i = 0; i < caterers.size(); i++)
		{
			double distance = util.DistanceBetweenTwoCity(City, caterers.get(i).getCaterer_City().toString());
			System.out.println(distance);
			if(caterers.get(i).getCaterer_SearchRadius() > distance)
				availableCaterer.add(caterers.get(i));
		}
		
		return availableCaterer;
	}

	@Override
	public List<Caterer> findAllCatererByZip(int Zipcode) {
		
		List<Caterer> availableCaterer = new ArrayList<Caterer>();
		Session sesh = HibernateUtil.getSession();
		
		List<Caterer> caterers = sesh.createQuery("from Caterer").list();
		Util util = new Util();
		
		for(int i = 0; i < caterers.size(); i++)
		{
			double distance = util.DistanceBetweenTwoZipcodes(Zipcode, caterers.get(i).getCaterer_Zipcode());
			//System.out.println(distance);
			if(caterers.get(i).getCaterer_SearchRadius() > distance)
				availableCaterer.add(caterers.get(i));
		}
		
		return availableCaterer;
	}

	

}
