package net.generica.katalog.web.rest;
import net.generica.katalog.domain.Single;
import net.generica.katalog.service.SingleService;
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
 * REST controller for managing Single.
 */
@RestController
@RequestMapping("/api")
public class SingleResource {

    private final Logger log = LoggerFactory.getLogger(SingleResource.class);

    private static final String ENTITY_NAME = "single";

    private final SingleService singleService;

    public SingleResource(SingleService singleService) {
        this.singleService = singleService;
    }

    /**
     * POST  /singles : Create a new single.
     *
     * @param single the single to create
     * @return the ResponseEntity with status 201 (Created) and with body the new single, or with status 400 (Bad Request) if the single has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/singles")
    public ResponseEntity<Single> createSingle(@Valid @RequestBody Single single) throws URISyntaxException {
        log.debug("REST request to save Single : {}", single);
        if (single.getId() != null) {
            throw new BadRequestAlertException("A new single cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Single result = singleService.save(single);
        return ResponseEntity.created(new URI("/api/singles/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /singles : Updates an existing single.
     *
     * @param single the single to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated single,
     * or with status 400 (Bad Request) if the single is not valid,
     * or with status 500 (Internal Server Error) if the single couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/singles")
    public ResponseEntity<Single> updateSingle(@Valid @RequestBody Single single) throws URISyntaxException {
        log.debug("REST request to update Single : {}", single);
        if (single.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Single result = singleService.save(single);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, single.getId().toString()))
            .body(result);
    }

    /**
     * GET  /singles : get all the singles.
     *
     * @param pageable the pagination information
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many)
     * @return the ResponseEntity with status 200 (OK) and the list of singles in body
     */
    @GetMapping("/singles")
    public ResponseEntity<List<Single>> getAllSingles(Pageable pageable, @RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get a page of Singles");
        Page<Single> page;
        if (eagerload) {
            page = singleService.findAllWithEagerRelationships(pageable);
        } else {
            page = singleService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, String.format("/api/singles?eagerload=%b", eagerload));
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /singles/:id : get the "id" single.
     *
     * @param id the id of the single to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the single, or with status 404 (Not Found)
     */
    @GetMapping("/singles/{id}")
    public ResponseEntity<Single> getSingle(@PathVariable String id) {
        log.debug("REST request to get Single : {}", id);
        Optional<Single> single = singleService.findOne(id);
        return ResponseUtil.wrapOrNotFound(single);
    }

    /**
     * DELETE  /singles/:id : delete the "id" single.
     *
     * @param id the id of the single to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/singles/{id}")
    public ResponseEntity<Void> deleteSingle(@PathVariable String id) {
        log.debug("REST request to delete Single : {}", id);
        singleService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
