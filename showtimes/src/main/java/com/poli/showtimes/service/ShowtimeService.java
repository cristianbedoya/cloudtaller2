package com.poli.showtimes.service;


import com.poli.showtimes.service.dto.ShowtimeDTO;

import java.util.List;

public interface ShowtimeService  {

    ShowtimeDTO save(ShowtimeDTO showtimeDTO);

    ShowtimeDTO findById(Long id);

    ShowtimeDTO update(Long id, ShowtimeDTO showtimeDTO);

    List<ShowtimeDTO> getAll();
}
