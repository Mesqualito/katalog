package net.generica.katalog.service.impl;

import net.generica.katalog.service.AusdruckService;
import net.generica.katalog.domain.Ausdruck;
import net.generica.katalog.repository.AusdruckRepository;
import net.generica.katalog.service.dto.AusdruckDTO;
import net.generica.katalog.service.mapper.AusdruckMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Ausdruck.
 */
@Service
@Transactional
public class AusdruckServiceImpl implements AusdruckService {

    private final Logger log = LoggerFactory.getLogger(AusdruckServiceImpl.class);

    private final AusdruckRepository ausdruckRepository;

    private final AusdruckMapper ausdruckMapper;

    public AusdruckServiceImpl(AusdruckRepository ausdruckRepository, AusdruckMapper ausdruckMapper) {
        this.ausdruckRepository = ausdruckRepository;
        this.ausdruckMapper = ausdruckMapper;
    }

    /**
     * Save a ausdruck.
     *
     * @param ausdruckDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public AusdruckDTO save(AusdruckDTO ausdruckDTO) {
        log.debug("Request to save Ausdruck : {}", ausdruckDTO);
        Ausdruck ausdruck = ausdruckMapper.toEntity(ausdruckDTO);
        ausdruck = ausdruckRepository.save(ausdruck);
        return ausdruckMapper.toDto(ausdruck);
    }

    /**
     * Get all the ausdrucks.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AusdruckDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Ausdrucks");
        return ausdruckRepository.findAll(pageable)
            .map(ausdruckMapper::toDto);
    }

    /**
     * Get all the Ausdruck with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<AusdruckDTO> findAllWithEagerRelationships(Pageable pageable) {
        return ausdruckRepository.findAllWithEagerRelationships(pageable).map(ausdruckMapper::toDto);
    }
    

    /**
     * Get one ausdruck by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AusdruckDTO> findOne(Long id) {
        log.debug("Request to get Ausdruck : {}", id);
        return ausdruckRepository.findOneWithEagerRelationships(id)
            .map(ausdruckMapper::toDto);
    }

    /**
     * Delete the ausdruck by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Ausdruck : {}", id);        ausdruckRepository.deleteById(id);
    }
}
