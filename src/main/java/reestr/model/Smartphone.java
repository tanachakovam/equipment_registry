package reestr.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Setter
@ToString
@Entity
@DiscriminatorValue("smartphone")
public class Smartphone extends ModelDetails {
    private Integer memory; // память
    @Column(name = "camera_count")
    private Integer cameraCount; // количество камер


    public Smartphone(String name, String colour, Double size, Double price, Boolean isAvailable, Integer memory, Integer cameraCount) {
        super(name, colour, size, price, isAvailable);
        this.memory = memory;
        this.cameraCount = cameraCount;
    }

    public Smartphone() {

    }
}
//	Смартфоны: наименование, серийный номер, цвет, размер, цена, память, количество камер, наличие товара (Да/Нет);