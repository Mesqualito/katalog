package net.generica.katalog.service.impl;

import net.generica.katalog.service.BezeichnungService;
import net.generica.katalog.domain.Bezeichnung;
import net.generica.katalog.repository.BezeichnungRepository;
import net.generica.katalog.service.dto.BezeichnungDTO;
import net.generica.katalog.service.mapper.BezeichnungMapper;
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

    private final BezeichnungMapper bezeichnungMapper;

    public BezeichnungServiceImpl(BezeichnungRepository bezeichnungRepository, BezeichnungMapper bezeichnungMapper) {
        this.bezeichnungRepository = bezeichnungRepository;
        this.bezeichnungMapper = bezeichnungMapper;
    }

    /**
     * Save a bezeichnung.
     *
     * @param bezeichnungDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public BezeichnungDTO save(BezeichnungDTO bezeichnungDTO) {
        log.debug("Request to save Bezeichnung : {}", bezeichnungDTO);
        Bezeichnung bezeichnung = bezeichnungMapper.toEntity(bezeichnungDTO);
        bezeichnung = bezeichnungRepository.save(bezeichnung);
        return bezeichnungMapper.toDto(bezeichnung);
    }

    /**
     * Get all the bezeichnungs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<BezeichnungDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Bezeichnungs");
        return bezeichnungRepository.findAll(pageable)
            .map(bezeichnungMapper::toDto);
    }

    /**
     * Get all the Bezeichnung with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<BezeichnungDTO> findAllWithEagerRelationships(Pageable pageable) {
        return bezeichnungRepository.findAllWithEagerRelationships(pageable).map(bezeichnungMapper::toDto);
    }
    

    /**
     * Get one bezeichnung by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<BezeichnungDTO> findOne(Long id) {
        log.debug("Request to get Bezeichnung : {}", id);
        return bezeichnungRepository.findOneWithEagerRelationships(id)
            .map(bezeichnungMapper::toDto);
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
