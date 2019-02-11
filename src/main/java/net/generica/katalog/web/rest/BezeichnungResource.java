package net.generica.katalog.web.rest;
import net.generica.katalog.domain.Bezeichnung;
import net.generica.katalog.service.BezeichnungService;
import net.generica.katalog.web.rest.errors.BadRequestAlertException;
import net.generica.katalog.web.rest.util.HeaderUtil;
import net.generica.katalog.web.rest.util.PaginationUtil;
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
 * REST controller for managing Bezeichnung.
 */
@RestController
@RequestMapping("/api")
public class BezeichnungResource {

    private final Logger log = LoggerFactory.getLogger(BezeichnungResource.class);

    private static final String ENTITY_NAME = "bezeichnung";

    private final BezeichnungService bezeichnungService;

    public BezeichnungResource(BezeichnungService bezeichnungService) {
        this.bezeichnungService = bezeichnungService;
    }

    /**
     * POST  /bezeichnungs : Create a new bezeichnung.
     *
     * @param bezeichnung the bezeichnung to create
     * @return the ResponseEntity with status 201 (Created) and with body the new bezeichnung, or with status 400 (Bad Request) if the bezeichnung has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/bezeichnungs")
    public ResponseEntity<Bezeichnung> createBezeichnung(@Valid @RequestBody Bezeichnung bezeichnung) throws URISyntaxException {
        log.debug("REST request to save Bezeichnung : {}", bezeichnung);
        if (bezeichnung.getId() != null) {
            throw new BadRequestAlertException("A new bezeichnung cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Bezeichnung result = bezeichnungService.save(bezeichnung);
        return ResponseEntity.created(new URI("/api/bezeichnungs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /bezeichnungs : Updates an existing bezeichnung.
     *
     * @param bezeichnung the bezeichnung to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated bezeichnung,
     * or with status 400 (Bad Request) if the bezeichnung is not valid,
     * or with status 500 (Internal Server Error) if the bezeichnung couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/bezeichnungs")
    public ResponseEntity<Bezeichnung> updateBezeichnung(@Valid @RequestBody Bezeichnung bezeichnung) throws URISyntaxException {
        log.debug("REST request to update Bezeichnung : {}", bezeichnung);
        if (bezeichnung.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Bezeichnung result = bezeichnungService.save(bezeichnung);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, bezeichnung.getId().toString()))
            .body(result);
    }

    /**
     * GET  /bezeichnungs : get all the bezeichnungs.
     *
     * @param pageable the pagination information
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many)
     * @return the ResponseEntity with status 200 (OK) and the list of bezeichnungs in body
     */
    @GetMapping("/bezeichnungs")
    public ResponseEntity<List<Bezeichnung>> getAllBezeichnungs(Pageable pageable, @RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get a page of Bezeichnungs");
        Page<Bezeichnung> page;
        if (eagerload) {
            page = bezeichnungService.findAllWithEagerRelationships(pageable);
        } else {
            page = bezeichnungService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, String.format("/api/bezeichnungs?eagerload=%b", eagerload));
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /bezeichnungs/:id : get the "id" bezeichnung.
     *
     * @param id the id of the bezeichnung to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the bezeichnung, or with status 404 (Not Found)
     */
    @GetMapping("/bezeichnungs/{id}")
    public ResponseEntity<Bezeichnung> getBezeichnung(@PathVariable String id) {
        log.debug("REST request to get Bezeichnung : {}", id);
        Optional<Bezeichnung> bezeichnung = bezeichnungService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bezeichnung);
    }

    /**
     * DELETE  /bezeichnungs/:id : delete the "id" bezeichnung.
     *
     * @param id the id of the bezeichnung to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/bezeichnungs/{id}")
    public ResponseEntity<Void> deleteBezeichnung(@PathVariable String id) {
        log.debug("REST request to delete Bezeichnung : {}", id);
        bezeichnungService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
