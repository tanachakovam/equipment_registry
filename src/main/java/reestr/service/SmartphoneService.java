package reestr.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import reestr.model.Smartphone;

import java.util.List;

public interface SmartphoneService {
    Smartphone create(Smartphone smartphone, Integer lineId);

    List<Smartphone> getSortedByPriceAndAlphabet(String sort, PageRequest pageable);

    List<Smartphone> getAllSmartphones();

    List<Smartphone> findByNameAndFilterByParams(String name, String colour, String from, String to, Integer memory, Integer camera, Pageable pageable);
}
