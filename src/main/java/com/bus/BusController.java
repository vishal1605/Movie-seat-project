package com.bus;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bus.beans.Customer;
import com.bus.beans.OrderHistory;
import com.bus.beans.Seat;
import com.bus.service.CustomerDao;

import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class BusController {

	@Autowired
	private CustomerDao dao;
	
	//Opening home page
		@GetMapping("/")
		public String home(Model m) {
			
			return "index";
		}

//second page
	@GetMapping("/booking")
	public String bookingCheck(Model m) {
		LocalDate now = LocalDate.now();
		String time = "09:00 am";
		List<String> seatNo1 = new ArrayList<String>();
		List<Seat> all = dao.getAllSeat(now, time);

		for (Seat s : all) {
			for (String s1 : s.getSeatNo()) {
				seatNo1.add(s1);
			}

		}

		m.addAttribute("date", now);
		m.addAttribute("time", time);
		m.addAttribute("seats", seatNo1);
		return "home";
	}

//Registeration User
	@GetMapping("/register")
	public String register() {
		return "register";
	}


//	Login form
	@GetMapping("/loginForm")
	public String loginForm(Model m) {
		return "login";
	}

//	User save process
	@PostMapping("/save")
	public String save(@ModelAttribute("customer") Customer customer) {
		dao.save(customer);
		return "redirect:/register";

	}

//	Login process
	@PostMapping("/processing")
	public String login(@RequestParam("email") String email, @RequestParam("password") String password,
			HttpSession session, Model m) {

		Customer object = (Customer) session.getAttribute("user");
		if (object != null) {
			return "redirect:/booking-seat";
		} else {

			Customer customer = dao.login(email, password);

			if (customer == null) {
				m.addAttribute("failed", "Invalied login");
				return "login";
			} else {
				session.setAttribute("user", customer);
			}
			return "redirect:/booking-seat";
		}
	}

//	Dashboard page
	@GetMapping("/booking-seat")
	public String getUser(HttpSession session, Model m) {
		session.getAttribute("bookingdate");
		session.removeAttribute("bookingdate");
		session.getAttribute("bookingtime");
		session.removeAttribute("bookingtime");
		LocalDate now = LocalDate.now();
		String time = "09:00 am";

		Customer customer = (Customer) session.getAttribute("user");
		List<String> seatNo1 = new ArrayList<String>();
		List<Seat> seat = customer.getSeat();

		List<Seat> all = dao.getAllSeat(now, time);

		for (Seat s : all) {
			for (String s1 : s.getSeatNo()) {
				seatNo1.add(s1);
			}

		}

		m.addAttribute("date", now);
		m.addAttribute("time", time);
		m.addAttribute("seats", seatNo1);
		m.addAttribute("seat", seat);
		session.setAttribute("user", customer);
		return "dashboard";
	}

//	Logout process
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		Customer object = (Customer) session.getAttribute("user");
		session.removeAttribute("user");

		return "redirect:/";
	}

//	Seat booking process
	@PostMapping("/book-seat")
	public String bookSeat(@ModelAttribute("Seat") Seat seat, HttpSession session, Model m) {
		LocalDate currentDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date todayDate = Date.from(currentDate.atStartOfDay(defaultZoneId).toInstant());
		LocalDate date = (LocalDate) session.getAttribute("bookingdate");
		String time = (String) session.getAttribute("bookingtime");
		Customer object = (Customer) session.getAttribute("user");
		if (object == null) {
			return "redirect:/loginForm";
		} else if (seat.getSeatNo() == null) {
			System.out.println("Seat is null");
			return "redirect:/booking-seat";
		} else if (date == null) {
			date = currentDate;
			time = "09:00 am";
			if ((date.isAfter(currentDate)) || (date.equals(currentDate))) {
				
				Date date2 = Date.from(date.atStartOfDay(defaultZoneId).toInstant());
				List<Double> price = new ArrayList<Double>();
				double sum = 0;
				double p = 525.22d;
				for (String s : seat.getSeatNo()) {
					sum = sum + p;
					price.add(p);
				}
				seat.setTotal(sum);
				seat.setPrice(price);

				OrderHistory history = new OrderHistory(seat.getSeatNo(), price, sum, todayDate, date2, time, object);
				dao.saveSeat(seat, object, date2, time);
				dao.saveHistory(history, object);
				List<String> seatNo1 = new ArrayList<String>();
				List<Customer> all = dao.getAll();
				for (Customer c : all) {
					for (Seat s : c.getSeat()) {
						for (String s1 : s.getSeatNo()) {
							seatNo1.add(s1);
						}

					}
				}
				List<String> in = new ArrayList<String>();
				List<String> out = new ArrayList<String>();
				for (String s1 : seat.getSeatNo()) {
					if (seatNo1.contains(s1)) {
						in.add(s1);
					} else {
						out.add(s1);
					}
				}
				m.addAttribute("available", in);
				m.addAttribute("booked", out);

				m.addAttribute("seats", seatNo1);
				session.setAttribute("user", object);
				return "redirect:/booking-seat";

			} else {
				System.out.println("ye date current date se pahle ki date hai");
				return "redirect:/booking-seat";

			}
		} else {
			if ((date.isAfter(currentDate)) || (date.equals(currentDate))) {
				Date date2 = Date.from(date.atStartOfDay(defaultZoneId).toInstant());
				List<Double> price = new ArrayList<Double>();
				double sum = 0;
				double p = 525.22d;
				for (String s : seat.getSeatNo()) {
					sum = sum + p;
					price.add(p);
				}
				seat.setTotal(sum);
				seat.setPrice(price);

				OrderHistory history = new OrderHistory(seat.getSeatNo(), price, sum, todayDate, date2, time, object);
				dao.saveSeat(seat, object, date2, time);
				dao.saveHistory(history, object);
				List<String> seatNo1 = new ArrayList<String>();
				List<Customer> all = dao.getAll();
				for (Customer c : all) {
					for (Seat s : c.getSeat()) {
						for (String s1 : s.getSeatNo()) {
							seatNo1.add(s1);
						}

					}
				}
				List<String> in = new ArrayList<String>();
				List<String> out = new ArrayList<String>();
				for (String s1 : seat.getSeatNo()) {
					if (seatNo1.contains(s1)) {
						in.add(s1);
					} else {
						out.add(s1);
					}
				}
				m.addAttribute("available", in);
				m.addAttribute("booked", out);

				m.addAttribute("seats", seatNo1);
				session.setAttribute("user", object);
				return "redirect:/booking-seat";

			} else {
				System.out.println("ye date current date se pahle ki date hai");
				return "redirect:/booking-seat";

			}
		}

	}

