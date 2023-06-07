package reestr.service;

import reestr.model.EquipmentLine;

import java.util.List;

public interface EquipmentLineService {
    EquipmentLine addNewLine(EquipmentLine equipmentLine, Integer typeId);
    List<EquipmentLine> getAllLines();
}
