package reestr.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reestr.model.EquipmentType;
import reestr.repository.TypeRepository;
import reestr.service.EquipmentTypeService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipmentTypeServiceImpl implements EquipmentTypeService {
    private final TypeRepository repository;

    @Override
    @Transactional
    public EquipmentType addNewEquipmentType(EquipmentType equipmentType) {
        return repository.save(equipmentType);
    }


    @Override
    @Transactional(readOnly = true)
    public List<EquipmentType> getAllTypes() {
        return repository.findAll();
    }


}
