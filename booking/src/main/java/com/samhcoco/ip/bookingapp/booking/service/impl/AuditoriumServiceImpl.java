package com.samhcoco.ip.bookingapp.booking.service.impl;

import com.samhcoco.ip.bookingapp.booking.model.Auditorium;
import com.samhcoco.ip.bookingapp.booking.model.AuditoriumSize;
import com.samhcoco.ip.bookingapp.booking.model.dto.AuditoriumDto;
import com.samhcoco.ip.bookingapp.booking.repository.AuditoriumRepository;
import com.samhcoco.ip.bookingapp.booking.repository.AuditoriumSizeRepository;
import com.samhcoco.ip.bookingapp.booking.service.AuditoriumService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class AuditoriumServiceImpl implements AuditoriumService {

    private AuditoriumRepository auditoriumRepository;
    private AuditoriumSizeRepository auditoriumSizeRepository;

    @Autowired
    public AuditoriumServiceImpl(AuditoriumSizeRepository auditoriumSizeRepository,
                                 AuditoriumRepository auditoriumRepository) {
        this.auditoriumSizeRepository = auditoriumSizeRepository;
        this.auditoriumRepository = auditoriumRepository;
    }

    @Override
    @Transactional
    public List<AuditoriumSize> createAuditorium(@NonNull AuditoriumDto auditoriumDto) {
        val auditorium = auditoriumRepository.save(Auditorium.builder()
                                                             .name(auditoriumDto.getName())
                                                             .build());
        log.info("Successfully persisted '{}'", auditorium);
        List<AuditoriumSize> seating = generateSeating(auditorium.getId(), auditoriumDto.getSeatsPerRow());
        log.info("Attempting to persist seating for '{}' auditorium: {}", auditorium.getName(), seating);
        return auditoriumSizeRepository.saveAll(seating);
    }

    /**
     * Generates the seat and rows for the given {@link Auditorium} ID using the given seats per row,
     * represented as a collection of {@link AuditoriumSize} objects.
     * @param auditoriumId {@link Auditorium} ID.
     * @param seatsPerRow Seats per row (where index specifies the row)
     * @return Seats and rows represented as {@link AuditoriumSize} collection.
     */
    private List<AuditoriumSize> generateSeating(@NonNull Long auditoriumId,
                                                 @NonNull List<Integer> seatsPerRow) {
        List<AuditoriumSize> seating = new ArrayList<>();

        for (int r = 0; r < seatsPerRow.size(); r++) {
            seating.add(AuditoriumSize.builder()
                                      .auditoriumId(auditoriumId)
                                      .row(r)
                                      .seats(seatsPerRow.get(r))
                                      .build());
        }
        return seating;
    }
}
