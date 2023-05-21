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
import reestr.model.PC;
import reestr.repository.LineRepository;
import reestr.repository.PcRepository;
import reestr.service.BaseSpecification;
import reestr.service.PCService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PCServiceImpl implements PCService {
    private final LineRepository typeRepository;
    private final BaseSpecification baseSpecification;
    private final PcRepository pcRepository;

    @Override
    @Transactional
    public PC create(PC computer, Integer lineId) {
        EquipmentLine line = typeRepository.findById(lineId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Такой линейки компьютеров не представлено, id=%s нет", lineId)));
        computer.setLine(line);
        return pcRepository.save(computer);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PC> getComputersSortedByPriceAndAlphabet(String price, PageRequest pageable) {
        if (price.equals("desc")) {
            return pcRepository.findByOrderByNameAscPriceDesc(pageable);
        } else return pcRepository.findByOrderByNameAscPriceAsc(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PC> getAllComputers() {
        return pcRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PC> findByNameAndFilterByParams(String name, String colour, String from, String to, String category, String processor, Pageable pageable) {
        Collection<PC> computers;

        Specification<PC> specification = baseSpecification.findByNameAndFilterByParams(name, colour, from, to, category, processor, null, null, null, null, null, null, null, null);
        Page<PC> computersPage = pcRepository.findAll(specification, pageable);
        computers = computersPage.getContent().stream().distinct().collect(Collectors.toList());

        return new ArrayList<>(computers);
    }
}
