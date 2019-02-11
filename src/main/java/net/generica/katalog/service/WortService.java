package net.generica.katalog.service;

import net.generica.katalog.domain.Wort;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Wort.
 */
public interface WortService {

    /**
     * Save a wort.
     *
     * @param wort the entity to save
     * @return the persisted entity
     */
    Wort save(Wort wort);

    /**
     * Get all the worts.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Wort> findAll(Pageable pageable);

    /**
     * Get all the Wort with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<Wort> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" wort.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Wort> findOne(String id);

    /**
     * Delete the "id" wort.
     *
     * @param id the id of the entity
     */
    void delete(String id);
}
