package com.sangtt.toeic_test.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sangtt.toeic_test.IntegrationTest;
import com.sangtt.toeic_test.domain.Vocabulary;
import com.sangtt.toeic_test.repository.VocabularyRepository;
import com.sangtt.toeic_test.service.dto.VocabularyDTO;
import com.sangtt.toeic_test.service.mapper.VocabularyMapper;
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
 * Integration tests for the {@link VocabularyResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class VocabularyResourceIT {

    private static final String DEFAULT_WORD = "AAAAAAAAAA";
    private static final String UPDATED_WORD = "BBBBBBBBBB";

    private static final String DEFAULT_MEAN = "AAAAAAAAAA";
    private static final String UPDATED_MEAN = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CREATED_AT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_AT = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_UPDATED_AT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_AT = LocalDate.now(ZoneId.systemDefault());

    private static final String ENTITY_API_URL = "/api/vocabularies";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private VocabularyRepository vocabularyRepository;

    @Autowired
    private VocabularyMapper vocabularyMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restVocabularyMockMvc;

    private Vocabulary vocabulary;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Vocabulary createEntity(EntityManager em) {
        Vocabulary vocabulary = new Vocabulary()
            .word(DEFAULT_WORD)
            .mean(DEFAULT_MEAN)
            .createdAt(DEFAULT_CREATED_AT)
            .updatedAt(DEFAULT_UPDATED_AT);
        return vocabulary;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Vocabulary createUpdatedEntity(EntityManager em) {
        Vocabulary vocabulary = new Vocabulary()
            .word(UPDATED_WORD)
            .mean(UPDATED_MEAN)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);
        return vocabulary;
    }

    @BeforeEach
    public void initTest() {
        vocabulary = createEntity(em);
    }

    @Test
    @Transactional
    void createVocabulary() throws Exception {
        int databaseSizeBeforeCreate = vocabularyRepository.findAll().size();
        // Create the Vocabulary
        VocabularyDTO vocabularyDTO = vocabularyMapper.toDto(vocabulary);
        restVocabularyMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(vocabularyDTO)))
            .andExpect(status().isCreated());

        // Validate the Vocabulary in the database
        List<Vocabulary> vocabularyList = vocabularyRepository.findAll();
        assertThat(vocabularyList).hasSize(databaseSizeBeforeCreate + 1);
        Vocabulary testVocabulary = vocabularyList.get(vocabularyList.size() - 1);
        assertThat(testVocabulary.getWord()).isEqualTo(DEFAULT_WORD);
        assertThat(testVocabulary.getMean()).isEqualTo(DEFAULT_MEAN);
        assertThat(testVocabulary.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testVocabulary.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
    }

    @Test
    @Transactional
    void createVocabularyWithExistingId() throws Exception {
        // Create the Vocabulary with an existing ID
        vocabulary.setId(1L);
        VocabularyDTO vocabularyDTO = vocabularyMapper.toDto(vocabulary);

        int databaseSizeBeforeCreate = vocabularyRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restVocabularyMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(vocabularyDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Vocabulary in the database
        List<Vocabulary> vocabularyList = vocabularyRepository.findAll();
        assertThat(vocabularyList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkWordIsRequired() throws Exception {
        int databaseSizeBeforeTest = vocabularyRepository.findAll().size();
        // set the field null
        vocabulary.setWord(null);

        // Create the Vocabulary, which fails.
        VocabularyDTO vocabularyDTO = vocabularyMapper.toDto(vocabulary);

        restVocabularyMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(vocabularyDTO)))
            .andExpect(status().isBadRequest());

        List<Vocabulary> vocabularyList = vocabularyRepository.findAll();
        assertThat(vocabularyList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkMeanIsRequired() throws Exception {
        int databaseSizeBeforeTest = vocabularyRepository.findAll().size();
        // set the field null
        vocabulary.setMean(null);

        // Create the Vocabulary, which fails.
        VocabularyDTO vocabularyDTO = vocabularyMapper.toDto(vocabulary);

        restVocabularyMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(vocabularyDTO)))
            .andExpect(status().isBadRequest());

        List<Vocabulary> vocabularyList = vocabularyRepository.findAll();
        assertThat(vocabularyList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = vocabularyRepository.findAll().size();
        // set the field null
        vocabulary.setCreatedAt(null);

        // Create the Vocabulary, which fails.
        VocabularyDTO vocabularyDTO = vocabularyMapper.toDto(vocabulary);

        restVocabularyMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(vocabularyDTO)))
            .andExpect(status().isBadRequest());

        List<Vocabulary> vocabularyList = vocabularyRepository.findAll();
        assertThat(vocabularyList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkUpdatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = vocabularyRepository.findAll().size();
        // set the field null
        vocabulary.setUpdatedAt(null);

        // Create the Vocabulary, which fails.
        VocabularyDTO vocabularyDTO = vocabularyMapper.toDto(vocabulary);

        restVocabularyMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(vocabularyDTO)))
            .andExpect(status().isBadRequest());

        List<Vocabulary> vocabularyList = vocabularyRepository.findAll();
        assertThat(vocabularyList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllVocabularies() throws Exception {
        // Initialize the database
        vocabularyRepository.saveAndFlush(vocabulary);

        // Get all the vocabularyList
        restVocabularyMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(vocabulary.getId().intValue())))
            .andExpect(jsonPath("$.[*].word").value(hasItem(DEFAULT_WORD)))
            .andExpect(jsonPath("$.[*].mean").value(hasItem(DEFAULT_MEAN)))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(DEFAULT_UPDATED_AT.toString())));
    }

    @Test
    @Transactional
    void getVocabulary() throws Exception {
        // Initialize the database
        vocabularyRepository.saveAndFlush(vocabulary);

        // Get the vocabulary
        restVocabularyMockMvc
            .perform(get(ENTITY_API_URL_ID, vocabulary.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(vocabulary.getId().intValue()))
            .andExpect(jsonPath("$.word").value(DEFAULT_WORD))
            .andExpect(jsonPath("$.mean").value(DEFAULT_MEAN))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()))
            .andExpect(jsonPath("$.updatedAt").value(DEFAULT_UPDATED_AT.toString()));
    }

    @Test
    @Transactional
    void getNonExistingVocabulary() throws Exception {
        // Get the vocabulary
        restVocabularyMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewVocabulary() throws Exception {
        // Initialize the database
        vocabularyRepository.saveAndFlush(vocabulary);

        int databaseSizeBeforeUpdate = vocabularyRepository.findAll().size();

        // Update the vocabulary
        Vocabulary updatedVocabulary = vocabularyRepository.findById(vocabulary.getId()).get();
        // Disconnect from session so that the updates on updatedVocabulary are not directly saved in db
        em.detach(updatedVocabulary);
        updatedVocabulary.word(UPDATED_WORD).mean(UPDATED_MEAN).createdAt(UPDATED_CREATED_AT).updatedAt(UPDATED_UPDATED_AT);
        VocabularyDTO vocabularyDTO = vocabularyMapper.toDto(updatedVocabulary);

        restVocabularyMockMvc
            .perform(
                put(ENTITY_API_URL_ID, vocabularyDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(vocabularyDTO))
            )
            .andExpect(status().isOk());

        // Validate the Vocabulary in the database
        List<Vocabulary> vocabularyList = vocabularyRepository.findAll();
        assertThat(vocabularyList).hasSize(databaseSizeBeforeUpdate);
        Vocabulary testVocabulary = vocabularyList.get(vocabularyList.size() - 1);
        assertThat(testVocabulary.getWord()).isEqualTo(UPDATED_WORD);
        assertThat(testVocabulary.getMean()).isEqualTo(UPDATED_MEAN);
        assertThat(testVocabulary.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testVocabulary.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
    }

    @Test
    @Transactional
    void putNonExistingVocabulary() throws Exception {
        int databaseSizeBeforeUpdate = vocabularyRepository.findAll().size();
        vocabulary.setId(count.incrementAndGet());

        // Create the Vocabulary
        VocabularyDTO vocabularyDTO = vocabularyMapper.toDto(vocabulary);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVocabularyMockMvc
            .perform(
                put(ENTITY_API_URL_ID, vocabularyDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(vocabularyDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Vocabulary in the database
        List<Vocabulary> vocabularyList = vocabularyRepository.findAll();
        assertThat(vocabularyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchVocabulary() throws Exception {
        int databaseSizeBeforeUpdate = vocabularyRepository.findAll().size();
        vocabulary.setId(count.incrementAndGet());

        // Create the Vocabulary
        VocabularyDTO vocabularyDTO = vocabularyMapper.toDto(vocabulary);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVocabularyMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(vocabularyDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Vocabulary in the database
        List<Vocabulary> vocabularyList = vocabularyRepository.findAll();
        assertThat(vocabularyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamVocabulary() throws Exception {
        int databaseSizeBeforeUpdate = vocabularyRepository.findAll().size();
        vocabulary.setId(count.incrementAndGet());

        // Create the Vocabulary
        VocabularyDTO vocabularyDTO = vocabularyMapper.toDto(vocabulary);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVocabularyMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(vocabularyDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Vocabulary in the database
        List<Vocabulary> vocabularyList = vocabularyRepository.findAll();
        assertThat(vocabularyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateVocabularyWithPatch() throws Exception {
        // Initialize the database
        vocabularyRepository.saveAndFlush(vocabulary);

        int databaseSizeBeforeUpdate = vocabularyRepository.findAll().size();

        // Update the vocabulary using partial update
        Vocabulary partialUpdatedVocabulary = new Vocabulary();
        partialUpdatedVocabulary.setId(vocabulary.getId());

        partialUpdatedVocabulary.updatedAt(UPDATED_UPDATED_AT);

        restVocabularyMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedVocabulary.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedVocabulary))
            )
            .andExpect(status().isOk());

        // Validate the Vocabulary in the database
        List<Vocabulary> vocabularyList = vocabularyRepository.findAll();
        assertThat(vocabularyList).hasSize(databaseSizeBeforeUpdate);
        Vocabulary testVocabulary = vocabularyList.get(vocabularyList.size() - 1);
        assertThat(testVocabulary.getWord()).isEqualTo(DEFAULT_WORD);
        assertThat(testVocabulary.getMean()).isEqualTo(DEFAULT_MEAN);
        assertThat(testVocabulary.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testVocabulary.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
    }

    @Test
    @Transactional
    void fullUpdateVocabularyWithPatch() throws Exception {
        // Initialize the database
        vocabularyRepository.saveAndFlush(vocabulary);

        int databaseSizeBeforeUpdate = vocabularyRepository.findAll().size();

        // Update the vocabulary using partial update
        Vocabulary partialUpdatedVocabulary = new Vocabulary();
        partialUpdatedVocabulary.setId(vocabulary.getId());

        partialUpdatedVocabulary.word(UPDATED_WORD).mean(UPDATED_MEAN).createdAt(UPDATED_CREATED_AT).updatedAt(UPDATED_UPDATED_AT);

        restVocabularyMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedVocabulary.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedVocabulary))
            )
            .andExpect(status().isOk());

        // Validate the Vocabulary in the database
        List<Vocabulary> vocabularyList = vocabularyRepository.findAll();
        assertThat(vocabularyList).hasSize(databaseSizeBeforeUpdate);
        Vocabulary testVocabulary = vocabularyList.get(vocabularyList.size() - 1);
        assertThat(testVocabulary.getWord()).isEqualTo(UPDATED_WORD);
        assertThat(testVocabulary.getMean()).isEqualTo(UPDATED_MEAN);
        assertThat(testVocabulary.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testVocabulary.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
    }

    @Test
    @Transactional
    void patchNonExistingVocabulary() throws Exception {
        int databaseSizeBeforeUpdate = vocabularyRepository.findAll().size();
        vocabulary.setId(count.incrementAndGet());

        // Create the Vocabulary
        VocabularyDTO vocabularyDTO = vocabularyMapper.toDto(vocabulary);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVocabularyMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, vocabularyDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(vocabularyDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Vocabulary in the database
        List<Vocabulary> vocabularyList = vocabularyRepository.findAll();
        assertThat(vocabularyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchVocabulary() throws Exception {
        int databaseSizeBeforeUpdate = vocabularyRepository.findAll().size();
        vocabulary.setId(count.incrementAndGet());

        // Create the Vocabulary
        VocabularyDTO vocabularyDTO = vocabularyMapper.toDto(vocabulary);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVocabularyMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(vocabularyDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Vocabulary in the database
        List<Vocabulary> vocabularyList = vocabularyRepository.findAll();
        assertThat(vocabularyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamVocabulary() throws Exception {
        int databaseSizeBeforeUpdate = vocabularyRepository.findAll().size();
        vocabulary.setId(count.incrementAndGet());

        // Create the Vocabulary
        VocabularyDTO vocabularyDTO = vocabularyMapper.toDto(vocabulary);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVocabularyMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(vocabularyDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Vocabulary in the database
        List<Vocabulary> vocabularyList = vocabularyRepository.findAll();
        assertThat(vocabularyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteVocabulary() throws Exception {
        // Initialize the database
        vocabularyRepository.saveAndFlush(vocabulary);

        int databaseSizeBeforeDelete = vocabularyRepository.findAll().size();

        // Delete the vocabulary
        restVocabularyMockMvc
            .perform(delete(ENTITY_API_URL_ID, vocabulary.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Vocabulary> vocabularyList = vocabularyRepository.findAll();
        assertThat(vocabularyList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
