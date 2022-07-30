package com.poli.bookings.persistence.repository;

import com.poli.bookings.persistence.entity.Bookings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingsRepository extends JpaRepository<Bookings,Long> {
}
