package net.generica.katalog.web.rest;
import net.generica.katalog.service.SpracheService;
import net.generica.katalog.web.rest.errors.BadRequestAlertException;
import net.generica.katalog.web.rest.util.HeaderUtil;
import net.generica.katalog.web.rest.util.PaginationUtil;
import net.generica.katalog.service.dto.SpracheDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Sprache.
 */
@RestController
@RequestMapping("/api")
public class SpracheResource {

    private final Logger log = LoggerFactory.getLogger(SpracheResource.class);

    private static final String ENTITY_NAME = "sprache";

    private final SpracheService spracheService;

    public SpracheResource(SpracheService spracheService) {
        this.spracheService = spracheService;
    }

    /**
     * POST  /spraches : Create a new sprache.
     *
     * @param spracheDTO the spracheDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new spracheDTO, or with status 400 (Bad Request) if the sprache has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/spraches")
    public ResponseEntity<SpracheDTO> createSprache(@Valid @RequestBody SpracheDTO spracheDTO) throws URISyntaxException {
        log.debug("REST request to save Sprache : {}", spracheDTO);
        if (spracheDTO.getId() != null) {
            throw new BadRequestAlertException("A new sprache cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SpracheDTO result = spracheService.save(spracheDTO);
        return ResponseEntity.created(new URI("/api/spraches/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /spraches : Updates an existing sprache.
     *
     * @param spracheDTO the spracheDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated spracheDTO,
     * or with status 400 (Bad Request) if the spracheDTO is not valid,
     * or with status 500 (Internal Server Error) if the spracheDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/spraches")
    public ResponseEntity<SpracheDTO> updateSprache(@Valid @RequestBody SpracheDTO spracheDTO) throws URISyntaxException {
        log.debug("REST request to update Sprache : {}", spracheDTO);
        if (spracheDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SpracheDTO result = spracheService.save(spracheDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, spracheDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /spraches : get all the spraches.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of spraches in body
     */
    @GetMapping("/spraches")
    public ResponseEntity<List<SpracheDTO>> getAllSpraches(Pageable pageable) {
        log.debug("REST request to get a page of Spraches");
        Page<SpracheDTO> page = spracheService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/spraches");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /spraches/:id : get the "id" sprache.
     *
     * @param id the id of the spracheDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the spracheDTO, or with status 404 (Not Found)
     */
    @GetMapping("/spraches/{id}")
    public ResponseEntity<SpracheDTO> getSprache(@PathVariable Long id) {
        log.debug("REST request to get Sprache : {}", id);
        Optional<SpracheDTO> spracheDTO = spracheService.findOne(id);
        return ResponseUtil.wrapOrNotFound(spracheDTO);
    }

    /**
     * DELETE  /spraches/:id : delete the "id" sprache.
     *
     * @param id the id of the spracheDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/spraches/{id}")
    public ResponseEntity<Void> deleteSprache(@PathVariable Long id) {
        log.debug("REST request to delete Sprache : {}", id);
        spracheService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
