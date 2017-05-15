package com.revature.controller;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.http.HttpSession;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.revature.dao.DaoImpl;
import com.revature.domain.Caterer;
import com.revature.domain.Order;
import com.revature.domain.Review;
import com.revature.domain.State;
import com.revature.domain.StatusType;
import com.revature.domain.User;
import com.revature.domain.UserType;
import com.revature.enums.Cuisines;
import com.revature.enums.States;
import com.revature.enums.StatusTypes;
import com.revature.enums.UserTypes;
import com.revature.util.dao.RandomStrings;
import com.revature.util.dao.mailer;

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

		s.setAttribute("city", city);
		s.setAttribute("zip", zip);

		if (zip.isEmpty())
			zip = String.valueOf(0);
		List<Caterer> caterers = dao.getCatererRefinedSearch(order, cuisine, city, Integer.valueOf(zip));

		s.setAttribute("list", caterers);
		return caterers.toString();
	}

	@RequestMapping(value = "/createOrder", method = RequestMethod.POST)
	public @ResponseBody ModelAndView createOrder(@RequestParam int numAtt, @RequestParam String comment,
			@RequestParam String eventDate, @RequestParam String eventTime) {

		System.out.println("testing");
		DaoImpl dao = new DaoImpl();
		Order newOrder = new Order();

		String[] ymd = eventDate.split("-");
		int year = Integer.valueOf(ymd[0]);
		int month = Integer.valueOf(ymd[1]);
		int day = Integer.valueOf(ymd[2]);

		String[] hm = eventTime.split(":");

		Date date = new Date();
		date.setYear(year);
		date.setMonth(month - 1);
		date.setDate(day);

		int hour = Integer.valueOf(hm[0]);
		int minute = Integer.valueOf(hm[1]);

		date.setHours(hour);
		date.setMinutes(minute);

		String newDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date);

		newOrder.setOrder_Caterer((Caterer) s.getAttribute("currentCaterer"));
		newOrder.setOrder_City((String) s.getAttribute("city"));
		newOrder.setOrder_Comment(comment);
		newOrder.setOrder_Customer((User) s.getAttribute("userBean"));

		newOrder.setOrder_Date(date);
		newOrder.setOrder_NumOfAttendees(numAtt);
		newOrder.setOrder_State(((Caterer) s.getAttribute("currentCaterer")).getCaterer_State());

		StatusType st = new StatusType(1, StatusTypes.New);

		newOrder.setOrder_Status(st);
		newOrder.setOrder_Zipcode(Integer.valueOf((String) s.getAttribute("zip")));
		System.out.println(st);
		// dao.saveStatusType(st);
		// dao.saveCaterer((Caterer) s.getAttribute("currentCaterer"));
		// dao.saveUser((User) s.getAttribute("userBean"));
		// dao.saveOrder(newOrder);
		System.out.println(newOrder);
		dao.persistOrder(newOrder);
		return new ModelAndView("redirect:/user");
	}

	@RequestMapping(value = "/ViewCaterer", method = RequestMethod.POST)
	public @ResponseBody ModelAndView ViewCaterer(@RequestParam int selectedCaterer,
			@RequestParam String selectedState) {
		s.setAttribute("selectedCaterer", selectedCaterer);
		DaoImpl dao = new DaoImpl();
		Caterer curCaterer = dao.getCatererById(selectedCaterer);
		System.out.println(curCaterer);
		Cuisines cuisines = Cuisines.American;

		List<Review> reviews = dao.getReviewByCatererId(selectedCaterer);

		float totalRating = 0;
		if (!reviews.isEmpty()) {
			for (Review review : reviews) {
				totalRating += review.getReview_Rating();
			}
			totalRating /= reviews.size();
			// totalRating = (float) (Math.round(totalRating*100.0)/100.0);

			DecimalFormat df = new DecimalFormat("###.#");
			totalRating = Float.valueOf(df.format(totalRating));
		}

		s.setAttribute("totalRating", totalRating);
		s.setAttribute("reviews", reviews);
		s.setAttribute("cuisines", cuisines);
		s.setAttribute("currentCaterer", curCaterer);
		s.setAttribute("selecetedState", selectedState);

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

	@RequestMapping(value = "/setDescr", method = RequestMethod.POST)
	public @ResponseBody String setDescr(@RequestParam String d, @RequestParam String u) {
		DaoImpl dao = new DaoImpl();
		Caterer c = new Caterer();
		c = (Caterer) s.getAttribute("catererBean");
		System.out.println(c);
		c.setCaterer_Description(d);
		dao.updateCaterer(c);
		s.setAttribute("catererBean", c);
		return c.getCaterer_Description();

	}

	@RequestMapping(value = "/setLoc", method = RequestMethod.POST)
	public @ResponseBody String setLoc(@RequestParam String city, @RequestParam String state,
			@RequestParam String zip) {
		DaoImpl dao = new DaoImpl();
		Caterer c = new Caterer();
		c = (Caterer) s.getAttribute("catererBean");

		c.setCaterer_City(city);
		State st = new State();
		States q = States.valueOf(state);
		st.setState_Name(q);
		st.setState_Id(q.ordinal() + 1);
		c.setCaterer_State(st);
		c.setCaterer_Zipcode(Integer.parseInt(zip));
		dao.updateCaterer(c);
		s.setAttribute("catererBean", c);
		return c.getCaterer_City() + ", " + c.getCaterer_State().getState_Name().toString() + " "
				+ c.getCaterer_Zipcode();

	}

	@RequestMapping(value = "/setRad", method = RequestMethod.POST)
	public @ResponseBody String setRad(@RequestParam String r, @RequestParam String u) {
		DaoImpl dao = new DaoImpl();
		Caterer c = new Caterer();
		c = (Caterer) s.getAttribute("catererBean");
		System.out.println(c);
		c.setCaterer_SearchRadius(Integer.parseInt(r));
		dao.updateCaterer(c);
		s.setAttribute("catererBean", c);
		return Integer.valueOf(c.getCaterer_SearchRadius()).toString();

	}

	@RequestMapping(value = "/resetEmail", method = RequestMethod.POST)
	public @ResponseBody String resetEmail(@RequestParam String e) {
		DaoImpl dao = new DaoImpl();
		User u = dao.getUserByEmail(e);
		// get string of random 64 alphanumeric characters
		String rand = RandomStrings.randomString(32);
		u.setUser_Password(rand);
		dao.updateUser(u);
		mailer.mail(u.getUser_Email(),
				"This is a notification that your CaterUpon account" + " with username " + u.getUser_Username()
						+ " has had " + "its password reset. \nThe new password is: \n" + rand
						+ " \nIf you feel that this password resset is in error "
						+ ", please contact the administrator of CaterUpon at" + " cater.upon@gmail.com");
		return "";
	}

	@Transactional
	@RequestMapping(value = "/setComment", method = RequestMethod.POST)
	public @ResponseBody String setComment(@RequestParam String oid, @RequestParam String cmt,
			@RequestParam String rate, @RequestParam String user) {
		@SuppressWarnings("unchecked")
		List<Order> oBean = (List<Order>) s.getAttribute("uOrders");
		DaoImpl dao = new DaoImpl();

		// scan through order bean, remove the current order
		// then set its flag, then place it back in the bean.
		Order o = new Order();
		for (ListIterator<Order> iter = oBean.listIterator(); iter.hasNext();) {
			Order a = iter.next();
			if (a.getOrder_Id() == Integer.parseInt(oid)) {
				o = a;
				iter.remove();
				break;
			}
		}
		o.setOrder_revFlag(1);
		oBean.add(o);
		// create and set values of the new order
		Review r = new Review();
		r.setReview_Author(Integer.parseInt(user));
		r.setReview_Message(cmt);
		r.setReview_Rating(Integer.parseInt(rate));
		r.setReview_Caterer(o.getOrder_Caterer().getCaterer_Id());
		// save to db and session
		dao.persistReview(r);
		dao.updateOrder(o);

		s.setAttribute("uOrders", oBean);
		return "";

	}

	@Transactional
	@RequestMapping(value = "/setPending", method = RequestMethod.POST)
	public @ResponseBody String setPending(@RequestParam String oid, @RequestParam String stat) {
		@SuppressWarnings("unchecked")
		List<Order> oBean = (List<Order>) s.getAttribute("uOrders");
		DaoImpl dao = new DaoImpl();

		// scan through order bean, remove the current order
		// then set its flag, then place it back in the bean.
		Order o = new Order();
		for (ListIterator<Order> iter = oBean.listIterator(); iter.hasNext();) {
			Order a = iter.next();
			if (a.getOrder_Id() == Integer.parseInt(oid)) {
				o = a;
				iter.remove();
				break;
			}
		}
		StatusType st = null;
		if (stat.equals("Approve"))
			st = new StatusType(2, StatusTypes.Approved);
		else if (stat.equals("Deny"))
			st = new StatusType(3, StatusTypes.Declined);
		o.setOrder_Status(st);
		// System.out.println(o);
		// System.out.println(oBean);
		oBean.add(o);
		// create and set values of the new order
		mailer.mail(o.getOrder_Customer().getUser_Email(),
				"Your order for the date of " + o.getOrder_Date() + " has been " + st.getStatus_Type().toString()
						+ ". Please contact your caterer if you feel " + "this was in error.");

		dao.updateOrder(o);
		s.setAttribute("uOrders", oBean);
		return "";

	}
	// sadas

	@Transactional
	@RequestMapping(value = "/setUpcoming", method = RequestMethod.POST)
	public @ResponseBody String setUpcoming(@RequestParam String oid, @RequestParam String stat) {
		@SuppressWarnings("unchecked")
		List<Order> oBean = (List<Order>) s.getAttribute("uOrders");
		DaoImpl dao = new DaoImpl();

		// scan through order bean, remove the current order
		// then set its flag, then place it back in the bean.
		Order o = new Order();
		for (ListIterator<Order> iter = oBean.listIterator(); iter.hasNext();) {
			Order a = iter.next();
			if (a.getOrder_Id() == Integer.parseInt(oid)) {
				o = a;
				iter.remove();
				break;
			}
		}
		StatusType st = null;
		if (stat.equals("Fulfilled"))
			st = new StatusType(5, StatusTypes.Fulfilled);
		else if (stat.equals("Cancelled"))
			st = new StatusType(3, StatusTypes.Declined);
		o.setOrder_Status(st);
		// System.out.println(o);
		// System.out.println(oBean);
		oBean.add(o);

		// create and set values of the new order
		mailer.mail(o.getOrder_Customer().getUser_Email(),
				"Your order for the date of " + o.getOrder_Date() + " has been " + st.getStatus_Type().toString()
						+ ". Please contact your caterer if you feel " + "this was in error.");
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

	@RequestMapping(value = { "/" }, method = RequestMethod.POST)
	public ModelAndView welcomePage() {
		ModelAndView model = new ModelAndView();

		model.setViewName("index");
		model.addObject("command", new User());
		return model;
	}

	@RequestMapping(value = "/newUser", method = RequestMethod.POST)
	public @ResponseBody String newUser(@RequestParam String u, @RequestParam String pw, @RequestParam String e) {
		DaoImpl dao = new DaoImpl();
		User newU = new User();
		newU.setUser_Email(e);
		newU.setUser_Username(u);
		newU.setUser_Password(pw);
		newU.setUser_BankBalance(0);
		UserType ut = new UserType();
		ut.setUserType_Type(UserTypes.Customer);
		ut.setUserType_Id(1);
		newU.setUser_UserType(ut);
		dao.persistUser(newU);
		return "";

	}

	@RequestMapping(value = "/newCaterer", method = RequestMethod.POST)
	public @ResponseBody String newCaterer(@RequestParam String ct, @RequestParam String d, @RequestParam String u,
			@RequestParam String pw, @RequestParam String e, @RequestParam String c, @RequestParam String s,
			@RequestParam String z, @RequestParam String r) {
		DaoImpl dao = new DaoImpl();
		// new user part
		User newU = new User();
		newU.setUser_Email(e);
		newU.setUser_Username(u);
		newU.setUser_Password(pw);
		newU.setUser_BankBalance(0);
		UserType ut = new UserType();
		ut.setUserType_Type(UserTypes.Customer);
		ut.setUserType_Id(2);
		newU.setUser_UserType(ut);
		// save the user
		dao.persistUser(newU);
		// new caterer part

		Caterer cate = new Caterer();
		cate.setCaterer_City(c);
		cate.setCaterer_Zipcode(Integer.parseInt(z));
		cate.setCaterer_SearchRadius(Integer.parseInt(r));
		cate.setCaterer_User(newU.getUser_Id());

		Cuisines cui = Cuisines.valueOf(ct);
		cate.setCaterer_CuisineId(cui.ordinal() + 1);
		cate.setCaterer_Description(d);

		State st = new State();
		st.setState_Name(States.valueOf(s));
		st.setState_Id(States.valueOf(s).ordinal() + 1);
		cate.setCaterer_State(st);
		// save the caterer
		dao.persistCaterer(cate);
		return "";

	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView loginGet(@ModelAttribute("command") User p, Model m) {
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
			User u = new User();
			u = dao.getUserByUsername(p.getUser_Username());
			if (u != null && u.getUser_Username() != "" && u.getUser_Password().equals(p.getUser_Password())) {

				u = dao.getUserByUsername(p.getUser_Username());
				System.out.println("Logging in with user: " + u);
				s.setAttribute("userBean", u);

				// User types from DB to reroute to correct page
				// 1: Customer
				// 2: Caterer
				UserType t = u.getUser_UserType();
				if (t.getUserType_Type() == UserTypes.Customer) {
					// get user orders
					List<Order> orders = dao.getOrdersByCustId(u.getUser_Id());
					System.out.println(orders);

					s.setAttribute("uOrders", orders);
					return new ModelAndView("redirect:/user");
				} else {
					// get caterer's orders
					Caterer c = dao.getCatererByUserId(u.getUser_Id());

					List<Order> orders = dao.getOrdersByCatererId(c.getCaterer_Id());
					s.setAttribute("catererBean", c);
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

	@RequestMapping(value = { "/catererInfo" }, method = RequestMethod.GET)
	public String catererInfoView(ModelAndView m) {
		DaoImpl dao = new DaoImpl();
		Caterer c = (Caterer) s.getAttribute("catererBean");
		// if(s.getAttribute("reviewList")!=null &&
		// s.getAttribute("reviewList")!=""){
		List<Review> reviews = dao.getReviewByCatererId(c.getCaterer_Id());
		float totalRating = 0;
		for (Review review : reviews) {
			totalRating += review.getReview_Rating();
		}
		totalRating /= reviews.size();
		// totalRating = (float) (Math.round(totalRating*100.0)/100.0);

		DecimalFormat df = new DecimalFormat("###.#");
		totalRating = Float.valueOf(df.format(totalRating));

		s.setAttribute("catererAverageReview", totalRating);
		s.setAttribute("reviewList", reviews);

		return "catererInfo";
	}

	@RequestMapping(value = { "/CatererProfile" }, method = RequestMethod.GET)
	public String CatererProfile(ModelAndView m) {
		return "CatererProfile";
	}

	@RequestMapping(value = { "/logout" }, method = RequestMethod.GET)
	public String addPerson(Model m) {
		return "logout";
	}

	@RequestMapping(value = { "/newUser" }, method = RequestMethod.GET)
	public String newUser(Model m) {
		return "newUser";
	}
}
