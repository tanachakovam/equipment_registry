package reestr.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@ToString
@Getter
@Setter
@Table(name = "equipment_types")
public class EquipmentType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @OneToMany(mappedBy = "type")
    private Set<EquipmentLine> equipmentLines; // линейки которые представлены в этом виде техники
}
