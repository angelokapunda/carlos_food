package com.algaworks.carlosfood_api.infrastructor.repository.spec;

import com.algaworks.carlosfood_api.domain.model.Restaurante;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class RestauranteComFreteGratesSpec implements Specification<Restaurante> {
    @Override
    public Predicate toPredicate(Root<Restaurante> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        return builder.equal(root.get("taxaFrete"), BigDecimal.ZERO);
    }
}
