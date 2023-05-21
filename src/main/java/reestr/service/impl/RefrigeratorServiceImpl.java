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
import reestr.model.Refrigerator;
import reestr.repository.LineRepository;
import reestr.repository.RefrigeratorRepository;
import reestr.service.BaseSpecification;
import reestr.service.RefrigeratorService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RefrigeratorServiceImpl implements RefrigeratorService {
    private final RefrigeratorRepository repository;
    private final BaseSpecification baseSpecification;
    private final LineRepository typeRepository;

    @Override
    @Transactional
    public Refrigerator create(Integer lineId, Refrigerator refrigerator) {
        EquipmentLine line = typeRepository.findById(lineId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Такой линейки телефонов не представлено, id=%s нет", lineId)));
        refrigerator.setLine(line);
        return repository.save(refrigerator);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Refrigerator> getSortedByPriceAndAlphabet(String price, PageRequest pageable) {
        if (price.equals("desc")) {
            return repository.findByOrderByNameAscPriceDesc(pageable);
        } else return repository.findByOrderByNameAscPriceAsc(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Refrigerator> findByNameAndFilterByParams(String name, String colour, String from, String to, String processor, Integer doorCount, Pageable pageable) {
        Collection<Refrigerator> refrigerators;

        Specification<Refrigerator> specification = baseSpecification.findByNameAndFilterByParams(name, colour, from, to, null, null, null, null, null, null, null, null, doorCount, processor);
        Page<Refrigerator> computersPage = repository.findAll(specification, pageable);
        refrigerators = computersPage.getContent().stream().distinct().collect(Collectors.toList());

        return new ArrayList<>(refrigerators);
    }
}
