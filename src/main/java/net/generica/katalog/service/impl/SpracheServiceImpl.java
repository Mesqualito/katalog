package net.generica.katalog.service.impl;

import net.generica.katalog.service.SpracheService;
import net.generica.katalog.domain.Sprache;
import net.generica.katalog.repository.SpracheRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing Sprache.
 */
@Service
public class SpracheServiceImpl implements SpracheService {

    private final Logger log = LoggerFactory.getLogger(SpracheServiceImpl.class);

    private final SpracheRepository spracheRepository;

    public SpracheServiceImpl(SpracheRepository spracheRepository) {
        this.spracheRepository = spracheRepository;
    }

    /**
     * Save a sprache.
     *
     * @param sprache the entity to save
     * @return the persisted entity
     */
    @Override
    public Sprache save(Sprache sprache) {
        log.debug("Request to save Sprache : {}", sprache);
        return spracheRepository.save(sprache);
    }

    /**
     * Get all the spraches.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    public Page<Sprache> findAll(Pageable pageable) {
        log.debug("Request to get all Spraches");
        return spracheRepository.findAll(pageable);
    }


    /**
     * Get one sprache by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public Optional<Sprache> findOne(String id) {
        log.debug("Request to get Sprache : {}", id);
        return spracheRepository.findById(id);
    }

    /**
     * Delete the sprache by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Sprache : {}", id);        spracheRepository.deleteById(id);
    }
}
