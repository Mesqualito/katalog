package net.generica.katalog.web.rest;

import net.generica.katalog.KatalogApp;

import net.generica.katalog.domain.Single;
import net.generica.katalog.repository.SingleRepository;
import net.generica.katalog.service.SingleService;
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
 * Test class for the SingleResource REST controller.
 *
 * @see SingleResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = KatalogApp.class)
public class SingleResourceIntTest {

    private static final String DEFAULT_WORT = "AAAAAAAAAA";
    private static final String UPDATED_WORT = "BBBBBBBBBB";

    @Autowired
    private SingleRepository singleRepository;

    @Mock
    private SingleRepository singleRepositoryMock;

    @Mock
    private SingleService singleServiceMock;

    @Autowired
    private SingleService singleService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restSingleMockMvc;

    private Single single;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SingleResource singleResource = new SingleResource(singleService);
        this.restSingleMockMvc = MockMvcBuilders.standaloneSetup(singleResource)
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
    public static Single createEntity() {
        Single single = new Single()
            .wort(DEFAULT_WORT);
        return single;
    }

    @Before
    public void initTest() {
        singleRepository.deleteAll();
        single = createEntity();
    }

    @Test
    public void createSingle() throws Exception {
        int databaseSizeBeforeCreate = singleRepository.findAll().size();

        // Create the Single
        restSingleMockMvc.perform(post("/api/singles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(single)))
            .andExpect(status().isCreated());

        // Validate the Single in the database
        List<Single> singleList = singleRepository.findAll();
        assertThat(singleList).hasSize(databaseSizeBeforeCreate + 1);
        Single testSingle = singleList.get(singleList.size() - 1);
        assertThat(testSingle.getWort()).isEqualTo(DEFAULT_WORT);
    }

    @Test
    public void createSingleWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = singleRepository.findAll().size();

        // Create the Single with an existing ID
        single.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restSingleMockMvc.perform(post("/api/singles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(single)))
            .andExpect(status().isBadRequest());

        // Validate the Single in the database
        List<Single> singleList = singleRepository.findAll();
        assertThat(singleList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void checkWortIsRequired() throws Exception {
        int databaseSizeBeforeTest = singleRepository.findAll().size();
        // set the field null
        single.setWort(null);

        // Create the Single, which fails.

        restSingleMockMvc.perform(post("/api/singles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(single)))
            .andExpect(status().isBadRequest());

        List<Single> singleList = singleRepository.findAll();
        assertThat(singleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllSingles() throws Exception {
        // Initialize the database
        singleRepository.save(single);

        // Get all the singleList
        restSingleMockMvc.perform(get("/api/singles?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(single.getId())))
            .andExpect(jsonPath("$.[*].wort").value(hasItem(DEFAULT_WORT.toString())));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllSinglesWithEagerRelationshipsIsEnabled() throws Exception {
        SingleResource singleResource = new SingleResource(singleServiceMock);
        when(singleServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        MockMvc restSingleMockMvc = MockMvcBuilders.standaloneSetup(singleResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restSingleMockMvc.perform(get("/api/singles?eagerload=true"))
        .andExpect(status().isOk());

        verify(singleServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllSinglesWithEagerRelationshipsIsNotEnabled() throws Exception {
        SingleResource singleResource = new SingleResource(singleServiceMock);
            when(singleServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));
            MockMvc restSingleMockMvc = MockMvcBuilders.standaloneSetup(singleResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restSingleMockMvc.perform(get("/api/singles?eagerload=true"))
        .andExpect(status().isOk());

            verify(singleServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    public void getSingle() throws Exception {
        // Initialize the database
        singleRepository.save(single);

        // Get the single
        restSingleMockMvc.perform(get("/api/singles/{id}", single.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(single.getId()))
            .andExpect(jsonPath("$.wort").value(DEFAULT_WORT.toString()));
    }

    @Test
    public void getNonExistingSingle() throws Exception {
        // Get the single
        restSingleMockMvc.perform(get("/api/singles/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateSingle() throws Exception {
        // Initialize the database
        singleService.save(single);

        int databaseSizeBeforeUpdate = singleRepository.findAll().size();

        // Update the single
        Single updatedSingle = singleRepository.findById(single.getId()).get();
        updatedSingle
            .wort(UPDATED_WORT);

        restSingleMockMvc.perform(put("/api/singles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSingle)))
            .andExpect(status().isOk());

        // Validate the Single in the database
        List<Single> singleList = singleRepository.findAll();
        assertThat(singleList).hasSize(databaseSizeBeforeUpdate);
        Single testSingle = singleList.get(singleList.size() - 1);
        assertThat(testSingle.getWort()).isEqualTo(UPDATED_WORT);
    }

    @Test
    public void updateNonExistingSingle() throws Exception {
        int databaseSizeBeforeUpdate = singleRepository.findAll().size();

        // Create the Single

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSingleMockMvc.perform(put("/api/singles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(single)))
            .andExpect(status().isBadRequest());

        // Validate the Single in the database
        List<Single> singleList = singleRepository.findAll();
        assertThat(singleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteSingle() throws Exception {
        // Initialize the database
        singleService.save(single);

        int databaseSizeBeforeDelete = singleRepository.findAll().size();

        // Delete the single
        restSingleMockMvc.perform(delete("/api/singles/{id}", single.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Single> singleList = singleRepository.findAll();
        assertThat(singleList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Single.class);
        Single single1 = new Single();
        single1.setId("id1");
        Single single2 = new Single();
        single2.setId(single1.getId());
        assertThat(single1).isEqualTo(single2);
        single2.setId("id2");
        assertThat(single1).isNotEqualTo(single2);
        single1.setId(null);
        assertThat(single1).isNotEqualTo(single2);
    }
}
