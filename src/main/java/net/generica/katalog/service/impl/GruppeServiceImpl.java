package net.generica.katalog.service.impl;

import net.generica.katalog.service.GruppeService;
import net.generica.katalog.domain.Gruppe;
import net.generica.katalog.repository.GruppeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Gruppe.
 */
@Service
@Transactional
public class GruppeServiceImpl implements GruppeService {

    private final Logger log = LoggerFactory.getLogger(GruppeServiceImpl.class);

    private final GruppeRepository gruppeRepository;

    public GruppeServiceImpl(GruppeRepository gruppeRepository) {
        this.gruppeRepository = gruppeRepository;
    }

    /**
     * Save a gruppe.
     *
     * @param gruppe the entity to save
     * @return the persisted entity
     */
    @Override
    public Gruppe save(Gruppe gruppe) {
        log.debug("Request to save Gruppe : {}", gruppe);
        return gruppeRepository.save(gruppe);
    }

    /**
     * Get all the gruppes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Gruppe> findAll(Pageable pageable) {
        log.debug("Request to get all Gruppes");
        return gruppeRepository.findAll(pageable);
    }


    /**
     * Get one gruppe by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Gruppe> findOne(Long id) {
        log.debug("Request to get Gruppe : {}", id);
        return gruppeRepository.findById(id);
    }

    /**
     * Delete the gruppe by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Gruppe : {}", id);        gruppeRepository.deleteById(id);
    }
}
