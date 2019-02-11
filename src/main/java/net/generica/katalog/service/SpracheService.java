package net.generica.katalog.service;

import net.generica.katalog.domain.Sprache;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Sprache.
 */
public interface SpracheService {

    /**
     * Save a sprache.
     *
     * @param sprache the entity to save
     * @return the persisted entity
     */
    Sprache save(Sprache sprache);

    /**
     * Get all the spraches.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Sprache> findAll(Pageable pageable);


    /**
     * Get the "id" sprache.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Sprache> findOne(String id);

    /**
     * Delete the "id" sprache.
     *
     * @param id the id of the entity
     */
    void delete(String id);
}
