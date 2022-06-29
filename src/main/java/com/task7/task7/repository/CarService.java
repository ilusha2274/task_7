package com.task7.task7.repository;

import com.task7.task7.domain.Mark;
import com.task7.task7.domain.ModelAuto;
import com.task7.task7.domain.Modification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService implements CarRepository {

    @Autowired
    MarkRepository markRepository;
    @Autowired
    ModelAutoRepository modelAutoRepository;
    @Autowired
    ModificationRepository modificationRepository;

    public List<Mark> getAllMark() {
        ArrayList<Mark> marks = new ArrayList<>();

        markRepository.findAll().forEach(marks::add);

        return marks
                .stream()
                .filter(Mark::isActive)
                .collect(Collectors.toList());
    }

    public void saveOrUpdateMark(Mark mark) {
        markRepository.save(mark);
    }

    public void deleteMark(Long id) {
        markRepository.deleteById(id);
    }

    public Mark getMarkById(Long id) {
        return markRepository.findById(id).get();
    }

    public List<ModelAuto> getAllModelAutoByIdMark(Long id) {

        return getMarkById(id).getModelAutoList()
                .stream()
                .filter(ModelAuto::isActive)
                .collect(Collectors.toList());
    }

    public void saveModelAutoByIdMark(Long idMark, ModelAuto modelAuto) {
        Mark mark = getMarkById(idMark);
        List<ModelAuto> modelAutoList = mark.getModelAutoList();

        modelAutoList.add(modelAuto);

        modelAutoRepository.save(modelAuto);
        saveOrUpdateMark(mark);
    }

    public void updateModelAuto(ModelAuto modelAuto) {
        modelAutoRepository.save(modelAuto);
    }

    public void deleteModelAuto(Long id) {
        modelAutoRepository.deleteById(id);
    }

    public ModelAuto getModelAutoById(Long id) {
        return modelAutoRepository.findById(id).get();
    }

    public List<Modification> getAllModificationByIdModelAuto(Long id) {

        return getModelAutoById(id).getModificationList()
                .stream()
                .filter(Modification::isActive)
                .collect(Collectors.toList());
    }

    public void saveModificationByIdModelAuto(Long idModelAuto, Modification modification) {
        ModelAuto modelAuto = getModelAutoById(idModelAuto);
        List<Modification> modificationList = modelAuto.getModificationList();

        modificationList.add(modification);

        modificationRepository.save(modification);
        modelAutoRepository.save(modelAuto);
    }

    public void updateModification(Modification modification) {
        modificationRepository.save(modification);
    }

    public void deleteModification(Long id) {
        modificationRepository.deleteById(id);
    }

    public Modification getModificationById(Long id) {
        return modificationRepository.findById(id).get();
    }

}
