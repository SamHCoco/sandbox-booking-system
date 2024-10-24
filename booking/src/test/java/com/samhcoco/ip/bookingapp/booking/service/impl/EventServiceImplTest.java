package com.samhcoco.ip.bookingapp.booking.service.impl;

import com.samhcoco.ip.bookingapp.booking.model.Event;
import com.samhcoco.ip.bookingapp.booking.repository.EventRepository;
import com.samhcoco.ip.bookingapp.booking.service.EventService;
import org.apache.commons.lang3.time.DateUtils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EventServiceImplTest {

    private EventService eventService;

    @Mock
    private EventRepository eventRepository;

    private Event event;

    @BeforeEach
    public void setup() {
        eventService = new EventServiceImpl(eventRepository);
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

}
