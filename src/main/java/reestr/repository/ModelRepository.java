package reestr.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import reestr.model.EquipmentType;
import reestr.model.ModelDetails;

import java.util.List;

@Repository
public interface ModelRepository extends JpaRepository<ModelDetails, Integer> {

    List<ModelDetails> findAll(Specification<ModelDetails> specification);


    List<ModelDetails> findByLine_TypeOrderByNameAscPriceAsc(EquipmentType type);


    List<ModelDetails> findByLine_TypeOrderByNameAscPriceDesc(EquipmentType type);

}
