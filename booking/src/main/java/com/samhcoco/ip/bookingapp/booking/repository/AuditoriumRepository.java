package com.samhcoco.ip.bookingapp.booking.repository;

import com.samhcoco.ip.bookingapp.booking.model.Auditorium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditoriumRepository extends JpaRepository<Auditorium, Long> {
}
