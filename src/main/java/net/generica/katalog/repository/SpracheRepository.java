package net.generica.katalog.repository;

import net.generica.katalog.domain.Sprache;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Sprache entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SpracheRepository extends JpaRepository<Sprache, Long> {

}
