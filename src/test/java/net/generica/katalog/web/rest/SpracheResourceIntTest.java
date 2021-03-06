package net.generica.katalog.web.rest;

import net.generica.katalog.KatalogApp;

import net.generica.katalog.domain.Sprache;
import net.generica.katalog.repository.SpracheRepository;
import net.generica.katalog.service.SpracheService;
import net.generica.katalog.service.dto.SpracheDTO;
import net.generica.katalog.service.mapper.SpracheMapper;
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
 * Test class for the SpracheResource REST controller.
 *
 * @see SpracheResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = KatalogApp.class)
public class SpracheResourceIntTest {

    private static final String DEFAULT_SPRACH_CODE = "AAAAAAAAAA";
    private static final String UPDATED_SPRACH_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_SPRACH_BEZEICHNUNG = "AAAAAAAAAA";
    private static final String UPDATED_SPRACH_BEZEICHNUNG = "BBBBBBBBBB";

    @Autowired
    private SpracheRepository spracheRepository;

    @Autowired
    private SpracheMapper spracheMapper;

    @Autowired
    private SpracheService spracheService;

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

    private MockMvc restSpracheMockMvc;

    private Sprache sprache;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SpracheResource spracheResource = new SpracheResource(spracheService);
        this.restSpracheMockMvc = MockMvcBuilders.standaloneSetup(spracheResource)
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
    public static Sprache createEntity(EntityManager em) {
        Sprache sprache = new Sprache()
            .sprachCode(DEFAULT_SPRACH_CODE)
            .sprachBezeichnung(DEFAULT_SPRACH_BEZEICHNUNG);
        return sprache;
    }

    @Before
    public void initTest() {
        sprache = createEntity(em);
    }

