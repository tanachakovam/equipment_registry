package reestr.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reestr.model.EquipmentLine;
import reestr.model.Refrigerator;
import reestr.service.EquipmentLineService;
import reestr.service.RefrigeratorService;

import javax.validation.constraints.Positive;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/refrigerators")
@Validated
@RequiredArgsConstructor
public class RefrigeratorController {
    private final RefrigeratorService service;
    private final EquipmentLineService lineService;


    @PostMapping("/lines")
    public EquipmentLine createRefrigeratorLine(@RequestBody EquipmentLine refrigeratorLine) {
        log.info("Получен запрос /POST /refrigerators/lines. Линейка холодильников {} добавлена.", refrigeratorLine.getName());
        return lineService.create(refrigeratorLine);
    }

    @PostMapping("/lines/{lineId}/models")
    public Refrigerator create(@PathVariable Integer lineId, @RequestBody Refrigerator refrigerator) {
        log.info("Получен запрос /POST /refrigerators/lines/{lineId}/models. Холодильник {} добавлен.", refrigerator.getName());
        return service.create(lineId, refrigerator);
    }

    @GetMapping("/models/sorted")
    public List<Refrigerator> getSortedByPriceAndAlphabet(@RequestParam(name = "price", required = false) String price,
                                                          @RequestParam(name = "from", defaultValue = "0") Integer from,
                                                          @Positive @RequestParam(name = "size", defaultValue = "10") Integer size) {
        return service.getSortedByPriceAndAlphabet(price, PageRequest.of(from / size, size));
    }

    @GetMapping("/models/filtered")
    public List<Refrigerator> findByNameAndFilterByParams(@RequestParam(name = "name") String name,
                                                          @RequestParam(name = "colour", required = false) String colour,
                                                          @Positive @RequestParam(name = "priceFrom", defaultValue = "0", required = false) String from,
                                                          @Positive @RequestParam(name = "priceTo", required = false) String to,
                                                          @Positive @RequestParam(name = "compressor", required = false) String compressor,
                                                          @Positive @RequestParam(name = "doorCount", required = false) Integer doorCount,
                                                          @RequestParam(name = "from", defaultValue = "0") Integer fromPage,
                                                          @Positive @RequestParam(name = "size", defaultValue = "10") Integer sizePage) {
        return service.findByNameAndFilterByParams(name, colour, from, to, compressor, doorCount, PageRequest.of(fromPage / sizePage, sizePage));
    }
}
