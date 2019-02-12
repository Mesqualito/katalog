package net.generica.katalog.service;

import net.generica.katalog.domain.Ausdruck;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Ausdruck.
 */
public interface AusdruckService {

    /**
     * Save a ausdruck.
     *
     * @param ausdruck the entity to save
     * @return the persisted entity
     */
    Ausdruck save(Ausdruck ausdruck);

    /**
     * Get all the ausdrucks.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Ausdruck> findAll(Pageable pageable);

    /**
     * Get all the Ausdruck with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<Ausdruck> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" ausdruck.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Ausdruck> findOne(Long id);

    /**
     * Delete the "id" ausdruck.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
