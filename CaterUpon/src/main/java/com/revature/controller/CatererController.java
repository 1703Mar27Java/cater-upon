package com.revature.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.revature.dao.DaoImpl;
import com.revature.domain.Order;
import com.revature.domain.User;
import com.revature.domain.UserType;
import com.revature.enums.UserTypes;

@Controller
public class CatererController {
	 @Autowired 
	 private HttpSession s;
	
	@RequestMapping(value = "/404")
	public String error404() {
		System.out.println("custom error handler");
		return "/error/404";
	}
	
	@RequestMapping(value = "/userSearch")
	public String userSearch() {
		return "userSearch";
	}
	
	
	@RequestMapping(value = "/resetPass", method = RequestMethod.POST)
	public @ResponseBody String resetPw(@RequestParam String pw, @RequestParam String u) {
		DaoImpl dao = new DaoImpl();
		User newU = new User();
		newU = dao.getUserByUsername(u);
		newU.setUser_Password(pw);
		dao.updateUser(newU);
		s.setAttribute("userBean", newU);
		System.out.println(dao.getUserByUsername(u));
		return "{\"msg\":\"success\"}";
		
	}
	
	@RequestMapping(value = "/setEmail", method = RequestMethod.POST)
	public @ResponseBody String setEmail(@RequestParam String em, @RequestParam String u) {
		DaoImpl dao = new DaoImpl();
		User newU = new User();
		newU = dao.getUserByUsername(u);
		newU.setUser_Email(em);
		dao.updateUser(newU);
	
		s.setAttribute("userBean", newU);
		System.out.println(dao.getUserByUsername(u));
		return newU.getUser_Email().toString();
		
	}

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public ModelAndView welcomePage() {
		/*
		DaoImpl dao = new DaoImpl();
		Order o = new Order();
		o.setOrder_NumOfAttendees(25);
		
		o.setOrder_Customer(dao.getUserByUsername("user"));
		o.setOrder_Comment("test");
		o.setOrder_City("Tampa");
		o.setOrder_Zipcode(33624);
		
		o.setOrder_Amount(111);
		dao.persistOrder(o);
		*/
		ModelAndView model = new ModelAndView();
		
		model.setViewName("index");
		model.addObject("command", new User());
		return model; 
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("command") User p, Model m) {
		if (p != null) {
			DaoImpl dao = new DaoImpl();
			
			// login test
			// if we fail the login we go back to the login page
			// else we check what page to reroute to based on user in DB
			if (dao.login(p)) {
				User u = new User(); 
				u = dao.getUserByUsername(p.getUser_Username());
				System.out.println("Logging in with user: " + u); 
				s.setAttribute("userBean", u);

				// User types from DB to reroute to correct page
				// 1: Customer
				// 2: Caterer
				UserType t = u.getUser_UserType();
				if (t.getUserType_Type()==UserTypes.Customer) {
					
					List<Order> orders=dao.getOrdersByCustId(u.getUser_Id());
					System.out.println(orders);
					s.setAttribute("uOrders", orders);
					
					return new ModelAndView("redirect:/user");
				} else {
					return new ModelAndView("redirect:/caterer");
				}
			}
		}
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = { "/user" }, method = RequestMethod.GET)
	public String userView(ModelAndView m) {
		return "user";
	}
	
	@RequestMapping(value = { "/logout" }, method = RequestMethod.GET)
	public String addPerson(Model m) {
		return "logout";
	}
}
