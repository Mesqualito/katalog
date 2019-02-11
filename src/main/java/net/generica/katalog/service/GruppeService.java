package net.generica.katalog.service;

import net.generica.katalog.domain.Gruppe;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Gruppe.
 */
public interface GruppeService {

    /**
     * Save a gruppe.
     *
     * @param gruppe the entity to save
     * @return the persisted entity
     */
    Gruppe save(Gruppe gruppe);

    /**
     * Get all the gruppes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Gruppe> findAll(Pageable pageable);


    /**
     * Get the "id" gruppe.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Gruppe> findOne(Long id);

    /**
     * Delete the "id" gruppe.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
