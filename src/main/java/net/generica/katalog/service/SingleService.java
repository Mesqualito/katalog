package net.generica.katalog.service;

import net.generica.katalog.domain.Single;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Single.
 */
public interface SingleService {

    /**
     * Save a single.
     *
     * @param single the entity to save
     * @return the persisted entity
     */
    Single save(Single single);

    /**
     * Get all the singles.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Single> findAll(Pageable pageable);

    /**
     * Get all the Single with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<Single> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" single.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Single> findOne(String id);

    /**
     * Delete the "id" single.
     *
     * @param id the id of the entity
     */
    void delete(String id);
}
