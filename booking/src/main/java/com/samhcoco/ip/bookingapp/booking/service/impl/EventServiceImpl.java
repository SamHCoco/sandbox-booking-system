package com.samhcoco.ip.bookingapp.booking.service.impl;

import com.samhcoco.ip.bookingapp.booking.model.Event;
import com.samhcoco.ip.bookingapp.booking.repository.AuditoriumRepository;
import com.samhcoco.ip.bookingapp.booking.repository.EventRepository;
import com.samhcoco.ip.bookingapp.booking.service.EventService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static java.lang.String.format;

@Slf4j
@Service
public class EventServiceImpl implements EventService {

    private EventRepository eventRepository;
    private AuditoriumRepository auditoriumRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository,
                            AuditoriumRepository auditoriumRepository) {
        this.eventRepository = eventRepository;
        this.auditoriumRepository = auditoriumRepository;
    }

    @Override
    public Event createEvent(@NonNull Event event) {
        final boolean isValidAuditorium = auditoriumRepository.existsById(event.getAuditoriumId());
        if (isValidAuditorium) {
            return eventRepository.save(event);
        }
        log.error("Failed to create {}: Auditorium with ID {} does not exist.", event, event.getId());
        return null;
    }

    private Map<String, String> validate(@NonNull Event event) {
        final HashMap<String, String> errors = new HashMap<>();

        final boolean isValidAuditorium = auditoriumRepository.existsById(event.getAuditoriumId());
        if (!isValidAuditorium) {
            errors.put("auditoriumId", format("Auditorium with ID %s does not exist.", event.getAuditoriumId()));
        }
        /*
            1. get all events from repo with given auditorium ID and whose start and end date fall within the provided dates
            todo - complete
         */
        return errors;
    }
}
