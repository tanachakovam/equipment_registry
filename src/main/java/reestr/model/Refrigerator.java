package reestr.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "refrigerators")
public class Refrigerator extends ModelDetails {
    @Column(name = "doors")
    private Integer doorCount;     //количество дверей
    @Column(name = "compressor")
    private String compressor;     //тип компрессора
}
// Холодильники: наименование, серийный номер, цвет, размер, цена, количество дверей, тип компрессора, наличие товара (Да/Нет);

