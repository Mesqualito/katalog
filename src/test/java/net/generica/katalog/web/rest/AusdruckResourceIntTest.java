package net.generica.katalog.web.rest;

import net.generica.katalog.KatalogApp;

import net.generica.katalog.domain.Ausdruck;
import net.generica.katalog.repository.AusdruckRepository;
import net.generica.katalog.service.AusdruckService;
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
 * Test class for the AusdruckResource REST controller.
 *
 * @see AusdruckResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = KatalogApp.class)
public class AusdruckResourceIntTest {

    private static final String DEFAULT_AUSDRUCK = "AAAAAAAAAA";
    private static final String UPDATED_AUSDRUCK = "BBBBBBBBBB";

    @Autowired
    private AusdruckRepository ausdruckRepository;

    @Mock
    private AusdruckRepository ausdruckRepositoryMock;

    @Mock
    private AusdruckService ausdruckServiceMock;

    @Autowired
    private AusdruckService ausdruckService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restAusdruckMockMvc;

    private Ausdruck ausdruck;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AusdruckResource ausdruckResource = new AusdruckResource(ausdruckService);
        this.restAusdruckMockMvc = MockMvcBuilders.standaloneSetup(ausdruckResource)
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
    public static Ausdruck createEntity() {
        Ausdruck ausdruck = new Ausdruck()
            .ausdruck(DEFAULT_AUSDRUCK);
        return ausdruck;
    }

    @Before
    public void initTest() {
        ausdruckRepository.deleteAll();
        ausdruck = createEntity();
    }

    @Test
    public void createAusdruck() throws Exception {
        int databaseSizeBeforeCreate = ausdruckRepository.findAll().size();

        // Create the Ausdruck
        restAusdruckMockMvc.perform(post("/api/ausdrucks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ausdruck)))
            .andExpect(status().isCreated());

        // Validate the Ausdruck in the database
        List<Ausdruck> ausdruckList = ausdruckRepository.findAll();
        assertThat(ausdruckList).hasSize(databaseSizeBeforeCreate + 1);
        Ausdruck testAusdruck = ausdruckList.get(ausdruckList.size() - 1);
        assertThat(testAusdruck.getAusdruck()).isEqualTo(DEFAULT_AUSDRUCK);
    }

    @Test
    public void createAusdruckWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ausdruckRepository.findAll().size();

        // Create the Ausdruck with an existing ID
        ausdruck.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restAusdruckMockMvc.perform(post("/api/ausdrucks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ausdruck)))
            .andExpect(status().isBadRequest());

        // Validate the Ausdruck in the database
        List<Ausdruck> ausdruckList = ausdruckRepository.findAll();
        assertThat(ausdruckList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllAusdrucks() throws Exception {
        // Initialize the database
        ausdruckRepository.save(ausdruck);

        // Get all the ausdruckList
        restAusdruckMockMvc.perform(get("/api/ausdrucks?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ausdruck.getId())))
            .andExpect(jsonPath("$.[*].ausdruck").value(hasItem(DEFAULT_AUSDRUCK.toString())));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllAusdrucksWithEagerRelationshipsIsEnabled() throws Exception {
        AusdruckResource ausdruckResource = new AusdruckResource(ausdruckServiceMock);
        when(ausdruckServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        MockMvc restAusdruckMockMvc = MockMvcBuilders.standaloneSetup(ausdruckResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restAusdruckMockMvc.perform(get("/api/ausdrucks?eagerload=true"))
        .andExpect(status().isOk());

        verify(ausdruckServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllAusdrucksWithEagerRelationshipsIsNotEnabled() throws Exception {
        AusdruckResource ausdruckResource = new AusdruckResource(ausdruckServiceMock);
            when(ausdruckServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));
            MockMvc restAusdruckMockMvc = MockMvcBuilders.standaloneSetup(ausdruckResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restAusdruckMockMvc.perform(get("/api/ausdrucks?eagerload=true"))
        .andExpect(status().isOk());

            verify(ausdruckServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    public void getAusdruck() throws Exception {
        // Initialize the database
        ausdruckRepository.save(ausdruck);

        // Get the ausdruck
        restAusdruckMockMvc.perform(get("/api/ausdrucks/{id}", ausdruck.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(ausdruck.getId()))
            .andExpect(jsonPath("$.ausdruck").value(DEFAULT_AUSDRUCK.toString()));
    }

    @Test
    public void getNonExistingAusdruck() throws Exception {
        // Get the ausdruck
        restAusdruckMockMvc.perform(get("/api/ausdrucks/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateAusdruck() throws Exception {
        // Initialize the database
        ausdruckService.save(ausdruck);

        int databaseSizeBeforeUpdate = ausdruckRepository.findAll().size();

        // Update the ausdruck
        Ausdruck updatedAusdruck = ausdruckRepository.findById(ausdruck.getId()).get();
        updatedAusdruck
            .ausdruck(UPDATED_AUSDRUCK);

        restAusdruckMockMvc.perform(put("/api/ausdrucks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedAusdruck)))
            .andExpect(status().isOk());

        // Validate the Ausdruck in the database
        List<Ausdruck> ausdruckList = ausdruckRepository.findAll();
        assertThat(ausdruckList).hasSize(databaseSizeBeforeUpdate);
        Ausdruck testAusdruck = ausdruckList.get(ausdruckList.size() - 1);
        assertThat(testAusdruck.getAusdruck()).isEqualTo(UPDATED_AUSDRUCK);
    }

    @Test
    public void updateNonExistingAusdruck() throws Exception {
        int databaseSizeBeforeUpdate = ausdruckRepository.findAll().size();

        // Create the Ausdruck

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAusdruckMockMvc.perform(put("/api/ausdrucks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ausdruck)))
            .andExpect(status().isBadRequest());

        // Validate the Ausdruck in the database
        List<Ausdruck> ausdruckList = ausdruckRepository.findAll();
        assertThat(ausdruckList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteAusdruck() throws Exception {
        // Initialize the database
        ausdruckService.save(ausdruck);

        int databaseSizeBeforeDelete = ausdruckRepository.findAll().size();

        // Delete the ausdruck
        restAusdruckMockMvc.perform(delete("/api/ausdrucks/{id}", ausdruck.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Ausdruck> ausdruckList = ausdruckRepository.findAll();
        assertThat(ausdruckList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Ausdruck.class);
        Ausdruck ausdruck1 = new Ausdruck();
        ausdruck1.setId("id1");
        Ausdruck ausdruck2 = new Ausdruck();
        ausdruck2.setId(ausdruck1.getId());
        assertThat(ausdruck1).isEqualTo(ausdruck2);
        ausdruck2.setId("id2");
        assertThat(ausdruck1).isNotEqualTo(ausdruck2);
        ausdruck1.setId(null);
        assertThat(ausdruck1).isNotEqualTo(ausdruck2);
    }
}
