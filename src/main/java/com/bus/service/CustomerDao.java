package com.bus.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bus.beans.Customer;
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
	
	public int save(Customer customer) {
		
		repo.save(customer);
		return 1;
		
	}
	
	public Customer login(String email, String password) {
		Customer customer = repo.findByEmailAndPassword(email, password);
		return customer;
	}
	
	public int saveSeat(Seat seat, Customer customer){
		List<Seat> list = new ArrayList<Seat>();
		list.add(seat);
		customer.setSeat(list);
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
	
	public List<OrderHistory> getAllHistory(long id){
		List<OrderHistory> list = repo2.getAllHistory(id);		
		return list;
	}
	
	public List<Seat> getAllSeat(){
		List<Seat> list = repo1.findAll();
		return list;
	}
	
	public void delete(long id) {
		repo1.deleteById(id);
	}
	
	
	

}
