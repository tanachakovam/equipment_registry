package reestr.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reestr.model.EquipmentLine;
import reestr.model.VacuumCleaner;
import reestr.service.EquipmentLineService;
import reestr.service.VacuumCleanerService;

import javax.validation.constraints.Positive;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/vacuumcleaners")
@Validated
@RequiredArgsConstructor
public class VacuumCleanerController {
    private final VacuumCleanerService service;
    private final EquipmentLineService lineService;


    @PostMapping("/lines")
    public EquipmentLine createVacuumCleanerLine(@RequestBody EquipmentLine vacuumCleanerLine) {
        log.info("Получен запрос /POST /vacuumcleaners/lines. Линейка пылесосов {} добавлена.", vacuumCleanerLine.getName());
        return lineService.create(vacuumCleanerLine);
    }

    @PostMapping("/lines/{lineId}/models")
    public VacuumCleaner create(@PathVariable Integer lineId, @RequestBody VacuumCleaner vacuumCleaner) {
        log.info("Получен запрос POST /cleaners. Пылесос {} добавлен.", vacuumCleaner.getName());
        return service.create(lineId, vacuumCleaner);
    }

    // @GetMapping("/cleaners?price=asc")
    @GetMapping("/models/sorted")
    public List<VacuumCleaner> getSortedByPriceAndAlphabet(@RequestParam(name = "price", required = false) String price,
                                                           @RequestParam(name = "from", defaultValue = "0") Integer from,
                                                           @Positive @RequestParam(name = "size", defaultValue = "10") Integer size) {
        return service.getSortedByPriceAndAlphabet(price, PageRequest.of(from / size, size));
    }

    @GetMapping("/all")
    public List<VacuumCleaner> getAllVacuumCleaners() {
        return service.getAllVacuumCleaners();
    }

    @GetMapping("/models/filtered")
    public List<VacuumCleaner> findByNameAndFilterByParams(@RequestParam(name = "name") String name,
                                                           @RequestParam(name = "colour", required = false) String colour,
                                                           @Positive @RequestParam(name = "priceFrom", defaultValue = "0", required = false) String from,
                                                           @Positive @RequestParam(name = "priceTo", required = false) String to,
                                                           @Positive @RequestParam(name = "collector", required = false) String collectorVolume,
                                                           @Positive @RequestParam(name = "regime", required = false) Integer regimeCount,
                                                           @RequestParam(name = "from", defaultValue = "0") Integer fromPage,
                                                           @Positive @RequestParam(name = "size", defaultValue = "10") Integer sizePage) {
        return service.findByNameAndFilterByParams(name, colour, from, to, regimeCount, collectorVolume, PageRequest.of(fromPage / sizePage, sizePage));
    }
}
