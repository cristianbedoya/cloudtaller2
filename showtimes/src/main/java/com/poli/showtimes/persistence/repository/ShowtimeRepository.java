package com.poli.showtimes.persistence.repository;

import com.poli.showtimes.persistence.entity.Showtimes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowtimeRepository extends JpaRepository<Showtimes,Long> {

}
