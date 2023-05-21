package reestr;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reestr.model.EquipmentLine;
import reestr.model.Smartphone;
import reestr.service.EquipmentLineService;
import reestr.service.SmartphoneService;

@SpringBootApplication(scanBasePackages = {"reestr"})
@RequiredArgsConstructor
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public CommandLineRunner set(SmartphoneService smartphoneService, EquipmentLineService typeService) {
        return (args) -> {
            // Для вида техники - телефоны, создаем линейку - Apple
            EquipmentLine phoneApple = new EquipmentLine();
            phoneApple.setName("Smartphone");
            phoneApple.setBrand("Apple");
            phoneApple.setCountry("USA");
            phoneApple.setIsInInstalment(Boolean.TRUE);
            phoneApple.setIsAvailableOnline(Boolean.TRUE);

            // Для вида техники - телефоны, создаем линейку - Xiaomi
            EquipmentLine phoneXiaomi = new EquipmentLine();
            phoneXiaomi.setName("Smartphone");
            phoneXiaomi.setBrand("Xiaomi");
            phoneXiaomi.setCountry("China");
            phoneXiaomi.setIsInInstalment(Boolean.TRUE);
            phoneXiaomi.setIsAvailableOnline(Boolean.TRUE);

            // Для линейки Apple добавляем конкретные модели Iphone:
            Smartphone iphone = new Smartphone();
            iphone.setCameraCount(2);
            iphone.setMemory(256);
            iphone.setPrice(100000.0);
            iphone.setColour("black");
            iphone.setName("Iphone X");
            iphone.setIsAvailable(Boolean.TRUE);
            iphone.setSize(16.1);
            iphone.setLine(phoneApple);

            Smartphone iphone2 = new Smartphone();
            iphone2.setCameraCount(1);
            iphone2.setMemory(16);
            iphone2.setPrice(80000.0);
            iphone2.setColour("red");
            iphone2.setName("Iphone X");
            iphone2.setIsAvailable(Boolean.FALSE);
            iphone2.setSize(10.0);
            iphone2.setLine(phoneApple);

            // Для линейки Xiaomi добавляем конкретные модели Xiaomi:
            Smartphone xiaomi = new Smartphone();
            xiaomi.setCameraCount(2);
            xiaomi.setMemory(256);
            xiaomi.setPrice(10000.0);
            xiaomi.setColour("white");
            xiaomi.setName("Redmi 3");
            xiaomi.setIsAvailable(Boolean.TRUE);
            xiaomi.setSize(16.1);
            xiaomi.setLine(phoneXiaomi);

            Smartphone xiaomi2 = new Smartphone();
            xiaomi2.setCameraCount(3);
            xiaomi2.setMemory(32);
            xiaomi2.setPrice(40000.0);
            xiaomi2.setColour("red");
            xiaomi2.setName("Note PRO");
            xiaomi2.setIsAvailable(Boolean.FALSE);
            xiaomi2.setSize(10.0);
            xiaomi2.setLine(phoneXiaomi);


            typeService.create(phoneApple);
            typeService.create(phoneXiaomi);

            smartphoneService.create(xiaomi, phoneXiaomi.getId());
            smartphoneService.create(xiaomi2, phoneXiaomi.getId());
            smartphoneService.create(iphone, phoneApple.getId());
            smartphoneService.create(iphone2, phoneApple.getId());

        };
    }
}

