package com.samhcoco.ip.bookingapp.booking.service;

import com.samhcoco.ip.bookingapp.booking.model.Auditorium;
import com.samhcoco.ip.bookingapp.booking.model.AuditoriumSize;
import com.samhcoco.ip.bookingapp.booking.model.dto.AuditoriumDto;

import java.util.List;

public interface AuditoriumService {

    /**
     * Creates a complete {@link Auditorium} from the given {@link AuditoriumDto}.
     * @param auditoriumDto A {@link AuditoriumDto}.
     * @return All the rows and seats for the created auditorium, represented as a collection of {@link AuditoriumSize} objects.
     */
    List<AuditoriumSize> createAuditorium(AuditoriumDto auditoriumDto);

}
