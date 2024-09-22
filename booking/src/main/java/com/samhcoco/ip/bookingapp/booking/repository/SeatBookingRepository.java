package com.samhcoco.ip.bookingapp.booking.repository;

import com.samhcoco.ip.bookingapp.booking.model.SeatBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatBookingRepository extends JpaRepository<SeatBooking, Long> {
}
