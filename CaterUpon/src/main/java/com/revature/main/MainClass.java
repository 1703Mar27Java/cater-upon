package com.revature.main;

import java.util.ArrayList;
import java.util.List;

import com.revature.dao.DaoImpl;
import com.revature.domain.Caterer;
import com.revature.domain.Order;
import com.revature.domain.State;
import com.revature.domain.StatusType;
import com.revature.domain.User;
import com.revature.domain.UserType;
import com.revature.enums.States;
import com.revature.enums.StatusTypes;
import com.revature.enums.UserTypes;
import com.revature.util.dao.Util;

public class MainClass {

	public static void main(String[] args) {
//		System.out.println("HelloWorld");
//		User user = new User();
//		Order order = new Order();
//		List<User> users = new ArrayList<User>();
//		
//		System.out.println(user.toString());
//		
		DaoImpl dao = new DaoImpl();
//		
//		User newuser = new User();
//		UserType ut = new UserType();
//		
//		ut.setUserType_Id(1);
//		ut.setUserType_Type(UserTypes.Customer);
//		
//		newuser.setUser_Username("shit");
//		newuser.setUser_Password("PassWord");
//		newuser.setUser_UserType(ut);
//		
////		StatusType st = new StatusType();
////		st.setStatus_Id(2);
////		st.setStatus_Type(StatusTypes.Approved);
////		order.setOrder_Status(st);
//		Caterer caterer = new Caterer();
//		State state = new State();
//		
//		state.setState_Name(States.Alaska);
//		caterer.setCaterer_State(state);
//		
//		dao.saveCaterer(caterer);
//		
//		//dao.persistOrder(order);
//		//dao.persistUser(newuser);
//		//dao.saveAllStates();
		
//		Util util = new Util();
//		System.out.println(util.DistanceBetweenTwoZipcodes(34205, 32792));
//		System.out.println(util.DistanceBetweenTwoCity("Bradenton", "Orlando"));
//		
		
		//System.out.println(dao.findAllCatererByZip(34212));
	}
}