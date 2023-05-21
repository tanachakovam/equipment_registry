package reestr.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import reestr.model.EquipmentLine;
import reestr.model.VacuumCleaner;
import reestr.repository.LineRepository;
import reestr.repository.VacuumCleanerRepository;
import reestr.service.BaseSpecification;
import reestr.service.VacuumCleanerService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VacuumCleanerServiceImpl implements VacuumCleanerService {
    private final VacuumCleanerRepository repository;
    private final LineRepository typeRepository;
    private final BaseSpecification baseSpecification;

    @Override
    @Transactional
    public VacuumCleaner create(Integer lineId, VacuumCleaner vacuumCleaner) {
        EquipmentLine line = typeRepository.findById(lineId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Такой линейки телефонов не представлено, id=%s нет", lineId)));
        vacuumCleaner.setLine(line);
        return repository.save(vacuumCleaner);
    }

    @Override
    @Transactional(readOnly = true)
    public List<VacuumCleaner> getSortedByPriceAndAlphabet(String price, PageRequest pageable) {
        if (price.equals("desc")) {
            return repository.findByOrderByNameAscPriceDesc(pageable);
        } else return repository.findByOrderByNameAscPriceAsc(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public List<VacuumCleaner> getAllVacuumCleaners() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<VacuumCleaner> findByNameAndFilterByParams(String name, String colour, String from, String to, Integer regimeCount, String collectorVolume, Pageable pageable) {
        Collection<VacuumCleaner> vacuumCleaners;

        Specification<VacuumCleaner> specification = baseSpecification.findByNameAndFilterByParams(name, colour, from, to, null, null, null, null, null, null, collectorVolume, regimeCount, null, null);
        Page<VacuumCleaner> computersPage = repository.findAll(specification, pageable);
        vacuumCleaners = computersPage.getContent().stream().distinct().collect(Collectors.toList());

        return new ArrayList<>(vacuumCleaners);
    }
}
