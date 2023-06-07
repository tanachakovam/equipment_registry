package reestr.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reestr.model.EquipmentLine;
import reestr.model.EquipmentType;
import reestr.model.ModelDetails;
import reestr.service.EquipmentLineService;
import reestr.service.EquipmentTypeService;
import reestr.service.ModelService;

import javax.validation.constraints.Positive;
import java.util.List;


@RestController
@Validated
@RequiredArgsConstructor
public class EquipmentLineController {
    private final EquipmentLineService lineService;
    private final EquipmentTypeService typeService;
    private final ModelService modelService;

    @PostMapping("/types")
    public EquipmentType addNewEquipmentType(@RequestBody EquipmentType equipmentType) {
        return typeService.addNewEquipmentType(equipmentType);
    }


    @PostMapping("/types/{typeId}/lines")
    public EquipmentLine addNewLine(@PathVariable Integer typeId, @RequestBody EquipmentLine equipmentLine) {
        return lineService.addNewLine(equipmentLine, typeId);
    }

    @PostMapping("/types/{typeId}/lines/{lineId}/models")
    public ModelDetails addNewModel(@PathVariable Integer typeId, @PathVariable Integer lineId,
                                    @RequestBody ModelDetails modelDetails) {
        return modelService.addNewModel(modelDetails, typeId, lineId);
    }

    @GetMapping("/types/{typeId}/models/sorted")
    public List<ModelDetails> getSortedByPriceAndAlphabet(@PathVariable Integer typeId, @RequestParam(name = "price", required = false) String price) {
        return modelService.getSortedByPriceAndAlphabet(typeId, price);
    }

    @GetMapping("/types/{typeId}/models/filtered")
    public List<ModelDetails> findByNameAndFilterByParams(@PathVariable Integer typeId,
                                                          @RequestParam(name = "name", required = false) String name,
                                                          @RequestParam(name = "colour", required = false) String colour,
                                                          @Positive @RequestParam(name = "priceFrom", defaultValue = "0", required = false) String from,
                                                          @Positive @RequestParam(name = "priceTo", required = false) String to) {
        return modelService.findByNameAndFilterByParams(typeId, name, colour, from, to);
    }

    @GetMapping("/lines/all")
    public List<EquipmentLine> getAllLines() {
        return lineService.getAllLines();
    }

    @GetMapping("/types/all")
    public List<EquipmentType> getAllTypes() {
        return typeService.getAllTypes();
    }

    @GetMapping("/models/all")
    public List<ModelDetails> getAllModels() {
        return modelService.getAllModels();
    }
}
