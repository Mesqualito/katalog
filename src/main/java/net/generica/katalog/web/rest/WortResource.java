package net.generica.katalog.web.rest;
import net.generica.katalog.domain.Wort;
import net.generica.katalog.service.WortService;
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
 * REST controller for managing Wort.
 */
@RestController
@RequestMapping("/api")
public class WortResource {

    private final Logger log = LoggerFactory.getLogger(WortResource.class);

    private static final String ENTITY_NAME = "wort";

    private final WortService wortService;

    public WortResource(WortService wortService) {
        this.wortService = wortService;
    }

    /**
     * POST  /worts : Create a new wort.
     *
     * @param wort the wort to create
     * @return the ResponseEntity with status 201 (Created) and with body the new wort, or with status 400 (Bad Request) if the wort has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/worts")
    public ResponseEntity<Wort> createWort(@Valid @RequestBody Wort wort) throws URISyntaxException {
        log.debug("REST request to save Wort : {}", wort);
        if (wort.getId() != null) {
            throw new BadRequestAlertException("A new wort cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Wort result = wortService.save(wort);
        return ResponseEntity.created(new URI("/api/worts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /worts : Updates an existing wort.
     *
     * @param wort the wort to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated wort,
     * or with status 400 (Bad Request) if the wort is not valid,
     * or with status 500 (Internal Server Error) if the wort couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/worts")
    public ResponseEntity<Wort> updateWort(@Valid @RequestBody Wort wort) throws URISyntaxException {
        log.debug("REST request to update Wort : {}", wort);
        if (wort.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Wort result = wortService.save(wort);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, wort.getId().toString()))
            .body(result);
    }

    /**
     * GET  /worts : get all the worts.
     *
     * @param pageable the pagination information
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many)
     * @return the ResponseEntity with status 200 (OK) and the list of worts in body
     */
    @GetMapping("/worts")
    public ResponseEntity<List<Wort>> getAllWorts(Pageable pageable, @RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get a page of Worts");
        Page<Wort> page;
        if (eagerload) {
            page = wortService.findAllWithEagerRelationships(pageable);
        } else {
            page = wortService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, String.format("/api/worts?eagerload=%b", eagerload));
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /worts/:id : get the "id" wort.
     *
     * @param id the id of the wort to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the wort, or with status 404 (Not Found)
     */
    @GetMapping("/worts/{id}")
    public ResponseEntity<Wort> getWort(@PathVariable String id) {
        log.debug("REST request to get Wort : {}", id);
        Optional<Wort> wort = wortService.findOne(id);
        return ResponseUtil.wrapOrNotFound(wort);
    }

    /**
     * DELETE  /worts/:id : delete the "id" wort.
     *
     * @param id the id of the wort to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/worts/{id}")
    public ResponseEntity<Void> deleteWort(@PathVariable String id) {
        log.debug("REST request to delete Wort : {}", id);
        wortService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
