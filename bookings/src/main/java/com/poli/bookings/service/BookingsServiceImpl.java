package com.poli.bookings.service;

import com.poli.bookings.clientfeign.MoviesClient;
import com.poli.bookings.persistence.entity.Bookings;
import com.poli.bookings.persistence.entity.Movies;
import com.poli.bookings.persistence.repository.BookingsRepository;
import com.poli.bookings.persistence.repository.MoviesRepository;
import com.poli.bookings.service.dto.BookingsDTO;
import com.poli.bookings.service.dto.MoviesDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BookingsServiceImpl implements BookingsService {

    private final BookingsRepository bookingsRepository;
    private final MoviesRepository moviesRepository;
    private final MoviesClient moviesClient;

    @Override
    public BookingsDTO save(BookingsDTO bookingsDTO) {

        ModelMapper mapper = new ModelMapper();

        Bookings bookings = mapper.map(bookingsDTO, Bookings.class);

        moviesRepository.saveAll(bookings.getMovies());

        bookings = bookingsRepository.save(bookings);

        return mapper.map(bookings, BookingsDTO.class);
    }

    @Override
    public void Delete(Long id) {
        Optional<Bookings> user = bookingsRepository.findById(id);

        user.ifPresent(bookingsRepository::delete);
    }

    @Override
    public BookingsDTO findById(Long id) {

        BookingsDTO bookingsDTO = null;
        ModelMapper mapper = new ModelMapper();

        Optional<Bookings> booking = bookingsRepository.findById(id);

        if (booking.isPresent()) {

            List<MoviesDTO> movies = booking.get().getMovies().stream().map(x -> {
                return mapper.map(moviesClient.getById(x.getId()).getData(),MoviesDTO.class);
            }).collect(Collectors.toList());

            bookingsDTO = booking.map(value -> mapper.map(value, BookingsDTO.class)).orElse(null);

            bookingsDTO.setMovies(movies);

        }

        return bookingsDTO;
    }

    @Override
    public List<BookingsDTO> getAll() {
        List<Bookings> bookings = bookingsRepository.findAll();
        ModelMapper mapper = new ModelMapper();

        return bookings.stream().map(booking ->
        {
            BookingsDTO bookingsDTO = null;
            List<MoviesDTO> movies = booking.getMovies().stream().map(x -> {
                return mapper.map(moviesClient.getById(x.getId()).getData(),MoviesDTO.class);
            }).collect(Collectors.toList());

            bookingsDTO = mapper.map(booking, BookingsDTO.class);

            bookingsDTO.setMovies(movies);

            return bookingsDTO;
        }).collect(Collectors.toList());
    }


}
