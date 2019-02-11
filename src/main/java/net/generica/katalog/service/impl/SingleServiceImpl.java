package net.generica.katalog.service.impl;

import net.generica.katalog.service.SingleService;
import net.generica.katalog.domain.Single;
import net.generica.katalog.repository.SingleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing Single.
 */
@Service
public class SingleServiceImpl implements SingleService {

    private final Logger log = LoggerFactory.getLogger(SingleServiceImpl.class);

    private final SingleRepository singleRepository;

    public SingleServiceImpl(SingleRepository singleRepository) {
        this.singleRepository = singleRepository;
    }

    /**
     * Save a single.
     *
     * @param single the entity to save
     * @return the persisted entity
     */
    @Override
    public Single save(Single single) {
        log.debug("Request to save Single : {}", single);
        return singleRepository.save(single);
    }

    /**
     * Get all the singles.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    public Page<Single> findAll(Pageable pageable) {
        log.debug("Request to get all Singles");
        return singleRepository.findAll(pageable);
    }

    /**
     * Get all the Single with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<Single> findAllWithEagerRelationships(Pageable pageable) {
        return singleRepository.findAllWithEagerRelationships(pageable);
    }
    

    /**
     * Get one single by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public Optional<Single> findOne(String id) {
        log.debug("Request to get Single : {}", id);
        return singleRepository.findOneWithEagerRelationships(id);
    }

    /**
     * Delete the single by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Single : {}", id);        singleRepository.deleteById(id);
    }
}
