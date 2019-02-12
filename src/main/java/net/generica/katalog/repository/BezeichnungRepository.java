package net.generica.katalog.repository;

import net.generica.katalog.domain.Bezeichnung;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Bezeichnung entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BezeichnungRepository extends JpaRepository<Bezeichnung, Long> {

    @Query(value = "select distinct bezeichnung from Bezeichnung bezeichnung left join fetch bezeichnung.einzelworts",
        countQuery = "select count(distinct bezeichnung) from Bezeichnung bezeichnung")
    Page<Bezeichnung> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct bezeichnung from Bezeichnung bezeichnung left join fetch bezeichnung.einzelworts")
    List<Bezeichnung> findAllWithEagerRelationships();

    @Query("select bezeichnung from Bezeichnung bezeichnung left join fetch bezeichnung.einzelworts where bezeichnung.id =:id")
    Optional<Bezeichnung> findOneWithEagerRelationships(@Param("id") Long id);

}
