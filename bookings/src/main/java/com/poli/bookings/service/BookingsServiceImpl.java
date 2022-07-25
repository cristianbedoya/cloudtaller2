package com.poli.bookings.service;

import com.poli.bookings.persistence.entity.Bookings;
import com.poli.bookings.persistence.repository.BookingsRepository;
import com.poli.bookings.service.dto.BookingsDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BookingsServiceImpl implements  BookingsService {

    private final BookingsRepository bookingsRepository;

    @Override
    public BookingsDTO save(BookingsDTO bookingsDTO) {
        ModelMapper mapper = new ModelMapper();

        Bookings movie = mapper.map(bookingsDTO, Bookings.class);
        movie = bookingsRepository.save(movie);

        return mapper.map(movie,BookingsDTO.class);
    }

    @Override
    public void Delete(Long id) {
        Optional<Bookings> user = bookingsRepository.findById(id);

        user.ifPresent(bookingsRepository::delete);
    }

    @Override
    public BookingsDTO findById(Long id) {
        ModelMapper mapper = new ModelMapper();

        Optional<Bookings> user = bookingsRepository.findById(id);

        return user.map(value -> mapper.map(value, BookingsDTO.class)).orElse(null);
    }

    @Override
    public List<BookingsDTO> getAll() {
        List<Bookings> movies =  bookingsRepository.findAll();
        ModelMapper mapper = new ModelMapper();
        return movies.stream().map(value -> mapper.map(value, BookingsDTO.class) ).collect(Collectors.toList());
    }

}
