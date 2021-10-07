package com.bus.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bus.beans.Seat;
@Repository
public interface SeatRepo extends JpaRepository<Seat, Long> {
	
	@Query(value = "select * from seat where customer_b_id=?", nativeQuery = true)
	public List<Seat> getAllSeat(long id);

}
