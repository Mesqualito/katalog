package net.generica.katalog.service.impl;

import net.generica.katalog.service.WortService;
import net.generica.katalog.domain.Wort;
import net.generica.katalog.repository.WortRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing Wort.
 */
@Service
public class WortServiceImpl implements WortService {

    private final Logger log = LoggerFactory.getLogger(WortServiceImpl.class);

    private final WortRepository wortRepository;

    public WortServiceImpl(WortRepository wortRepository) {
        this.wortRepository = wortRepository;
    }

    /**
     * Save a wort.
     *
     * @param wort the entity to save
     * @return the persisted entity
     */
    @Override
    public Wort save(Wort wort) {
        log.debug("Request to save Wort : {}", wort);
        return wortRepository.save(wort);
    }

    /**
     * Get all the worts.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    public Page<Wort> findAll(Pageable pageable) {
        log.debug("Request to get all Worts");
        return wortRepository.findAll(pageable);
    }

    /**
     * Get all the Wort with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<Wort> findAllWithEagerRelationships(Pageable pageable) {
        return wortRepository.findAllWithEagerRelationships(pageable);
    }
    

    /**
     * Get one wort by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public Optional<Wort> findOne(String id) {
        log.debug("Request to get Wort : {}", id);
        return wortRepository.findOneWithEagerRelationships(id);
    }

    /**
     * Delete the wort by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Wort : {}", id);        wortRepository.deleteById(id);
    }
}
