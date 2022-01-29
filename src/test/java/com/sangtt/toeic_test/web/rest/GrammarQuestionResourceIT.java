package com.sangtt.toeic_test.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sangtt.toeic_test.IntegrationTest;
import com.sangtt.toeic_test.domain.GrammarQuestion;
import com.sangtt.toeic_test.repository.GrammarQuestionRepository;
import com.sangtt.toeic_test.service.dto.GrammarQuestionDTO;
import com.sangtt.toeic_test.service.mapper.GrammarQuestionMapper;
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
 * Integration tests for the {@link GrammarQuestionResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class GrammarQuestionResourceIT {

    private static final String DEFAULT_QUESTION = "AAAAAAAAAA";
    private static final String UPDATED_QUESTION = "BBBBBBBBBB";

    private static final String DEFAULT_CORRECT = "AAAAAAAAAA";
    private static final String UPDATED_CORRECT = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CREATED_AT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_AT = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_UPDATED_AT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_AT = LocalDate.now(ZoneId.systemDefault());

    private static final String ENTITY_API_URL = "/api/grammar-questions";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private GrammarQuestionRepository grammarQuestionRepository;

    @Autowired
    private GrammarQuestionMapper grammarQuestionMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restGrammarQuestionMockMvc;

    private GrammarQuestion grammarQuestion;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GrammarQuestion createEntity(EntityManager em) {
        GrammarQuestion grammarQuestion = new GrammarQuestion()
            .question(DEFAULT_QUESTION)
            .correct(DEFAULT_CORRECT)
            .createdAt(DEFAULT_CREATED_AT)
            .updatedAt(DEFAULT_UPDATED_AT);
        return grammarQuestion;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GrammarQuestion createUpdatedEntity(EntityManager em) {
        GrammarQuestion grammarQuestion = new GrammarQuestion()
            .question(UPDATED_QUESTION)
            .correct(UPDATED_CORRECT)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);
        return grammarQuestion;
    }

    @BeforeEach
    public void initTest() {
        grammarQuestion = createEntity(em);
    }

    @Test
    @Transactional
    void createGrammarQuestion() throws Exception {
        int databaseSizeBeforeCreate = grammarQuestionRepository.findAll().size();
        // Create the GrammarQuestion
        GrammarQuestionDTO grammarQuestionDTO = grammarQuestionMapper.toDto(grammarQuestion);
        restGrammarQuestionMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(grammarQuestionDTO))
            )
            .andExpect(status().isCreated());

        // Validate the GrammarQuestion in the database
        List<GrammarQuestion> grammarQuestionList = grammarQuestionRepository.findAll();
        assertThat(grammarQuestionList).hasSize(databaseSizeBeforeCreate + 1);
        GrammarQuestion testGrammarQuestion = grammarQuestionList.get(grammarQuestionList.size() - 1);
        assertThat(testGrammarQuestion.getQuestion()).isEqualTo(DEFAULT_QUESTION);
        assertThat(testGrammarQuestion.getCorrect()).isEqualTo(DEFAULT_CORRECT);
        assertThat(testGrammarQuestion.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testGrammarQuestion.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
    }

    @Test
    @Transactional
    void createGrammarQuestionWithExistingId() throws Exception {
        // Create the GrammarQuestion with an existing ID
        grammarQuestion.setId(1L);
        GrammarQuestionDTO grammarQuestionDTO = grammarQuestionMapper.toDto(grammarQuestion);

        int databaseSizeBeforeCreate = grammarQuestionRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restGrammarQuestionMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(grammarQuestionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the GrammarQuestion in the database
        List<GrammarQuestion> grammarQuestionList = grammarQuestionRepository.findAll();
        assertThat(grammarQuestionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkQuestionIsRequired() throws Exception {
        int databaseSizeBeforeTest = grammarQuestionRepository.findAll().size();
        // set the field null
        grammarQuestion.setQuestion(null);

        // Create the GrammarQuestion, which fails.
        GrammarQuestionDTO grammarQuestionDTO = grammarQuestionMapper.toDto(grammarQuestion);

        restGrammarQuestionMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(grammarQuestionDTO))
            )
            .andExpect(status().isBadRequest());

        List<GrammarQuestion> grammarQuestionList = grammarQuestionRepository.findAll();
        assertThat(grammarQuestionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCorrectIsRequired() throws Exception {
        int databaseSizeBeforeTest = grammarQuestionRepository.findAll().size();
        // set the field null
        grammarQuestion.setCorrect(null);

        // Create the GrammarQuestion, which fails.
        GrammarQuestionDTO grammarQuestionDTO = grammarQuestionMapper.toDto(grammarQuestion);

        restGrammarQuestionMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(grammarQuestionDTO))
            )
            .andExpect(status().isBadRequest());

        List<GrammarQuestion> grammarQuestionList = grammarQuestionRepository.findAll();
        assertThat(grammarQuestionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = grammarQuestionRepository.findAll().size();
        // set the field null
        grammarQuestion.setCreatedAt(null);

        // Create the GrammarQuestion, which fails.
        GrammarQuestionDTO grammarQuestionDTO = grammarQuestionMapper.toDto(grammarQuestion);

        restGrammarQuestionMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(grammarQuestionDTO))
            )
            .andExpect(status().isBadRequest());

        List<GrammarQuestion> grammarQuestionList = grammarQuestionRepository.findAll();
        assertThat(grammarQuestionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkUpdatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = grammarQuestionRepository.findAll().size();
        // set the field null
        grammarQuestion.setUpdatedAt(null);

        // Create the GrammarQuestion, which fails.
        GrammarQuestionDTO grammarQuestionDTO = grammarQuestionMapper.toDto(grammarQuestion);

        restGrammarQuestionMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(grammarQuestionDTO))
            )
            .andExpect(status().isBadRequest());

        List<GrammarQuestion> grammarQuestionList = grammarQuestionRepository.findAll();
        assertThat(grammarQuestionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllGrammarQuestions() throws Exception {
        // Initialize the database
        grammarQuestionRepository.saveAndFlush(grammarQuestion);

        // Get all the grammarQuestionList
        restGrammarQuestionMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(grammarQuestion.getId().intValue())))
            .andExpect(jsonPath("$.[*].question").value(hasItem(DEFAULT_QUESTION)))
            .andExpect(jsonPath("$.[*].correct").value(hasItem(DEFAULT_CORRECT)))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(DEFAULT_UPDATED_AT.toString())));
    }

    @Test
    @Transactional
    void getGrammarQuestion() throws Exception {
        // Initialize the database
        grammarQuestionRepository.saveAndFlush(grammarQuestion);

        // Get the grammarQuestion
        restGrammarQuestionMockMvc
            .perform(get(ENTITY_API_URL_ID, grammarQuestion.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(grammarQuestion.getId().intValue()))
            .andExpect(jsonPath("$.question").value(DEFAULT_QUESTION))
            .andExpect(jsonPath("$.correct").value(DEFAULT_CORRECT))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()))
            .andExpect(jsonPath("$.updatedAt").value(DEFAULT_UPDATED_AT.toString()));
    }

    @Test
    @Transactional
    void getNonExistingGrammarQuestion() throws Exception {
        // Get the grammarQuestion
        restGrammarQuestionMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewGrammarQuestion() throws Exception {
        // Initialize the database
        grammarQuestionRepository.saveAndFlush(grammarQuestion);

        int databaseSizeBeforeUpdate = grammarQuestionRepository.findAll().size();

        // Update the grammarQuestion
        GrammarQuestion updatedGrammarQuestion = grammarQuestionRepository.findById(grammarQuestion.getId()).get();
        // Disconnect from session so that the updates on updatedGrammarQuestion are not directly saved in db
        em.detach(updatedGrammarQuestion);
        updatedGrammarQuestion
            .question(UPDATED_QUESTION)
            .correct(UPDATED_CORRECT)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);
        GrammarQuestionDTO grammarQuestionDTO = grammarQuestionMapper.toDto(updatedGrammarQuestion);

        restGrammarQuestionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, grammarQuestionDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(grammarQuestionDTO))
            )
            .andExpect(status().isOk());

        // Validate the GrammarQuestion in the database
        List<GrammarQuestion> grammarQuestionList = grammarQuestionRepository.findAll();
        assertThat(grammarQuestionList).hasSize(databaseSizeBeforeUpdate);
        GrammarQuestion testGrammarQuestion = grammarQuestionList.get(grammarQuestionList.size() - 1);
        assertThat(testGrammarQuestion.getQuestion()).isEqualTo(UPDATED_QUESTION);
        assertThat(testGrammarQuestion.getCorrect()).isEqualTo(UPDATED_CORRECT);
        assertThat(testGrammarQuestion.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testGrammarQuestion.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
    }

    @Test
    @Transactional
    void putNonExistingGrammarQuestion() throws Exception {
        int databaseSizeBeforeUpdate = grammarQuestionRepository.findAll().size();
        grammarQuestion.setId(count.incrementAndGet());

        // Create the GrammarQuestion
        GrammarQuestionDTO grammarQuestionDTO = grammarQuestionMapper.toDto(grammarQuestion);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGrammarQuestionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, grammarQuestionDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(grammarQuestionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the GrammarQuestion in the database
        List<GrammarQuestion> grammarQuestionList = grammarQuestionRepository.findAll();
        assertThat(grammarQuestionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchGrammarQuestion() throws Exception {
        int databaseSizeBeforeUpdate = grammarQuestionRepository.findAll().size();
        grammarQuestion.setId(count.incrementAndGet());

        // Create the GrammarQuestion
        GrammarQuestionDTO grammarQuestionDTO = grammarQuestionMapper.toDto(grammarQuestion);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restGrammarQuestionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(grammarQuestionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the GrammarQuestion in the database
        List<GrammarQuestion> grammarQuestionList = grammarQuestionRepository.findAll();
        assertThat(grammarQuestionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamGrammarQuestion() throws Exception {
        int databaseSizeBeforeUpdate = grammarQuestionRepository.findAll().size();
        grammarQuestion.setId(count.incrementAndGet());

        // Create the GrammarQuestion
        GrammarQuestionDTO grammarQuestionDTO = grammarQuestionMapper.toDto(grammarQuestion);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restGrammarQuestionMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(grammarQuestionDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the GrammarQuestion in the database
        List<GrammarQuestion> grammarQuestionList = grammarQuestionRepository.findAll();
        assertThat(grammarQuestionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateGrammarQuestionWithPatch() throws Exception {
        // Initialize the database
        grammarQuestionRepository.saveAndFlush(grammarQuestion);

        int databaseSizeBeforeUpdate = grammarQuestionRepository.findAll().size();

        // Update the grammarQuestion using partial update
        GrammarQuestion partialUpdatedGrammarQuestion = new GrammarQuestion();
        partialUpdatedGrammarQuestion.setId(grammarQuestion.getId());

        partialUpdatedGrammarQuestion.question(UPDATED_QUESTION);

        restGrammarQuestionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedGrammarQuestion.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedGrammarQuestion))
            )
            .andExpect(status().isOk());

        // Validate the GrammarQuestion in the database
        List<GrammarQuestion> grammarQuestionList = grammarQuestionRepository.findAll();
        assertThat(grammarQuestionList).hasSize(databaseSizeBeforeUpdate);
        GrammarQuestion testGrammarQuestion = grammarQuestionList.get(grammarQuestionList.size() - 1);
        assertThat(testGrammarQuestion.getQuestion()).isEqualTo(UPDATED_QUESTION);
        assertThat(testGrammarQuestion.getCorrect()).isEqualTo(DEFAULT_CORRECT);
        assertThat(testGrammarQuestion.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testGrammarQuestion.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
    }

    @Test
    @Transactional
    void fullUpdateGrammarQuestionWithPatch() throws Exception {
        // Initialize the database
        grammarQuestionRepository.saveAndFlush(grammarQuestion);

        int databaseSizeBeforeUpdate = grammarQuestionRepository.findAll().size();

        // Update the grammarQuestion using partial update
        GrammarQuestion partialUpdatedGrammarQuestion = new GrammarQuestion();
        partialUpdatedGrammarQuestion.setId(grammarQuestion.getId());

        partialUpdatedGrammarQuestion
            .question(UPDATED_QUESTION)
            .correct(UPDATED_CORRECT)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);

        restGrammarQuestionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedGrammarQuestion.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedGrammarQuestion))
            )
            .andExpect(status().isOk());

        // Validate the GrammarQuestion in the database
        List<GrammarQuestion> grammarQuestionList = grammarQuestionRepository.findAll();
        assertThat(grammarQuestionList).hasSize(databaseSizeBeforeUpdate);
        GrammarQuestion testGrammarQuestion = grammarQuestionList.get(grammarQuestionList.size() - 1);
        assertThat(testGrammarQuestion.getQuestion()).isEqualTo(UPDATED_QUESTION);
        assertThat(testGrammarQuestion.getCorrect()).isEqualTo(UPDATED_CORRECT);
        assertThat(testGrammarQuestion.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testGrammarQuestion.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
    }

    @Test
    @Transactional
    void patchNonExistingGrammarQuestion() throws Exception {
        int databaseSizeBeforeUpdate = grammarQuestionRepository.findAll().size();
        grammarQuestion.setId(count.incrementAndGet());

        // Create the GrammarQuestion
        GrammarQuestionDTO grammarQuestionDTO = grammarQuestionMapper.toDto(grammarQuestion);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGrammarQuestionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, grammarQuestionDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(grammarQuestionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the GrammarQuestion in the database
        List<GrammarQuestion> grammarQuestionList = grammarQuestionRepository.findAll();
        assertThat(grammarQuestionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchGrammarQuestion() throws Exception {
        int databaseSizeBeforeUpdate = grammarQuestionRepository.findAll().size();
        grammarQuestion.setId(count.incrementAndGet());

        // Create the GrammarQuestion
        GrammarQuestionDTO grammarQuestionDTO = grammarQuestionMapper.toDto(grammarQuestion);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restGrammarQuestionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(grammarQuestionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the GrammarQuestion in the database
        List<GrammarQuestion> grammarQuestionList = grammarQuestionRepository.findAll();
        assertThat(grammarQuestionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamGrammarQuestion() throws Exception {
        int databaseSizeBeforeUpdate = grammarQuestionRepository.findAll().size();
        grammarQuestion.setId(count.incrementAndGet());

        // Create the GrammarQuestion
        GrammarQuestionDTO grammarQuestionDTO = grammarQuestionMapper.toDto(grammarQuestion);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restGrammarQuestionMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(grammarQuestionDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the GrammarQuestion in the database
        List<GrammarQuestion> grammarQuestionList = grammarQuestionRepository.findAll();
        assertThat(grammarQuestionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteGrammarQuestion() throws Exception {
        // Initialize the database
        grammarQuestionRepository.saveAndFlush(grammarQuestion);

        int databaseSizeBeforeDelete = grammarQuestionRepository.findAll().size();

        // Delete the grammarQuestion
        restGrammarQuestionMockMvc
            .perform(delete(ENTITY_API_URL_ID, grammarQuestion.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<GrammarQuestion> grammarQuestionList = grammarQuestionRepository.findAll();
        assertThat(grammarQuestionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
