package reestr.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import reestr.model.PC;

import java.util.List;

@Repository
public interface PcRepository extends JpaRepository<PC, Integer> {
    Page<PC> findAll(Specification<PC> specification, Pageable pageable);

    List<PC> findByOrderByNameAscPriceAsc(Pageable pageable);

    List<PC> findByOrderByNameAscPriceDesc(Pageable pageable);

}
