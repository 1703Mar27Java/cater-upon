package com.revature.controller;

import java.util.List;
import java.util.ListIterator;

import javax.servlet.http.HttpSession;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.revature.dao.DaoImpl;
import com.revature.domain.*;
import com.revature.enums.StatusTypes;
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

	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public @ResponseBody String searchData(@RequestParam String order, @RequestParam String cuisine,
			@RequestParam String city, @RequestParam String zip) {
		DaoImpl dao = new DaoImpl();

		if (zip.isEmpty())
			zip = String.valueOf(0);
		List<Caterer> caterers = dao.getCatererRefinedSearch(order, cuisine, city, Integer.valueOf(zip));

		s.setAttribute("list", caterers);
		return caterers.toString();

	}
	
	@RequestMapping(value = "/ViewCaterer", method = RequestMethod.POST)
	public @ResponseBody ModelAndView ViewCaterer(@RequestParam int selectedCaterer) {
		System.out.println("made it here");
		s.setAttribute("selectedCaterer", selectedCaterer);
		DaoImpl dao = new DaoImpl();
	
		
		return new ModelAndView("redirect:/CatererProfile");
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
	@Transactional

	@RequestMapping(value = "/setComment", method = RequestMethod.POST)
	public @ResponseBody String setComment( @RequestParam String oid, @RequestParam String cmt, @RequestParam String rate, @RequestParam String user) {
		@SuppressWarnings("unchecked")
		List<Order>  oBean =  (List<Order>) s.getAttribute("uOrders");
		DaoImpl dao = new DaoImpl();
		
		//scan through order bean, remove the current order
		//then set its flag, then place it back in the bean.
		Order o = new Order();
		for (ListIterator<Order> iter = oBean.listIterator(); iter.hasNext(); ) {
		    Order a = iter.next();
		    if (a.getOrder_Id()==Integer.parseInt(oid)) {
		    	o=a;
		    	iter.remove();
		    	break;
		    }
		}
		o.setOrder_revFlag(1);
		oBean.add(o);
		//create and set values of the new order
		Review r = new Review();
		r.setReview_Author(Integer.parseInt(user));
		r.setReview_Message(cmt);
		r.setReview_Rating(Integer.parseInt(rate));
		r.setReview_Caterer(o.getOrder_Caterer().getCaterer_Id());
		//save to db and session
		dao.persistReview(r);
		dao.updateOrder(o);
		s.setAttribute("uOrders", oBean);
		return "";
		

	}

	@Transactional
	@RequestMapping(value = "/setPending", method = RequestMethod.POST)
	public @ResponseBody String setPending( @RequestParam String oid, @RequestParam String stat) {
		@SuppressWarnings("unchecked")
		List<Order>  oBean =  (List<Order>) s.getAttribute("uOrders");
		DaoImpl dao = new DaoImpl();
		
		//scan through order bean, remove the current order
		//then set its flag, then place it back in the bean.
		Order o = new Order();
		for (ListIterator<Order> iter = oBean.listIterator(); iter.hasNext(); ) {
		    Order a = iter.next();
		    if (a.getOrder_Id()==Integer.parseInt(oid)) {
		    	o=a;
		    	iter.remove();
		    	break;
		    }
		}
		StatusType st = null;
		if (stat.equals("Approve"))
			st=new StatusType(2,StatusTypes.Approved);
		else if (stat.equals("Deny"))
			st=new StatusType(3,StatusTypes.Declined);
		o.setOrder_Status(st);
		//System.out.println(o);
		//System.out.println(oBean);
		oBean.add(o);
		//create and set values of the new order
		
		dao.updateOrder(o);
		s.setAttribute("uOrders", oBean);
		return "";
		
}
	
	@Transactional
	@RequestMapping(value = "/setUpcoming", method = RequestMethod.POST)
	public @ResponseBody String setUpcoming( @RequestParam String oid, @RequestParam String stat) {
		@SuppressWarnings("unchecked")
		List<Order>  oBean =  (List<Order>) s.getAttribute("uOrders");
		DaoImpl dao = new DaoImpl();
		
		//scan through order bean, remove the current order
		//then set its flag, then place it back in the bean.
		Order o = new Order();
		for (ListIterator<Order> iter = oBean.listIterator(); iter.hasNext(); ) {
		    Order a = iter.next();
		    if (a.getOrder_Id()==Integer.parseInt(oid)) {
		    	o=a;
		    	iter.remove();
		    	break;
		    }
		}
		StatusType st = null;
		if (stat.equals("Fulfilled"))
			st=new StatusType(5,StatusTypes.Fulfilled);
		else if (stat.equals("Cancelled"))
			st=new StatusType(3,StatusTypes.Declined);
		o.setOrder_Status(st);
		//System.out.println(o);
		//System.out.println(oBean);
		oBean.add(o);
		//create and set values of the new order
		
		dao.updateOrder(o);
		s.setAttribute("uOrders", oBean);
		return "";
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
					//get user orders
					List<Order> orders=dao.getOrdersByCustId(u.getUser_Id());

					System.out.println(orders);
					
					s.setAttribute("uOrders", orders);
					return new ModelAndView("redirect:/user");
				} else {
					//get caterer's orders
					Caterer c = dao.getCatererByUserId(u.getUser_Id());
					List<Order> orders=dao.getOrdersByCatererId(c.getCaterer_Id());
					s.setAttribute("uOrders", orders);
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
	@RequestMapping(value = { "/caterer" }, method = RequestMethod.GET)
	public String catererView(ModelAndView m) {
		return "caterer";
	}
	
	@RequestMapping(value = { "/CatererProfile" }, method = RequestMethod.GET)
	public String CatererProfile(ModelAndView m) {
		return "CatererProfile";
	}

	@RequestMapping(value = { "/logout" }, method = RequestMethod.GET)
	public String addPerson(Model m) {
		return "logout";
	}
}
