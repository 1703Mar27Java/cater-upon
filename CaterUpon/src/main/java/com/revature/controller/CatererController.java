package com.revature.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.revature.dao.DaoImpl;
import com.revature.domain.User;

@Controller
public class CatererController {

	@RequestMapping(value = "/404")
	public String error404() {
		System.out.println("custom error handler");
		return "/error/404";
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
			if (dao.login(p)) {
				User u = new User();
				u=dao.getUserByUsername(p.getUser_Username());
				System.out.println(u);
				m.addAttribute("user_Username", p.getUser_Username());
				m.addAttribute("user_Password", p.getUser_Password());
				m.addAttribute("userBean", u);
				return "caterer";
			}
		}
		return "index";
	}

	@RequestMapping(value = { "/logout" }, method = RequestMethod.GET)
	public String addPerson(Model m) {
		return "logout";
	}
}
