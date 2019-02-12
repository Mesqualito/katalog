package net.generica.katalog.repository;

import net.generica.katalog.domain.Wort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Wort entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WortRepository extends JpaRepository<Wort, Long> {

    @Query(value = "select distinct wort from Wort wort left join fetch wort.einzelworts",
        countQuery = "select count(distinct wort) from Wort wort")
    Page<Wort> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct wort from Wort wort left join fetch wort.einzelworts")
    List<Wort> findAllWithEagerRelationships();

    @Query("select wort from Wort wort left join fetch wort.einzelworts where wort.id =:id")
    Optional<Wort> findOneWithEagerRelationships(@Param("id") Long id);

}
