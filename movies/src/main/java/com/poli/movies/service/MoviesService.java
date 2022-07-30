package com.poli.movies.service;

import com.poli.movies.service.dto.MovieDTO;

import java.util.List;

public interface MoviesService {
    MovieDTO save(MovieDTO movieDTO);

    boolean Delete(Long id);

    MovieDTO findById(Long id);

    List<MovieDTO> getAll();
}
