package com.sangtt.toeic_test.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sangtt.toeic_test.IntegrationTest;
import com.sangtt.toeic_test.domain.Parts;
import com.sangtt.toeic_test.repository.PartsRepository;
import com.sangtt.toeic_test.service.dto.PartsDTO;
import com.sangtt.toeic_test.service.mapper.PartsMapper;
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
 * Integration tests for the {@link PartsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PartsResourceIT {

    private static final Integer DEFAULT_PART = 1;
    private static final Integer UPDATED_PART = 2;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_EXAMPLE = "AAAAAAAAAA";
    private static final String UPDATED_EXAMPLE = "BBBBBBBBBB";

    private static final String DEFAULT_FILE_LC = "AAAAAAAAAA";
    private static final String UPDATED_FILE_LC = "BBBBBBBBBB";

    private static final Long DEFAULT_VIEW = 1L;
    private static final Long UPDATED_VIEW = 2L;

    private static final Long DEFAULT_TEST = 1L;
    private static final Long UPDATED_TEST = 2L;

    private static final LocalDate DEFAULT_CREATED_AT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_AT = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_UPDATED_AT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_AT = LocalDate.now(ZoneId.systemDefault());

    private static final String ENTITY_API_URL = "/api/parts";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PartsRepository partsRepository;

    @Autowired
    private PartsMapper partsMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPartsMockMvc;

    private Parts parts;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Parts createEntity(EntityManager em) {
        Parts parts = new Parts()
            .part(DEFAULT_PART)
            .description(DEFAULT_DESCRIPTION)
            .example(DEFAULT_EXAMPLE)
            .fileLC(DEFAULT_FILE_LC)
            .view(DEFAULT_VIEW)
            .test(DEFAULT_TEST)
            .createdAt(DEFAULT_CREATED_AT)
            .updatedAt(DEFAULT_UPDATED_AT);
        return parts;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Parts createUpdatedEntity(EntityManager em) {
        Parts parts = new Parts()
            .part(UPDATED_PART)
            .description(UPDATED_DESCRIPTION)
            .example(UPDATED_EXAMPLE)
            .fileLC(UPDATED_FILE_LC)
            .view(UPDATED_VIEW)
            .test(UPDATED_TEST)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);
        return parts;
    }

    @BeforeEach
    public void initTest() {
        parts = createEntity(em);
    }

    @Test
    @Transactional
    void createParts() throws Exception {
        int databaseSizeBeforeCreate = partsRepository.findAll().size();
        // Create the Parts
        PartsDTO partsDTO = partsMapper.toDto(parts);
        restPartsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(partsDTO)))
            .andExpect(status().isCreated());

        // Validate the Parts in the database
        List<Parts> partsList = partsRepository.findAll();
        assertThat(partsList).hasSize(databaseSizeBeforeCreate + 1);
        Parts testParts = partsList.get(partsList.size() - 1);
        assertThat(testParts.getPart()).isEqualTo(DEFAULT_PART);
        assertThat(testParts.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testParts.getExample()).isEqualTo(DEFAULT_EXAMPLE);
        assertThat(testParts.getFileLC()).isEqualTo(DEFAULT_FILE_LC);
        assertThat(testParts.getView()).isEqualTo(DEFAULT_VIEW);
        assertThat(testParts.getTest()).isEqualTo(DEFAULT_TEST);
        assertThat(testParts.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testParts.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
    }

    @Test
    @Transactional
    void createPartsWithExistingId() throws Exception {
        // Create the Parts with an existing ID
        parts.setId(1L);
        PartsDTO partsDTO = partsMapper.toDto(parts);

        int databaseSizeBeforeCreate = partsRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPartsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(partsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Parts in the database
        List<Parts> partsList = partsRepository.findAll();
        assertThat(partsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkPartIsRequired() throws Exception {
        int databaseSizeBeforeTest = partsRepository.findAll().size();
        // set the field null
        parts.setPart(null);

        // Create the Parts, which fails.
        PartsDTO partsDTO = partsMapper.toDto(parts);

        restPartsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(partsDTO)))
            .andExpect(status().isBadRequest());

        List<Parts> partsList = partsRepository.findAll();
        assertThat(partsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDescriptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = partsRepository.findAll().size();
        // set the field null
        parts.setDescription(null);

        // Create the Parts, which fails.
        PartsDTO partsDTO = partsMapper.toDto(parts);

        restPartsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(partsDTO)))
            .andExpect(status().isBadRequest());

        List<Parts> partsList = partsRepository.findAll();
        assertThat(partsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkExampleIsRequired() throws Exception {
        int databaseSizeBeforeTest = partsRepository.findAll().size();
        // set the field null
        parts.setExample(null);

        // Create the Parts, which fails.
        PartsDTO partsDTO = partsMapper.toDto(parts);

        restPartsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(partsDTO)))
            .andExpect(status().isBadRequest());

        List<Parts> partsList = partsRepository.findAll();
        assertThat(partsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkFileLCIsRequired() throws Exception {
        int databaseSizeBeforeTest = partsRepository.findAll().size();
        // set the field null
        parts.setFileLC(null);

        // Create the Parts, which fails.
        PartsDTO partsDTO = partsMapper.toDto(parts);

        restPartsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(partsDTO)))
            .andExpect(status().isBadRequest());

        List<Parts> partsList = partsRepository.findAll();
        assertThat(partsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkViewIsRequired() throws Exception {
        int databaseSizeBeforeTest = partsRepository.findAll().size();
        // set the field null
        parts.setView(null);

        // Create the Parts, which fails.
        PartsDTO partsDTO = partsMapper.toDto(parts);

        restPartsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(partsDTO)))
            .andExpect(status().isBadRequest());

        List<Parts> partsList = partsRepository.findAll();
        assertThat(partsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTestIsRequired() throws Exception {
        int databaseSizeBeforeTest = partsRepository.findAll().size();
        // set the field null
        parts.setTest(null);

        // Create the Parts, which fails.
        PartsDTO partsDTO = partsMapper.toDto(parts);

        restPartsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(partsDTO)))
            .andExpect(status().isBadRequest());

        List<Parts> partsList = partsRepository.findAll();
        assertThat(partsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = partsRepository.findAll().size();
        // set the field null
        parts.setCreatedAt(null);

        // Create the Parts, which fails.
        PartsDTO partsDTO = partsMapper.toDto(parts);

        restPartsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(partsDTO)))
            .andExpect(status().isBadRequest());

        List<Parts> partsList = partsRepository.findAll();
        assertThat(partsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkUpdatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = partsRepository.findAll().size();
        // set the field null
        parts.setUpdatedAt(null);

        // Create the Parts, which fails.
        PartsDTO partsDTO = partsMapper.toDto(parts);

        restPartsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(partsDTO)))
            .andExpect(status().isBadRequest());

        List<Parts> partsList = partsRepository.findAll();
        assertThat(partsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllParts() throws Exception {
        // Initialize the database
        partsRepository.saveAndFlush(parts);

        // Get all the partsList
        restPartsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(parts.getId().intValue())))
            .andExpect(jsonPath("$.[*].part").value(hasItem(DEFAULT_PART)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].example").value(hasItem(DEFAULT_EXAMPLE)))
            .andExpect(jsonPath("$.[*].fileLC").value(hasItem(DEFAULT_FILE_LC)))
            .andExpect(jsonPath("$.[*].view").value(hasItem(DEFAULT_VIEW.intValue())))
            .andExpect(jsonPath("$.[*].test").value(hasItem(DEFAULT_TEST.intValue())))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(DEFAULT_UPDATED_AT.toString())));
    }

    @Test
    @Transactional
    void getParts() throws Exception {
        // Initialize the database
        partsRepository.saveAndFlush(parts);

        // Get the parts
        restPartsMockMvc
            .perform(get(ENTITY_API_URL_ID, parts.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(parts.getId().intValue()))
            .andExpect(jsonPath("$.part").value(DEFAULT_PART))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.example").value(DEFAULT_EXAMPLE))
            .andExpect(jsonPath("$.fileLC").value(DEFAULT_FILE_LC))
            .andExpect(jsonPath("$.view").value(DEFAULT_VIEW.intValue()))
            .andExpect(jsonPath("$.test").value(DEFAULT_TEST.intValue()))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()))
            .andExpect(jsonPath("$.updatedAt").value(DEFAULT_UPDATED_AT.toString()));
    }

    @Test
    @Transactional
    void getNonExistingParts() throws Exception {
        // Get the parts
        restPartsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewParts() throws Exception {
        // Initialize the database
        partsRepository.saveAndFlush(parts);

        int databaseSizeBeforeUpdate = partsRepository.findAll().size();

        // Update the parts
        Parts updatedParts = partsRepository.findById(parts.getId()).get();
        // Disconnect from session so that the updates on updatedParts are not directly saved in db
        em.detach(updatedParts);
        updatedParts
            .part(UPDATED_PART)
            .description(UPDATED_DESCRIPTION)
            .example(UPDATED_EXAMPLE)
            .fileLC(UPDATED_FILE_LC)
            .view(UPDATED_VIEW)
            .test(UPDATED_TEST)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);
        PartsDTO partsDTO = partsMapper.toDto(updatedParts);

        restPartsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, partsDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(partsDTO))
            )
            .andExpect(status().isOk());

        // Validate the Parts in the database
        List<Parts> partsList = partsRepository.findAll();
        assertThat(partsList).hasSize(databaseSizeBeforeUpdate);
        Parts testParts = partsList.get(partsList.size() - 1);
        assertThat(testParts.getPart()).isEqualTo(UPDATED_PART);
        assertThat(testParts.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testParts.getExample()).isEqualTo(UPDATED_EXAMPLE);
        assertThat(testParts.getFileLC()).isEqualTo(UPDATED_FILE_LC);
        assertThat(testParts.getView()).isEqualTo(UPDATED_VIEW);
        assertThat(testParts.getTest()).isEqualTo(UPDATED_TEST);
        assertThat(testParts.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testParts.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
    }

    @Test
    @Transactional
    void putNonExistingParts() throws Exception {
        int databaseSizeBeforeUpdate = partsRepository.findAll().size();
        parts.setId(count.incrementAndGet());

        // Create the Parts
        PartsDTO partsDTO = partsMapper.toDto(parts);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPartsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, partsDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(partsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Parts in the database
        List<Parts> partsList = partsRepository.findAll();
        assertThat(partsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchParts() throws Exception {
        int databaseSizeBeforeUpdate = partsRepository.findAll().size();
        parts.setId(count.incrementAndGet());

        // Create the Parts
        PartsDTO partsDTO = partsMapper.toDto(parts);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPartsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(partsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Parts in the database
        List<Parts> partsList = partsRepository.findAll();
        assertThat(partsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamParts() throws Exception {
        int databaseSizeBeforeUpdate = partsRepository.findAll().size();
        parts.setId(count.incrementAndGet());

        // Create the Parts
        PartsDTO partsDTO = partsMapper.toDto(parts);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPartsMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(partsDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Parts in the database
        List<Parts> partsList = partsRepository.findAll();
        assertThat(partsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdatePartsWithPatch() throws Exception {
        // Initialize the database
        partsRepository.saveAndFlush(parts);

        int databaseSizeBeforeUpdate = partsRepository.findAll().size();

        // Update the parts using partial update
        Parts partialUpdatedParts = new Parts();
        partialUpdatedParts.setId(parts.getId());

        partialUpdatedParts.part(UPDATED_PART).description(UPDATED_DESCRIPTION).fileLC(UPDATED_FILE_LC).updatedAt(UPDATED_UPDATED_AT);

        restPartsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedParts.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedParts))
            )
            .andExpect(status().isOk());

        // Validate the Parts in the database
        List<Parts> partsList = partsRepository.findAll();
        assertThat(partsList).hasSize(databaseSizeBeforeUpdate);
        Parts testParts = partsList.get(partsList.size() - 1);
        assertThat(testParts.getPart()).isEqualTo(UPDATED_PART);
        assertThat(testParts.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testParts.getExample()).isEqualTo(DEFAULT_EXAMPLE);
        assertThat(testParts.getFileLC()).isEqualTo(UPDATED_FILE_LC);
        assertThat(testParts.getView()).isEqualTo(DEFAULT_VIEW);
        assertThat(testParts.getTest()).isEqualTo(DEFAULT_TEST);
        assertThat(testParts.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testParts.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
    }

    @Test
    @Transactional
    void fullUpdatePartsWithPatch() throws Exception {
        // Initialize the database
        partsRepository.saveAndFlush(parts);

        int databaseSizeBeforeUpdate = partsRepository.findAll().size();

        // Update the parts using partial update
        Parts partialUpdatedParts = new Parts();
        partialUpdatedParts.setId(parts.getId());

        partialUpdatedParts
            .part(UPDATED_PART)
            .description(UPDATED_DESCRIPTION)
            .example(UPDATED_EXAMPLE)
            .fileLC(UPDATED_FILE_LC)
            .view(UPDATED_VIEW)
            .test(UPDATED_TEST)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);

        restPartsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedParts.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedParts))
            )
            .andExpect(status().isOk());

        // Validate the Parts in the database
        List<Parts> partsList = partsRepository.findAll();
        assertThat(partsList).hasSize(databaseSizeBeforeUpdate);
        Parts testParts = partsList.get(partsList.size() - 1);
        assertThat(testParts.getPart()).isEqualTo(UPDATED_PART);
        assertThat(testParts.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testParts.getExample()).isEqualTo(UPDATED_EXAMPLE);
        assertThat(testParts.getFileLC()).isEqualTo(UPDATED_FILE_LC);
        assertThat(testParts.getView()).isEqualTo(UPDATED_VIEW);
        assertThat(testParts.getTest()).isEqualTo(UPDATED_TEST);
        assertThat(testParts.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testParts.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
    }

    @Test
    @Transactional
    void patchNonExistingParts() throws Exception {
        int databaseSizeBeforeUpdate = partsRepository.findAll().size();
        parts.setId(count.incrementAndGet());

        // Create the Parts
        PartsDTO partsDTO = partsMapper.toDto(parts);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPartsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partsDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Parts in the database
        List<Parts> partsList = partsRepository.findAll();
        assertThat(partsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchParts() throws Exception {
        int databaseSizeBeforeUpdate = partsRepository.findAll().size();
        parts.setId(count.incrementAndGet());

        // Create the Parts
        PartsDTO partsDTO = partsMapper.toDto(parts);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPartsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Parts in the database
        List<Parts> partsList = partsRepository.findAll();
        assertThat(partsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamParts() throws Exception {
        int databaseSizeBeforeUpdate = partsRepository.findAll().size();
        parts.setId(count.incrementAndGet());

        // Create the Parts
        PartsDTO partsDTO = partsMapper.toDto(parts);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPartsMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(partsDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Parts in the database
        List<Parts> partsList = partsRepository.findAll();
        assertThat(partsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteParts() throws Exception {
        // Initialize the database
        partsRepository.saveAndFlush(parts);

        int databaseSizeBeforeDelete = partsRepository.findAll().size();

        // Delete the parts
        restPartsMockMvc
            .perform(delete(ENTITY_API_URL_ID, parts.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Parts> partsList = partsRepository.findAll();
        assertThat(partsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
