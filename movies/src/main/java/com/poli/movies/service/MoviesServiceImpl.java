package com.poli.movies.service;

import com.poli.movies.clientfeign.BookingsClient;
import com.poli.movies.clientfeign.ShowtimesClient;
import com.poli.movies.persistence.entity.Movie;
import com.poli.movies.persistence.repository.MovieRepository;
import com.poli.movies.service.dto.BookingsDTO;
import com.poli.movies.service.dto.MovieDTO;
import com.poli.movies.service.dto.ShowtimeDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MoviesServiceImpl implements MoviesService {

    private final MovieRepository movieRepository;
    private final BookingsClient bookingsClient;
    private final ShowtimesClient showtimesClient;

    @Override
    public MovieDTO save(MovieDTO movieDTO) {
        ModelMapper mapper = new ModelMapper();

        Movie movie = mapper.map(movieDTO, Movie.class);
        movie = movieRepository.save(movie);

        return mapper.map(movie,MovieDTO.class);
    }

    @Override
    public boolean Delete(Long id) {
        ModelMapper modelMapper = new ModelMapper();
        List<BookingsDTO> bookingsDTOS = modelMapper.map(bookingsClient.getAll().getData(), new TypeToken<List<BookingsDTO>>() {}.getType());
        List<ShowtimeDTO> showtimeDTOS =  modelMapper.map(showtimesClient.getAll().getData(), new TypeToken<List<ShowtimeDTO>>() {}.getType());

        var resultBookings = bookingsDTOS.stream().map(x->{
            return x.getMovies().stream().filter(y->id.equals(y.getId())).collect(Collectors.toList()).stream().findFirst();
        }).filter(Optional::isPresent).collect(Collectors.toList());

        var resultShowtime = showtimeDTOS.stream().map(x->{
            return x.getMovies().stream().filter(y->id.equals(y.getId())).collect(Collectors.toList()).stream().findFirst();
        }).filter(Optional::isPresent).collect(Collectors.toList());

        if(resultBookings.isEmpty() && resultShowtime.isEmpty()) {
            Optional<Movie> user = movieRepository.findById(id);
            user.ifPresent(movieRepository::delete);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public MovieDTO findById(Long id) {
        ModelMapper mapper = new ModelMapper();

        Optional<Movie> user = movieRepository.findById(id);

        return user.map(value -> mapper.map(value, MovieDTO.class)).orElse(null);
    }

    @Override
    public List<MovieDTO> getAll() {

        List<Movie> movies =  movieRepository.findAll();
        ModelMapper mapper = new ModelMapper();
        return movies.stream().map(value -> mapper.map(value, MovieDTO.class) ).collect(Collectors.toList());
    }


}
