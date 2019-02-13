package net.generica.katalog.web.rest;
import net.generica.katalog.service.WortService;
import net.generica.katalog.web.rest.errors.BadRequestAlertException;
import net.generica.katalog.web.rest.util.HeaderUtil;
import net.generica.katalog.web.rest.util.PaginationUtil;
import net.generica.katalog.service.dto.WortDTO;
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
     * @param wortDTO the wortDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new wortDTO, or with status 400 (Bad Request) if the wort has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/worts")
    public ResponseEntity<WortDTO> createWort(@Valid @RequestBody WortDTO wortDTO) throws URISyntaxException {
        log.debug("REST request to save Wort : {}", wortDTO);
        if (wortDTO.getId() != null) {
            throw new BadRequestAlertException("A new wort cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WortDTO result = wortService.save(wortDTO);
        return ResponseEntity.created(new URI("/api/worts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /worts : Updates an existing wort.
     *
     * @param wortDTO the wortDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated wortDTO,
     * or with status 400 (Bad Request) if the wortDTO is not valid,
     * or with status 500 (Internal Server Error) if the wortDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/worts")
    public ResponseEntity<WortDTO> updateWort(@Valid @RequestBody WortDTO wortDTO) throws URISyntaxException {
        log.debug("REST request to update Wort : {}", wortDTO);
        if (wortDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        WortDTO result = wortService.save(wortDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, wortDTO.getId().toString()))
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
    public ResponseEntity<List<WortDTO>> getAllWorts(Pageable pageable, @RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get a page of Worts");
        Page<WortDTO> page;
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
     * @param id the id of the wortDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the wortDTO, or with status 404 (Not Found)
     */
    @GetMapping("/worts/{id}")
    public ResponseEntity<WortDTO> getWort(@PathVariable Long id) {
        log.debug("REST request to get Wort : {}", id);
        Optional<WortDTO> wortDTO = wortService.findOne(id);
        return ResponseUtil.wrapOrNotFound(wortDTO);
    }

    /**
     * DELETE  /worts/:id : delete the "id" wort.
     *
     * @param id the id of the wortDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/worts/{id}")
    public ResponseEntity<Void> deleteWort(@PathVariable Long id) {
        log.debug("REST request to delete Wort : {}", id);
        wortService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
