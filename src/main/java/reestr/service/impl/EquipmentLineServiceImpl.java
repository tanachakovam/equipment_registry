package reestr.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import reestr.exception.NotFoundException;
import reestr.model.EquipmentLine;
import reestr.model.EquipmentType;
import reestr.repository.LineRepository;
import reestr.repository.TypeRepository;
import reestr.service.EquipmentLineService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipmentLineServiceImpl implements EquipmentLineService {
    private final LineRepository repository;
    private final TypeRepository typeRepository;

    @Override
    @Transactional
    public EquipmentLine  addNewLine(EquipmentLine equipmentLine, Integer typeId) {
        EquipmentType type = typeRepository.findById(typeId).orElseThrow(
                () -> new NotFoundException(String.format("Вид техники с id=%s не представлен", typeId)));
        equipmentLine.setType(type);
        return repository.save(equipmentLine);
    }


    @Override
    @Transactional(readOnly = true)
    public List<EquipmentLine> getAllLines() {
        return repository.findAll();
    }
}
