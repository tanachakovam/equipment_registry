package reestr.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import reestr.model.Refrigerator;

import java.util.List;

public interface RefrigeratorService {
    Refrigerator create(Integer lineId, Refrigerator refrigerator);

    List<Refrigerator> getSortedByPriceAndAlphabet(String sort, PageRequest pageable);


    List<Refrigerator> findByNameAndFilterByParams(String name, String colour, String from, String to, String compressor, Integer doorCount, Pageable pageable);
}
