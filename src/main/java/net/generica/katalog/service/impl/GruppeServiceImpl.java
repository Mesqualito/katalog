package net.generica.katalog.service.impl;

import net.generica.katalog.service.GruppeService;
import net.generica.katalog.domain.Gruppe;
import net.generica.katalog.repository.GruppeRepository;
import net.generica.katalog.service.dto.GruppeDTO;
import net.generica.katalog.service.mapper.GruppeMapper;
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

    private final GruppeMapper gruppeMapper;

    public GruppeServiceImpl(GruppeRepository gruppeRepository, GruppeMapper gruppeMapper) {
        this.gruppeRepository = gruppeRepository;
        this.gruppeMapper = gruppeMapper;
    }

    /**
     * Save a gruppe.
     *
     * @param gruppeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public GruppeDTO save(GruppeDTO gruppeDTO) {
        log.debug("Request to save Gruppe : {}", gruppeDTO);
        Gruppe gruppe = gruppeMapper.toEntity(gruppeDTO);
        gruppe = gruppeRepository.save(gruppe);
        return gruppeMapper.toDto(gruppe);
    }

    /**
     * Get all the gruppes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<GruppeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Gruppes");
        return gruppeRepository.findAll(pageable)
            .map(gruppeMapper::toDto);
    }


    /**
     * Get one gruppe by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<GruppeDTO> findOne(Long id) {
        log.debug("Request to get Gruppe : {}", id);
        return gruppeRepository.findById(id)
            .map(gruppeMapper::toDto);
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
