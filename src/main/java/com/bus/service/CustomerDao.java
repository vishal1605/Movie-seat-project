package com.bus.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.bus.beans.CurrentDateOperation;
import com.bus.beans.Customer;
import com.bus.beans.MovieDetails;
import com.bus.beans.OrderHistory;
import com.bus.beans.Seat;

@Component
public class CustomerDao {
	
	@Autowired
	private CustomerRepo repo;
	
	@Autowired
	private SeatRepo repo1;
	
	@Autowired
	private HistoryRepo repo2;
	
	@Autowired
	private MovieRepo movieRepo;
	
	
	public int save(Customer customer) {
		
		repo.save(customer);
		return 1;
		
	}
	
//	@Cacheable(cacheNames = "login", key = "'customer'+#email+#password")
	public Customer login(String email, String password) {
		Customer customer = repo.findByEmailAndPassword(email, password);
		return customer;
	}
	
	public int saveSeat(Seat seat, Customer customer, Date date, String time){
		List<Seat> list = new ArrayList<Seat>();
		list.add(seat);
		customer.setSeat(list);
		CurrentDateOperation cdo= new CurrentDateOperation();
		cdo.setShowDate(date);
		cdo.setShowTime(time);
		cdo.setSeat(list);
		
		seat.setOperation(cdo);
		seat.setOperation(cdo);
		seat.setCustomer(customer);
		Seat save = repo1.save(seat);
		return 1;
	}
	
	public List<Seat> getSeats(long id){
		List<Seat> list = repo1.getAllSeat(id);
		return list;
	}
	
	public List<Customer> getAll(){
		List<Customer> findAll = repo.findAll();
		return findAll;
	}
	
	public OrderHistory saveHistory(OrderHistory history, Customer customer) {
		customer.setHistory(history);
		OrderHistory save = repo2.save(history);
		return save;
	}
	
//	@Cacheable(cacheNames = "history", key = "#id")
	public List<OrderHistory> getAllHistory(long id){
		List<OrderHistory> list = repo2.getAllHistory(id);		
		return list;
	}
	
	public List<Seat> getAllSeat(LocalDate date, String time){
		List<Seat> list = repo1.getAllByDate(date, time);
		return list;
	}
	
	public void delete(long id) {
		repo1.deleteById(id);
	}
	
	public int updateDetail(Customer customer) {
		repo.save(customer);
		return 1;
	}
	
	public List<MovieDetails> getAllMovie(){
		List<MovieDetails> list = this.movieRepo.findAll();
		return list;
	}
	
	
	
	

}
