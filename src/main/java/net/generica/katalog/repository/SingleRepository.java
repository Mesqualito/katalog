package net.generica.katalog.repository;

import net.generica.katalog.domain.Single;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data MongoDB repository for the Single entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SingleRepository extends MongoRepository<Single, String> {
    @Query("{}")
    Page<Single> findAllWithEagerRelationships(Pageable pageable);

    @Query("{}")
    List<Single> findAllWithEagerRelationships();

    @Query("{'id': ?0}")
    Optional<Single> findOneWithEagerRelationships(String id);

}
