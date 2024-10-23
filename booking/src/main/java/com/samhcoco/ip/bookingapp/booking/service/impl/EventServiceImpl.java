package com.samhcoco.ip.bookingapp.booking.service.impl;

import com.samhcoco.ip.bookingapp.booking.model.Event;
import com.samhcoco.ip.bookingapp.booking.repository.AuditoriumRepository;
import com.samhcoco.ip.bookingapp.booking.repository.EventRepository;
import com.samhcoco.ip.bookingapp.booking.service.EventService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EventServiceImpl implements EventService {

    private EventRepository eventRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Event createEvent(@NonNull Event event) {
        final Event created = eventRepository.save(event);
        log.info("Successfully created {}", event);
        return created;
    }

}
