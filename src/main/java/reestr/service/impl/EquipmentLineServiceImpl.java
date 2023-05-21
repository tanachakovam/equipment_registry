package reestr.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reestr.model.EquipmentLine;
import reestr.repository.LineRepository;
import reestr.service.EquipmentLineService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipmentLineServiceImpl implements EquipmentLineService {
    private final LineRepository repository;

    @Override
    @Transactional
    public EquipmentLine create(EquipmentLine EquipmentLine) {
        return repository.save(EquipmentLine);
    }


    @Override
    @Transactional(readOnly = true)
    public List<EquipmentLine> getAllTypes() {
        return repository.findAll();
    }


}
