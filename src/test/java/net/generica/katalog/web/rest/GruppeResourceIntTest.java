package net.generica.katalog.web.rest;

import net.generica.katalog.KatalogApp;

import net.generica.katalog.domain.Gruppe;
import net.generica.katalog.repository.GruppeRepository;
import net.generica.katalog.service.GruppeService;
import net.generica.katalog.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;


import static net.generica.katalog.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the GruppeResource REST controller.
 *
 * @see GruppeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = KatalogApp.class)
public class GruppeResourceIntTest {

    private static final String DEFAULT_GRUPPEN_CODE = "AAAAAAAAAA";
    private static final String UPDATED_GRUPPEN_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_GRUPPEN_BEZEICHNUNG = "AAAAAAAAAA";
    private static final String UPDATED_GRUPPEN_BEZEICHNUNG = "BBBBBBBBBB";

    @Autowired
    private GruppeRepository gruppeRepository;

    @Autowired
    private GruppeService gruppeService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restGruppeMockMvc;

    private Gruppe gruppe;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final GruppeResource gruppeResource = new GruppeResource(gruppeService);
        this.restGruppeMockMvc = MockMvcBuilders.standaloneSetup(gruppeResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Gruppe createEntity(EntityManager em) {
        Gruppe gruppe = new Gruppe()
            .gruppenCode(DEFAULT_GRUPPEN_CODE)
            .gruppenBezeichnung(DEFAULT_GRUPPEN_BEZEICHNUNG);
        return gruppe;
    }

    @Before
    public void initTest() {
        gruppe = createEntity(em);
    }

    @Test
    @Transactional
    public void createGruppe() throws Exception {
        int databaseSizeBeforeCreate = gruppeRepository.findAll().size();

        // Create the Gruppe
        restGruppeMockMvc.perform(post("/api/gruppes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(gruppe)))
            .andExpect(status().isCreated());

        // Validate the Gruppe in the database
        List<Gruppe> gruppeList = gruppeRepository.findAll();
        assertThat(gruppeList).hasSize(databaseSizeBeforeCreate + 1);
        Gruppe testGruppe = gruppeList.get(gruppeList.size() - 1);
        assertThat(testGruppe.getGruppenCode()).isEqualTo(DEFAULT_GRUPPEN_CODE);
        assertThat(testGruppe.getGruppenBezeichnung()).isEqualTo(DEFAULT_GRUPPEN_BEZEICHNUNG);
    }

    @Test
    @Transactional
    public void createGruppeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = gruppeRepository.findAll().size();

        // Create the Gruppe with an existing ID
        gruppe.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGruppeMockMvc.perform(post("/api/gruppes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(gruppe)))
            .andExpect(status().isBadRequest());

        // Validate the Gruppe in the database
        List<Gruppe> gruppeList = gruppeRepository.findAll();
        assertThat(gruppeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkGruppenCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = gruppeRepository.findAll().size();
        // set the field null
        gruppe.setGruppenCode(null);

        // Create the Gruppe, which fails.

        restGruppeMockMvc.perform(post("/api/gruppes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(gruppe)))
            .andExpect(status().isBadRequest());

        List<Gruppe> gruppeList = gruppeRepository.findAll();
        assertThat(gruppeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllGruppes() throws Exception {
        // Initialize the database
        gruppeRepository.saveAndFlush(gruppe);

        // Get all the gruppeList
        restGruppeMockMvc.perform(get("/api/gruppes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(gruppe.getId().intValue())))
            .andExpect(jsonPath("$.[*].gruppenCode").value(hasItem(DEFAULT_GRUPPEN_CODE.toString())))
            .andExpect(jsonPath("$.[*].gruppenBezeichnung").value(hasItem(DEFAULT_GRUPPEN_BEZEICHNUNG.toString())));
    }
    
    @Test
    @Transactional
    public void getGruppe() throws Exception {
        // Initialize the database
        gruppeRepository.saveAndFlush(gruppe);

        // Get the gruppe
        restGruppeMockMvc.perform(get("/api/gruppes/{id}", gruppe.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(gruppe.getId().intValue()))
            .andExpect(jsonPath("$.gruppenCode").value(DEFAULT_GRUPPEN_CODE.toString()))
            .andExpect(jsonPath("$.gruppenBezeichnung").value(DEFAULT_GRUPPEN_BEZEICHNUNG.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingGruppe() throws Exception {
        // Get the gruppe
        restGruppeMockMvc.perform(get("/api/gruppes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGruppe() throws Exception {
        // Initialize the database
        gruppeService.save(gruppe);

        int databaseSizeBeforeUpdate = gruppeRepository.findAll().size();

        // Update the gruppe
        Gruppe updatedGruppe = gruppeRepository.findById(gruppe.getId()).get();
        // Disconnect from session so that the updates on updatedGruppe are not directly saved in db
        em.detach(updatedGruppe);
        updatedGruppe
            .gruppenCode(UPDATED_GRUPPEN_CODE)
            .gruppenBezeichnung(UPDATED_GRUPPEN_BEZEICHNUNG);

        restGruppeMockMvc.perform(put("/api/gruppes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedGruppe)))
            .andExpect(status().isOk());

        // Validate the Gruppe in the database
        List<Gruppe> gruppeList = gruppeRepository.findAll();
        assertThat(gruppeList).hasSize(databaseSizeBeforeUpdate);
        Gruppe testGruppe = gruppeList.get(gruppeList.size() - 1);
        assertThat(testGruppe.getGruppenCode()).isEqualTo(UPDATED_GRUPPEN_CODE);
        assertThat(testGruppe.getGruppenBezeichnung()).isEqualTo(UPDATED_GRUPPEN_BEZEICHNUNG);
    }

    @Test
    @Transactional
    public void updateNonExistingGruppe() throws Exception {
        int databaseSizeBeforeUpdate = gruppeRepository.findAll().size();

        // Create the Gruppe

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGruppeMockMvc.perform(put("/api/gruppes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(gruppe)))
            .andExpect(status().isBadRequest());

        // Validate the Gruppe in the database
        List<Gruppe> gruppeList = gruppeRepository.findAll();
        assertThat(gruppeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGruppe() throws Exception {
        // Initialize the database
        gruppeService.save(gruppe);

        int databaseSizeBeforeDelete = gruppeRepository.findAll().size();

        // Delete the gruppe
        restGruppeMockMvc.perform(delete("/api/gruppes/{id}", gruppe.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Gruppe> gruppeList = gruppeRepository.findAll();
        assertThat(gruppeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Gruppe.class);
        Gruppe gruppe1 = new Gruppe();
        gruppe1.setId(1L);
        Gruppe gruppe2 = new Gruppe();
        gruppe2.setId(gruppe1.getId());
        assertThat(gruppe1).isEqualTo(gruppe2);
        gruppe2.setId(2L);
        assertThat(gruppe1).isNotEqualTo(gruppe2);
        gruppe1.setId(null);
        assertThat(gruppe1).isNotEqualTo(gruppe2);
    }
}
