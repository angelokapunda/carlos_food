package com.algaworks.carlosfood_api.infrastructor.repository.spec;

import com.algaworks.carlosfood_api.domain.model.Restaurante;
import jakarta.persistence.criteria.CriteriaQuery;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class RestauranteSpecs {

    public static Specification<Restaurante> comFreteGrates() {
        return (root, query, builder) -> builder.equal(root.get("taxaFrete"), BigDecimal.ZERO);
    }

    public static Specification<Restaurante> comNomeSemelhante(String nome) {
        return (root, query, builder) -> builder.like(root.get("nome"), "%"+ nome +"%");
    }
}
