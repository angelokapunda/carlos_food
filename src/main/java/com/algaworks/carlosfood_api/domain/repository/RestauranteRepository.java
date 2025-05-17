package com.algaworks.carlosfood_api.domain.repository;

import com.algaworks.carlosfood_api.domain.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long>, RestauranteRepositoryQueris,
        JpaSpecificationExecutor<Restaurante> {

    @Query("from Restaurante r join fetch r.cozinha join fetch r.formasPagamento")
    List<Restaurante> findAll();

    List<Restaurante> findByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal TaxaFinal);

//    List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long cozinhaId);

    @Query("from Restaurante where nome like %:nome% and cozinha.id = :id")
    List<Restaurante> consultaPorNome(String nome, @Param("id") Long cozinhaId);

    Optional<Restaurante> findFirstByNomeContaining (String nome);

    List<Restaurante> findTop2ByNomeContaining(String nome);


}
