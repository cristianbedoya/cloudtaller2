package com.poli.showtimes.service;


import com.poli.showtimes.service.dto.ShowtimeDTO;

import java.util.List;

public interface ShowtimeService  {


    ShowtimeDTO save(ShowtimeDTO showtimeDTO);

    void Delete(Long id);

    ShowtimeDTO findById(Long id);

    void update(ShowtimeDTO showtimeDTO);
}
