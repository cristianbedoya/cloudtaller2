package com.poli.showtimes.service;


import com.ctc.wstx.dtd.OptionalModel;
import com.poli.showtimes.persistence.entity.Showtimes;
import com.poli.showtimes.persistence.repository.MoviesRepository;
import com.poli.showtimes.persistence.repository.ShowtimeRepository;
import com.poli.showtimes.service.dto.ShowtimeDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShowtimeServiceImpl implements ShowtimeService {

    private final ShowtimeRepository showtimeRepository;
    private final MoviesRepository moviesRepository;

    @Override
    public ShowtimeDTO save(ShowtimeDTO showtimeDTO) {
        ModelMapper mapper = new ModelMapper();

        Showtimes showtimes = mapper.map(showtimeDTO, Showtimes.class);

        moviesRepository.saveAll(showtimes.getMovies());

        showtimes = showtimeRepository.save(showtimes);

        return mapper.map(showtimes,ShowtimeDTO.class);
    }

    @Override
    public ShowtimeDTO findById(Long id) {
        ModelMapper mapper = new ModelMapper();

        Optional<Showtimes> user = showtimeRepository.findById(id);

        return user.map(value -> mapper.map(value, ShowtimeDTO.class)).orElse(null);
    }

    @Override
    public ShowtimeDTO update(Long id, ShowtimeDTO showtimeDTO) {
        ModelMapper mapper = new ModelMapper();
        Showtimes showtimesNew = mapper.map(showtimeDTO, Showtimes.class);

        Optional<Showtimes> showtimesData = showtimeRepository.findById(id);

        if(showtimesData.isPresent())
        {
            Showtimes showtimes = showtimesData.get();

            showtimes.setDate(showtimesNew.getDate());
            showtimes.setMovies(showtimesNew.getMovies());

            moviesRepository.saveAll(showtimes.getMovies());

            showtimes = showtimeRepository.save(showtimes);

            return mapper.map(showtimes,ShowtimeDTO.class);
        }

        return null;
    }

    @Override
    public List<ShowtimeDTO> getAll() {

        List<Showtimes> showtimes = showtimeRepository.findAll();
        ModelMapper mapper = new ModelMapper();
        return showtimes.stream().map(value -> mapper.map(value, ShowtimeDTO.class) ).collect(Collectors.toList());

    }

}
