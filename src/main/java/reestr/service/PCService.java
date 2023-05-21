package reestr.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import reestr.model.PC;

import java.util.List;

public interface PCService {
    PC create(PC pc, Integer typeId);

    List<PC> getComputersSortedByPriceAndAlphabet(String sort, PageRequest pageable);

    List<PC> getAllComputers();

    List<PC> findByNameAndFilterByParams(String name, String colour, String from, String to, String category, String processor, Pageable pageable);
}
