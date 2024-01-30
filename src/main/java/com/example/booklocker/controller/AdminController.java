package com.example.booklocker.controller;

import com.example.booklocker.entity.Reservations;
import com.example.booklocker.repository.ReservationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private ReservationsRepository reservationsRepository;

    @GetMapping("/admin")
    public String getAllReservations(Model model) {
        // 전체 예약 리스트를 조회하는 로직 구현
        List<Reservations> reservations = reservationsRepository.findAll();
        model.addAttribute("reservations", reservations);

        return "adminPage";
    }

}
