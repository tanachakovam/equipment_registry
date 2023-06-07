package reestr.service;

import reestr.model.EquipmentType;

import java.util.List;

public interface EquipmentTypeService {
    EquipmentType addNewEquipmentType(EquipmentType equipmentType);

    List<EquipmentType> getAllTypes();
}
