package reestr.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "vacuum_cleaners")
public class VacuumCleaner extends ModelDetails {
    @Column(name = "collector_volume")
    private Double collectorVolume; // объём пылесборника
    @Column(name = "regime_count")
    private Integer regimeCount; // количество режимов
}
// Пылесос: наименование, серийный номер, цвет, размер, цена, объём пылесборника, количество режимов, наличие товара (Да/Нет);