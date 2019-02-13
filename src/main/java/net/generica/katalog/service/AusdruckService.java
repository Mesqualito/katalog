package net.generica.katalog.service;

import net.generica.katalog.service.dto.AusdruckDTO;

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
     * @param ausdruckDTO the entity to save
     * @return the persisted entity
     */
    AusdruckDTO save(AusdruckDTO ausdruckDTO);

    /**
     * Get all the ausdrucks.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<AusdruckDTO> findAll(Pageable pageable);

    /**
     * Get all the Ausdruck with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<AusdruckDTO> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" ausdruck.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<AusdruckDTO> findOne(Long id);

    /**
     * Delete the "id" ausdruck.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
