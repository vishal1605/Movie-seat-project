package com.bus.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bus.beans.CurrentDateOperation;

@Repository
public interface OpeningDate extends JpaRepository<CurrentDateOperation, Long> {

}
