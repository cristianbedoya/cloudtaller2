package com.poli.showtimes.persistence.repository;

import com.poli.showtimes.persistence.entity.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowtimeRepository extends JpaRepository<Showtime,Long> {
}
