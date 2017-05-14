package com.revature.dao;

import java.util.List;
import com.revature.domain.*;

public interface Dao {

	// Returns list from table
	public User getUserByUsername(String userName);
	
	// Returns by Id
	public User getUserById(int id);
	public Order getOrderById(int id);
	public List<Order> getOrdersByCustId(int id);
	public List<Order> getOrdersByCatererId(int id);
	public Caterer getCatererByUserId(int id);
	// Save
	public int saveUser(User user);
	public int saveOrder(Order order);
	public int saveCaterer(Caterer caterer);
	public int saveReview(Review review);
	
	public int saveCuisine(Cuisine cuisine);
	public int saveState(State state);
	public int saveStatusType(StatusType statusType);
	public int saveUserType(UserType userType);
	
	// Persist
	public void persistUser(User user);
	public void persistOrder(Order order);
	public void persistReview(Review review);
	public void persistCaterer(Caterer caterer);
	
	public void persistState(State state);
	public void persistCuisine(Cuisine cuisine);
	public void persistStatusType(StatusType statusType);
	public void persistUserType(UserType userType);
	
	// Functionality 
	public boolean login(User user);
	public List<Caterer> findAllCatererByZip(int Zipcode);
	public List<Caterer> findAllCatererByCity(String City);
	
	//Update
	public void updateUser(User user);
	public void updateOrder(Order o);

	public void updateCaterer(Caterer c);

	public List<Review> getReviewByCatererId(int id);

	User getUserByEmail(String e);

	List<Caterer> getCatererRefinedSearch(String order, String cuisine, String city, int zipcode);

	Caterer getCatererById(int id);
}
