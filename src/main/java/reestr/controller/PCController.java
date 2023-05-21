package reestr.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reestr.model.EquipmentLine;
import reestr.model.PC;
import reestr.service.EquipmentLineService;
import reestr.service.PCService;

import javax.validation.constraints.Positive;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/computers")
@Validated
@RequiredArgsConstructor
public class PCController {
    private final PCService service;
    private final EquipmentLineService lineService;


    @PostMapping("/lines")
    public EquipmentLine createComputerLine(@RequestBody EquipmentLine computerLine) {
        log.info("Получен запрос /POST /computers/lines. Линейка компьютеров {} добавлена.", computerLine.getName());
        return lineService.create(computerLine);
    }

    @PostMapping("/lines/{lineId}/models")
    public PC create(@PathVariable Integer lineId, @RequestBody PC computer) {
        log.info("Получен запрос /POST /computer/lines/{lineId}/models. Компьютер {} добавлен.", computer.getName());
        return service.create(computer, lineId);
    }

    // @GetMapping("/computers?price=asc")
    @GetMapping("/models/sorted")
    public List<PC> getSortedByPriceAndAlphabet(@RequestParam(name = "price", required = false) String price,
                                                @RequestParam(name = "from", defaultValue = "0") Integer from,
                                                @Positive @RequestParam(name = "size", defaultValue = "10") Integer size) {
        return service.getComputersSortedByPriceAndAlphabet(price, PageRequest.of(from / size, size));
    }

    @GetMapping("/all")
    public List<PC> getAllComputers() {
        return service.getAllComputers();
    }

    @GetMapping("/models/filtered")
    public List<PC> findByNameAndFilterByParams(@RequestParam(name = "name") String name,
                                                @RequestParam(name = "colour", required = false) String colour,
                                                @Positive @RequestParam(name = "priceFrom", defaultValue = "0", required = false) String from,
                                                @Positive @RequestParam(name = "priceTo", required = false) String to,
                                                @Positive @RequestParam(name = "category", required = false) String category,
                                                @Positive @RequestParam(name = "processor", required = false) String processor,
                                                @RequestParam(name = "from", defaultValue = "0") Integer fromPage,
                                                @Positive @RequestParam(name = "size", defaultValue = "10") Integer size) {
        return service.findByNameAndFilterByParams(name, colour, from, to, category, processor, PageRequest.of(fromPage / size, size));
    }
}
