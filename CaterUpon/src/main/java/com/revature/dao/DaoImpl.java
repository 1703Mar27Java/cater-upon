package com.revature.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.*;
import com.revature.util.dao.HibernateUtil;
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int saveReview(Review review) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int saveCuisine(Cuisine cuisine) {
		// TODO Auto-generated method stub
		return 0;
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
			newState.setState_Name(state.toString());
			newState.setState_Id(stateCounter);
			stateCounter++;
			System.out.println(newState.getState_Name());
			sesh.save(newState);
		}
		tx.commit();
		sesh.close();
	}

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

	@Override
	public int saveStatusType(StatusType statusType) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int saveUserType(UserType userType) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void persistReview(Review review) {
		// TODO Auto-generated method stub

	}

	@Override
	public void persistCaterer(Caterer caterer) {
		// TODO Auto-generated method stub

	}

	@Override
	public void persistCuisine(Cuisine cuisine) {
		// TODO Auto-generated method stub

	}

	@Override
	public void persistStatusType(StatusType statusType) {
		// TODO Auto-generated method stub

	}

	@Override
	public void persistUserType(UserType userType) {
		// TODO Auto-generated method stub

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

}
