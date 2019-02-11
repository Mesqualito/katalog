package net.generica.katalog.service.impl;

import net.generica.katalog.service.AusdruckService;
import net.generica.katalog.domain.Ausdruck;
import net.generica.katalog.repository.AusdruckRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing Ausdruck.
 */
@Service
public class AusdruckServiceImpl implements AusdruckService {

    private final Logger log = LoggerFactory.getLogger(AusdruckServiceImpl.class);

    private final AusdruckRepository ausdruckRepository;

    public AusdruckServiceImpl(AusdruckRepository ausdruckRepository) {
        this.ausdruckRepository = ausdruckRepository;
    }

    /**
     * Save a ausdruck.
     *
     * @param ausdruck the entity to save
     * @return the persisted entity
     */
    @Override
    public Ausdruck save(Ausdruck ausdruck) {
        log.debug("Request to save Ausdruck : {}", ausdruck);
        return ausdruckRepository.save(ausdruck);
    }

    /**
     * Get all the ausdrucks.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    public Page<Ausdruck> findAll(Pageable pageable) {
        log.debug("Request to get all Ausdrucks");
        return ausdruckRepository.findAll(pageable);
    }

    /**
     * Get all the Ausdruck with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<Ausdruck> findAllWithEagerRelationships(Pageable pageable) {
        return ausdruckRepository.findAllWithEagerRelationships(pageable);
    }
    

    /**
     * Get one ausdruck by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public Optional<Ausdruck> findOne(String id) {
        log.debug("Request to get Ausdruck : {}", id);
        return ausdruckRepository.findOneWithEagerRelationships(id);
    }

    /**
     * Delete the ausdruck by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Ausdruck : {}", id);        ausdruckRepository.deleteById(id);
    }
}
