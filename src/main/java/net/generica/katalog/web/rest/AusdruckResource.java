package net.generica.katalog.web.rest;
import net.generica.katalog.service.AusdruckService;
import net.generica.katalog.web.rest.errors.BadRequestAlertException;
import net.generica.katalog.web.rest.util.HeaderUtil;
import net.generica.katalog.web.rest.util.PaginationUtil;
import net.generica.katalog.service.dto.AusdruckDTO;
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
 * REST controller for managing Ausdruck.
 */
@RestController
@RequestMapping("/api")
public class AusdruckResource {

    private final Logger log = LoggerFactory.getLogger(AusdruckResource.class);

    private static final String ENTITY_NAME = "ausdruck";

    private final AusdruckService ausdruckService;

    public AusdruckResource(AusdruckService ausdruckService) {
        this.ausdruckService = ausdruckService;
    }

    /**
     * POST  /ausdrucks : Create a new ausdruck.
     *
     * @param ausdruckDTO the ausdruckDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new ausdruckDTO, or with status 400 (Bad Request) if the ausdruck has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ausdrucks")
    public ResponseEntity<AusdruckDTO> createAusdruck(@Valid @RequestBody AusdruckDTO ausdruckDTO) throws URISyntaxException {
        log.debug("REST request to save Ausdruck : {}", ausdruckDTO);
        if (ausdruckDTO.getId() != null) {
            throw new BadRequestAlertException("A new ausdruck cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AusdruckDTO result = ausdruckService.save(ausdruckDTO);
        return ResponseEntity.created(new URI("/api/ausdrucks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ausdrucks : Updates an existing ausdruck.
     *
     * @param ausdruckDTO the ausdruckDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated ausdruckDTO,
     * or with status 400 (Bad Request) if the ausdruckDTO is not valid,
     * or with status 500 (Internal Server Error) if the ausdruckDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ausdrucks")
    public ResponseEntity<AusdruckDTO> updateAusdruck(@Valid @RequestBody AusdruckDTO ausdruckDTO) throws URISyntaxException {
        log.debug("REST request to update Ausdruck : {}", ausdruckDTO);
        if (ausdruckDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AusdruckDTO result = ausdruckService.save(ausdruckDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, ausdruckDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ausdrucks : get all the ausdrucks.
     *
     * @param pageable the pagination information
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many)
     * @return the ResponseEntity with status 200 (OK) and the list of ausdrucks in body
     */
    @GetMapping("/ausdrucks")
    public ResponseEntity<List<AusdruckDTO>> getAllAusdrucks(Pageable pageable, @RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get a page of Ausdrucks");
        Page<AusdruckDTO> page;
        if (eagerload) {
            page = ausdruckService.findAllWithEagerRelationships(pageable);
        } else {
            page = ausdruckService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, String.format("/api/ausdrucks?eagerload=%b", eagerload));
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /ausdrucks/:id : get the "id" ausdruck.
     *
     * @param id the id of the ausdruckDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the ausdruckDTO, or with status 404 (Not Found)
     */
    @GetMapping("/ausdrucks/{id}")
    public ResponseEntity<AusdruckDTO> getAusdruck(@PathVariable Long id) {
        log.debug("REST request to get Ausdruck : {}", id);
        Optional<AusdruckDTO> ausdruckDTO = ausdruckService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ausdruckDTO);
    }

    /**
     * DELETE  /ausdrucks/:id : delete the "id" ausdruck.
     *
     * @param id the id of the ausdruckDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ausdrucks/{id}")
    public ResponseEntity<Void> deleteAusdruck(@PathVariable Long id) {
        log.debug("REST request to delete Ausdruck : {}", id);
        ausdruckService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
