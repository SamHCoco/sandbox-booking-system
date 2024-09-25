package com.samhcoco.ip.bookingapp.booking.service.impl;

import com.samhcoco.ip.bookingapp.booking.model.Event;
import com.samhcoco.ip.bookingapp.booking.repository.AuditoriumRepository;
import com.samhcoco.ip.bookingapp.booking.repository.EventRepository;
import com.samhcoco.ip.bookingapp.booking.service.EventService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.String.format;
import static java.util.Objects.isNull;

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
        final Event created = eventRepository.save(event);
        log.info("Successfully created {}", event);
        return created;
    }

    @Override
    public Map<String, Object> validate(@NonNull Event event) {
        final HashMap<String, Object> errors = new HashMap<>();

        final long auditoriumId = event.getAuditoriumId();
        if (auditoriumId > 0) {
            final boolean exists = auditoriumRepository.existsById(auditoriumId);
            if (!exists) {
                errors.put("auditoriumId", format("Auditorium with ID %s does not exist.", auditoriumId));
            }
        } else {
            errors.put("auditoriumId", "Auditorium ID invalid.");
        }

        final Date startDate = event.getStartDate();
        final Date endDate = event.getEndDate();
        if (isNull(startDate)) {
            errors.put("startDate", "Event start date invalid");
        }
        if (isNull(endDate)) {
            errors.put("endDate", "Event end date invalid");
        }
        if (!errors.isEmpty()) {
            return errors;
        }
        if (!startDate.before(endDate)) {
            errors.put("Start & End Date", "Event start and end dates invalid");
            return errors;
        }

        final List<Event> events =  listAllEventsBetweenDates(startDate, endDate, auditoriumId);
        if (!events.isEmpty()) {
            errors.put("start & end date",
               format("Invalid dates: '%s' existing events booked between '%s' - '%s' for auditorium ID '%s'",
                       events.size(), startDate, endDate, auditoriumId));
        }

        if (!errors.isEmpty()) {
            log.error("{} validation failed - {}", event, errors);
        }
        return errors;
    }

    /**
     * Lists all {@link Event} within the given start and end dates (inclusive).
     * @param startDate Start date.
     * @param endDate End date.
     * @return List of {@link Event}.
     */
    private List<Event> listAllEventsBetweenDates(@NonNull Date startDate,
                                                  @NonNull Date endDate,
                                                  @NonNull Long auditoriumId) {
        return eventRepository.findAllEventsBetweenDates(startDate, endDate, auditoriumId);
    }
}
