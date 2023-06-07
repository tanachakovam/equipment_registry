package reestr.service;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import reestr.model.EquipmentType;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class BaseSpecification<ModelDetails> {

    public Specification<ModelDetails> findByNameAndFilterByParams(String name, String colour, String from,
                                                                   String to, EquipmentType type) {

        //поиск по наименованию,
        //вне зависимости от регистра, а также реализовать фильтрацию по виду техники, цвету, цене (от/до).
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
            predicates.add(criteriaBuilder.equal(root.get("type"), type.getId()));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}



