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
import reestr.model.TV;
import reestr.repository.LineRepository;
import reestr.repository.TvRepository;
import reestr.service.BaseSpecification;
import reestr.service.TvService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TvServiceImpl implements TvService {
    private final TvRepository repository;
    private final LineRepository typeRepository;
    private final BaseSpecification baseSpecification;

    @Override
    @Transactional
    public TV create(Integer lineId, TV tv) {
        EquipmentLine line = typeRepository.findById(lineId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Такой линейки телефонов не представлено, id=%s нет", lineId)));
        tv.setLine(line);
        return repository.save(tv);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TV> getSortedByPriceAndAlphabet(String price, PageRequest pageable) {
        if (price.equals("desc")) {
            return repository.findByOrderByNameAscPriceDesc(pageable);
        } else return repository.findByOrderByNameAscPriceAsc(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TV> getAllTvs() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<TV> findByNameAndFilterByParams(String name, String colour, String from, String to, String category, String technology, Pageable pageable) {
        Collection<TV> tvs;

        Specification<TV> specification = baseSpecification.findByNameAndFilterByParams(name, colour, from, to, null, null, null, null, category, technology, null, null, null, null);
        Page<TV> computersPage = repository.findAll(specification, pageable);
        tvs = computersPage.getContent().stream().distinct().collect(Collectors.toList());

        return new ArrayList<>(tvs);
    }
}
