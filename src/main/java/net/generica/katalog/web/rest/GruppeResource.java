package net.generica.katalog.web.rest;
import net.generica.katalog.service.GruppeService;
import net.generica.katalog.web.rest.errors.BadRequestAlertException;
import net.generica.katalog.web.rest.util.HeaderUtil;
import net.generica.katalog.web.rest.util.PaginationUtil;
import net.generica.katalog.service.dto.GruppeDTO;
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
 * REST controller for managing Gruppe.
 */
@RestController
@RequestMapping("/api")
public class GruppeResource {

    private final Logger log = LoggerFactory.getLogger(GruppeResource.class);

    private static final String ENTITY_NAME = "gruppe";

    private final GruppeService gruppeService;

    public GruppeResource(GruppeService gruppeService) {
        this.gruppeService = gruppeService;
    }

    /**
     * POST  /gruppes : Create a new gruppe.
     *
     * @param gruppeDTO the gruppeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new gruppeDTO, or with status 400 (Bad Request) if the gruppe has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/gruppes")
    public ResponseEntity<GruppeDTO> createGruppe(@Valid @RequestBody GruppeDTO gruppeDTO) throws URISyntaxException {
        log.debug("REST request to save Gruppe : {}", gruppeDTO);
        if (gruppeDTO.getId() != null) {
            throw new BadRequestAlertException("A new gruppe cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GruppeDTO result = gruppeService.save(gruppeDTO);
        return ResponseEntity.created(new URI("/api/gruppes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /gruppes : Updates an existing gruppe.
     *
     * @param gruppeDTO the gruppeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated gruppeDTO,
     * or with status 400 (Bad Request) if the gruppeDTO is not valid,
     * or with status 500 (Internal Server Error) if the gruppeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/gruppes")
    public ResponseEntity<GruppeDTO> updateGruppe(@Valid @RequestBody GruppeDTO gruppeDTO) throws URISyntaxException {
        log.debug("REST request to update Gruppe : {}", gruppeDTO);
        if (gruppeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GruppeDTO result = gruppeService.save(gruppeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, gruppeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /gruppes : get all the gruppes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of gruppes in body
     */
    @GetMapping("/gruppes")
    public ResponseEntity<List<GruppeDTO>> getAllGruppes(Pageable pageable) {
        log.debug("REST request to get a page of Gruppes");
        Page<GruppeDTO> page = gruppeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/gruppes");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /gruppes/:id : get the "id" gruppe.
     *
     * @param id the id of the gruppeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the gruppeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/gruppes/{id}")
    public ResponseEntity<GruppeDTO> getGruppe(@PathVariable Long id) {
        log.debug("REST request to get Gruppe : {}", id);
        Optional<GruppeDTO> gruppeDTO = gruppeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(gruppeDTO);
    }

    /**
     * DELETE  /gruppes/:id : delete the "id" gruppe.
     *
     * @param id the id of the gruppeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/gruppes/{id}")
    public ResponseEntity<Void> deleteGruppe(@PathVariable Long id) {
        log.debug("REST request to delete Gruppe : {}", id);
        gruppeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
