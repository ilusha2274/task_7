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

import java.util.ArrayList;
import java.util.List;

@RestController
public class UpdateController {

    @Autowired
    CarRepository carRepository;

    @PostMapping("/update/mark/{markId}")
    public Mark updateMarkById(@PathVariable Long markId,
                               @RequestParam(value = "name", required = false, defaultValue = "mark") String name,
                               @RequestParam(value = "isActive", required = false, defaultValue = "true") boolean isActive) {

        try {
            Mark mark = carRepository.getMarkById(markId);

            mark.setName(name);
            mark.setActive(isActive);

            carRepository.saveOrUpdateMark(mark);

            return mark;
        } catch (Exception e) {
            return null;
        }
    }

    @PostMapping("/update/model/{modelId}")
    public ModelAuto updateModelAutoById(@PathVariable Long modelId,
                                         @RequestParam(value = "name", required = false, defaultValue = "model") String name,
                                         @RequestParam(value = "isActive", required = false, defaultValue = "true") boolean isActive) {

        try {
            ModelAuto modelAuto = carRepository.getModelAutoById(modelId);

            modelAuto.setName(name);
            modelAuto.setActive(isActive);

            carRepository.updateModelAuto(modelAuto);

            return modelAuto;
        } catch (Exception e) {
            return null;
        }
    }

    @PostMapping("/update/modification/{modificationId}")
    public Modification updateModificationById(@PathVariable Long modificationId,
                                               @RequestParam(value = "name", required = false, defaultValue = "modification") String name,
                                               @RequestParam(value = "isActive", required = false, defaultValue = "true") boolean isActive,
                                               @RequestParam(value = "periodBegin", required = false, defaultValue = "-1") int periodBegin,
                                               @RequestParam(value = "periodEnd", required = false, defaultValue = "-1") int periodEnd) {

        try {
            Modification modification = carRepository.getModificationById(modificationId);

            modification.setName(name);
            modification.setActive(isActive);
            if (periodBegin != -1)
                modification.setPeriodBegin(periodBegin);
            if (periodEnd != -1)
                modification.setPeriodEnd(periodEnd);

            carRepository.updateModification(modification);

            return modification;
        } catch (Exception e) {
            return null;
        }
    }

    @PostMapping("/create")
    public List<Mark> create() {
        List<ModelAuto> modelAutoListAudi = new ArrayList<>();
        List<Modification> modificationListAudi = new ArrayList<>();

        modificationListAudi.add(new Modification("3D", 2010, 2015));
        modificationListAudi.add(new Modification("5D", 2010, 2015));
        modificationListAudi.add(new Modification("3D", 2014, 2018));
        modificationListAudi.add(new Modification("5D", 2014, 2018));
        modificationListAudi.add(new Modification("3D", 2018, 9999));
        modificationListAudi.add(new Modification("5D", 2018, 9999));

        modelAutoListAudi.add(new ModelAuto("a1", modificationListAudi));

        carRepository.saveOrUpdateMark(new Mark("Audi", modelAutoListAudi));

        List<ModelAuto> modelAutoListBmw = new ArrayList<>();
        List<Modification> modificationListBmw = new ArrayList<>();

        modificationListBmw.add(new Modification("3D", 2019, 2022));
        modificationListBmw.add(new Modification("5D", 2019, 2022));
        modificationListBmw.add(new Modification("3D", 2022, 9999));
        modificationListBmw.add(new Modification("5D", 2022, 9999));

        modelAutoListBmw.add(new ModelAuto("m8", modificationListBmw));

        carRepository.saveOrUpdateMark(new Mark("Bmw", modelAutoListBmw));

        return carRepository.getAllMark();

    }

}
