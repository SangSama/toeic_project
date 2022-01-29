package com.sangtt.toeic_test.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sangtt.toeic_test.IntegrationTest;
import com.sangtt.toeic_test.domain.VocabularyTopic;
import com.sangtt.toeic_test.repository.VocabularyTopicRepository;
import com.sangtt.toeic_test.service.dto.VocabularyTopicDTO;
import com.sangtt.toeic_test.service.mapper.VocabularyTopicMapper;
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
 * Integration tests for the {@link VocabularyTopicResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class VocabularyTopicResourceIT {

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

    private static final LocalDate DEFAULT_CREATED_AT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_AT = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_UPDATED_AT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_AT = LocalDate.now(ZoneId.systemDefault());

    private static final String ENTITY_API_URL = "/api/vocabulary-topics";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private VocabularyTopicRepository vocabularyTopicRepository;

    @Autowired
    private VocabularyTopicMapper vocabularyTopicMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restVocabularyTopicMockMvc;

    private VocabularyTopic vocabularyTopic;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static VocabularyTopic createEntity(EntityManager em) {
        VocabularyTopic vocabularyTopic = new VocabularyTopic()
            .nameTopic(DEFAULT_NAME_TOPIC)
            .description(DEFAULT_DESCRIPTION)
            .view(DEFAULT_VIEW)
            .test(DEFAULT_TEST)
            .level(DEFAULT_LEVEL)
            .createdAt(DEFAULT_CREATED_AT)
            .updatedAt(DEFAULT_UPDATED_AT);
        return vocabularyTopic;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static VocabularyTopic createUpdatedEntity(EntityManager em) {
        VocabularyTopic vocabularyTopic = new VocabularyTopic()
            .nameTopic(UPDATED_NAME_TOPIC)
            .description(UPDATED_DESCRIPTION)
            .view(UPDATED_VIEW)
            .test(UPDATED_TEST)
            .level(UPDATED_LEVEL)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);
        return vocabularyTopic;
    }

    @BeforeEach
    public void initTest() {
        vocabularyTopic = createEntity(em);
    }

    @Test
    @Transactional
    void createVocabularyTopic() throws Exception {
        int databaseSizeBeforeCreate = vocabularyTopicRepository.findAll().size();
        // Create the VocabularyTopic
        VocabularyTopicDTO vocabularyTopicDTO = vocabularyTopicMapper.toDto(vocabularyTopic);
        restVocabularyTopicMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(vocabularyTopicDTO))
            )
            .andExpect(status().isCreated());

        // Validate the VocabularyTopic in the database
        List<VocabularyTopic> vocabularyTopicList = vocabularyTopicRepository.findAll();
        assertThat(vocabularyTopicList).hasSize(databaseSizeBeforeCreate + 1);
        VocabularyTopic testVocabularyTopic = vocabularyTopicList.get(vocabularyTopicList.size() - 1);
        assertThat(testVocabularyTopic.getNameTopic()).isEqualTo(DEFAULT_NAME_TOPIC);
        assertThat(testVocabularyTopic.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testVocabularyTopic.getView()).isEqualTo(DEFAULT_VIEW);
        assertThat(testVocabularyTopic.getTest()).isEqualTo(DEFAULT_TEST);
        assertThat(testVocabularyTopic.getLevel()).isEqualTo(DEFAULT_LEVEL);
        assertThat(testVocabularyTopic.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testVocabularyTopic.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
    }

    @Test
    @Transactional
    void createVocabularyTopicWithExistingId() throws Exception {
        // Create the VocabularyTopic with an existing ID
        vocabularyTopic.setId(1L);
        VocabularyTopicDTO vocabularyTopicDTO = vocabularyTopicMapper.toDto(vocabularyTopic);

        int databaseSizeBeforeCreate = vocabularyTopicRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restVocabularyTopicMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(vocabularyTopicDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the VocabularyTopic in the database
        List<VocabularyTopic> vocabularyTopicList = vocabularyTopicRepository.findAll();
        assertThat(vocabularyTopicList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkNameTopicIsRequired() throws Exception {
        int databaseSizeBeforeTest = vocabularyTopicRepository.findAll().size();
        // set the field null
        vocabularyTopic.setNameTopic(null);

        // Create the VocabularyTopic, which fails.
        VocabularyTopicDTO vocabularyTopicDTO = vocabularyTopicMapper.toDto(vocabularyTopic);

        restVocabularyTopicMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(vocabularyTopicDTO))
            )
            .andExpect(status().isBadRequest());

        List<VocabularyTopic> vocabularyTopicList = vocabularyTopicRepository.findAll();
        assertThat(vocabularyTopicList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkViewIsRequired() throws Exception {
        int databaseSizeBeforeTest = vocabularyTopicRepository.findAll().size();
        // set the field null
        vocabularyTopic.setView(null);

        // Create the VocabularyTopic, which fails.
        VocabularyTopicDTO vocabularyTopicDTO = vocabularyTopicMapper.toDto(vocabularyTopic);

        restVocabularyTopicMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(vocabularyTopicDTO))
            )
            .andExpect(status().isBadRequest());

        List<VocabularyTopic> vocabularyTopicList = vocabularyTopicRepository.findAll();
        assertThat(vocabularyTopicList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTestIsRequired() throws Exception {
        int databaseSizeBeforeTest = vocabularyTopicRepository.findAll().size();
        // set the field null
        vocabularyTopic.setTest(null);

        // Create the VocabularyTopic, which fails.
        VocabularyTopicDTO vocabularyTopicDTO = vocabularyTopicMapper.toDto(vocabularyTopic);

        restVocabularyTopicMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(vocabularyTopicDTO))
            )
            .andExpect(status().isBadRequest());

        List<VocabularyTopic> vocabularyTopicList = vocabularyTopicRepository.findAll();
        assertThat(vocabularyTopicList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = vocabularyTopicRepository.findAll().size();
        // set the field null
        vocabularyTopic.setCreatedAt(null);

        // Create the VocabularyTopic, which fails.
        VocabularyTopicDTO vocabularyTopicDTO = vocabularyTopicMapper.toDto(vocabularyTopic);

        restVocabularyTopicMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(vocabularyTopicDTO))
            )
            .andExpect(status().isBadRequest());

        List<VocabularyTopic> vocabularyTopicList = vocabularyTopicRepository.findAll();
        assertThat(vocabularyTopicList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkUpdatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = vocabularyTopicRepository.findAll().size();
        // set the field null
        vocabularyTopic.setUpdatedAt(null);

        // Create the VocabularyTopic, which fails.
        VocabularyTopicDTO vocabularyTopicDTO = vocabularyTopicMapper.toDto(vocabularyTopic);

        restVocabularyTopicMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(vocabularyTopicDTO))
            )
            .andExpect(status().isBadRequest());

        List<VocabularyTopic> vocabularyTopicList = vocabularyTopicRepository.findAll();
        assertThat(vocabularyTopicList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllVocabularyTopics() throws Exception {
        // Initialize the database
        vocabularyTopicRepository.saveAndFlush(vocabularyTopic);

        // Get all the vocabularyTopicList
        restVocabularyTopicMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(vocabularyTopic.getId().intValue())))
            .andExpect(jsonPath("$.[*].nameTopic").value(hasItem(DEFAULT_NAME_TOPIC)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].view").value(hasItem(DEFAULT_VIEW.intValue())))
            .andExpect(jsonPath("$.[*].test").value(hasItem(DEFAULT_TEST.intValue())))
            .andExpect(jsonPath("$.[*].level").value(hasItem(DEFAULT_LEVEL.intValue())))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(DEFAULT_UPDATED_AT.toString())));
    }

    @Test
    @Transactional
    void getVocabularyTopic() throws Exception {
        // Initialize the database
        vocabularyTopicRepository.saveAndFlush(vocabularyTopic);

        // Get the vocabularyTopic
        restVocabularyTopicMockMvc
            .perform(get(ENTITY_API_URL_ID, vocabularyTopic.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(vocabularyTopic.getId().intValue()))
            .andExpect(jsonPath("$.nameTopic").value(DEFAULT_NAME_TOPIC))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.view").value(DEFAULT_VIEW.intValue()))
            .andExpect(jsonPath("$.test").value(DEFAULT_TEST.intValue()))
            .andExpect(jsonPath("$.level").value(DEFAULT_LEVEL.intValue()))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()))
            .andExpect(jsonPath("$.updatedAt").value(DEFAULT_UPDATED_AT.toString()));
    }

    @Test
    @Transactional
    void getNonExistingVocabularyTopic() throws Exception {
        // Get the vocabularyTopic
        restVocabularyTopicMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewVocabularyTopic() throws Exception {
        // Initialize the database
        vocabularyTopicRepository.saveAndFlush(vocabularyTopic);

        int databaseSizeBeforeUpdate = vocabularyTopicRepository.findAll().size();

        // Update the vocabularyTopic
        VocabularyTopic updatedVocabularyTopic = vocabularyTopicRepository.findById(vocabularyTopic.getId()).get();
        // Disconnect from session so that the updates on updatedVocabularyTopic are not directly saved in db
        em.detach(updatedVocabularyTopic);
        updatedVocabularyTopic
            .nameTopic(UPDATED_NAME_TOPIC)
            .description(UPDATED_DESCRIPTION)
            .view(UPDATED_VIEW)
            .test(UPDATED_TEST)
            .level(UPDATED_LEVEL)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);
        VocabularyTopicDTO vocabularyTopicDTO = vocabularyTopicMapper.toDto(updatedVocabularyTopic);

        restVocabularyTopicMockMvc
            .perform(
                put(ENTITY_API_URL_ID, vocabularyTopicDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(vocabularyTopicDTO))
            )
            .andExpect(status().isOk());

        // Validate the VocabularyTopic in the database
        List<VocabularyTopic> vocabularyTopicList = vocabularyTopicRepository.findAll();
        assertThat(vocabularyTopicList).hasSize(databaseSizeBeforeUpdate);
        VocabularyTopic testVocabularyTopic = vocabularyTopicList.get(vocabularyTopicList.size() - 1);
        assertThat(testVocabularyTopic.getNameTopic()).isEqualTo(UPDATED_NAME_TOPIC);
        assertThat(testVocabularyTopic.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testVocabularyTopic.getView()).isEqualTo(UPDATED_VIEW);
        assertThat(testVocabularyTopic.getTest()).isEqualTo(UPDATED_TEST);
        assertThat(testVocabularyTopic.getLevel()).isEqualTo(UPDATED_LEVEL);
        assertThat(testVocabularyTopic.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testVocabularyTopic.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
    }

    @Test
    @Transactional
    void putNonExistingVocabularyTopic() throws Exception {
        int databaseSizeBeforeUpdate = vocabularyTopicRepository.findAll().size();
        vocabularyTopic.setId(count.incrementAndGet());

        // Create the VocabularyTopic
        VocabularyTopicDTO vocabularyTopicDTO = vocabularyTopicMapper.toDto(vocabularyTopic);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVocabularyTopicMockMvc
            .perform(
                put(ENTITY_API_URL_ID, vocabularyTopicDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(vocabularyTopicDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the VocabularyTopic in the database
        List<VocabularyTopic> vocabularyTopicList = vocabularyTopicRepository.findAll();
        assertThat(vocabularyTopicList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchVocabularyTopic() throws Exception {
        int databaseSizeBeforeUpdate = vocabularyTopicRepository.findAll().size();
        vocabularyTopic.setId(count.incrementAndGet());

        // Create the VocabularyTopic
        VocabularyTopicDTO vocabularyTopicDTO = vocabularyTopicMapper.toDto(vocabularyTopic);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVocabularyTopicMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(vocabularyTopicDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the VocabularyTopic in the database
        List<VocabularyTopic> vocabularyTopicList = vocabularyTopicRepository.findAll();
        assertThat(vocabularyTopicList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamVocabularyTopic() throws Exception {
        int databaseSizeBeforeUpdate = vocabularyTopicRepository.findAll().size();
        vocabularyTopic.setId(count.incrementAndGet());

        // Create the VocabularyTopic
        VocabularyTopicDTO vocabularyTopicDTO = vocabularyTopicMapper.toDto(vocabularyTopic);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVocabularyTopicMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(vocabularyTopicDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the VocabularyTopic in the database
        List<VocabularyTopic> vocabularyTopicList = vocabularyTopicRepository.findAll();
        assertThat(vocabularyTopicList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateVocabularyTopicWithPatch() throws Exception {
        // Initialize the database
        vocabularyTopicRepository.saveAndFlush(vocabularyTopic);

        int databaseSizeBeforeUpdate = vocabularyTopicRepository.findAll().size();

        // Update the vocabularyTopic using partial update
        VocabularyTopic partialUpdatedVocabularyTopic = new VocabularyTopic();
        partialUpdatedVocabularyTopic.setId(vocabularyTopic.getId());

        partialUpdatedVocabularyTopic.test(UPDATED_TEST).level(UPDATED_LEVEL);

        restVocabularyTopicMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedVocabularyTopic.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedVocabularyTopic))
            )
            .andExpect(status().isOk());

        // Validate the VocabularyTopic in the database
        List<VocabularyTopic> vocabularyTopicList = vocabularyTopicRepository.findAll();
        assertThat(vocabularyTopicList).hasSize(databaseSizeBeforeUpdate);
        VocabularyTopic testVocabularyTopic = vocabularyTopicList.get(vocabularyTopicList.size() - 1);
        assertThat(testVocabularyTopic.getNameTopic()).isEqualTo(DEFAULT_NAME_TOPIC);
        assertThat(testVocabularyTopic.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testVocabularyTopic.getView()).isEqualTo(DEFAULT_VIEW);
        assertThat(testVocabularyTopic.getTest()).isEqualTo(UPDATED_TEST);
        assertThat(testVocabularyTopic.getLevel()).isEqualTo(UPDATED_LEVEL);
        assertThat(testVocabularyTopic.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testVocabularyTopic.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
    }

    @Test
    @Transactional
    void fullUpdateVocabularyTopicWithPatch() throws Exception {
        // Initialize the database
        vocabularyTopicRepository.saveAndFlush(vocabularyTopic);

        int databaseSizeBeforeUpdate = vocabularyTopicRepository.findAll().size();

        // Update the vocabularyTopic using partial update
        VocabularyTopic partialUpdatedVocabularyTopic = new VocabularyTopic();
        partialUpdatedVocabularyTopic.setId(vocabularyTopic.getId());

        partialUpdatedVocabularyTopic
            .nameTopic(UPDATED_NAME_TOPIC)
            .description(UPDATED_DESCRIPTION)
            .view(UPDATED_VIEW)
            .test(UPDATED_TEST)
            .level(UPDATED_LEVEL)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);

        restVocabularyTopicMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedVocabularyTopic.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedVocabularyTopic))
            )
            .andExpect(status().isOk());

        // Validate the VocabularyTopic in the database
        List<VocabularyTopic> vocabularyTopicList = vocabularyTopicRepository.findAll();
        assertThat(vocabularyTopicList).hasSize(databaseSizeBeforeUpdate);
        VocabularyTopic testVocabularyTopic = vocabularyTopicList.get(vocabularyTopicList.size() - 1);
        assertThat(testVocabularyTopic.getNameTopic()).isEqualTo(UPDATED_NAME_TOPIC);
        assertThat(testVocabularyTopic.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testVocabularyTopic.getView()).isEqualTo(UPDATED_VIEW);
        assertThat(testVocabularyTopic.getTest()).isEqualTo(UPDATED_TEST);
        assertThat(testVocabularyTopic.getLevel()).isEqualTo(UPDATED_LEVEL);
        assertThat(testVocabularyTopic.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testVocabularyTopic.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
    }

    @Test
    @Transactional
    void patchNonExistingVocabularyTopic() throws Exception {
        int databaseSizeBeforeUpdate = vocabularyTopicRepository.findAll().size();
        vocabularyTopic.setId(count.incrementAndGet());

        // Create the VocabularyTopic
        VocabularyTopicDTO vocabularyTopicDTO = vocabularyTopicMapper.toDto(vocabularyTopic);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVocabularyTopicMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, vocabularyTopicDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(vocabularyTopicDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the VocabularyTopic in the database
        List<VocabularyTopic> vocabularyTopicList = vocabularyTopicRepository.findAll();
        assertThat(vocabularyTopicList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchVocabularyTopic() throws Exception {
        int databaseSizeBeforeUpdate = vocabularyTopicRepository.findAll().size();
        vocabularyTopic.setId(count.incrementAndGet());

        // Create the VocabularyTopic
        VocabularyTopicDTO vocabularyTopicDTO = vocabularyTopicMapper.toDto(vocabularyTopic);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVocabularyTopicMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(vocabularyTopicDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the VocabularyTopic in the database
        List<VocabularyTopic> vocabularyTopicList = vocabularyTopicRepository.findAll();
        assertThat(vocabularyTopicList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamVocabularyTopic() throws Exception {
        int databaseSizeBeforeUpdate = vocabularyTopicRepository.findAll().size();
        vocabularyTopic.setId(count.incrementAndGet());

        // Create the VocabularyTopic
        VocabularyTopicDTO vocabularyTopicDTO = vocabularyTopicMapper.toDto(vocabularyTopic);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVocabularyTopicMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(vocabularyTopicDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the VocabularyTopic in the database
        List<VocabularyTopic> vocabularyTopicList = vocabularyTopicRepository.findAll();
        assertThat(vocabularyTopicList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteVocabularyTopic() throws Exception {
        // Initialize the database
        vocabularyTopicRepository.saveAndFlush(vocabularyTopic);

        int databaseSizeBeforeDelete = vocabularyTopicRepository.findAll().size();

        // Delete the vocabularyTopic
        restVocabularyTopicMockMvc
            .perform(delete(ENTITY_API_URL_ID, vocabularyTopic.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<VocabularyTopic> vocabularyTopicList = vocabularyTopicRepository.findAll();
        assertThat(vocabularyTopicList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
