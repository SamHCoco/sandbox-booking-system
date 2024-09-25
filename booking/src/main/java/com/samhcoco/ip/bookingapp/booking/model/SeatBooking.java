package com.samhcoco.ip.bookingapp.booking.model;

import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "`row`")
    private int row;

    private int seat;
    private long auditoriumId;
    private long userId;
    private long eventId;
}
