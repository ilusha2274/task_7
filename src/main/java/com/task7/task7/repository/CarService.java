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

    @Override
    public List<Mark> getByNameMark(String nameMark, int periodBegin, int periodEnd) {
        List<Mark> markListResult = new ArrayList<>();
        List<Mark> marks = filterMark(markRepository.findByNameMark(nameMark, periodBegin, periodEnd));

        for (Mark mark : marks) {
            List<ModelAuto> modelAutoResult = new ArrayList<>();
            List<ModelAuto> modelAutoList = mark.getModelAutoList();

            for (ModelAuto modelAuto : modelAutoList) {
                List<Modification> modificationList = filterModification(modelAuto.getModificationList(), periodBegin, periodEnd);

                modelAutoResult.add(new ModelAuto(modelAuto.getId(), modelAuto.getName(), modelAuto.isActive(), modificationList, modelAuto.getMark()));
            }

            markListResult.add(new Mark(mark.getId(), mark.getName(), mark.isActive(), modelAutoResult));
        }

        return markListResult;
    }

    @Override
    public List<Mark> getByNameMarkNameModel(String nameMark, String nameModel, int periodBegin, int periodEnd) {
        List<Mark> markListResult = new ArrayList<>();
        List<Mark> marks = filterMark(markRepository.findByNameMark(nameMark, periodBegin, periodEnd));

        for (Mark mark : marks) {
            List<ModelAuto> modelAutoResult = new ArrayList<>();
            List<ModelAuto> modelAutoList = filterModelAuto(mark.getModelAutoList(), nameModel);

            for (ModelAuto modelAuto : modelAutoList) {
                List<Modification> modificationList = filterModification(modelAuto.getModificationList(), periodBegin, periodEnd);

                modelAutoResult.add(new ModelAuto(modelAuto.getId(), modelAuto.getName(), modelAuto.isActive(), modificationList, modelAuto.getMark()));
            }

            markListResult.add(new Mark(mark.getId(), mark.getName(), mark.isActive(), modelAutoResult));
        }

        return markListResult;
    }

    @Override
    public List<Mark> getByNameMarkNameModification(String nameMark, String nameModification, int periodBegin, int periodEnd) {
        List<Mark> markListResult = new ArrayList<>();
        List<Mark> marks = filterMark(markRepository.findByNameMark(nameMark, periodBegin, periodEnd));

        for (Mark mark : marks) {
            List<ModelAuto> modelAutoResult = new ArrayList<>();
            List<ModelAuto> modelAutoList = mark.getModelAutoList();

            for (ModelAuto modelAuto : modelAutoList) {
                List<Modification> modificationList = filterModification(modelAuto.getModificationList(), periodBegin, periodEnd, nameModification);

                modelAutoResult.add(new ModelAuto(modelAuto.getId(), modelAuto.getName(), modelAuto.isActive(), modificationList, modelAuto.getMark()));
            }

            markListResult.add(new Mark(mark.getId(), mark.getName(), mark.isActive(), modelAutoResult));
        }

        return markListResult;
    }

    @Override
    public List<Mark> getByNameMarkNameModelNameModification(String nameMark, String nameModel, String nameModification, int periodBegin, int periodEnd) {
        List<Mark> markListResult = new ArrayList<>();
        List<Mark> marks = filterMark(markRepository.findByNameMark(nameMark, periodBegin, periodEnd));

        for (Mark mark : marks) {
            List<ModelAuto> modelAutoResult = new ArrayList<>();
            List<ModelAuto> modelAutoList = filterModelAuto(mark.getModelAutoList(), nameModel);

            for (ModelAuto modelAuto : modelAutoList) {
                List<Modification> modificationList = filterModification(modelAuto.getModificationList(), periodBegin, periodEnd, nameModification);

                modelAutoResult.add(new ModelAuto(modelAuto.getId(), modelAuto.getName(), modelAuto.isActive(), modificationList, modelAuto.getMark()));
            }

            markListResult.add(new Mark(mark.getId(), mark.getName(), mark.isActive(), modelAutoResult));
        }

        return markListResult;
    }

    @Override
    public List<Mark> getByNameModel(String nameModel, int periodBegin, int periodEnd) {
        List<Mark> markListResult = new ArrayList<>();
        List<Mark> marks = getAllMark();
        List<ModelAuto> modelAutoListResult = filterModelAuto(modelAutoRepository.findByNameModel(nameModel, periodBegin, periodEnd));

        for (Mark mark : marks) {
            List<ModelAuto> modelAutoResult = new ArrayList<>();
            List<ModelAuto> modelAutoList = filterModelAutoNameMark(modelAutoListResult, mark.getName());

            for (ModelAuto modelAuto : modelAutoList) {
                List<Modification> modificationList = filterModification(modelAuto.getModificationList(), periodBegin, periodEnd);

                modelAutoResult.add(new ModelAuto(modelAuto.getId(), modelAuto.getName(), modelAuto.isActive(), modificationList, modelAuto.getMark()));
            }

            if (modelAutoList.size() != 0)
                markListResult.add(new Mark(mark.getId(), mark.getName(), mark.isActive(), modelAutoResult));
        }

        return markListResult;
    }

    @Override
    public List<Mark> getByNameModelNameModification(String nameModel, String nameModification, int periodBegin, int periodEnd) {
        List<Mark> markListResult = new ArrayList<>();
        List<Mark> marks = getAllMark();
        List<ModelAuto> modelAutoListResult = filterModelAuto(modelAutoRepository.findByNameModel(nameModel, periodBegin, periodEnd));

        for (Mark mark : marks) {
            List<ModelAuto> modelAutoResult = new ArrayList<>();
            List<ModelAuto> modelAutoList = filterModelAutoNameMark(modelAutoListResult, mark.getName());

            for (ModelAuto modelAuto : modelAutoList) {
                List<Modification> modificationList = filterModification(modelAuto.getModificationList(), periodBegin, periodEnd, nameModification);

                modelAutoResult.add(new ModelAuto(modelAuto.getId(), modelAuto.getName(), modelAuto.isActive(), modificationList, modelAuto.getMark()));
            }

            if (modelAutoList.size() != 0)
                markListResult.add(new Mark(mark.getId(), mark.getName(), mark.isActive(), modelAutoResult));
        }

        return markListResult;
    }

    @Override
    public List<Mark> getByNameModification(String nameModification, int periodBegin, int periodEnd) {
        List<Mark> markListResult = new ArrayList<>();
        List<Mark> marks = getAllMark();
        List<Modification> modificationListResult = modificationRepository.findByNameModification(nameModification, periodBegin, periodEnd);

        for (Mark mark : marks) {
            List<ModelAuto> modelAutoResult = new ArrayList<>();
            List<ModelAuto> modelAutoList = mark.getModelAutoList();

            for (ModelAuto modelAuto : modelAutoList) {
                List<Modification> modificationList = filterModification(modificationListResult, modelAuto.getName());

                if (modificationList.size() != 0)
                    modelAutoResult.add(new ModelAuto(modelAuto.getId(), modelAuto.getName(), modelAuto.isActive(), modificationList, modelAuto.getMark()));
            }

            if (modelAutoResult.size() != 0)
                markListResult.add(new Mark(mark.getId(), mark.getName(), mark.isActive(), modelAutoResult));
        }

        return markListResult;
    }

    @Override
    public List<Mark> getByDate(int periodBegin, int periodEnd) {
        List<Mark> markListResult = new ArrayList<>();
        List<Mark> marks = getAllMark();
        List<Modification> modificationListResult = modificationRepository.findByDate(periodBegin, periodEnd);

        for (Mark mark : marks) {
            List<ModelAuto> modelAutoResult = new ArrayList<>();
            List<ModelAuto> modelAutoList = mark.getModelAutoList();

            for (ModelAuto modelAuto : modelAutoList) {
                List<Modification> modificationList = filterModification(modificationListResult, modelAuto.getName());

                if (modificationList.size() != 0)
                    modelAutoResult.add(new ModelAuto(modelAuto.getId(), modelAuto.getName(), modelAuto.isActive(), modificationList, modelAuto.getMark()));
            }

            if (modelAutoResult.size() != 0)
                markListResult.add(new Mark(mark.getId(), mark.getName(), mark.isActive(), modelAutoResult));
        }

        return markListResult;
    }

    private List<Mark> filterMark(List<Mark> marks) {
        return marks
                .stream()
                .distinct()
                .collect(Collectors.toList());
    }

    private List<ModelAuto> filterModelAuto(List<ModelAuto> modelAutoList) {
        return modelAutoList
                .stream()
                .distinct()
                .collect(Collectors.toList());
    }

    private List<ModelAuto> filterModelAutoNameMark(List<ModelAuto> modelAutoList, String nameMark) {
        return modelAutoList
                .stream()
                .filter(modelAuto -> modelAuto.getMark().getName().equals(nameMark))
                .distinct()
                .collect(Collectors.toList());
    }

    private List<ModelAuto> filterModelAuto(List<ModelAuto> modelAutoList, String nameModel) {
        return modelAutoList
                .stream()
                .filter(modelAuto -> modelAuto.getName().equals(nameModel))
                .collect(Collectors.toList());
    }

    private List<Modification> filterModification(List<Modification> modificationList, int periodBegin, int periodEnd) {
        return modificationList
                .stream()
                .filter(modification -> modification.getPeriodBegin() >= periodBegin &&
                        modification.getPeriodEnd() <= periodEnd)
                .collect(Collectors.toList());
    }

    private List<Modification> filterModification(List<Modification> modificationList, int periodBegin, int periodEnd, String nameModification) {
        return modificationList
                .stream()
                .filter(modification -> modification.getPeriodBegin() >= periodBegin &&
                        modification.getPeriodEnd() <= periodEnd &&
                        modification.getName().equals(nameModification))
                .collect(Collectors.toList());
    }

    private List<Modification> filterModification(List<Modification> modificationList, String nameModel) {
        return modificationList
                .stream()
                .filter(modification -> modification.getModelAuto().getName().equals(nameModel))
                .collect(Collectors.toList());
    }
}
