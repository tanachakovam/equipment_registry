package reestr.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "tvs")
public class TV extends ModelDetails {
    private String category; //категория
    private String technology; // технология
}
// телевизоры: наименование, серийный номер, цвет, размер, цена, категория, технология, наличие товара (Да/Нет);

