package net.generica.katalog.service;

import net.generica.katalog.service.dto.SpracheDTO;

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
     * @param spracheDTO the entity to save
     * @return the persisted entity
     */
    SpracheDTO save(SpracheDTO spracheDTO);

    /**
     * Get all the spraches.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<SpracheDTO> findAll(Pageable pageable);


    /**
     * Get the "id" sprache.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<SpracheDTO> findOne(Long id);

    /**
     * Delete the "id" sprache.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
