package net.generica.katalog.service.impl;

import net.generica.katalog.service.SpracheService;
import net.generica.katalog.domain.Sprache;
import net.generica.katalog.repository.SpracheRepository;
import net.generica.katalog.service.dto.SpracheDTO;
import net.generica.katalog.service.mapper.SpracheMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Sprache.
 */
@Service
@Transactional
public class SpracheServiceImpl implements SpracheService {

    private final Logger log = LoggerFactory.getLogger(SpracheServiceImpl.class);

    private final SpracheRepository spracheRepository;

    private final SpracheMapper spracheMapper;

    public SpracheServiceImpl(SpracheRepository spracheRepository, SpracheMapper spracheMapper) {
        this.spracheRepository = spracheRepository;
        this.spracheMapper = spracheMapper;
    }

    /**
     * Save a sprache.
     *
     * @param spracheDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public SpracheDTO save(SpracheDTO spracheDTO) {
        log.debug("Request to save Sprache : {}", spracheDTO);
        Sprache sprache = spracheMapper.toEntity(spracheDTO);
        sprache = spracheRepository.save(sprache);
        return spracheMapper.toDto(sprache);
    }

    /**
     * Get all the spraches.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SpracheDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Spraches");
        return spracheRepository.findAll(pageable)
            .map(spracheMapper::toDto);
    }


    /**
     * Get one sprache by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SpracheDTO> findOne(Long id) {
        log.debug("Request to get Sprache : {}", id);
        return spracheRepository.findById(id)
            .map(spracheMapper::toDto);
    }

    /**
     * Delete the sprache by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Sprache : {}", id);        spracheRepository.deleteById(id);
    }
}
