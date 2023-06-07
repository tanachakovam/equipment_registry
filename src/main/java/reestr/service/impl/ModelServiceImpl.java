package reestr.service.impl;


import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reestr.exception.NotFoundException;
import reestr.model.EquipmentLine;
import reestr.model.EquipmentType;
import reestr.model.ModelDetails;
import reestr.repository.LineRepository;
import reestr.repository.ModelRepository;
import reestr.repository.TypeRepository;
import reestr.service.BaseSpecification;
import reestr.service.ModelService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ModelServiceImpl implements ModelService {
    private final LineRepository lineRepository;
    private final TypeRepository typeRepository;
    private final BaseSpecification baseSpecification;
    private final ModelRepository modelRepository;


    @SneakyThrows
    @Override
    @Transactional
    public ModelDetails addNewModel(ModelDetails modelDetails, Integer typeId, Integer lineId) {
        EquipmentType type = typeRepository.findById(typeId).orElseThrow(
                () -> new NotFoundException(String.format("Вид техники с id=%s не представлен", typeId)));
        EquipmentLine line = lineRepository.findById(lineId).orElseThrow(
                () -> new NotFoundException(String.format("Такой линейки телефонов не представлено, id=%s нет", lineId)));

        modelDetails.setLine(line);
        modelDetails.setType(type);
        return modelRepository.save(modelDetails);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ModelDetails> getSortedByPriceAndAlphabet(Integer typeId, String price) {
        EquipmentType type = typeRepository.findById(typeId).orElseThrow(
                () -> new NotFoundException(String.format("Вид техники с id=%s не представлен", typeId)));

        if (price.equals("desc")) {
            return modelRepository.findByLine_TypeOrderByNameAscPriceDesc(type);
        } else return modelRepository.findByLine_TypeOrderByNameAscPriceAsc(type);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ModelDetails> getAllModels() {
        return modelRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ModelDetails> findByNameAndFilterByParams(Integer typeId, String name, String colour, String from, String to) {
       // Collection<ModelDetails> models;
        EquipmentType type = typeRepository.findById(typeId).orElseThrow(
                () -> new NotFoundException(String.format("Вид техники с id=%s не представлен", typeId)));


        Specification<ModelDetails> specification = baseSpecification.findByNameAndFilterByParams(name, colour, from, to, type);
        List<ModelDetails> modelDetailsList = modelRepository.findAll(specification);
      //  models = modelDetailsList.stream().distinct().collect(Collectors.toList());

        return modelDetailsList;
        //return new ArrayList<>(models);
    }

   /* @Override
    @Transactional(readOnly = true)
    public List<Smartphone> findByNameAndFilterByParams(String name, String colour, String from, String to, Integer memory, Integer camera) {
        Collection<Smartphone> smartphones;

        Specification<Smartphone> specification = baseSpecification.findByNameAndFilterByParams(name, colour, from, to, null, null, memory, camera, null, null, null, null, null, null);
        List<Smartphone> smartphoneList = smartphoneRepository.findAll(specification);
        smartphones = smartphoneList.stream().distinct().collect(Collectors.toList());

        return new ArrayList<>(smartphones);
    }

       @Override
    @Transactional(readOnly = true)
    public List<TV> findByNameAndFilterByParams(String name, String colour, String from, String to, String category, String technology) {
        Collection<TV> tvs;

        Specification<TV> specification = baseSpecification.findByNameAndFilterByParams(name, colour, from, to, null, null, null, null, category, technology, null, null, null, null);
        List<TV> tvList = repository.findAll(specification);
        tvs = tvList.stream().distinct().collect(Collectors.toList());

        return new ArrayList<>(tvs);
    }

        @Override
    @Transactional(readOnly = true)
    public List<VacuumCleaner> findByNameAndFilterByParams(String name, String colour, String from, String to, Integer regimeCount, String collectorVolume) {
        List<VacuumCleaner> vacuumCleaners;

        Specification<VacuumCleaner> specification = baseSpecification.findByNameAndFilterByParams(name, colour, from, to, null, null, null, null, null, null, collectorVolume, regimeCount, null, null);
        List<VacuumCleaner> vacuumCleanerList = repository.findAll(specification);
        vacuumCleaners = vacuumCleanerList.stream().distinct().collect(Collectors.toList());

        return new ArrayList<>(vacuumCleaners);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PC> findByNameAndFilterByParams(String name, String colour, String from, String to, String category, String processor) {
        Collection<PC> computers;

        Specification<PC> specification = baseSpecification.findByNameAndFilterByParams(name, colour, from, to, category, processor, null, null, null, null, null, null, null, null);
        List<PC> computersList = pcRepository.findAll(specification);
        computers = computersList.stream().distinct().collect(Collectors.toList());

        return new ArrayList<>(computers);
    }

        @Override
    @Transactional(readOnly = true)
    public List<Refrigerator> findByNameAndFilterByParams(String name, String colour, String from, String to, String processor, Integer doorCount) {
        Collection<Refrigerator> refrigerators;

        Specification<Refrigerator> specification = baseSpecification.findByNameAndFilterByParams(name, colour, from, to, null, null, null, null, null, null, null, null, doorCount, processor);
        List<Refrigerator> refrigeratorList = repository.findAll(specification);
        refrigerators = refrigeratorList.stream().distinct().collect(Collectors.toList());

        return new ArrayList<>(refrigerators);
    }
    */
}
