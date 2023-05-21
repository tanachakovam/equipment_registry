package reestr.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import reestr.model.Smartphone;

import java.util.List;

@Repository
public interface SmartphoneRepository extends JpaRepository<Smartphone, Integer> {
    Page<Smartphone> findAll(Specification<Smartphone> specification, Pageable pageable);

    List<Smartphone> findByOrderByNameAscPriceAsc(Pageable pageable);

    List<Smartphone> findByOrderByNameAscPriceDesc(Pageable pageable);

}
