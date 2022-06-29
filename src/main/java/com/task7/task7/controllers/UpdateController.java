package com.task7.task7.controllers;

import com.task7.task7.domain.Mark;
import com.task7.task7.domain.ModelAuto;
import com.task7.task7.domain.Modification;
import com.task7.task7.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UpdateController {

    @Autowired
    CarRepository carRepository;

    @PostMapping("/update/mark/{markId}")
    public void updateMarkById(@PathVariable Long markId,
                               @RequestParam(value = "name", required = false, defaultValue = "mark") String name,
                               @RequestParam(value = "isActive", required = false, defaultValue = "true") boolean isActive) {

        Mark mark = carRepository.getMarkById(markId);

        mark.setName(name);
        mark.setActive(isActive);

        carRepository.saveOrUpdateMark(mark);
    }

    @PostMapping("/update/model/{modelId}")
    public void updateModelAutoById(@PathVariable Long modelId,
                                    @RequestParam(value = "name", required = false, defaultValue = "model") String name,
                                    @RequestParam(value = "isActive", required = false, defaultValue = "true") boolean isActive) {

        ModelAuto modelAuto = carRepository.getModelAutoById(modelId);

        modelAuto.setName(name);
        modelAuto.setActive(isActive);

        carRepository.updateModelAuto(modelAuto);
    }

    @PostMapping("/update/modification/{modificationId}")
    public Modification updateModificationById(@PathVariable Long modificationId,
                                               @RequestParam(value = "name", required = false, defaultValue = "modification") String name,
                                               @RequestParam(value = "isActive", required = false, defaultValue = "true") boolean isActive,
                                               @RequestParam(value = "periodBegin", required = false, defaultValue = "") String periodBegin,
                                               @RequestParam(value = "periodEnd", required = false, defaultValue = "") String periodEnd) {

        Modification modification = carRepository.getModificationById(modificationId);

        modification.setName(name);
        modification.setActive(isActive);
        if (!periodBegin.equals(""))
            modification.setPeriodBegin(periodBegin);
        if (!periodEnd.equals(""))
            modification.setPeriodEnd(periodEnd);

        carRepository.updateModification(modification);

        return modification;
    }

}
