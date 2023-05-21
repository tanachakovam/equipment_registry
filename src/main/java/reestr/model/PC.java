package reestr.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@Entity
@Table(name = "pcs")
public class PC extends ModelDetails {
    private String category; //категория
    private String processor; // процессор
}
//	Компьютеры: наименование, серийный номер, цвет, размер, цена, категория, тип процессора, наличие товара (Да/Нет);