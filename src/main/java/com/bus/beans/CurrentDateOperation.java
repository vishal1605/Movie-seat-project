package com.bus.beans;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "current_date_operation")
public class CurrentDateOperation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "date_id")
	private long DateId;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "show_date")
	private Date showDate;
	
	@Column(name = "show_time")
	private String showTime;
	
	@OneToMany(mappedBy = "operation", fetch = FetchType.EAGER)
	private List<Seat> seat;

	public long getDateId() {
		return DateId;
	}

	public void setDateId(long dateId) {
		DateId = dateId;
	}

	public Date getShowDate() {
		return showDate;
	}

	public void setShowDate(Date showDate) {
		this.showDate = showDate;
	}

	public String getShowTime() {
		return showTime;
	}

	public void setShowTime(String showTime) {
		this.showTime = showTime;
	}

	public List<Seat> getSeat() {
		return seat;
	}

	public void setSeat(List<Seat> seat) {
		this.seat = seat;
	}

	public CurrentDateOperation(Date showDate, String showTime, List<Seat> seat) {
		super();
		this.showDate = showDate;
		this.showTime = showTime;
		this.seat = seat;
	}

	public CurrentDateOperation(long dateId, Date showDate, String showTime, List<Seat> seat) {
		super();
		DateId = dateId;
		this.showDate = showDate;
		this.showTime = showTime;
		this.seat = seat;
	}

	public CurrentDateOperation() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "CurrentDateOperation [DateId=" + DateId + ", showDate=" + showDate + ", showTime=" + showTime
				+ ", seat=" + seat + "]";
	}

	

}
