package com.task7.task7.controllers;

import com.task7.task7.domain.Mark;
import com.task7.task7.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MarkController {

    @Autowired
    CarRepository carRepository;

    @GetMapping("/")
    public String printMark(Model model) {
        model.addAttribute("listMark", carRepository.getAllMark());

        return "mark";
    }

    @PostMapping("/")
    public String addMark(String name) {
        carRepository.saveOrUpdateMark(new Mark(name));

        return "redirect:/";
    }

    @PostMapping("/delete")
    public String deleteMark(Long id) {
        carRepository.deleteMark(id);

        return "redirect:/";
    }
}
