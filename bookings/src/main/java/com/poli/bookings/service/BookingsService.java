package com.poli.bookings.service;

import com.poli.bookings.service.dto.BookingsDTO;

import java.util.List;

public interface BookingsService {
    BookingsDTO save(BookingsDTO bookingsDTO);

    void Delete(Long id);

    BookingsDTO findById(Long id);


    List<BookingsDTO> getAll();
}
