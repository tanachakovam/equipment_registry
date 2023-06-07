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
@DiscriminatorValue("vacuum_cleaner")
@NoArgsConstructor
public class VacuumCleaner extends ModelDetails {
    @Column(name = "collector_volume")
    private Double collectorVolume; // объём пылесборника
    @Column(name = "regime_count")
    private Integer regimeCount; // количество режимов

    public VacuumCleaner(String name, String colour, Double size, Double price, Boolean isAvailable, Double collectorVolume, Integer regimeCount) {
        super(name, colour, size, price, isAvailable);
        this.collectorVolume = collectorVolume;
        this.regimeCount = regimeCount;
    }
}
// Пылесос: наименование, серийный номер, цвет, размер, цена, объём пылесборника, количество режимов, наличие товара (Да/Нет);