package net.generica.katalog.web.rest;

import net.generica.katalog.KatalogApp;

import net.generica.katalog.domain.Bezeichnung;
import net.generica.katalog.repository.BezeichnungRepository;
import net.generica.katalog.service.BezeichnungService;
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
 * Test class for the BezeichnungResource REST controller.
 *
 * @see BezeichnungResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = KatalogApp.class)
public class BezeichnungResourceIntTest {

    private static final String DEFAULT_BEZEICHNUNG = "AAAAAAAAAA";
    private static final String UPDATED_BEZEICHNUNG = "BBBBBBBBBB";

    @Autowired
    private BezeichnungRepository bezeichnungRepository;

    @Mock
    private BezeichnungRepository bezeichnungRepositoryMock;

    @Mock
    private BezeichnungService bezeichnungServiceMock;

    @Autowired
    private BezeichnungService bezeichnungService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restBezeichnungMockMvc;

    private Bezeichnung bezeichnung;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BezeichnungResource bezeichnungResource = new BezeichnungResource(bezeichnungService);
        this.restBezeichnungMockMvc = MockMvcBuilders.standaloneSetup(bezeichnungResource)
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
    public static Bezeichnung createEntity() {
        Bezeichnung bezeichnung = new Bezeichnung()
            .bezeichnung(DEFAULT_BEZEICHNUNG);
        return bezeichnung;
    }

    @Before
    public void initTest() {
        bezeichnungRepository.deleteAll();
        bezeichnung = createEntity();
    }

