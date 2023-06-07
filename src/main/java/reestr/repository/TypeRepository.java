package reestr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reestr.model.EquipmentType;


public interface TypeRepository extends JpaRepository<EquipmentType, Integer> {


}
