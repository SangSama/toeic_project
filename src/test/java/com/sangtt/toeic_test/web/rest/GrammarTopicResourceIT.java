package com.sangtt.toeic_test.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sangtt.toeic_test.IntegrationTest;
import com.sangtt.toeic_test.domain.GrammarTopic;
import com.sangtt.toeic_test.repository.GrammarTopicRepository;
import com.sangtt.toeic_test.service.dto.GrammarTopicDTO;
import com.sangtt.toeic_test.service.mapper.GrammarTopicMapper;
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
 * Integration tests for the {@link GrammarTopicResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class GrammarTopicResourceIT {

    private static final String DEFAULT_NAME_TOPIC = "AAAAAAAAAA";
    private static final String UPDATED_NAME_TOPIC = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Long DEFAULT_VIEW = 1L;
    private static final Long UPDATED_VIEW = 2L;

    private static final Long DEFAULT_TEST = 1L;
    private static final Long UPDATED_TEST = 2L;

    private static final Long DEFAULT_LEVEL = 1L;
    private static final Long UPDATED_LEVEL = 2L;

    private static final String DEFAULT_FILE_PRACTICE = "AAAAAAAAAA";
    private static final String UPDATED_FILE_PRACTICE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CREATED_AT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_AT = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_UPDATED_AT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_AT = LocalDate.now(ZoneId.systemDefault());

    private static final String ENTITY_API_URL = "/api/grammar-topics";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private GrammarTopicRepository grammarTopicRepository;

    @Autowired
    private GrammarTopicMapper grammarTopicMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restGrammarTopicMockMvc;

    private GrammarTopic grammarTopic;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GrammarTopic createEntity(EntityManager em) {
        GrammarTopic grammarTopic = new GrammarTopic()
            .nameTopic(DEFAULT_NAME_TOPIC)
            .description(DEFAULT_DESCRIPTION)
            .view(DEFAULT_VIEW)
            .test(DEFAULT_TEST)
            .level(DEFAULT_LEVEL)
            .filePractice(DEFAULT_FILE_PRACTICE)
            .createdAt(DEFAULT_CREATED_AT)
            .updatedAt(DEFAULT_UPDATED_AT);
        return grammarTopic;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GrammarTopic createUpdatedEntity(EntityManager em) {
        GrammarTopic grammarTopic = new GrammarTopic()
            .nameTopic(UPDATED_NAME_TOPIC)
            .description(UPDATED_DESCRIPTION)
            .view(UPDATED_VIEW)
            .test(UPDATED_TEST)
            .level(UPDATED_LEVEL)
            .filePractice(UPDATED_FILE_PRACTICE)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);
        return grammarTopic;
    }

    @BeforeEach
    public void initTest() {
        grammarTopic = createEntity(em);
    }

    @Test
    @Transactional
    void createGrammarTopic() throws Exception {
        int databaseSizeBeforeCreate = grammarTopicRepository.findAll().size();
        // Create the GrammarTopic
        GrammarTopicDTO grammarTopicDTO = grammarTopicMapper.toDto(grammarTopic);
        restGrammarTopicMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(grammarTopicDTO))
            )
            .andExpect(status().isCreated());

        // Validate the GrammarTopic in the database
        List<GrammarTopic> grammarTopicList = grammarTopicRepository.findAll();
        assertThat(grammarTopicList).hasSize(databaseSizeBeforeCreate + 1);
        GrammarTopic testGrammarTopic = grammarTopicList.get(grammarTopicList.size() - 1);
        assertThat(testGrammarTopic.getNameTopic()).isEqualTo(DEFAULT_NAME_TOPIC);
        assertThat(testGrammarTopic.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testGrammarTopic.getView()).isEqualTo(DEFAULT_VIEW);
        assertThat(testGrammarTopic.getTest()).isEqualTo(DEFAULT_TEST);
        assertThat(testGrammarTopic.getLevel()).isEqualTo(DEFAULT_LEVEL);
        assertThat(testGrammarTopic.getFilePractice()).isEqualTo(DEFAULT_FILE_PRACTICE);
        assertThat(testGrammarTopic.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testGrammarTopic.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
    }

    @Test
    @Transactional
    void createGrammarTopicWithExistingId() throws Exception {
        // Create the GrammarTopic with an existing ID
        grammarTopic.setId(1L);
        GrammarTopicDTO grammarTopicDTO = grammarTopicMapper.toDto(grammarTopic);

        int databaseSizeBeforeCreate = grammarTopicRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restGrammarTopicMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(grammarTopicDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the GrammarTopic in the database
        List<GrammarTopic> grammarTopicList = grammarTopicRepository.findAll();
        assertThat(grammarTopicList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkNameTopicIsRequired() throws Exception {
        int databaseSizeBeforeTest = grammarTopicRepository.findAll().size();
        // set the field null
        grammarTopic.setNameTopic(null);

        // Create the GrammarTopic, which fails.
        GrammarTopicDTO grammarTopicDTO = grammarTopicMapper.toDto(grammarTopic);

        restGrammarTopicMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(grammarTopicDTO))
            )
            .andExpect(status().isBadRequest());

        List<GrammarTopic> grammarTopicList = grammarTopicRepository.findAll();
        assertThat(grammarTopicList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkViewIsRequired() throws Exception {
        int databaseSizeBeforeTest = grammarTopicRepository.findAll().size();
        // set the field null
        grammarTopic.setView(null);

        // Create the GrammarTopic, which fails.
        GrammarTopicDTO grammarTopicDTO = grammarTopicMapper.toDto(grammarTopic);

        restGrammarTopicMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(grammarTopicDTO))
            )
            .andExpect(status().isBadRequest());

        List<GrammarTopic> grammarTopicList = grammarTopicRepository.findAll();
        assertThat(grammarTopicList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTestIsRequired() throws Exception {
        int databaseSizeBeforeTest = grammarTopicRepository.findAll().size();
        // set the field null
        grammarTopic.setTest(null);

        // Create the GrammarTopic, which fails.
        GrammarTopicDTO grammarTopicDTO = grammarTopicMapper.toDto(grammarTopic);

        restGrammarTopicMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(grammarTopicDTO))
            )
            .andExpect(status().isBadRequest());

        List<GrammarTopic> grammarTopicList = grammarTopicRepository.findAll();
        assertThat(grammarTopicList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = grammarTopicRepository.findAll().size();
        // set the field null
        grammarTopic.setCreatedAt(null);

        // Create the GrammarTopic, which fails.
        GrammarTopicDTO grammarTopicDTO = grammarTopicMapper.toDto(grammarTopic);

        restGrammarTopicMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(grammarTopicDTO))
            )
            .andExpect(status().isBadRequest());

        List<GrammarTopic> grammarTopicList = grammarTopicRepository.findAll();
        assertThat(grammarTopicList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkUpdatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = grammarTopicRepository.findAll().size();
        // set the field null
        grammarTopic.setUpdatedAt(null);

        // Create the GrammarTopic, which fails.
        GrammarTopicDTO grammarTopicDTO = grammarTopicMapper.toDto(grammarTopic);

        restGrammarTopicMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(grammarTopicDTO))
            )
            .andExpect(status().isBadRequest());

        List<GrammarTopic> grammarTopicList = grammarTopicRepository.findAll();
        assertThat(grammarTopicList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllGrammarTopics() throws Exception {
        // Initialize the database
        grammarTopicRepository.saveAndFlush(grammarTopic);

        // Get all the grammarTopicList
        restGrammarTopicMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(grammarTopic.getId().intValue())))
            .andExpect(jsonPath("$.[*].nameTopic").value(hasItem(DEFAULT_NAME_TOPIC)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].view").value(hasItem(DEFAULT_VIEW.intValue())))
            .andExpect(jsonPath("$.[*].test").value(hasItem(DEFAULT_TEST.intValue())))
            .andExpect(jsonPath("$.[*].level").value(hasItem(DEFAULT_LEVEL.intValue())))
            .andExpect(jsonPath("$.[*].filePractice").value(hasItem(DEFAULT_FILE_PRACTICE)))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(DEFAULT_UPDATED_AT.toString())));
    }

    @Test
    @Transactional
    void getGrammarTopic() throws Exception {
        // Initialize the database
        grammarTopicRepository.saveAndFlush(grammarTopic);

        // Get the grammarTopic
        restGrammarTopicMockMvc
            .perform(get(ENTITY_API_URL_ID, grammarTopic.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(grammarTopic.getId().intValue()))
            .andExpect(jsonPath("$.nameTopic").value(DEFAULT_NAME_TOPIC))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.view").value(DEFAULT_VIEW.intValue()))
            .andExpect(jsonPath("$.test").value(DEFAULT_TEST.intValue()))
            .andExpect(jsonPath("$.level").value(DEFAULT_LEVEL.intValue()))
            .andExpect(jsonPath("$.filePractice").value(DEFAULT_FILE_PRACTICE))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()))
            .andExpect(jsonPath("$.updatedAt").value(DEFAULT_UPDATED_AT.toString()));
    }

    @Test
    @Transactional
    void getNonExistingGrammarTopic() throws Exception {
        // Get the grammarTopic
        restGrammarTopicMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewGrammarTopic() throws Exception {
        // Initialize the database
        grammarTopicRepository.saveAndFlush(grammarTopic);

        int databaseSizeBeforeUpdate = grammarTopicRepository.findAll().size();

        // Update the grammarTopic
        GrammarTopic updatedGrammarTopic = grammarTopicRepository.findById(grammarTopic.getId()).get();
        // Disconnect from session so that the updates on updatedGrammarTopic are not directly saved in db
        em.detach(updatedGrammarTopic);
        updatedGrammarTopic
            .nameTopic(UPDATED_NAME_TOPIC)
            .description(UPDATED_DESCRIPTION)
            .view(UPDATED_VIEW)
            .test(UPDATED_TEST)
            .level(UPDATED_LEVEL)
            .filePractice(UPDATED_FILE_PRACTICE)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);
        GrammarTopicDTO grammarTopicDTO = grammarTopicMapper.toDto(updatedGrammarTopic);

        restGrammarTopicMockMvc
            .perform(
                put(ENTITY_API_URL_ID, grammarTopicDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(grammarTopicDTO))
            )
            .andExpect(status().isOk());

        // Validate the GrammarTopic in the database
        List<GrammarTopic> grammarTopicList = grammarTopicRepository.findAll();
        assertThat(grammarTopicList).hasSize(databaseSizeBeforeUpdate);
        GrammarTopic testGrammarTopic = grammarTopicList.get(grammarTopicList.size() - 1);
        assertThat(testGrammarTopic.getNameTopic()).isEqualTo(UPDATED_NAME_TOPIC);
        assertThat(testGrammarTopic.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testGrammarTopic.getView()).isEqualTo(UPDATED_VIEW);
        assertThat(testGrammarTopic.getTest()).isEqualTo(UPDATED_TEST);
        assertThat(testGrammarTopic.getLevel()).isEqualTo(UPDATED_LEVEL);
        assertThat(testGrammarTopic.getFilePractice()).isEqualTo(UPDATED_FILE_PRACTICE);
        assertThat(testGrammarTopic.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testGrammarTopic.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
    }

    @Test
    @Transactional
    void putNonExistingGrammarTopic() throws Exception {
        int databaseSizeBeforeUpdate = grammarTopicRepository.findAll().size();
        grammarTopic.setId(count.incrementAndGet());

        // Create the GrammarTopic
        GrammarTopicDTO grammarTopicDTO = grammarTopicMapper.toDto(grammarTopic);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGrammarTopicMockMvc
            .perform(
                put(ENTITY_API_URL_ID, grammarTopicDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(grammarTopicDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the GrammarTopic in the database
        List<GrammarTopic> grammarTopicList = grammarTopicRepository.findAll();
        assertThat(grammarTopicList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchGrammarTopic() throws Exception {
        int databaseSizeBeforeUpdate = grammarTopicRepository.findAll().size();
        grammarTopic.setId(count.incrementAndGet());

        // Create the GrammarTopic
        GrammarTopicDTO grammarTopicDTO = grammarTopicMapper.toDto(grammarTopic);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restGrammarTopicMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(grammarTopicDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the GrammarTopic in the database
        List<GrammarTopic> grammarTopicList = grammarTopicRepository.findAll();
        assertThat(grammarTopicList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamGrammarTopic() throws Exception {
        int databaseSizeBeforeUpdate = grammarTopicRepository.findAll().size();
        grammarTopic.setId(count.incrementAndGet());

        // Create the GrammarTopic
        GrammarTopicDTO grammarTopicDTO = grammarTopicMapper.toDto(grammarTopic);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restGrammarTopicMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(grammarTopicDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the GrammarTopic in the database
        List<GrammarTopic> grammarTopicList = grammarTopicRepository.findAll();
        assertThat(grammarTopicList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateGrammarTopicWithPatch() throws Exception {
        // Initialize the database
        grammarTopicRepository.saveAndFlush(grammarTopic);

        int databaseSizeBeforeUpdate = grammarTopicRepository.findAll().size();

        // Update the grammarTopic using partial update
        GrammarTopic partialUpdatedGrammarTopic = new GrammarTopic();
        partialUpdatedGrammarTopic.setId(grammarTopic.getId());

        partialUpdatedGrammarTopic
            .nameTopic(UPDATED_NAME_TOPIC)
            .description(UPDATED_DESCRIPTION)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);

        restGrammarTopicMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedGrammarTopic.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedGrammarTopic))
            )
            .andExpect(status().isOk());

        // Validate the GrammarTopic in the database
        List<GrammarTopic> grammarTopicList = grammarTopicRepository.findAll();
        assertThat(grammarTopicList).hasSize(databaseSizeBeforeUpdate);
        GrammarTopic testGrammarTopic = grammarTopicList.get(grammarTopicList.size() - 1);
        assertThat(testGrammarTopic.getNameTopic()).isEqualTo(UPDATED_NAME_TOPIC);
        assertThat(testGrammarTopic.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testGrammarTopic.getView()).isEqualTo(DEFAULT_VIEW);
        assertThat(testGrammarTopic.getTest()).isEqualTo(DEFAULT_TEST);
        assertThat(testGrammarTopic.getLevel()).isEqualTo(DEFAULT_LEVEL);
        assertThat(testGrammarTopic.getFilePractice()).isEqualTo(DEFAULT_FILE_PRACTICE);
        assertThat(testGrammarTopic.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testGrammarTopic.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
    }

    @Test
    @Transactional
    void fullUpdateGrammarTopicWithPatch() throws Exception {
        // Initialize the database
        grammarTopicRepository.saveAndFlush(grammarTopic);

        int databaseSizeBeforeUpdate = grammarTopicRepository.findAll().size();

        // Update the grammarTopic using partial update
        GrammarTopic partialUpdatedGrammarTopic = new GrammarTopic();
        partialUpdatedGrammarTopic.setId(grammarTopic.getId());

        partialUpdatedGrammarTopic
            .nameTopic(UPDATED_NAME_TOPIC)
            .description(UPDATED_DESCRIPTION)
            .view(UPDATED_VIEW)
            .test(UPDATED_TEST)
            .level(UPDATED_LEVEL)
            .filePractice(UPDATED_FILE_PRACTICE)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);

        restGrammarTopicMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedGrammarTopic.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedGrammarTopic))
            )
            .andExpect(status().isOk());

        // Validate the GrammarTopic in the database
        List<GrammarTopic> grammarTopicList = grammarTopicRepository.findAll();
        assertThat(grammarTopicList).hasSize(databaseSizeBeforeUpdate);
        GrammarTopic testGrammarTopic = grammarTopicList.get(grammarTopicList.size() - 1);
        assertThat(testGrammarTopic.getNameTopic()).isEqualTo(UPDATED_NAME_TOPIC);
        assertThat(testGrammarTopic.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testGrammarTopic.getView()).isEqualTo(UPDATED_VIEW);
        assertThat(testGrammarTopic.getTest()).isEqualTo(UPDATED_TEST);
        assertThat(testGrammarTopic.getLevel()).isEqualTo(UPDATED_LEVEL);
        assertThat(testGrammarTopic.getFilePractice()).isEqualTo(UPDATED_FILE_PRACTICE);
        assertThat(testGrammarTopic.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testGrammarTopic.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
    }

    @Test
    @Transactional
    void patchNonExistingGrammarTopic() throws Exception {
        int databaseSizeBeforeUpdate = grammarTopicRepository.findAll().size();
        grammarTopic.setId(count.incrementAndGet());

        // Create the GrammarTopic
        GrammarTopicDTO grammarTopicDTO = grammarTopicMapper.toDto(grammarTopic);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGrammarTopicMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, grammarTopicDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(grammarTopicDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the GrammarTopic in the database
        List<GrammarTopic> grammarTopicList = grammarTopicRepository.findAll();
        assertThat(grammarTopicList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchGrammarTopic() throws Exception {
        int databaseSizeBeforeUpdate = grammarTopicRepository.findAll().size();
        grammarTopic.setId(count.incrementAndGet());

        // Create the GrammarTopic
        GrammarTopicDTO grammarTopicDTO = grammarTopicMapper.toDto(grammarTopic);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restGrammarTopicMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(grammarTopicDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the GrammarTopic in the database
        List<GrammarTopic> grammarTopicList = grammarTopicRepository.findAll();
        assertThat(grammarTopicList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamGrammarTopic() throws Exception {
        int databaseSizeBeforeUpdate = grammarTopicRepository.findAll().size();
        grammarTopic.setId(count.incrementAndGet());

        // Create the GrammarTopic
        GrammarTopicDTO grammarTopicDTO = grammarTopicMapper.toDto(grammarTopic);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restGrammarTopicMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(grammarTopicDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the GrammarTopic in the database
        List<GrammarTopic> grammarTopicList = grammarTopicRepository.findAll();
        assertThat(grammarTopicList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteGrammarTopic() throws Exception {
        // Initialize the database
        grammarTopicRepository.saveAndFlush(grammarTopic);

        int databaseSizeBeforeDelete = grammarTopicRepository.findAll().size();

        // Delete the grammarTopic
        restGrammarTopicMockMvc
            .perform(delete(ENTITY_API_URL_ID, grammarTopic.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<GrammarTopic> grammarTopicList = grammarTopicRepository.findAll();
        assertThat(grammarTopicList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
