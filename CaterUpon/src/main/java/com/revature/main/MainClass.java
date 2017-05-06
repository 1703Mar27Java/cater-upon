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
		user.setUser_Username("user");
		user.setUser_Password("pass");
		
		System.out.println(dao.login(user));
	}
}