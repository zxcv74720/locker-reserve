package com.example.booklocker.repository;

import com.example.booklocker.entity.Lockers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LockersRepository extends JpaRepository<Lockers, String> {
    // 기본 키 값을 사용하여 Lockers 엔티티를 검색하는 findById() 메서드
    Optional<Lockers> findById(String lockerNumber);

    Lockers findByLockerNum(String lockerNum);
}

