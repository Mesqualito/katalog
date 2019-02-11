package net.generica.katalog.web.rest;

import net.generica.katalog.KatalogApp;

import net.generica.katalog.domain.Wort;
import net.generica.katalog.repository.WortRepository;
import net.generica.katalog.service.WortService;
import net.generica.katalog.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.Validator;

import java.util.ArrayList;
import java.util.List;


import static net.generica.katalog.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the WortResource REST controller.
 *
 * @see WortResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = KatalogApp.class)
public class WortResourceIntTest {

    private static final String DEFAULT_E_WORT = "AAAAAAAAAA";
    private static final String UPDATED_E_WORT = "BBBBBBBBBB";

    @Autowired
    private WortRepository wortRepository;

    @Mock
    private WortRepository wortRepositoryMock;

    @Mock
    private WortService wortServiceMock;

    @Autowired
    private WortService wortService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restWortMockMvc;

    private Wort wort;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final WortResource wortResource = new WortResource(wortService);
        this.restWortMockMvc = MockMvcBuilders.standaloneSetup(wortResource)
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
    public static Wort createEntity() {
        Wort wort = new Wort()
            .eWort(DEFAULT_E_WORT);
        return wort;
    }

    @Before
    public void initTest() {
        wortRepository.deleteAll();
        wort = createEntity();
    }

    @Test
    public void createWort() throws Exception {
        int databaseSizeBeforeCreate = wortRepository.findAll().size();

        // Create the Wort
        restWortMockMvc.perform(post("/api/worts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(wort)))
            .andExpect(status().isCreated());

        // Validate the Wort in the database
        List<Wort> wortList = wortRepository.findAll();
        assertThat(wortList).hasSize(databaseSizeBeforeCreate + 1);
        Wort testWort = wortList.get(wortList.size() - 1);
        assertThat(testWort.geteWort()).isEqualTo(DEFAULT_E_WORT);
    }

    @Test
    public void createWortWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = wortRepository.findAll().size();

        // Create the Wort with an existing ID
        wort.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restWortMockMvc.perform(post("/api/worts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(wort)))
            .andExpect(status().isBadRequest());

        // Validate the Wort in the database
        List<Wort> wortList = wortRepository.findAll();
        assertThat(wortList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void checkeWortIsRequired() throws Exception {
        int databaseSizeBeforeTest = wortRepository.findAll().size();
        // set the field null
        wort.seteWort(null);

        // Create the Wort, which fails.

        restWortMockMvc.perform(post("/api/worts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(wort)))
            .andExpect(status().isBadRequest());

        List<Wort> wortList = wortRepository.findAll();
        assertThat(wortList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllWorts() throws Exception {
        // Initialize the database
        wortRepository.save(wort);

        // Get all the wortList
        restWortMockMvc.perform(get("/api/worts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(wort.getId())))
            .andExpect(jsonPath("$.[*].eWort").value(hasItem(DEFAULT_E_WORT.toString())));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllWortsWithEagerRelationshipsIsEnabled() throws Exception {
        WortResource wortResource = new WortResource(wortServiceMock);
        when(wortServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        MockMvc restWortMockMvc = MockMvcBuilders.standaloneSetup(wortResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restWortMockMvc.perform(get("/api/worts?eagerload=true"))
        .andExpect(status().isOk());

        verify(wortServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllWortsWithEagerRelationshipsIsNotEnabled() throws Exception {
        WortResource wortResource = new WortResource(wortServiceMock);
            when(wortServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));
            MockMvc restWortMockMvc = MockMvcBuilders.standaloneSetup(wortResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restWortMockMvc.perform(get("/api/worts?eagerload=true"))
        .andExpect(status().isOk());

            verify(wortServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    public void getWort() throws Exception {
        // Initialize the database
        wortRepository.save(wort);

        // Get the wort
        restWortMockMvc.perform(get("/api/worts/{id}", wort.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(wort.getId()))
            .andExpect(jsonPath("$.eWort").value(DEFAULT_E_WORT.toString()));
    }

    @Test
    public void getNonExistingWort() throws Exception {
        // Get the wort
        restWortMockMvc.perform(get("/api/worts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateWort() throws Exception {
        // Initialize the database
        wortService.save(wort);

        int databaseSizeBeforeUpdate = wortRepository.findAll().size();

        // Update the wort
        Wort updatedWort = wortRepository.findById(wort.getId()).get();
        updatedWort
            .eWort(UPDATED_E_WORT);

        restWortMockMvc.perform(put("/api/worts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedWort)))
            .andExpect(status().isOk());

        // Validate the Wort in the database
        List<Wort> wortList = wortRepository.findAll();
        assertThat(wortList).hasSize(databaseSizeBeforeUpdate);
        Wort testWort = wortList.get(wortList.size() - 1);
        assertThat(testWort.geteWort()).isEqualTo(UPDATED_E_WORT);
    }

    @Test
    public void updateNonExistingWort() throws Exception {
        int databaseSizeBeforeUpdate = wortRepository.findAll().size();

        // Create the Wort

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWortMockMvc.perform(put("/api/worts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(wort)))
            .andExpect(status().isBadRequest());

        // Validate the Wort in the database
        List<Wort> wortList = wortRepository.findAll();
        assertThat(wortList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteWort() throws Exception {
        // Initialize the database
        wortService.save(wort);

        int databaseSizeBeforeDelete = wortRepository.findAll().size();

        // Delete the wort
        restWortMockMvc.perform(delete("/api/worts/{id}", wort.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Wort> wortList = wortRepository.findAll();
        assertThat(wortList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Wort.class);
        Wort wort1 = new Wort();
        wort1.setId("id1");
        Wort wort2 = new Wort();
        wort2.setId(wort1.getId());
        assertThat(wort1).isEqualTo(wort2);
        wort2.setId("id2");
        assertThat(wort1).isNotEqualTo(wort2);
        wort1.setId(null);
        assertThat(wort1).isNotEqualTo(wort2);
    }
}
