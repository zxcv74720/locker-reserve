package com.example.booklocker.repository;

import com.example.booklocker.entity.Lockers;
import com.example.booklocker.entity.Reservations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationsRepository extends JpaRepository<Reservations, Integer> {

    @Query("SELECT r.lockerNum FROM Reservations r")
    List<String> findReservedLockers();

    Reservations findByLockerNum(String lockerNum);
}
