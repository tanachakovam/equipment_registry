package reestr.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import reestr.model.Refrigerator;

import java.util.List;

@Repository
public interface RefrigeratorRepository extends JpaRepository<Refrigerator, Integer> {
    Page<Refrigerator> findAll(Specification<Refrigerator> specification, Pageable pageable);

    List<Refrigerator> findByOrderByNameAscPriceAsc(Pageable pageable);

    List<Refrigerator> findByOrderByNameAscPriceDesc(Pageable pageable);

}
