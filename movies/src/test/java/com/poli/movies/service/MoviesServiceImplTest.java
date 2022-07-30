package com.poli.movies.service;

import com.poli.movies.clientfeign.BookingsClient;
import com.poli.movies.clientfeign.ShowtimesClient;
import com.poli.movies.persistence.entity.Movie;
import com.poli.movies.persistence.repository.MovieRepository;
import com.poli.movies.service.dto.MovieDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class MoviesServiceImplTest {

    private MoviesServiceImpl moviesService;

    @Mock
    private MovieRepository movieRepository;
    @Mock
    private BookingsClient bookingsClient;
    @Mock
    private ShowtimesClient showtimesClient;

    @BeforeEach
    public void begin() {
        MockitoAnnotations.openMocks(this);
        moviesService = new MoviesServiceImpl(movieRepository,bookingsClient,showtimesClient);

        Movie movie = Movie.builder()
                .id(4L)
                .title("Naruto")
                .director("Cristian").build();
        Mockito.when(movieRepository.findById(4L))
                .thenReturn(Optional.of(movie));
    }

    @Test
    public void findById() {
        MovieDTO movieDTO = moviesService.findById(4L);
        Assertions.assertEquals("Naruto",movieDTO.getTitle());
    }
}