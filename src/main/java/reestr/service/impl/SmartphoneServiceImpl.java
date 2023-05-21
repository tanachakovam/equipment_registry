package reestr.service.impl;


import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import reestr.model.EquipmentLine;
import reestr.model.Smartphone;
import reestr.repository.LineRepository;
import reestr.repository.SmartphoneRepository;
import reestr.service.BaseSpecification;
import reestr.service.SmartphoneService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SmartphoneServiceImpl implements SmartphoneService {
    private final SmartphoneRepository smartphoneRepository;
    private final LineRepository typeRepository;
    private final BaseSpecification baseSpecification;

    @SneakyThrows
    @Override
    @Transactional
    public Smartphone create(Smartphone smartphone, Integer lineId) {
        EquipmentLine line = typeRepository.findById(lineId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Такой линейки телефонов не представлено, id=%s нет", lineId)));
        smartphone.setLine(line);
        return smartphoneRepository.save(smartphone);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Smartphone> getSortedByPriceAndAlphabet(String price, PageRequest pageable) {
        if (price.equals("desc")) {
            return smartphoneRepository.findByOrderByNameAscPriceDesc(pageable);
        } else return smartphoneRepository.findByOrderByNameAscPriceAsc(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Smartphone> getAllSmartphones() {
        return smartphoneRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Smartphone> findByNameAndFilterByParams(String name, String colour, String from, String to, Integer memory, Integer camera, Pageable pageable) {
        Collection<Smartphone> smartphones;

        Specification<Smartphone> specification = baseSpecification.findByNameAndFilterByParams(name, colour, from, to, null, null, memory, camera, null, null, null, null, null, null);
        Page<Smartphone> smartphonePage = smartphoneRepository.findAll(specification, pageable);
        smartphones = smartphonePage.getContent().stream().distinct().collect(Collectors.toList());

        return new ArrayList<>(smartphones);
    }
}
