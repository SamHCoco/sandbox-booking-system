package com.samhcoco.ip.bookingapp.booking.service.impl;

import com.samhcoco.ip.bookingapp.booking.model.Event;
import com.samhcoco.ip.bookingapp.booking.repository.AuditoriumRepository;
import com.samhcoco.ip.bookingapp.booking.repository.EventRepository;
import com.samhcoco.ip.bookingapp.booking.service.EventService;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Date;
import java.util.Map;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EventServiceImplTest {

    private EventService eventService;

    @Mock
    private EventRepository eventRepository;

    @Mock
    private AuditoriumRepository auditoriumRepository;

    private Event event;

    @BeforeEach
    public void setup() {
        eventService = new EventServiceImpl(eventRepository, auditoriumRepository);
        final Date now = new Date();

        event = Event.builder()
                    .name("Concert")
                    .auditoriumId(1)
                    .startDate(now)
                    .endDate(DateUtils.addHours(now, 2))
                    .build();
    }

    @Test
    public void testCreateEvent_happyPath() {
        Event event = new Event();
        when(eventRepository.save(any())).thenReturn(event);

        final Event result = eventService.createEvent(event);
        assertThat(result).isNotNull();
        verify(eventRepository).save(any());
    }

    @Test
    public void testValidate_happyPath() {
        when(auditoriumRepository.existsById(any())).thenReturn(true);
        when(eventRepository.findAllEventsBetweenDates(any(), any(), any())).thenReturn(emptyList());

        final Map<String, Object> errors = eventService.validate(event);

        assertThat(errors).isEmpty();
        verify(auditoriumRepository).existsById(any());
        verify(eventRepository).findAllEventsBetweenDates(any(), any(), any());
    }

}
