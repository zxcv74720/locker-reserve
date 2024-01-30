package com.example.booklocker.controller;

import com.example.booklocker.entity.Lockers;
import com.example.booklocker.entity.Students;
import com.example.booklocker.repository.LockersRepository;
import com.example.booklocker.repository.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private StudentsRepository studentsRepository;

    @Autowired
    private LockersRepository lockersRepository;

    @GetMapping
    public String showLoginForm() {
        return "index";
    }

    @PostMapping
    public String processLoginForm(@RequestParam("studentId") Integer studentId, HttpSession session, Model model) {
        Optional<Students> studentOptional = studentsRepository.findById(studentId);

        if (studentOptional.isPresent()) {
            Students student = studentOptional.get();
            session.setAttribute("student", student);

            // 사물함 목록을 조회하여 model에 추가
            List<Lockers> lockers = lockersRepository.findAll();
            model.addAttribute("lockers", lockers);

            return "reservationPage";
        } else {
            model.addAttribute("errorMessage", "잘못된 학번입니다.");
            return "index";
        }
    }

}










