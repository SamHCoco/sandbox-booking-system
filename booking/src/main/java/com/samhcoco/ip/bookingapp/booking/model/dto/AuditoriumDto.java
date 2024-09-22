package com.samhcoco.ip.bookingapp.booking.model.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class AuditoriumDto {
    private String name;
    private List<Integer> seatsPerRow;
}
