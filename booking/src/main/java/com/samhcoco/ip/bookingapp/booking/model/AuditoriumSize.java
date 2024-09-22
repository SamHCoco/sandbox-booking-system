package com.samhcoco.ip.bookingapp.booking.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
@Entity
@Table(name = "auditorium_size")
public class AuditoriumSize {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long auditoriumId;

    @Column(name = "`row`")
    private int row;
    private int seats;
}
