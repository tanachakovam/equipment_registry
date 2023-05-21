package reestr.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import reestr.model.TV;

import java.util.List;

@Repository
public interface TvRepository extends JpaRepository<TV, Integer> {
    Page<TV> findAll(Specification<TV> specification, Pageable pageable);

    List<TV> findByOrderByNameAscPriceAsc(Pageable pageable);

    List<TV> findByOrderByNameAscPriceDesc(Pageable pageable);

}
