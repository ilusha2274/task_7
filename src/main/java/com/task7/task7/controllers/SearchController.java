package com.task7.task7.controllers;

import com.task7.task7.domain.Mark;
import com.task7.task7.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchController {

    @Autowired
    CarRepository carRepository;

    @PostMapping("/search")
    public List<Mark> search(@RequestParam(value = "nameMark", required = false, defaultValue = "null") String nameMark,
                             @RequestParam(value = "nameModel", required = false, defaultValue = "null") String nameModel,
                             @RequestParam(value = "nameModification", required = false, defaultValue = "null") String nameModification,
                             @RequestParam(value = "periodBegin", required = false, defaultValue = "0000") int periodBegin,
                             @RequestParam(value = "periodEnd", required = false, defaultValue = "9999") int periodEnd) {

        if (nameModel.equals("null") && nameModel.equals("null") && nameModification.equals("null"))
            return carRepository.getByDate(periodBegin, periodEnd);

        if (nameModel.equals("null") && nameModification.equals("null"))
            return carRepository.getByNameMark(nameMark.toUpperCase(), periodBegin, periodEnd);

        if (nameMark.equals("null") && nameModification.equals("null"))
            return carRepository.getByNameModel(nameModel.toLowerCase(), periodBegin, periodEnd);

        if (nameMark.equals("null") && nameModel.equals("null"))
            return carRepository.getByNameModification(nameModification.toLowerCase(), periodBegin, periodEnd);

        if (nameMark.equals("null"))
            return carRepository.getByNameModelNameModification(nameModel.toLowerCase(), nameModification.toLowerCase(), periodBegin, periodEnd);

        if (nameModel.equals("null"))
            return carRepository.getByNameMarkNameModification(nameMark.toUpperCase(), nameModification.toLowerCase(), periodBegin, periodEnd);

        if (nameModification.equals("null"))
            return carRepository.getByNameMarkNameModel(nameMark.toUpperCase(), nameModel.toLowerCase(), periodBegin, periodEnd);

        return carRepository.getByNameMarkNameModelNameModification(nameMark.toUpperCase(), nameModel.toLowerCase(),
                nameModification.toLowerCase(), periodBegin, periodEnd);
    }
}
