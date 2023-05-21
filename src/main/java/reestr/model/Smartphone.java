package reestr.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@Entity
@Table(name = "smartphones")
@RequiredArgsConstructor
public class Smartphone extends ModelDetails {
    private Integer memory; // память
    @Column(name = "camera_count")
    private Integer cameraCount; // количество камер
}
//	Смартфоны: наименование, серийный номер, цвет, размер, цена, память, количество камер, наличие товара (Да/Нет);