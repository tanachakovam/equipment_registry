package reestr.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reestr.model.EquipmentLine;
import reestr.model.TV;
import reestr.service.EquipmentLineService;
import reestr.service.TvService;

import javax.validation.constraints.Positive;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/tvs")
@Validated
@RequiredArgsConstructor
public class TVController {
    private final TvService service;
    private final EquipmentLineService lineService;


    @PostMapping("/lines")
    public EquipmentLine createTvLine(@RequestBody EquipmentLine tvLine) {
        log.info("Получен запрос /POST /tvs/lines. Линейка телевизоров {} добавлена.", tvLine.getName());
        return lineService.create(tvLine);
    }

    @PostMapping("/lines/{lineId}/models")
    public TV create(@PathVariable Integer lineId, @RequestBody TV tv) {
        log.info("Получен запрос POST /tvs. Телевизор {} добавлен.", tv.getName());
        return service.create(lineId, tv);
    }


    // @GetMapping("/tvs?price=asc")
    @GetMapping("/models/sorted")
    public List<TV> getSortedByPriceAndAlphabet(@RequestParam(name = "price", required = false) String price,
                                                @RequestParam(name = "from", defaultValue = "0") Integer from,
                                                @Positive @RequestParam(name = "size", defaultValue = "10") Integer size) {
        return service.getSortedByPriceAndAlphabet(price, PageRequest.of(from / size, size));
    }

    @GetMapping("/all")
    public List<TV> getAllTvs() {
        return service.getAllTvs();
    }

    // 2.	По выделенным атрибутам необходимо реализовать поиск по наименованию,
    //вне зависимости от регистра, а также реализовать фильтрацию по виду техники, цвету, цене (от/до). Остальные фильтры сделать зависимыми от выбора вида техники и фильтровать по атрибутам моделей.
    @GetMapping("/models/filtered")
    public List<TV> findByNameAndFilterByParams(@RequestParam(name = "name") String name,
                                                @RequestParam(name = "colour", required = false) String colour,
                                                @Positive @RequestParam(name = "priceFrom", defaultValue = "0", required = false) String from,
                                                @Positive @RequestParam(name = "priceTo", required = false) String to,
                                                @Positive @RequestParam(name = "memory", required = false) String category,
                                                @Positive @RequestParam(name = "camera", required = false) String technology,
                                                @RequestParam(name = "from", defaultValue = "0") Integer fromPage,
                                                @Positive @RequestParam(name = "size", defaultValue = "10") Integer sizePage) {
        return service.findByNameAndFilterByParams(name, colour, from, to, category, technology, PageRequest.of(fromPage / sizePage, sizePage));
    }
}
