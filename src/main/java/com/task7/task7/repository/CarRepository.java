package com.task7.task7.repository;

import com.task7.task7.domain.Mark;
import com.task7.task7.domain.ModelAuto;
import com.task7.task7.domain.Modification;

import java.util.List;

public interface CarRepository {

    List<Mark> getAllMark();

    void saveOrUpdateMark(Mark mark);

    void deleteMark(Long id);

    Mark getMarkById(Long id);

    List<ModelAuto> getAllModelAutoByIdMark(Long id);

    void saveModelAutoByIdMark(Long idMark, ModelAuto modelAuto);

    void updateModelAuto(ModelAuto modelAuto);

    void deleteModelAuto(Long id);

    ModelAuto getModelAutoById(Long id);

    List<Modification> getAllModificationByIdModelAuto(Long id);

    void saveModificationByIdModelAuto(Long idMark, Modification modification);

    void updateModification(Modification modification);

    void deleteModification(Long id);

    Modification getModificationById(Long id);

}
