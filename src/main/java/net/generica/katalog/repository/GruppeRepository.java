package net.generica.katalog.repository;

import net.generica.katalog.domain.Gruppe;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Gruppe entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GruppeRepository extends JpaRepository<Gruppe, Long> {

}
