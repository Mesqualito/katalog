package net.generica.katalog.web.rest;
import net.generica.katalog.domain.Gruppe;
import net.generica.katalog.service.GruppeService;
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
     * @param gruppe the gruppe to create
     * @return the ResponseEntity with status 201 (Created) and with body the new gruppe, or with status 400 (Bad Request) if the gruppe has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/gruppes")
    public ResponseEntity<Gruppe> createGruppe(@Valid @RequestBody Gruppe gruppe) throws URISyntaxException {
        log.debug("REST request to save Gruppe : {}", gruppe);
        if (gruppe.getId() != null) {
            throw new BadRequestAlertException("A new gruppe cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Gruppe result = gruppeService.save(gruppe);
        return ResponseEntity.created(new URI("/api/gruppes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /gruppes : Updates an existing gruppe.
     *
     * @param gruppe the gruppe to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated gruppe,
     * or with status 400 (Bad Request) if the gruppe is not valid,
     * or with status 500 (Internal Server Error) if the gruppe couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/gruppes")
    public ResponseEntity<Gruppe> updateGruppe(@Valid @RequestBody Gruppe gruppe) throws URISyntaxException {
        log.debug("REST request to update Gruppe : {}", gruppe);
        if (gruppe.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Gruppe result = gruppeService.save(gruppe);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, gruppe.getId().toString()))
            .body(result);
    }

    /**
     * GET  /gruppes : get all the gruppes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of gruppes in body
     */
    @GetMapping("/gruppes")
    public ResponseEntity<List<Gruppe>> getAllGruppes(Pageable pageable) {
        log.debug("REST request to get a page of Gruppes");
        Page<Gruppe> page = gruppeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/gruppes");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /gruppes/:id : get the "id" gruppe.
     *
     * @param id the id of the gruppe to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the gruppe, or with status 404 (Not Found)
     */
    @GetMapping("/gruppes/{id}")
    public ResponseEntity<Gruppe> getGruppe(@PathVariable Long id) {
        log.debug("REST request to get Gruppe : {}", id);
        Optional<Gruppe> gruppe = gruppeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(gruppe);
    }

    /**
     * DELETE  /gruppes/:id : delete the "id" gruppe.
     *
     * @param id the id of the gruppe to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/gruppes/{id}")
    public ResponseEntity<Void> deleteGruppe(@PathVariable Long id) {
        log.debug("REST request to delete Gruppe : {}", id);
        gruppeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
