package reestr.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import reestr.model.VacuumCleaner;

import java.util.List;

@Repository
public interface VacuumCleanerRepository extends JpaRepository<VacuumCleaner, Integer> {
    Page<VacuumCleaner> findAll(Specification<VacuumCleaner> specification, Pageable pageable);

    List<VacuumCleaner> findByOrderByNameAscPriceAsc(Pageable pageable);

    List<VacuumCleaner> findByOrderByNameAscPriceDesc(Pageable pageable);

}
