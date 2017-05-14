package com.revature.main;

import com.revature.dao.DaoImpl;
import com.revature.domain.*;

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
		Caterer caterer = new Caterer();
//		State state = new State();
//		
//		state.setState_Name(States.Alaska);
//		caterer.setCaterer_State(state);
//		
		dao.persistCaterer(caterer);
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