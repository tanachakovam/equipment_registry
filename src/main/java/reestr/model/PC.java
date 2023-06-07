package reestr.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Setter
@ToString
@Entity
@DiscriminatorValue("pc")
@NoArgsConstructor
public class PC extends ModelDetails {
    @Column(name = "pc_category")
    private String category; //категория
    private String processor; // процессор

    public PC(String name, String colour, Double size, Double price, Boolean isAvailable, String category, String processor) {
        super(name, colour, size, price, isAvailable);
        this.category = category;
        this.processor = processor;
    }

}
//	Компьютеры: наименование, серийный номер, цвет, размер, цена, категория, тип процессора, наличие товара (Да/Нет);