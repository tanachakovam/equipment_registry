package reestr.service;

import reestr.model.EquipmentLine;

import java.util.List;

public interface EquipmentLineService {
    EquipmentLine create(EquipmentLine equipmentLine);

    List<EquipmentLine> getAllTypes();
}
