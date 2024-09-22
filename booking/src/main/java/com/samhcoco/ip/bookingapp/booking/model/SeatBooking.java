package com.samhcoco.ip.bookingapp.booking.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "seat_booking")
public class SeatBooking {
    @Id
    private long id;
    private int row;
    private int seat;
    private long auditoriumId;
    private long userId;
    private long eventId;
}
