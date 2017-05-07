package com.revature.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.revature.dao.DaoImpl;
import com.revature.domain.User;
import com.revature.domain.UserType;
import com.revature.enums.UserTypes;

@Controller
public class CatererController {

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
		System.out.println("pass resetting");
		DaoImpl dao = new DaoImpl();
		User newU = new User();
		newU = dao.getUserByUsername(u);
		newU.setUser_Password(pw);
		dao.updateUser(newU);
		System.out.println(dao.getUserByUsername(u));
		return "successfully returned";
		
	}

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public ModelAndView welcomePage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("index");
		model.addObject("command", new User());
		return model;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute("command") User p, Model m) {
		if (p != null) {
			DaoImpl dao = new DaoImpl();
			// login test
			// if we fail the login we go back to the login page
			// else we check what page to reroute to based on user in DB
			if (dao.login(p)) {
				User u = new User();
				u = dao.getUserByUsername(p.getUser_Username());
				System.out.println("Logging in with user: " + u);
				m.addAttribute("userBean", u);

				// User types from DB to reroute to correct page
				// 1: Customer
				// 2: Caterer
				UserType t = u.getUser_UserType();
				
				if (t.getUserType_Type()==UserTypes.Customer) {
					return "user";
				} else
					return "aterer";

			}
		}
		return "index";
	}

	@RequestMapping(value = { "/logout" }, method = RequestMethod.GET)
	public String addPerson(Model m) {
		return "logout";
	}
}
