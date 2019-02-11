package net.generica.katalog.repository;

import net.generica.katalog.domain.Bezeichnung;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data MongoDB repository for the Bezeichnung entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BezeichnungRepository extends MongoRepository<Bezeichnung, String> {
    @Query("{}")
    Page<Bezeichnung> findAllWithEagerRelationships(Pageable pageable);

    @Query("{}")
    List<Bezeichnung> findAllWithEagerRelationships();

    @Query("{'id': ?0}")
    Optional<Bezeichnung> findOneWithEagerRelationships(String id);

}
