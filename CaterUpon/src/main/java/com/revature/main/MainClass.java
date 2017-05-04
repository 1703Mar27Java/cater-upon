package com.revature.main;

import java.util.ArrayList;
import java.util.List;

import com.revature.dao.DaoImpl;
import com.revature.domain.Order;
import com.revature.domain.State;
import com.revature.domain.User;
import com.revature.enums.States;

public class MainClass {

	public static void main(String[] args) {
		System.out.println("HelloWorld");
		User user = new User();
		Order order = new Order();
		List<User> users = new ArrayList<User>();
		
		System.out.println(user.toString());
		
		DaoImpl dao = new DaoImpl();
		
		dao.persistUser(user);
		dao.persistOrder(order);
		
		int stateCounter = 0;
//		for(States state : States.values())
//		{
//			State newState = new State();
//			newState.setState_Name(state.toString());
//			newState.setState_Id(stateCounter);
//			stateCounter++;
//			dao.persistState(newState);
//		}
	}

}
