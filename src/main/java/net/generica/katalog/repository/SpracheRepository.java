package net.generica.katalog.repository;

import net.generica.katalog.domain.Sprache;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the Sprache entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SpracheRepository extends MongoRepository<Sprache, String> {

}