    @Test
    public void createBezeichnung() throws Exception {
        int databaseSizeBeforeCreate = bezeichnungRepository.findAll().size();

        // Create the Bezeichnung
        restBezeichnungMockMvc.perform(post("/api/bezeichnungs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bezeichnung)))
            .andExpect(status().isCreated());

        // Validate the Bezeichnung in the database
        List<Bezeichnung> bezeichnungList = bezeichnungRepository.findAll();
        assertThat(bezeichnungList).hasSize(databaseSizeBeforeCreate + 1);
        Bezeichnung testBezeichnung = bezeichnungList.get(bezeichnungList.size() - 1);
        assertThat(testBezeichnung.getBezeichnung()).isEqualTo(DEFAULT_BEZEICHNUNG);
    }

    @Test
    public void createBezeichnungWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = bezeichnungRepository.findAll().size();

        // Create the Bezeichnung with an existing ID
        bezeichnung.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restBezeichnungMockMvc.perform(post("/api/bezeichnungs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bezeichnung)))
            .andExpect(status().isBadRequest());

        // Validate the Bezeichnung in the database
        List<Bezeichnung> bezeichnungList = bezeichnungRepository.findAll();
        assertThat(bezeichnungList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void checkBezeichnungIsRequired() throws Exception {
        int databaseSizeBeforeTest = bezeichnungRepository.findAll().size();
        // set the field null
        bezeichnung.setBezeichnung(null);

        // Create the Bezeichnung, which fails.

        restBezeichnungMockMvc.perform(post("/api/bezeichnungs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bezeichnung)))
            .andExpect(status().isBadRequest());

        List<Bezeichnung> bezeichnungList = bezeichnungRepository.findAll();
        assertThat(bezeichnungList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllBezeichnungs() throws Exception {
        // Initialize the database
        bezeichnungRepository.save(bezeichnung);

        // Get all the bezeichnungList
        restBezeichnungMockMvc.perform(get("/api/bezeichnungs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bezeichnung.getId())))
            .andExpect(jsonPath("$.[*].bezeichnung").value(hasItem(DEFAULT_BEZEICHNUNG.toString())));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllBezeichnungsWithEagerRelationshipsIsEnabled() throws Exception {
        BezeichnungResource bezeichnungResource = new BezeichnungResource(bezeichnungServiceMock);
        when(bezeichnungServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        MockMvc restBezeichnungMockMvc = MockMvcBuilders.standaloneSetup(bezeichnungResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restBezeichnungMockMvc.perform(get("/api/bezeichnungs?eagerload=true"))
        .andExpect(status().isOk());

        verify(bezeichnungServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllBezeichnungsWithEagerRelationshipsIsNotEnabled() throws Exception {
        BezeichnungResource bezeichnungResource = new BezeichnungResource(bezeichnungServiceMock);
            when(bezeichnungServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));
            MockMvc restBezeichnungMockMvc = MockMvcBuilders.standaloneSetup(bezeichnungResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restBezeichnungMockMvc.perform(get("/api/bezeichnungs?eagerload=true"))
        .andExpect(status().isOk());

            verify(bezeichnungServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    public void getBezeichnung() throws Exception {
        // Initialize the database
        bezeichnungRepository.save(bezeichnung);

        // Get the bezeichnung
        restBezeichnungMockMvc.perform(get("/api/bezeichnungs/{id}", bezeichnung.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(bezeichnung.getId()))
            .andExpect(jsonPath("$.bezeichnung").value(DEFAULT_BEZEICHNUNG.toString()));
    }

    @Test
    public void getNonExistingBezeichnung() throws Exception {
        // Get the bezeichnung
        restBezeichnungMockMvc.perform(get("/api/bezeichnungs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateBezeichnung() throws Exception {
        // Initialize the database
        bezeichnungService.save(bezeichnung);

        int databaseSizeBeforeUpdate = bezeichnungRepository.findAll().size();

        // Update the bezeichnung
        Bezeichnung updatedBezeichnung = bezeichnungRepository.findById(bezeichnung.getId()).get();
        updatedBezeichnung
            .bezeichnung(UPDATED_BEZEICHNUNG);

        restBezeichnungMockMvc.perform(put("/api/bezeichnungs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedBezeichnung)))
            .andExpect(status().isOk());

        // Validate the Bezeichnung in the database
        List<Bezeichnung> bezeichnungList = bezeichnungRepository.findAll();
        assertThat(bezeichnungList).hasSize(databaseSizeBeforeUpdate);
        Bezeichnung testBezeichnung = bezeichnungList.get(bezeichnungList.size() - 1);
        assertThat(testBezeichnung.getBezeichnung()).isEqualTo(UPDATED_BEZEICHNUNG);
    }

    @Test
    public void updateNonExistingBezeichnung() throws Exception {
        int databaseSizeBeforeUpdate = bezeichnungRepository.findAll().size();

        // Create the Bezeichnung

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBezeichnungMockMvc.perform(put("/api/bezeichnungs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bezeichnung)))
            .andExpect(status().isBadRequest());

        // Validate the Bezeichnung in the database
        List<Bezeichnung> bezeichnungList = bezeichnungRepository.findAll();
        assertThat(bezeichnungList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteBezeichnung() throws Exception {
        // Initialize the database
        bezeichnungService.save(bezeichnung);

        int databaseSizeBeforeDelete = bezeichnungRepository.findAll().size();

        // Delete the bezeichnung
        restBezeichnungMockMvc.perform(delete("/api/bezeichnungs/{id}", bezeichnung.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Bezeichnung> bezeichnungList = bezeichnungRepository.findAll();
        assertThat(bezeichnungList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Bezeichnung.class);
        Bezeichnung bezeichnung1 = new Bezeichnung();
        bezeichnung1.setId("id1");
        Bezeichnung bezeichnung2 = new Bezeichnung();
        bezeichnung2.setId(bezeichnung1.getId());
        assertThat(bezeichnung1).isEqualTo(bezeichnung2);
        bezeichnung2.setId("id2");
        assertThat(bezeichnung1).isNotEqualTo(bezeichnung2);
        bezeichnung1.setId(null);
        assertThat(bezeichnung1).isNotEqualTo(bezeichnung2);
    }
}
