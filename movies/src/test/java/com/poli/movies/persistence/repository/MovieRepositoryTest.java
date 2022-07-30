package com.poli.movies.persistence.repository;

import com.poli.movies.persistence.entity.Movie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
class MovieRepositoryTest {

    @Autowired
    private MovieRepository movieRepository;

    @Test
    public void when_findByMovie()
    {
        Movie movie = Movie.builder()
                .id(4L)
                .title("Naruto")
                .director("Cristian")
                .rating(5).build();

        Movie movieResponseDB = movieRepository.save(movie);

        Optional<Movie> moviesRes = movieRepository.findById(movieResponseDB.getId());
        moviesRes.ifPresent(value -> Assertions.assertEquals(movie.getId(), value.getId()));
    }
}