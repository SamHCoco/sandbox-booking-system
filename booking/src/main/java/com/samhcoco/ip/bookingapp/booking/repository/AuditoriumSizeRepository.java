package com.samhcoco.ip.bookingapp.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditoriumSizeRepository extends JpaRepository<com.samhcoco.ip.bookingapp.booking.model.AuditoriumSize, Long> {
}
