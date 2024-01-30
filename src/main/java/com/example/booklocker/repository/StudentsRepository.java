package com.example.booklocker.repository;

import com.example.booklocker.entity.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentsRepository extends JpaRepository<Students, Integer> {

    Optional<Students> findByStudentId(Integer studentId);

    Students findByLockerNum(String lockerNum);
}

