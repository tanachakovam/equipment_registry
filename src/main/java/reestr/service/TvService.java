package reestr.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import reestr.model.TV;

import java.util.List;

public interface TvService {
    TV create(Integer lineId, TV tv);

    List<TV> getSortedByPriceAndAlphabet(String sort, PageRequest pageable);

    List<TV> getAllTvs();

    List<TV> findByNameAndFilterByParams(String name, String colour, String from, String to, String category, String technology, Pageable pageable);
}