    @Test
    @Transactional
    public void createSprache() throws Exception {
        int databaseSizeBeforeCreate = spracheRepository.findAll().size();

        // Create the Sprache
        SpracheDTO spracheDTO = spracheMapper.toDto(sprache);
        restSpracheMockMvc.perform(post("/api/spraches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(spracheDTO)))
            .andExpect(status().isCreated());

        // Validate the Sprache in the database
        List<Sprache> spracheList = spracheRepository.findAll();
        assertThat(spracheList).hasSize(databaseSizeBeforeCreate + 1);
        Sprache testSprache = spracheList.get(spracheList.size() - 1);
        assertThat(testSprache.getSprachCode()).isEqualTo(DEFAULT_SPRACH_CODE);
        assertThat(testSprache.getSprachBezeichnung()).isEqualTo(DEFAULT_SPRACH_BEZEICHNUNG);
    }

    @Test
    @Transactional
    public void createSpracheWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = spracheRepository.findAll().size();

        // Create the Sprache with an existing ID
        sprache.setId(1L);
        SpracheDTO spracheDTO = spracheMapper.toDto(sprache);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSpracheMockMvc.perform(post("/api/spraches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(spracheDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Sprache in the database
        List<Sprache> spracheList = spracheRepository.findAll();
        assertThat(spracheList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkSprachCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = spracheRepository.findAll().size();
        // set the field null
        sprache.setSprachCode(null);

        // Create the Sprache, which fails.
        SpracheDTO spracheDTO = spracheMapper.toDto(sprache);

        restSpracheMockMvc.perform(post("/api/spraches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(spracheDTO)))
            .andExpect(status().isBadRequest());

        List<Sprache> spracheList = spracheRepository.findAll();
        assertThat(spracheList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSpraches() throws Exception {
        // Initialize the database
        spracheRepository.saveAndFlush(sprache);

        // Get all the spracheList
        restSpracheMockMvc.perform(get("/api/spraches?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sprache.getId().intValue())))
            .andExpect(jsonPath("$.[*].sprachCode").value(hasItem(DEFAULT_SPRACH_CODE.toString())))
            .andExpect(jsonPath("$.[*].sprachBezeichnung").value(hasItem(DEFAULT_SPRACH_BEZEICHNUNG.toString())));
    }
    
    @Test
    @Transactional
    public void getSprache() throws Exception {
        // Initialize the database
        spracheRepository.saveAndFlush(sprache);

        // Get the sprache
        restSpracheMockMvc.perform(get("/api/spraches/{id}", sprache.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sprache.getId().intValue()))
            .andExpect(jsonPath("$.sprachCode").value(DEFAULT_SPRACH_CODE.toString()))
            .andExpect(jsonPath("$.sprachBezeichnung").value(DEFAULT_SPRACH_BEZEICHNUNG.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingSprache() throws Exception {
        // Get the sprache
        restSpracheMockMvc.perform(get("/api/spraches/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSprache() throws Exception {
        // Initialize the database
        spracheRepository.saveAndFlush(sprache);

        int databaseSizeBeforeUpdate = spracheRepository.findAll().size();

        // Update the sprache
        Sprache updatedSprache = spracheRepository.findById(sprache.getId()).get();
        // Disconnect from session so that the updates on updatedSprache are not directly saved in db
        em.detach(updatedSprache);
        updatedSprache
            .sprachCode(UPDATED_SPRACH_CODE)
            .sprachBezeichnung(UPDATED_SPRACH_BEZEICHNUNG);
        SpracheDTO spracheDTO = spracheMapper.toDto(updatedSprache);

        restSpracheMockMvc.perform(put("/api/spraches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(spracheDTO)))
            .andExpect(status().isOk());

        // Validate the Sprache in the database
        List<Sprache> spracheList = spracheRepository.findAll();
        assertThat(spracheList).hasSize(databaseSizeBeforeUpdate);
        Sprache testSprache = spracheList.get(spracheList.size() - 1);
        assertThat(testSprache.getSprachCode()).isEqualTo(UPDATED_SPRACH_CODE);
        assertThat(testSprache.getSprachBezeichnung()).isEqualTo(UPDATED_SPRACH_BEZEICHNUNG);
    }

    @Test
    @Transactional
    public void updateNonExistingSprache() throws Exception {
        int databaseSizeBeforeUpdate = spracheRepository.findAll().size();

        // Create the Sprache
        SpracheDTO spracheDTO = spracheMapper.toDto(sprache);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSpracheMockMvc.perform(put("/api/spraches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(spracheDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Sprache in the database
        List<Sprache> spracheList = spracheRepository.findAll();
        assertThat(spracheList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSprache() throws Exception {
        // Initialize the database
        spracheRepository.saveAndFlush(sprache);

        int databaseSizeBeforeDelete = spracheRepository.findAll().size();

        // Delete the sprache
        restSpracheMockMvc.perform(delete("/api/spraches/{id}", sprache.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Sprache> spracheList = spracheRepository.findAll();
        assertThat(spracheList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Sprache.class);
        Sprache sprache1 = new Sprache();
        sprache1.setId(1L);
        Sprache sprache2 = new Sprache();
        sprache2.setId(sprache1.getId());
        assertThat(sprache1).isEqualTo(sprache2);
        sprache2.setId(2L);
        assertThat(sprache1).isNotEqualTo(sprache2);
        sprache1.setId(null);
        assertThat(sprache1).isNotEqualTo(sprache2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SpracheDTO.class);
        SpracheDTO spracheDTO1 = new SpracheDTO();
        spracheDTO1.setId(1L);
        SpracheDTO spracheDTO2 = new SpracheDTO();
        assertThat(spracheDTO1).isNotEqualTo(spracheDTO2);
        spracheDTO2.setId(spracheDTO1.getId());
        assertThat(spracheDTO1).isEqualTo(spracheDTO2);
        spracheDTO2.setId(2L);
        assertThat(spracheDTO1).isNotEqualTo(spracheDTO2);
        spracheDTO1.setId(null);
        assertThat(spracheDTO1).isNotEqualTo(spracheDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(spracheMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(spracheMapper.fromId(null)).isNull();
    }
}
