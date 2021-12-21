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
	@Column(name = "opening_date")
	private Date OpeningDate;
	
	@OneToMany(mappedBy = "operation", fetch = FetchType.EAGER)
	private List<Seat> seat;

	public long getDateId() {
		return DateId;
	}

	public void setDateId(long dateId) {
		DateId = dateId;
	}

	public Date getOpeningDate() {
		return OpeningDate;
	}

	public void setOpeningDate(Date openingDate) {
		OpeningDate = openingDate;
	}

	public List<Seat> getSeat() {
		return seat;
	}

	public void setSeat(List<Seat> seat) {
		this.seat = seat;
	}

	public CurrentDateOperation(long dateId, Date openingDate, List<Seat> seat) {
		super();
		DateId = dateId;
		OpeningDate = openingDate;
		this.seat = seat;
	}

	public CurrentDateOperation(Date openingDate, List<Seat> seat) {
		super();
		OpeningDate = openingDate;
		this.seat = seat;
	}

	public CurrentDateOperation() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "CurrentDateOperation [DateId=" + DateId + ", OpeningDate=" + OpeningDate + ", seat=" + seat + "]";
	}
	
	

}
