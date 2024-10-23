package com.samhcoco.ip.bookingapp.booking.service;

import com.samhcoco.ip.bookingapp.booking.model.Event;

public interface EventService {

    /**
     * Creates a single {@link Event}.
     * @param event {@link Event}.
     * @return Created {@link Event}.
     */
    Event createEvent(Event event);

}
