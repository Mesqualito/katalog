package net.generica.katalog.repository;

import net.generica.katalog.domain.Wort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data MongoDB repository for the Wort entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WortRepository extends MongoRepository<Wort, String> {
    @Query("{}")
    Page<Wort> findAllWithEagerRelationships(Pageable pageable);

    @Query("{}")
    List<Wort> findAllWithEagerRelationships();

    @Query("{'id': ?0}")
    Optional<Wort> findOneWithEagerRelationships(String id);

}
