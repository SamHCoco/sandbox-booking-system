package com.samhcoco.ip.bookingapp.booking.service.impl;

import com.samhcoco.ip.bookingapp.booking.model.Event;
import com.samhcoco.ip.bookingapp.booking.repository.AuditoriumRepository;
import com.samhcoco.ip.bookingapp.booking.repository.EventRepository;
import com.samhcoco.ip.bookingapp.core.service.ValidatorService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.String.format;
import static java.util.Objects.isNull;

@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Service
public class EventValidatorServiceImpl implements ValidatorService<Event> {

    private EventRepository eventRepository;
    private AuditoriumRepository auditoriumRepository;

    @Override
    public Map<String, Object> validate(Event object) {
        final HashMap<String, Object> errors = new HashMap<>();

        final long auditoriumId = object.getAuditoriumId();
        if (auditoriumId > 0) {
            final boolean exists = auditoriumRepository.existsById(auditoriumId);
            if (!exists) {
                errors.put("auditoriumId", format("Auditorium with ID %s does not exist.", auditoriumId));
            }
        } else {
            errors.put("auditoriumId", "Auditorium ID invalid.");
        }

        final Date startDate = object.getStartDate();
        final Date endDate = object.getEndDate();
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

        final List<Event> events = eventRepository.findAllEventsBetweenDates(startDate, endDate, auditoriumId);
        if (!events.isEmpty()) {
            errors.put("start & end date",
                    format("Invalid dates: '%s' existing events booked between '%s' - '%s' for auditorium ID '%s'",
                            events.size(), startDate, endDate, auditoriumId));
        }

        if (!errors.isEmpty()) {
            log.error("{} validation failed - {}", object, errors);
        }
        return errors;
    }
}
