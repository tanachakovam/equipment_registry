package reestr.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "equipment_lines")
public class EquipmentLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name; // наименование
    private String country; // Страна производитель
    private String brand; // Фирма производитель
    @Column(name = "is_available_online")
    private Boolean isAvailableOnline; // возможность заказа онлайн(да/нет),
    @Column(name = "is_in_instalment")
    private Boolean isInInstalment; // возможность оформления рассрочки(да/нет)
    @OneToMany(mappedBy = "line")
    private Set<ModelDetails> models; // модели в наличии (выборка из моделей, которые представлены в виде справочника)

    // Наименование, Страна производитель, Фирма производитель, возможность заказа онлайн(да/нет),
    // возможность оформления рассрочки(да/нет), модели в наличии (выборка из моделей, которые представлены в виде справочника).
}
