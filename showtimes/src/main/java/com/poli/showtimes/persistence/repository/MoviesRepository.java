package com.poli.showtimes.persistence.repository;


import com.poli.showtimes.persistence.entity.Movies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoviesRepository extends JpaRepository<Movies,Long> {

}
