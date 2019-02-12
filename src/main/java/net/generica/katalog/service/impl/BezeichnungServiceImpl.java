package net.generica.katalog.service.impl;

import net.generica.katalog.service.BezeichnungService;
import net.generica.katalog.domain.Bezeichnung;
import net.generica.katalog.repository.BezeichnungRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Bezeichnung.
 */
@Service
@Transactional
public class BezeichnungServiceImpl implements BezeichnungService {

    private final Logger log = LoggerFactory.getLogger(BezeichnungServiceImpl.class);

    private final BezeichnungRepository bezeichnungRepository;

    public BezeichnungServiceImpl(BezeichnungRepository bezeichnungRepository) {
        this.bezeichnungRepository = bezeichnungRepository;
    }

    /**
     * Save a bezeichnung.
     *
     * @param bezeichnung the entity to save
     * @return the persisted entity
     */
    @Override
    public Bezeichnung save(Bezeichnung bezeichnung) {
        log.debug("Request to save Bezeichnung : {}", bezeichnung);
        return bezeichnungRepository.save(bezeichnung);
    }

    /**
     * Get all the bezeichnungs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Bezeichnung> findAll(Pageable pageable) {
        log.debug("Request to get all Bezeichnungs");
        return bezeichnungRepository.findAll(pageable);
    }

    /**
     * Get all the Bezeichnung with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<Bezeichnung> findAllWithEagerRelationships(Pageable pageable) {
        return bezeichnungRepository.findAllWithEagerRelationships(pageable);
    }
    

    /**
     * Get one bezeichnung by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Bezeichnung> findOne(Long id) {
        log.debug("Request to get Bezeichnung : {}", id);
        return bezeichnungRepository.findOneWithEagerRelationships(id);
    }

    /**
     * Delete the bezeichnung by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Bezeichnung : {}", id);        bezeichnungRepository.deleteById(id);
    }
}
