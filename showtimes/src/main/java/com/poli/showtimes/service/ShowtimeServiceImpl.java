package com.poli.showtimes.service;


import com.poli.showtimes.persistence.entity.Showtime;
import com.poli.showtimes.persistence.repository.ShowtimeRepository;
import com.poli.showtimes.service.dto.ShowtimeDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShowtimeServiceImpl implements ShowtimeService {

    private final ShowtimeRepository showtimeRepository;

    @Override
    public ShowtimeDTO save(ShowtimeDTO showtimeDTO) {
        ModelMapper mapper = new ModelMapper();

        Showtime showtime = mapper.map(showtimeDTO, Showtime.class);
        showtime = showtimeRepository.save(showtime);

        return mapper.map(showtime,ShowtimeDTO.class);
    }

    @Override
    public void Delete(Long id) {
        Optional<Showtime> user = showtimeRepository.findById(id);

        user.ifPresent(showtimeRepository::delete);
    }

    @Override
    public ShowtimeDTO findById(Long id) {
        ModelMapper mapper = new ModelMapper();

        Optional<Showtime> user = showtimeRepository.findById(id);

        return user.map(value -> mapper.map(value, ShowtimeDTO.class)).orElse(null);
    }

    @Override
    public void update(ShowtimeDTO showtimeDTO) {

    }


}
