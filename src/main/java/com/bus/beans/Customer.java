package com.bus.beans;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "b_id")
	private long bid;
	
	@Column(nullable = false)
	private String name;
	@Column(nullable = false, unique = true)
	private String email;
	@Column(nullable = false)
	private String password;

	@OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
	private List<Seat> seat;
	
	@OneToOne(mappedBy = "customer", fetch = FetchType.EAGER)
	private OrderHistory history;

	public long getBid() {
		return bid;
	}

	public void setBid(long bid) {
		this.bid = bid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Seat> getSeat() {
		return seat;
	}

	public void setSeat(List<Seat> seat) {
		this.seat = seat;
	}

	public OrderHistory getHistory() {
		return history;
	}

	public void setHistory(OrderHistory history) {
		this.history = history;
	}

	public Customer(String name, String email, String password, List<Seat> seat, OrderHistory history) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.seat = seat;
		this.history = history;
	}

	public Customer(long bid, String name, String email, String password, List<Seat> seat, OrderHistory history) {
		super();
		this.bid = bid;
		this.name = name;
		this.email = email;
		this.password = password;
		this.seat = seat;
		this.history = history;
	}

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Customer [bid=" + bid + ", name=" + name + ", email=" + email + ", password=" + password + ", seat="
				+ seat + ", history=" + history + "]";
	}
	

		
	
}
