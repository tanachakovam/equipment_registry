package reestr.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reestr.model.EquipmentLine;
import reestr.service.EquipmentLineService;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/lines")
@Validated
public class EquipmentLineController {
    private final EquipmentLineService service;

    public EquipmentLineController(EquipmentLineService service) {
        this.service = service;
    }


    @GetMapping("/all")
    public List<EquipmentLine> getAllComputers() {
        return service.getAllTypes();
    }
}
