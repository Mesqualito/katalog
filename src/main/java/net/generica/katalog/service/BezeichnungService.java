package net.generica.katalog.service;

import net.generica.katalog.domain.Bezeichnung;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Bezeichnung.
 */
public interface BezeichnungService {

    /**
     * Save a bezeichnung.
     *
     * @param bezeichnung the entity to save
     * @return the persisted entity
     */
    Bezeichnung save(Bezeichnung bezeichnung);

    /**
     * Get all the bezeichnungs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Bezeichnung> findAll(Pageable pageable);

    /**
     * Get all the Bezeichnung with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<Bezeichnung> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" bezeichnung.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Bezeichnung> findOne(Long id);

    /**
     * Delete the "id" bezeichnung.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
