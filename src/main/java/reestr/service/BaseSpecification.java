package reestr.service;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import reestr.model.*;

import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class BaseSpecification<T> {
    public Specification<T> findByNameAndFilterByParams(String name, String colour, String from,
                                                        String to, String pcCategory, String pcProcessor,
                                                        Integer phoneMemory, Integer phoneCamera, String tvCategory,
                                                        String tvTechnology, String vacuumVolume, Integer vacuumRegime,
                                                        Integer refrigeratorDoor, String refrigeratorCompressor) {

        //поиск по наименованию,
        //вне зависимости от регистра, а также реализовать фильтрацию по виду техники, цвету, цене (от/до).
        // Остальные фильтры сделать зависимыми от выбора вида техники и фильтровать по атрибутам моделей.
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (name != null) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("name")), "%"
                        + name.toUpperCase() + "%"));
            }

            if (colour != null) {
                predicates.add(criteriaBuilder.equal(root.get("colour"), colour));
            }

            if (from != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"), Double.valueOf(from)));
            }
            if (to != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"), Double.valueOf(to)));
            }

            Root<ModelDetails> details = criteriaQuery.from(ModelDetails.class);
            Root<PC> rootPC = criteriaBuilder.treat(details, PC.class);
            Root<Smartphone> rootSmartphone = criteriaBuilder.treat(details, Smartphone.class);
            Root<TV> rootTv = criteriaBuilder.treat(details, TV.class);
            Root<VacuumCleaner> rootVacuum = criteriaBuilder.treat(details, VacuumCleaner.class);
            Root<Refrigerator> rootRefrigerator = criteriaBuilder.treat(details, Refrigerator.class);
            if (pcCategory != null) {
                predicates.add(criteriaBuilder.equal(rootPC.get("category"), pcCategory));
            }
            if (pcProcessor != null) {
                predicates.add(criteriaBuilder.equal(rootPC.get("processor"), pcProcessor));
            }
            if (phoneMemory != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(rootSmartphone.get("memory"), phoneMemory));
            }
            if (phoneCamera != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(rootSmartphone.get("camera"), phoneCamera));
            }
            if (tvCategory != null) {
                predicates.add(criteriaBuilder.equal(rootTv.get("category"), tvCategory));
            }
            if (tvTechnology != null) {
                predicates.add(criteriaBuilder.equal(rootTv.get("technology"), tvTechnology));
            }
            if (vacuumVolume != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(rootVacuum.get("collectorVolume"), Double.valueOf(vacuumVolume)));
            }
            if (vacuumRegime != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(rootVacuum.get("regimeCount"), vacuumRegime));
            }
            if (refrigeratorDoor != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(rootRefrigerator.get("doorCount"), refrigeratorDoor));
            }
            if (refrigeratorCompressor != null) {
                predicates.add(criteriaBuilder.equal(rootRefrigerator.get("compressor"), refrigeratorCompressor));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

}



