package reestr.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;


@Entity
@ToString
@Getter
@Setter
@Table(name = "equipment_models")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ModelDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // серийный номер
    private String name; // наименование
    private String colour; //цвет
    private Double size; // размер
    private Double price; // цена
    @Column(name = "is_available")
    private Boolean isAvailable; // наличие товара (Да/Нет)
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "line_id")
    private EquipmentLine line;
}
