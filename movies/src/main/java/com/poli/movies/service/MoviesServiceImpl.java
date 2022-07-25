package com.poli.movies.service;

import com.poli.movies.persistence.entity.Movie;
import com.poli.movies.persistence.repository.MovieRepository;
import com.poli.movies.service.dto.MovieDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MoviesServiceImpl implements MoviesService {

    private final MovieRepository movieRepository;

    @Override
    public MovieDTO save(MovieDTO movieDTO) {
        ModelMapper mapper = new ModelMapper();

        Movie movie = mapper.map(movieDTO, Movie.class);
        movie = movieRepository.save(movie);

        return mapper.map(movie,MovieDTO.class);
    }

    @Override
    public void Delete(Long id) {
        Optional<Movie> user = movieRepository.findById(id);

        user.ifPresent(movieRepository::delete);
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
