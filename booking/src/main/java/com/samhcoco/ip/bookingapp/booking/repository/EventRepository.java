package com.samhcoco.ip.bookingapp.booking.repository;

import com.samhcoco.ip.bookingapp.booking.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}