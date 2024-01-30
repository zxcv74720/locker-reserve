package com.example.booklocker.controller;

import com.example.booklocker.entity.Lockers;
import com.example.booklocker.entity.Reservations;
import com.example.booklocker.entity.Students;
import com.example.booklocker.repository.LockersRepository;
import com.example.booklocker.repository.ReservationsRepository;
import com.example.booklocker.repository.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
public class ReservationController {

    @Autowired
    private LockersRepository lockersRepository;

    @Autowired
    private StudentsRepository studentsRepository;

    @Autowired
    private ReservationsRepository reservationsRepository;

    // 예약 페이지
    @GetMapping("/reservation")
    public String showReservationPage(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Students student = (Students) session.getAttribute("student"); // 세션에서 학생 정보 가져오기

        // 모델에 학생 정보 추가
        model.addAttribute("student", student);

        // 모든 사물함 정보 조회
        List<Lockers> lockers = lockersRepository.findAll();
        model.addAttribute("lockers", lockers);

        return "reservationPage";
    }

    @PostMapping("/reservation")
    public String reserveLocker(@RequestParam("lockerNumber") String lockerNum, Model model, HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();
        Students student = (Students) session.getAttribute("student");

        Optional<Lockers> lockerOptional = lockersRepository.findById(lockerNum);
        Lockers locker = lockerOptional.get();
        if (locker.getStatus() == 0) {
            Reservations reservation = new Reservations();
            reservation.setStudentId(student.getStudentId());
            reservation.setStudentName(student.getStudentName());
            reservation.setStudentContactNum(student.getStudentContactNum());
            reservation.setLockerNum(lockerNum);
            reservation.setLocation(locker.getLocation());
            reservation.setTerm("23-1");
            reservationsRepository.save(reservation);

            student.setLockerNum(lockerNum);
            studentsRepository.save(student);

            locker.setStatus(1);
            lockersRepository.save(locker);

            // 모든 사물함 정보 조회
            List<Lockers> lockers = lockersRepository.findAll();
            model.addAttribute("lockers", lockers);


            model.addAttribute("student", student);
        }

        return "reservationPage";
    }
}

