package com.sangtt.toeic_test.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sangtt.toeic_test.IntegrationTest;
import com.sangtt.toeic_test.domain.Toeics;
import com.sangtt.toeic_test.repository.ToeicsRepository;
import com.sangtt.toeic_test.service.dto.ToeicsDTO;
import com.sangtt.toeic_test.service.mapper.ToeicsMapper;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link ToeicsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ToeicsResourceIT {

    private static final String DEFAULT_NAME_TOEIC = "AAAAAAAAAA";
    private static final String UPDATED_NAME_TOEIC = "BBBBBBBBBB";

    private static final Integer DEFAULT_NUMBER = 1;
    private static final Integer UPDATED_NUMBER = 2;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Long DEFAULT_VIEW = 1L;
    private static final Long UPDATED_VIEW = 2L;

    private static final Long DEFAULT_TEST = 1L;
    private static final Long UPDATED_TEST = 2L;

    private static final String DEFAULT_LINK_DETAIL = "AAAAAAAAAA";
    private static final String UPDATED_LINK_DETAIL = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CREATED_AT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_AT = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_UPDATED_AT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_AT = LocalDate.now(ZoneId.systemDefault());

    private static final String ENTITY_API_URL = "/api/toeics";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ToeicsRepository toeicsRepository;

    @Autowired
    private ToeicsMapper toeicsMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restToeicsMockMvc;

    private Toeics toeics;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Toeics createEntity(EntityManager em) {
        Toeics toeics = new Toeics()
            .nameToeic(DEFAULT_NAME_TOEIC)
            .number(DEFAULT_NUMBER)
            .description(DEFAULT_DESCRIPTION)
            .view(DEFAULT_VIEW)
            .test(DEFAULT_TEST)
            .linkDetail(DEFAULT_LINK_DETAIL)
            .createdAt(DEFAULT_CREATED_AT)
            .updatedAt(DEFAULT_UPDATED_AT);
        return toeics;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Toeics createUpdatedEntity(EntityManager em) {
        Toeics toeics = new Toeics()
            .nameToeic(UPDATED_NAME_TOEIC)
            .number(UPDATED_NUMBER)
            .description(UPDATED_DESCRIPTION)
            .view(UPDATED_VIEW)
            .test(UPDATED_TEST)
            .linkDetail(UPDATED_LINK_DETAIL)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);
        return toeics;
    }

    @BeforeEach
    public void initTest() {
        toeics = createEntity(em);
    }

    @Test
    @Transactional
    void createToeics() throws Exception {
        int databaseSizeBeforeCreate = toeicsRepository.findAll().size();
        // Create the Toeics
        ToeicsDTO toeicsDTO = toeicsMapper.toDto(toeics);
        restToeicsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(toeicsDTO)))
            .andExpect(status().isCreated());

        // Validate the Toeics in the database
        List<Toeics> toeicsList = toeicsRepository.findAll();
        assertThat(toeicsList).hasSize(databaseSizeBeforeCreate + 1);
        Toeics testToeics = toeicsList.get(toeicsList.size() - 1);
        assertThat(testToeics.getNameToeic()).isEqualTo(DEFAULT_NAME_TOEIC);
        assertThat(testToeics.getNumber()).isEqualTo(DEFAULT_NUMBER);
        assertThat(testToeics.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testToeics.getView()).isEqualTo(DEFAULT_VIEW);
        assertThat(testToeics.getTest()).isEqualTo(DEFAULT_TEST);
        assertThat(testToeics.getLinkDetail()).isEqualTo(DEFAULT_LINK_DETAIL);
        assertThat(testToeics.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testToeics.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
    }

    @Test
    @Transactional
    void createToeicsWithExistingId() throws Exception {
        // Create the Toeics with an existing ID
        toeics.setId(1L);
        ToeicsDTO toeicsDTO = toeicsMapper.toDto(toeics);

        int databaseSizeBeforeCreate = toeicsRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restToeicsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(toeicsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Toeics in the database
        List<Toeics> toeicsList = toeicsRepository.findAll();
        assertThat(toeicsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkNameToeicIsRequired() throws Exception {
        int databaseSizeBeforeTest = toeicsRepository.findAll().size();
        // set the field null
        toeics.setNameToeic(null);

        // Create the Toeics, which fails.
        ToeicsDTO toeicsDTO = toeicsMapper.toDto(toeics);

        restToeicsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(toeicsDTO)))
            .andExpect(status().isBadRequest());

        List<Toeics> toeicsList = toeicsRepository.findAll();
        assertThat(toeicsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = toeicsRepository.findAll().size();
        // set the field null
        toeics.setNumber(null);

        // Create the Toeics, which fails.
        ToeicsDTO toeicsDTO = toeicsMapper.toDto(toeics);

        restToeicsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(toeicsDTO)))
            .andExpect(status().isBadRequest());

        List<Toeics> toeicsList = toeicsRepository.findAll();
        assertThat(toeicsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkViewIsRequired() throws Exception {
        int databaseSizeBeforeTest = toeicsRepository.findAll().size();
        // set the field null
        toeics.setView(null);

        // Create the Toeics, which fails.
        ToeicsDTO toeicsDTO = toeicsMapper.toDto(toeics);

        restToeicsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(toeicsDTO)))
            .andExpect(status().isBadRequest());

        List<Toeics> toeicsList = toeicsRepository.findAll();
        assertThat(toeicsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTestIsRequired() throws Exception {
        int databaseSizeBeforeTest = toeicsRepository.findAll().size();
        // set the field null
        toeics.setTest(null);

        // Create the Toeics, which fails.
        ToeicsDTO toeicsDTO = toeicsMapper.toDto(toeics);

        restToeicsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(toeicsDTO)))
            .andExpect(status().isBadRequest());

        List<Toeics> toeicsList = toeicsRepository.findAll();
        assertThat(toeicsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = toeicsRepository.findAll().size();
        // set the field null
        toeics.setCreatedAt(null);

        // Create the Toeics, which fails.
        ToeicsDTO toeicsDTO = toeicsMapper.toDto(toeics);

        restToeicsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(toeicsDTO)))
            .andExpect(status().isBadRequest());

        List<Toeics> toeicsList = toeicsRepository.findAll();
        assertThat(toeicsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkUpdatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = toeicsRepository.findAll().size();
        // set the field null
        toeics.setUpdatedAt(null);

        // Create the Toeics, which fails.
        ToeicsDTO toeicsDTO = toeicsMapper.toDto(toeics);

        restToeicsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(toeicsDTO)))
            .andExpect(status().isBadRequest());

        List<Toeics> toeicsList = toeicsRepository.findAll();
        assertThat(toeicsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllToeics() throws Exception {
        // Initialize the database
        toeicsRepository.saveAndFlush(toeics);

        // Get all the toeicsList
        restToeicsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(toeics.getId().intValue())))
            .andExpect(jsonPath("$.[*].nameToeic").value(hasItem(DEFAULT_NAME_TOEIC)))
            .andExpect(jsonPath("$.[*].number").value(hasItem(DEFAULT_NUMBER)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].view").value(hasItem(DEFAULT_VIEW.intValue())))
            .andExpect(jsonPath("$.[*].test").value(hasItem(DEFAULT_TEST.intValue())))
            .andExpect(jsonPath("$.[*].linkDetail").value(hasItem(DEFAULT_LINK_DETAIL)))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(DEFAULT_UPDATED_AT.toString())));
    }

    @Test
    @Transactional
    void getToeics() throws Exception {
        // Initialize the database
        toeicsRepository.saveAndFlush(toeics);

        // Get the toeics
        restToeicsMockMvc
            .perform(get(ENTITY_API_URL_ID, toeics.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(toeics.getId().intValue()))
            .andExpect(jsonPath("$.nameToeic").value(DEFAULT_NAME_TOEIC))
            .andExpect(jsonPath("$.number").value(DEFAULT_NUMBER))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.view").value(DEFAULT_VIEW.intValue()))
            .andExpect(jsonPath("$.test").value(DEFAULT_TEST.intValue()))
            .andExpect(jsonPath("$.linkDetail").value(DEFAULT_LINK_DETAIL))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()))
            .andExpect(jsonPath("$.updatedAt").value(DEFAULT_UPDATED_AT.toString()));
    }

    @Test
    @Transactional
    void getNonExistingToeics() throws Exception {
        // Get the toeics
        restToeicsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewToeics() throws Exception {
        // Initialize the database
        toeicsRepository.saveAndFlush(toeics);

        int databaseSizeBeforeUpdate = toeicsRepository.findAll().size();

        // Update the toeics
        Toeics updatedToeics = toeicsRepository.findById(toeics.getId()).get();
        // Disconnect from session so that the updates on updatedToeics are not directly saved in db
        em.detach(updatedToeics);
        updatedToeics
            .nameToeic(UPDATED_NAME_TOEIC)
            .number(UPDATED_NUMBER)
            .description(UPDATED_DESCRIPTION)
            .view(UPDATED_VIEW)
            .test(UPDATED_TEST)
            .linkDetail(UPDATED_LINK_DETAIL)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);
        ToeicsDTO toeicsDTO = toeicsMapper.toDto(updatedToeics);

        restToeicsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, toeicsDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(toeicsDTO))
            )
            .andExpect(status().isOk());

        // Validate the Toeics in the database
        List<Toeics> toeicsList = toeicsRepository.findAll();
        assertThat(toeicsList).hasSize(databaseSizeBeforeUpdate);
        Toeics testToeics = toeicsList.get(toeicsList.size() - 1);
        assertThat(testToeics.getNameToeic()).isEqualTo(UPDATED_NAME_TOEIC);
        assertThat(testToeics.getNumber()).isEqualTo(UPDATED_NUMBER);
        assertThat(testToeics.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testToeics.getView()).isEqualTo(UPDATED_VIEW);
        assertThat(testToeics.getTest()).isEqualTo(UPDATED_TEST);
        assertThat(testToeics.getLinkDetail()).isEqualTo(UPDATED_LINK_DETAIL);
        assertThat(testToeics.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testToeics.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
    }

    @Test
    @Transactional
    void putNonExistingToeics() throws Exception {
        int databaseSizeBeforeUpdate = toeicsRepository.findAll().size();
        toeics.setId(count.incrementAndGet());

        // Create the Toeics
        ToeicsDTO toeicsDTO = toeicsMapper.toDto(toeics);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restToeicsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, toeicsDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(toeicsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Toeics in the database
        List<Toeics> toeicsList = toeicsRepository.findAll();
        assertThat(toeicsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchToeics() throws Exception {
        int databaseSizeBeforeUpdate = toeicsRepository.findAll().size();
        toeics.setId(count.incrementAndGet());

        // Create the Toeics
        ToeicsDTO toeicsDTO = toeicsMapper.toDto(toeics);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restToeicsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(toeicsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Toeics in the database
        List<Toeics> toeicsList = toeicsRepository.findAll();
        assertThat(toeicsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamToeics() throws Exception {
        int databaseSizeBeforeUpdate = toeicsRepository.findAll().size();
        toeics.setId(count.incrementAndGet());

        // Create the Toeics
        ToeicsDTO toeicsDTO = toeicsMapper.toDto(toeics);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restToeicsMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(toeicsDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Toeics in the database
        List<Toeics> toeicsList = toeicsRepository.findAll();
        assertThat(toeicsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateToeicsWithPatch() throws Exception {
        // Initialize the database
        toeicsRepository.saveAndFlush(toeics);

        int databaseSizeBeforeUpdate = toeicsRepository.findAll().size();

        // Update the toeics using partial update
        Toeics partialUpdatedToeics = new Toeics();
        partialUpdatedToeics.setId(toeics.getId());

        partialUpdatedToeics.nameToeic(UPDATED_NAME_TOEIC);

        restToeicsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedToeics.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedToeics))
            )
            .andExpect(status().isOk());

        // Validate the Toeics in the database
        List<Toeics> toeicsList = toeicsRepository.findAll();
        assertThat(toeicsList).hasSize(databaseSizeBeforeUpdate);
        Toeics testToeics = toeicsList.get(toeicsList.size() - 1);
        assertThat(testToeics.getNameToeic()).isEqualTo(UPDATED_NAME_TOEIC);
        assertThat(testToeics.getNumber()).isEqualTo(DEFAULT_NUMBER);
        assertThat(testToeics.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testToeics.getView()).isEqualTo(DEFAULT_VIEW);
        assertThat(testToeics.getTest()).isEqualTo(DEFAULT_TEST);
        assertThat(testToeics.getLinkDetail()).isEqualTo(DEFAULT_LINK_DETAIL);
        assertThat(testToeics.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testToeics.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
    }

    @Test
    @Transactional
    void fullUpdateToeicsWithPatch() throws Exception {
        // Initialize the database
        toeicsRepository.saveAndFlush(toeics);

        int databaseSizeBeforeUpdate = toeicsRepository.findAll().size();

        // Update the toeics using partial update
        Toeics partialUpdatedToeics = new Toeics();
        partialUpdatedToeics.setId(toeics.getId());

        partialUpdatedToeics
            .nameToeic(UPDATED_NAME_TOEIC)
            .number(UPDATED_NUMBER)
            .description(UPDATED_DESCRIPTION)
            .view(UPDATED_VIEW)
            .test(UPDATED_TEST)
            .linkDetail(UPDATED_LINK_DETAIL)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);

        restToeicsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedToeics.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedToeics))
            )
            .andExpect(status().isOk());

        // Validate the Toeics in the database
        List<Toeics> toeicsList = toeicsRepository.findAll();
        assertThat(toeicsList).hasSize(databaseSizeBeforeUpdate);
        Toeics testToeics = toeicsList.get(toeicsList.size() - 1);
        assertThat(testToeics.getNameToeic()).isEqualTo(UPDATED_NAME_TOEIC);
        assertThat(testToeics.getNumber()).isEqualTo(UPDATED_NUMBER);
        assertThat(testToeics.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testToeics.getView()).isEqualTo(UPDATED_VIEW);
        assertThat(testToeics.getTest()).isEqualTo(UPDATED_TEST);
        assertThat(testToeics.getLinkDetail()).isEqualTo(UPDATED_LINK_DETAIL);
        assertThat(testToeics.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testToeics.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
    }

    @Test
    @Transactional
    void patchNonExistingToeics() throws Exception {
        int databaseSizeBeforeUpdate = toeicsRepository.findAll().size();
        toeics.setId(count.incrementAndGet());

        // Create the Toeics
        ToeicsDTO toeicsDTO = toeicsMapper.toDto(toeics);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restToeicsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, toeicsDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(toeicsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Toeics in the database
        List<Toeics> toeicsList = toeicsRepository.findAll();
        assertThat(toeicsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchToeics() throws Exception {
        int databaseSizeBeforeUpdate = toeicsRepository.findAll().size();
        toeics.setId(count.incrementAndGet());

        // Create the Toeics
        ToeicsDTO toeicsDTO = toeicsMapper.toDto(toeics);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restToeicsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(toeicsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Toeics in the database
        List<Toeics> toeicsList = toeicsRepository.findAll();
        assertThat(toeicsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamToeics() throws Exception {
        int databaseSizeBeforeUpdate = toeicsRepository.findAll().size();
        toeics.setId(count.incrementAndGet());

        // Create the Toeics
        ToeicsDTO toeicsDTO = toeicsMapper.toDto(toeics);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restToeicsMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(toeicsDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Toeics in the database
        List<Toeics> toeicsList = toeicsRepository.findAll();
        assertThat(toeicsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteToeics() throws Exception {
        // Initialize the database
        toeicsRepository.saveAndFlush(toeics);

        int databaseSizeBeforeDelete = toeicsRepository.findAll().size();

        // Delete the toeics
        restToeicsMockMvc
            .perform(delete(ENTITY_API_URL_ID, toeics.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Toeics> toeicsList = toeicsRepository.findAll();
        assertThat(toeicsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
