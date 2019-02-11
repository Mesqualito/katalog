package net.generica.katalog.repository;

import net.generica.katalog.domain.Gruppe;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the Gruppe entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GruppeRepository extends MongoRepository<Gruppe, String> {

}
