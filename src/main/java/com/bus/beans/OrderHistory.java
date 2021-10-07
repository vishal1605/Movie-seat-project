package com.bus.beans;

import java.util.Date;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="o_history")
public class OrderHistory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long hId;
	
	@ElementCollection
	private List<String> seat;
	
	@ElementCollection
	private List<Double> price;
	
	private double total;
	
	@Temporal(value=TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;
	
	@OneToOne
	private Customer customer;
	public long gethId() {
		return hId;
	}
	public void sethId(long hId) {
		this.hId = hId;
	}
	public List<String> getSeat() {
		return seat;
	}
	public void setSeat(List<String> seat) {
		this.seat = seat;
	}
	public List<Double> getPrice() {
		return price;
	}
	public void setPrice(List<Double> price) {
		this.price = price;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public OrderHistory(long hId, List<String> seat, List<Double> price, double total, Date date, Customer customer) {
		super();
		this.hId = hId;
		this.seat = seat;
		this.price = price;
		this.total = total;
		this.date = date;
		this.customer = customer;
	}
	public OrderHistory(List<String> seat, List<Double> price, double total, Date date, Customer customer) {
		super();
		this.seat = seat;
		this.price = price;
		this.total = total;
		this.date = date;
		this.customer = customer;
	}
	public OrderHistory() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "OrderHistory [hId=" + hId + ", seat=" + seat + ", price=" + price + ", total=" + total + ", date="
				+ date + ", customer=" + customer + "]";
	}
	
	
	
	

}
