package com.task7.task7.controllers;

import com.task7.task7.domain.Modification;
import com.task7.task7.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ModificationController {

    @Autowired
    CarRepository carRepository;

    @GetMapping("/car/{markId}/{modelId}")
    public String printModification(@PathVariable Long markId, @PathVariable Long modelId, Model model) {

        try {
            model.addAttribute("listModification", carRepository.getAllModificationByIdModelAuto(modelId));
            model.addAttribute("modelId", modelId);
            model.addAttribute("markId", markId);
        } catch (Exception e) {
            return "redirect:/";
        }

        return "modification";
    }

    @PostMapping("/car/{markId}/{modelId}")
    public String addModification(@PathVariable Long markId, @PathVariable Long modelId, String name) {
        carRepository.saveModificationByIdModelAuto(modelId, new Modification(name));

        return "redirect:/car/" + markId + "/" + modelId;
    }

    @PostMapping("/car/{markId}/{modelId}/delete")
    public String deleteModification(@PathVariable Long markId, @PathVariable Long modelId, Long id) {
        carRepository.deleteModification(id);

        return "redirect:/car/" + markId + "/" + modelId;
    }
}
