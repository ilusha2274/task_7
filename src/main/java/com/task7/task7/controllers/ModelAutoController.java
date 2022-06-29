package com.task7.task7.controllers;


import com.task7.task7.domain.ModelAuto;
import com.task7.task7.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ModelAutoController {

    @Autowired
    CarRepository carRepository;

    @GetMapping("/car/{markId}")
    public String printModelAuto(@PathVariable Long markId, Model model) {

        try {
            model.addAttribute("listModelAuto", carRepository.getAllModelAutoByIdMark(markId));
            model.addAttribute("markId", markId);
        } catch (Exception e) {
            return "redirect:/";
        }

        return "modelAuto";
    }

    @PostMapping("/car/{markId}")
    public String addModelAuto(@PathVariable Long markId, String name) {
        carRepository.saveModelAutoByIdMark(markId, new ModelAuto(name));

        return "redirect:/car/" + markId;
    }

    @PostMapping("/car/{markId}/delete")
    public String deleteModelAuto(@PathVariable Long markId, Long id) {
        carRepository.deleteModelAuto(id);

        return "redirect:/car/" + markId;
    }
}
