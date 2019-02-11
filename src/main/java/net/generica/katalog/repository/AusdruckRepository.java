package net.generica.katalog.repository;

import net.generica.katalog.domain.Ausdruck;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data MongoDB repository for the Ausdruck entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AusdruckRepository extends MongoRepository<Ausdruck, String> {
    @Query("{}")
    Page<Ausdruck> findAllWithEagerRelationships(Pageable pageable);

    @Query("{}")
    List<Ausdruck> findAllWithEagerRelationships();

    @Query("{'id': ?0}")
    Optional<Ausdruck> findOneWithEagerRelationships(String id);

}
