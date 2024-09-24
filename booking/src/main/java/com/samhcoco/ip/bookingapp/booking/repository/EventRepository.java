package com.samhcoco.ip.bookingapp.booking.repository;

import com.samhcoco.ip.bookingapp.booking.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    @Query(value = """
               SELECT * FROM event
               WHERE auditorium_id = :auditoriumId
               AND (
                   :startDate BETWEEN start_date AND end_date
                   OR
                   :endDate BETWEEN start_date AND end_date
               )
               """, nativeQuery = true)
    List<Event> findAllEventsBetweenDates(@Param("startDate") Date startDate,
                                          @Param("endDate") Date endDate,
                                          @Param("auditoriumId") Long auditoriumId);
}
