package net.generica.katalog.service;

import net.generica.katalog.service.dto.WortDTO;

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
     * @param wortDTO the entity to save
     * @return the persisted entity
     */
    WortDTO save(WortDTO wortDTO);

    /**
     * Get all the worts.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<WortDTO> findAll(Pageable pageable);

    /**
     * Get all the Wort with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<WortDTO> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" wort.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<WortDTO> findOne(Long id);

    /**
     * Delete the "id" wort.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
