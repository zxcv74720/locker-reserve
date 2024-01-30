package com.example.booklocker.controller;

import com.example.booklocker.dto.CancelReservationRequest;
import com.example.booklocker.entity.Lockers;
import com.example.booklocker.entity.Students;
import com.example.booklocker.repository.LockersRepository;
import com.example.booklocker.service.CancelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CancelController {

    @Autowired
    private CancelService cancelService;

    @Autowired
    private LockersRepository lockersRepository;


    // 예약 취소 처리를 위한 POST 요청 핸들러
    @PostMapping("/cancel")
    public String cancelReservation(@RequestBody CancelReservationRequest request, HttpServletRequest httpServletRequest, Model model) {
        String lockerNum = request.getLockerNum();

        HttpSession session = httpServletRequest.getSession();
        Students student = (Students) session.getAttribute("student"); // 세션에서 학생 정보 가져오기

        // 예약 취소 로직 구현
        boolean success = cancelService.cancelService(lockerNum);
        if (success) {
            student.setLockerNum(null); // 예약 취소 시 lockerNum 값을 null로 설정
        }

        // 예약 페이지로 이동
        List<Lockers> lockers = lockersRepository.findAll();
        model.addAttribute("lockers", lockers);
        return "reservationPage";
    }

}
