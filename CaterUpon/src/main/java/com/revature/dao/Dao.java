package com.revature.dao;

import java.util.List;

import com.revature.domain.Order;
import com.revature.domain.State;
import com.revature.domain.User;

public interface Dao {

	// Returns list from table
	public List<User> getUsers();
	public List<Order> getOrders();
	
	// Returns a User/Caterer by Id
	public User getUserById(int id);
	public Order getOrderById(int id);
	
	// Saves a User/Caterer
	public int saveUser(User user);
	public int saveOrder(Order order);
	
	// Persist User/Caterer
	public void persistUser(User user);
	public void persistOrder(Order order);
	
	public void persistState(State state);
}
