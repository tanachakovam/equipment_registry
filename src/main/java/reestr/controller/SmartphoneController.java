package reestr.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reestr.model.EquipmentLine;
import reestr.model.Smartphone;
import reestr.service.EquipmentLineService;
import reestr.service.SmartphoneService;

import javax.validation.constraints.Positive;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/smartphones")
@Validated
@RequiredArgsConstructor
public class SmartphoneController {
    private final SmartphoneService service;
    private final EquipmentLineService lineService;


    @PostMapping("/lines")
    public EquipmentLine createSmartphoneLine(@RequestBody EquipmentLine smartphoneLine) {
        log.info("Получен запрос /POST /smartphones/lines. Линейка телефонов {} добавлена.", smartphoneLine.getName());
        return lineService.create(smartphoneLine);
    }


    //4.	Реализовать возможность добавлять новые позиции и модели к ним, в зависимости от выбранного вида техники.
    @PostMapping("/lines/{lineId}/models")
    public Smartphone create(@PathVariable Integer lineId, @RequestBody Smartphone smartphone) {
        log.info("Получен запрос /POST /smartphone/lines/{lineId}/models. Телефон {} добавлен.", smartphone.getName());
        return service.create(smartphone, lineId);
    }

    //3.	Реализовать сортировку реестра техники по алфавиту и по стоимости;
    // @GetMapping("/smartphones/models/sorted?price=asc")
    @GetMapping("/models/sorted")
    public List<Smartphone> getSortedByPriceAndAlphabet(@RequestParam(name = "price", required = false) String price,
                                                        @RequestParam(name = "from", defaultValue = "0") Integer from,
                                                        @Positive @RequestParam(name = "sizePage", defaultValue = "10") Integer size) {
        return service.getSortedByPriceAndAlphabet(price, PageRequest.of(from / size, size));
    }

    @GetMapping("/all")
    public List<Smartphone> getAllSmartphones() {
        return service.getAllSmartphones();
    }

    // 2.	По выделенным атрибутам необходимо реализовать поиск по наименованию,
    //вне зависимости от регистра, а также реализовать фильтрацию по виду техники, цвету, цене (от/до). Остальные фильтры сделать зависимыми от выбора вида техники и фильтровать по атрибутам моделей.
    @GetMapping("/models/filtered")
    public List<Smartphone> findByNameAndFilterByParams(@RequestParam(name = "name") String name,
                                                        @RequestParam(name = "colour", required = false) String colour,
                                                        @Positive @RequestParam(name = "priceFrom", defaultValue = "0", required = false) String from,
                                                        @Positive @RequestParam(name = "priceTo", required = false) String to,
                                                        @Positive @RequestParam(name = "memory", required = false) Integer memory,
                                                        @Positive @RequestParam(name = "camera", required = false) Integer camera,
                                                        @RequestParam(name = "from", defaultValue = "0") Integer fromPage,
                                                        @Positive @RequestParam(name = "size", defaultValue = "10") Integer size) {
        return service.findByNameAndFilterByParams(name, colour, from, to, memory, camera, PageRequest.of(fromPage / size, size));
    }
}
