package reestr.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
@DiscriminatorValue("refrigerator")
@NoArgsConstructor
public class Refrigerator extends ModelDetails {
    @Column(name = "doors")
    private Integer doorCount;     //количество дверей
    @Column(name = "compressor")
    private String compressor;     //тип компрессора

    public Refrigerator(String name, String colour, Double size, Double price, Boolean isAvailable, Integer doorCount, String compressor) {
        super(name, colour, size, price, isAvailable);
        this.doorCount = doorCount;
        this.compressor = compressor;
    }
}
// Холодильники: наименование, серийный номер, цвет, размер, цена, количество дверей, тип компрессора, наличие товара (Да/Нет);

