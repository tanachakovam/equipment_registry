package reestr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reestr.model.EquipmentLine;


public interface LineRepository extends JpaRepository<EquipmentLine, Integer> {


}
