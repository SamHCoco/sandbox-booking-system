package com.samhcoco.ip.bookingapp.booking.controller;

import com.samhcoco.ip.bookingapp.booking.model.AuditoriumSize;
import com.samhcoco.ip.bookingapp.booking.model.dto.AuditoriumDto;
import com.samhcoco.ip.bookingapp.booking.service.AuditoriumService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class BookingController {

    private static final String VERSION_1 = "api/v1/";
    private static final String BOOKING = "booking";

    private AuditoriumService auditoriumService;

    @Autowired
    public BookingController(AuditoriumService auditoriumService) {
        this.auditoriumService = auditoriumService;
    }

    @PostMapping(VERSION_1 + BOOKING)
    public ResponseEntity<List<AuditoriumSize>> createAuditorium(@RequestBody AuditoriumDto auditoriumDto) {
        final List<AuditoriumSize> auditorium = auditoriumService.createAuditorium(auditoriumDto);

        log.info("Auditorium '{}' successfully created, with seating: {}", auditoriumDto.getName(), auditorium);

        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(auditorium);
    }
}
