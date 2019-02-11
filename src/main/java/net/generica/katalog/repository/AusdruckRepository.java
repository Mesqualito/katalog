package net.generica.katalog.repository;

import net.generica.katalog.domain.Ausdruck;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Ausdruck entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AusdruckRepository extends JpaRepository<Ausdruck, Long> {

    @Query(value = "select distinct ausdruck from Ausdruck ausdruck left join fetch ausdruck.einzelworts",
        countQuery = "select count(distinct ausdruck) from Ausdruck ausdruck")
    Page<Ausdruck> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct ausdruck from Ausdruck ausdruck left join fetch ausdruck.einzelworts")
    List<Ausdruck> findAllWithEagerRelationships();

    @Query("select ausdruck from Ausdruck ausdruck left join fetch ausdruck.einzelworts where ausdruck.id =:id")
    Optional<Ausdruck> findOneWithEagerRelationships(@Param("id") Long id);

}
