package reestr.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import reestr.model.VacuumCleaner;

import java.util.List;

public interface VacuumCleanerService {
    VacuumCleaner create(Integer lineId, VacuumCleaner vacuumCleaner);

    List<VacuumCleaner> getAllVacuumCleaners();

    List<VacuumCleaner> getSortedByPriceAndAlphabet(String sort, PageRequest pageable);

    List<VacuumCleaner> findByNameAndFilterByParams(String name, String colour, String from, String to, Integer regimeCount, String collectorVolume, Pageable pageable);
}
