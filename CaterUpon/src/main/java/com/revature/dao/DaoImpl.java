package com.revature.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.*;
import com.revature.util.dao.HibernateUtil;
import com.revature.domain.*;


public class DaoImpl implements Dao {

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUsers() {
		List<User> users = new ArrayList<User>();
		Session sesh = HibernateUtil.getSession();
		users = sesh.createQuery("from User").list();
		return users;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> getOrders() {
		List<Order> orders = new ArrayList<Order>();
		Session sesh = HibernateUtil.getSession();
		orders = sesh.createQuery("from Order").list();
		return orders;
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

}
