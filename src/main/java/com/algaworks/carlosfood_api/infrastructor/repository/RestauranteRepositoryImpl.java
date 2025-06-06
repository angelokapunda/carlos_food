package com.algaworks.carlosfood_api.infrastructor.repository;

import com.algaworks.carlosfood_api.domain.model.Restaurante;
import com.algaworks.carlosfood_api.domain.repository.RestauranteRepositoryQueris;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQueris {

    @PersistenceContext
    private EntityManager manager;


    @Override
    public List<Restaurante> find(String nome, BigDecimal taxaFreteInicial,
                                  BigDecimal taxaFreteFinal) {

        CriteriaBuilder builder = manager.getCriteriaBuilder();

        CriteriaQuery<Restaurante> criteria = builder.createQuery(Restaurante.class);
        Root<Restaurante> root =  criteria.from(Restaurante.class);

        var predicates = new ArrayList<Predicate>();
        if (StringUtils.hasText(nome)) {
            predicates.add(builder.like(root.get("nome"), "%"+ nome +"%"));
        }
        if (taxaFreteInicial != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("taxaFrete"), taxaFreteInicial));
        }
        if (taxaFreteFinal != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("taxaFrete"), taxaFreteFinal));
        }

        criteria.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Restaurante> query = manager.createQuery(criteria);

        return query.getResultList();
    }
}
