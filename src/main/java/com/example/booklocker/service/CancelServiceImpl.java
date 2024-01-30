package com.example.booklocker.service;

import com.example.booklocker.entity.Lockers;
import com.example.booklocker.entity.Reservations;
import com.example.booklocker.entity.Students;
import com.example.booklocker.repository.LockersRepository;
import com.example.booklocker.repository.ReservationsRepository;
import com.example.booklocker.repository.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CancelServiceImpl implements CancelService {
    @Autowired
    private ReservationsRepository reservationRepository;

    @Autowired
    private StudentsRepository studentsRepository;

    @Autowired
    private LockersRepository lockersRepository;

    @Override
    public boolean cancelService(String lockerNum) {
        try {
            // 예약 취소 로직 구현
            Reservations reservation = reservationRepository.findByLockerNum(lockerNum);
            if (reservation != null) {
                // 예약 취소
                reservationRepository.delete(reservation);

                // students 테이블에서 lockerNum 삭제
                Students student = studentsRepository.findByLockerNum(lockerNum);
                if (student != null) {
                    student.setLockerNum(null);
                    studentsRepository.save(student);
                }

                // lockers 테이블에서 status 삭제
                Lockers locker = lockersRepository.findByLockerNum(lockerNum);
                if (locker != null) {
                    locker.setStatus(0);
                    lockersRepository.save(locker);
                }

                return true;
            }
        } catch (Exception e) {
            // 예외 처리 로직
            e.printStackTrace();
        }
        return false;
    }
}

