package net.generica.katalog.service;

import net.generica.katalog.service.dto.BezeichnungDTO;

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
     * @param bezeichnungDTO the entity to save
     * @return the persisted entity
     */
    BezeichnungDTO save(BezeichnungDTO bezeichnungDTO);

    /**
     * Get all the bezeichnungs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<BezeichnungDTO> findAll(Pageable pageable);

    /**
     * Get all the Bezeichnung with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<BezeichnungDTO> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" bezeichnung.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<BezeichnungDTO> findOne(Long id);

    /**
     * Delete the "id" bezeichnung.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
