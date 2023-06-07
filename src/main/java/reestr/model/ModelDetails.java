package reestr.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DiscriminatorFormula;
import reestr.PayloadDeserializer;

import javax.persistence.*;


@Entity
@ToString
@Getter
@Setter
@Table(name = "equipment_models")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorFormula("CASE WHEN MEMORY IS NOT NULL THEN 'smartphone' " +
        "WHEN processor IS NOT NULL THEN 'pc' " +
        "WHEN compressor IS NOT NULL THEN 'refrigerator' " +
        "WHEN technology IS NOT NULL THEN 'tv' " +
        "WHEN regime_count IS NOT NULL THEN 'vacuum_cleaner' END")
//@Inheritance(strategy = InheritanceType.JOINED)
@JsonDeserialize(using = PayloadDeserializer.class)
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "line_id")
    private EquipmentLine line; // к какой линейке эта модель относится
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id")
    private EquipmentType type;


    public ModelDetails(String name, String colour, Double size, Double price, Boolean isAvailable) {
        this.name = name;
        this.colour = colour;
        this.size = size;
        this.price = price;
        this.isAvailable = isAvailable;
    }

    public ModelDetails() {

    }
}
