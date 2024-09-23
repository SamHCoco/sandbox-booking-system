package com.samhcoco.ip.bookingapp.booking.controller;

import com.samhcoco.ip.bookingapp.booking.model.Event;
import com.samhcoco.ip.bookingapp.booking.service.EventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static java.util.Objects.nonNull;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;

@Slf4j
@RestController
public class EventController {

    private static final String VERSION_1 = "api/v1/";
    private static final String EVENT = "event";

    private EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping(VERSION_1 + EVENT)
    public ResponseEntity<Object> createEvent(@RequestBody Event event) {
        final Map<String, Object> errors = eventService.validate(event);
        if (!errors.isEmpty()) {
            return ResponseEntity.status(BAD_REQUEST)
                                 .body(errors);
        }
        return ResponseEntity.status(CREATED)
                             .body(eventService.createEvent(event));

    }
}
