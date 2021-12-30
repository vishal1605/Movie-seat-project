package com.bus.beans;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
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
	
	@Column(name = "movie_name")
	private String movieName;
	
	@Temporal(value=TemporalType.DATE)
	@Column(name = "book_on_date")
	private Date bookOnDate;
	
	@Temporal(value=TemporalType.DATE)
	@Column(name = "show_on_date")
	private Date showOnDate;
	
	@Column(name = "show_time")
	private String showTime;
	
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

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public Date getBookOnDate() {
		return bookOnDate;
	}

	public void setBookOnDate(Date bookOnDate) {
		this.bookOnDate = bookOnDate;
	}

	public Date getShowOnDate() {
		return showOnDate;
	}

	public void setShowOnDate(Date showOnDate) {
		this.showOnDate = showOnDate;
	}

	public String getShowTime() {
		return showTime;
	}

	public void setShowTime(String showTime) {
		this.showTime = showTime;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public OrderHistory(long hId, List<String> seat, List<Double> price, double total, String movieName,
			Date bookOnDate, Date showOnDate, String showTime, Customer customer) {
		super();
		this.hId = hId;
		this.seat = seat;
		this.price = price;
		this.total = total;
		this.movieName = movieName;
		this.bookOnDate = bookOnDate;
		this.showOnDate = showOnDate;
		this.showTime = showTime;
		this.customer = customer;
	}

	public OrderHistory(List<String> seat, List<Double> price, double total, String movieName, Date bookOnDate,
			Date showOnDate, String showTime, Customer customer) {
		super();
		this.seat = seat;
		this.price = price;
		this.total = total;
		this.movieName = movieName;
		this.bookOnDate = bookOnDate;
		this.showOnDate = showOnDate;
		this.showTime = showTime;
		this.customer = customer;
	}

	public OrderHistory() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "OrderHistory [hId=" + hId + ", seat=" + seat + ", price=" + price + ", total=" + total + ", movieName="
				+ movieName + ", bookOnDate=" + bookOnDate + ", showOnDate=" + showOnDate + ", showTime=" + showTime
				+ ", customer=" + customer + "]";
	}

	
	

}
