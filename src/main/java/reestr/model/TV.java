package reestr.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@NoArgsConstructor
@DiscriminatorValue("tv")
public class TV extends ModelDetails {
    private String category; //категория
    private String technology; // технология

    public TV(String name, String colour, Double size, Double price, Boolean isAvailable, String category, String technology) {
        super(name, colour, size, price, isAvailable);
        this.category = category;
        this.technology = technology;
    }
}
// телевизоры: наименование, серийный номер, цвет, размер, цена, категория, технология, наличие товара (Да/Нет);