//	Order history
	@GetMapping("/order-history")
	public String history(HttpSession session, Model m) {
		Date todayDate= new Date();
		Customer object = (Customer) session.getAttribute("user");
		session.setAttribute("user", object);
		List<OrderHistory> list = dao.getAllHistory(object.getBid());
		m.addAttribute("hList", list);
		m.addAttribute("todaydate", todayDate);
//		ObjectMapper obj = new ObjectMapper();
//		
//		try {
//			String str = obj.writeValueAsString(list);
//			System.out.println(str);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		LocalDate date = (LocalDate) session.getAttribute("bookingdate");
		System.out.println(date);
		return "history";
	}

//	Admin power to clear All seats
	@GetMapping("/clear-seats")
	public String eraseSeat(HttpSession session) {
		LocalDate now = LocalDate.now();
		String time = "09:00 am";
		Customer object = (Customer) session.getAttribute("user");

		if (object != null) {
			List<Seat> list = dao.getAllSeat(now, time);
			for (Seat seat : list) {
				long id = seat.getsId();
				dao.delete(id);
			}

		}
		return "redirect:/booking-seat";
	}

//	Admin can see all Customers
	@GetMapping("/all-customers-records")
	public String allRecords(Model m) {
		List<Customer> all = dao.getAll();
		m.addAttribute("records", all);
		return "user_records";
	}

//	Admin can see all Customers and their seats
	@GetMapping("/all-seats/{id}")
	public String allSeats(@PathVariable("id") long id, Model m) {
		List<OrderHistory> list = dao.getAllHistory(id);
		m.addAttribute("seatRecords", list);
		return "seat-records";

	}

	@PostMapping("/check")
	public String checkDate(@RequestParam("localdate") String date, @RequestParam("localtime") String time, Model m, HttpSession session) {
		Customer object = (Customer) session.getAttribute("user");
		if (object == null) {
			LocalDate now = LocalDate.parse(date);
			List<String> seatNo1 = new ArrayList<String>();
			List<Seat> all = dao.getAllSeat(now, time);

			for (Seat s : all) {
				for (String s1 : s.getSeatNo()) {
					seatNo1.add(s1);
				}

			}

			session.setAttribute("bookingdate", now);
			session.setAttribute("bookingtime", time);
			m.addAttribute("date", now);
			m.addAttribute("time", time);
			m.addAttribute("seats", seatNo1);

			return "home";
		} else {
			LocalDate now = LocalDate.parse(date);
			List<String> seatNo1 = new ArrayList<String>();
			List<Seat> all = dao.getAllSeat(now, time);

			for (Seat s : all) {
				for (String s1 : s.getSeatNo()) {
					seatNo1.add(s1);
				}

			}

			session.setAttribute("bookingdate", now);
			session.setAttribute("bookingtime", time);
			m.addAttribute("date", now);
			m.addAttribute("time", time);
			m.addAttribute("seats", seatNo1);

			return "dashboard";
		}

	}

//	Exception handling
	@ExceptionHandler(Exception.class)
	public String handleError(Exception ex, Model m) {

		return "redirect:/loginForm";
	}

}
