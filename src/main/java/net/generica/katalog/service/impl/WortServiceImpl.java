package net.generica.katalog.service.impl;

import net.generica.katalog.service.WortService;
import net.generica.katalog.domain.Wort;
import net.generica.katalog.repository.WortRepository;
import net.generica.katalog.service.dto.WortDTO;
import net.generica.katalog.service.mapper.WortMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Wort.
 */
@Service
@Transactional
public class WortServiceImpl implements WortService {

    private final Logger log = LoggerFactory.getLogger(WortServiceImpl.class);

    private final WortRepository wortRepository;

    private final WortMapper wortMapper;

    public WortServiceImpl(WortRepository wortRepository, WortMapper wortMapper) {
        this.wortRepository = wortRepository;
        this.wortMapper = wortMapper;
    }

    /**
     * Save a wort.
     *
     * @param wortDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public WortDTO save(WortDTO wortDTO) {
        log.debug("Request to save Wort : {}", wortDTO);
        Wort wort = wortMapper.toEntity(wortDTO);
        wort = wortRepository.save(wort);
        return wortMapper.toDto(wort);
    }

    /**
     * Get all the worts.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<WortDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Worts");
        return wortRepository.findAll(pageable)
            .map(wortMapper::toDto);
    }

    /**
     * Get all the Wort with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<WortDTO> findAllWithEagerRelationships(Pageable pageable) {
        return wortRepository.findAllWithEagerRelationships(pageable).map(wortMapper::toDto);
    }
    

    /**
     * Get one wort by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<WortDTO> findOne(Long id) {
        log.debug("Request to get Wort : {}", id);
        return wortRepository.findOneWithEagerRelationships(id)
            .map(wortMapper::toDto);
    }

    /**
     * Delete the wort by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Wort : {}", id);        wortRepository.deleteById(id);
    }
}
