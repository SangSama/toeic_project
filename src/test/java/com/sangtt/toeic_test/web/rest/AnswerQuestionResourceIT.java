package com.sangtt.toeic_test.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sangtt.toeic_test.IntegrationTest;
import com.sangtt.toeic_test.domain.AnswerQuestion;
import com.sangtt.toeic_test.repository.AnswerQuestionRepository;
import com.sangtt.toeic_test.service.dto.AnswerQuestionDTO;
import com.sangtt.toeic_test.service.mapper.AnswerQuestionMapper;
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
 * Integration tests for the {@link AnswerQuestionResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AnswerQuestionResourceIT {

    private static final String DEFAULT_ANSWER_A = "AAAAAAAAAA";
    private static final String UPDATED_ANSWER_A = "BBBBBBBBBB";

    private static final String DEFAULT_ANSWER_B = "AAAAAAAAAA";
    private static final String UPDATED_ANSWER_B = "BBBBBBBBBB";

    private static final String DEFAULT_ANSWER_C = "AAAAAAAAAA";
    private static final String UPDATED_ANSWER_C = "BBBBBBBBBB";

    private static final String DEFAULT_ANSWER_D = "AAAAAAAAAA";
    private static final String UPDATED_ANSWER_D = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CREATED_AT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_AT = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_UPDATED_AT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_AT = LocalDate.now(ZoneId.systemDefault());

    private static final String ENTITY_API_URL = "/api/answer-questions";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private AnswerQuestionRepository answerQuestionRepository;

    @Autowired
    private AnswerQuestionMapper answerQuestionMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAnswerQuestionMockMvc;

    private AnswerQuestion answerQuestion;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AnswerQuestion createEntity(EntityManager em) {
        AnswerQuestion answerQuestion = new AnswerQuestion()
            .answerA(DEFAULT_ANSWER_A)
            .answerB(DEFAULT_ANSWER_B)
            .answerC(DEFAULT_ANSWER_C)
            .answerD(DEFAULT_ANSWER_D)
            .createdAt(DEFAULT_CREATED_AT)
            .updatedAt(DEFAULT_UPDATED_AT);
        return answerQuestion;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AnswerQuestion createUpdatedEntity(EntityManager em) {
        AnswerQuestion answerQuestion = new AnswerQuestion()
            .answerA(UPDATED_ANSWER_A)
            .answerB(UPDATED_ANSWER_B)
            .answerC(UPDATED_ANSWER_C)
            .answerD(UPDATED_ANSWER_D)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);
        return answerQuestion;
    }

    @BeforeEach
    public void initTest() {
        answerQuestion = createEntity(em);
    }

    @Test
    @Transactional
    void createAnswerQuestion() throws Exception {
        int databaseSizeBeforeCreate = answerQuestionRepository.findAll().size();
        // Create the AnswerQuestion
        AnswerQuestionDTO answerQuestionDTO = answerQuestionMapper.toDto(answerQuestion);
        restAnswerQuestionMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(answerQuestionDTO))
            )
            .andExpect(status().isCreated());

        // Validate the AnswerQuestion in the database
        List<AnswerQuestion> answerQuestionList = answerQuestionRepository.findAll();
        assertThat(answerQuestionList).hasSize(databaseSizeBeforeCreate + 1);
        AnswerQuestion testAnswerQuestion = answerQuestionList.get(answerQuestionList.size() - 1);
        assertThat(testAnswerQuestion.getAnswerA()).isEqualTo(DEFAULT_ANSWER_A);
        assertThat(testAnswerQuestion.getAnswerB()).isEqualTo(DEFAULT_ANSWER_B);
        assertThat(testAnswerQuestion.getAnswerC()).isEqualTo(DEFAULT_ANSWER_C);
        assertThat(testAnswerQuestion.getAnswerD()).isEqualTo(DEFAULT_ANSWER_D);
        assertThat(testAnswerQuestion.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testAnswerQuestion.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
    }

    @Test
    @Transactional
    void createAnswerQuestionWithExistingId() throws Exception {
        // Create the AnswerQuestion with an existing ID
        answerQuestion.setId(1L);
        AnswerQuestionDTO answerQuestionDTO = answerQuestionMapper.toDto(answerQuestion);

        int databaseSizeBeforeCreate = answerQuestionRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAnswerQuestionMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(answerQuestionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AnswerQuestion in the database
        List<AnswerQuestion> answerQuestionList = answerQuestionRepository.findAll();
        assertThat(answerQuestionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkAnswerAIsRequired() throws Exception {
        int databaseSizeBeforeTest = answerQuestionRepository.findAll().size();
        // set the field null
        answerQuestion.setAnswerA(null);

        // Create the AnswerQuestion, which fails.
        AnswerQuestionDTO answerQuestionDTO = answerQuestionMapper.toDto(answerQuestion);

        restAnswerQuestionMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(answerQuestionDTO))
            )
            .andExpect(status().isBadRequest());

        List<AnswerQuestion> answerQuestionList = answerQuestionRepository.findAll();
        assertThat(answerQuestionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkAnswerBIsRequired() throws Exception {
        int databaseSizeBeforeTest = answerQuestionRepository.findAll().size();
        // set the field null
        answerQuestion.setAnswerB(null);

        // Create the AnswerQuestion, which fails.
        AnswerQuestionDTO answerQuestionDTO = answerQuestionMapper.toDto(answerQuestion);

        restAnswerQuestionMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(answerQuestionDTO))
            )
            .andExpect(status().isBadRequest());

        List<AnswerQuestion> answerQuestionList = answerQuestionRepository.findAll();
        assertThat(answerQuestionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkAnswerCIsRequired() throws Exception {
        int databaseSizeBeforeTest = answerQuestionRepository.findAll().size();
        // set the field null
        answerQuestion.setAnswerC(null);

        // Create the AnswerQuestion, which fails.
        AnswerQuestionDTO answerQuestionDTO = answerQuestionMapper.toDto(answerQuestion);

        restAnswerQuestionMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(answerQuestionDTO))
            )
            .andExpect(status().isBadRequest());

        List<AnswerQuestion> answerQuestionList = answerQuestionRepository.findAll();
        assertThat(answerQuestionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkAnswerDIsRequired() throws Exception {
        int databaseSizeBeforeTest = answerQuestionRepository.findAll().size();
        // set the field null
        answerQuestion.setAnswerD(null);

        // Create the AnswerQuestion, which fails.
        AnswerQuestionDTO answerQuestionDTO = answerQuestionMapper.toDto(answerQuestion);

        restAnswerQuestionMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(answerQuestionDTO))
            )
            .andExpect(status().isBadRequest());

        List<AnswerQuestion> answerQuestionList = answerQuestionRepository.findAll();
        assertThat(answerQuestionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = answerQuestionRepository.findAll().size();
        // set the field null
        answerQuestion.setCreatedAt(null);

        // Create the AnswerQuestion, which fails.
        AnswerQuestionDTO answerQuestionDTO = answerQuestionMapper.toDto(answerQuestion);

        restAnswerQuestionMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(answerQuestionDTO))
            )
            .andExpect(status().isBadRequest());

        List<AnswerQuestion> answerQuestionList = answerQuestionRepository.findAll();
        assertThat(answerQuestionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkUpdatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = answerQuestionRepository.findAll().size();
        // set the field null
        answerQuestion.setUpdatedAt(null);

        // Create the AnswerQuestion, which fails.
        AnswerQuestionDTO answerQuestionDTO = answerQuestionMapper.toDto(answerQuestion);

        restAnswerQuestionMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(answerQuestionDTO))
            )
            .andExpect(status().isBadRequest());

        List<AnswerQuestion> answerQuestionList = answerQuestionRepository.findAll();
        assertThat(answerQuestionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllAnswerQuestions() throws Exception {
        // Initialize the database
        answerQuestionRepository.saveAndFlush(answerQuestion);

        // Get all the answerQuestionList
        restAnswerQuestionMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(answerQuestion.getId().intValue())))
            .andExpect(jsonPath("$.[*].answerA").value(hasItem(DEFAULT_ANSWER_A)))
            .andExpect(jsonPath("$.[*].answerB").value(hasItem(DEFAULT_ANSWER_B)))
            .andExpect(jsonPath("$.[*].answerC").value(hasItem(DEFAULT_ANSWER_C)))
            .andExpect(jsonPath("$.[*].answerD").value(hasItem(DEFAULT_ANSWER_D)))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(DEFAULT_UPDATED_AT.toString())));
    }

    @Test
    @Transactional
    void getAnswerQuestion() throws Exception {
        // Initialize the database
        answerQuestionRepository.saveAndFlush(answerQuestion);

        // Get the answerQuestion
        restAnswerQuestionMockMvc
            .perform(get(ENTITY_API_URL_ID, answerQuestion.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(answerQuestion.getId().intValue()))
            .andExpect(jsonPath("$.answerA").value(DEFAULT_ANSWER_A))
            .andExpect(jsonPath("$.answerB").value(DEFAULT_ANSWER_B))
            .andExpect(jsonPath("$.answerC").value(DEFAULT_ANSWER_C))
            .andExpect(jsonPath("$.answerD").value(DEFAULT_ANSWER_D))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()))
            .andExpect(jsonPath("$.updatedAt").value(DEFAULT_UPDATED_AT.toString()));
    }

    @Test
    @Transactional
    void getNonExistingAnswerQuestion() throws Exception {
        // Get the answerQuestion
        restAnswerQuestionMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewAnswerQuestion() throws Exception {
        // Initialize the database
        answerQuestionRepository.saveAndFlush(answerQuestion);

        int databaseSizeBeforeUpdate = answerQuestionRepository.findAll().size();

        // Update the answerQuestion
        AnswerQuestion updatedAnswerQuestion = answerQuestionRepository.findById(answerQuestion.getId()).get();
        // Disconnect from session so that the updates on updatedAnswerQuestion are not directly saved in db
        em.detach(updatedAnswerQuestion);
        updatedAnswerQuestion
            .answerA(UPDATED_ANSWER_A)
            .answerB(UPDATED_ANSWER_B)
            .answerC(UPDATED_ANSWER_C)
            .answerD(UPDATED_ANSWER_D)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);
        AnswerQuestionDTO answerQuestionDTO = answerQuestionMapper.toDto(updatedAnswerQuestion);

        restAnswerQuestionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, answerQuestionDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(answerQuestionDTO))
            )
            .andExpect(status().isOk());

        // Validate the AnswerQuestion in the database
        List<AnswerQuestion> answerQuestionList = answerQuestionRepository.findAll();
        assertThat(answerQuestionList).hasSize(databaseSizeBeforeUpdate);
        AnswerQuestion testAnswerQuestion = answerQuestionList.get(answerQuestionList.size() - 1);
        assertThat(testAnswerQuestion.getAnswerA()).isEqualTo(UPDATED_ANSWER_A);
        assertThat(testAnswerQuestion.getAnswerB()).isEqualTo(UPDATED_ANSWER_B);
        assertThat(testAnswerQuestion.getAnswerC()).isEqualTo(UPDATED_ANSWER_C);
        assertThat(testAnswerQuestion.getAnswerD()).isEqualTo(UPDATED_ANSWER_D);
        assertThat(testAnswerQuestion.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testAnswerQuestion.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
    }

    @Test
    @Transactional
    void putNonExistingAnswerQuestion() throws Exception {
        int databaseSizeBeforeUpdate = answerQuestionRepository.findAll().size();
        answerQuestion.setId(count.incrementAndGet());

        // Create the AnswerQuestion
        AnswerQuestionDTO answerQuestionDTO = answerQuestionMapper.toDto(answerQuestion);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAnswerQuestionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, answerQuestionDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(answerQuestionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AnswerQuestion in the database
        List<AnswerQuestion> answerQuestionList = answerQuestionRepository.findAll();
        assertThat(answerQuestionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAnswerQuestion() throws Exception {
        int databaseSizeBeforeUpdate = answerQuestionRepository.findAll().size();
        answerQuestion.setId(count.incrementAndGet());

        // Create the AnswerQuestion
        AnswerQuestionDTO answerQuestionDTO = answerQuestionMapper.toDto(answerQuestion);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAnswerQuestionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(answerQuestionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AnswerQuestion in the database
        List<AnswerQuestion> answerQuestionList = answerQuestionRepository.findAll();
        assertThat(answerQuestionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAnswerQuestion() throws Exception {
        int databaseSizeBeforeUpdate = answerQuestionRepository.findAll().size();
        answerQuestion.setId(count.incrementAndGet());

        // Create the AnswerQuestion
        AnswerQuestionDTO answerQuestionDTO = answerQuestionMapper.toDto(answerQuestion);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAnswerQuestionMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(answerQuestionDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the AnswerQuestion in the database
        List<AnswerQuestion> answerQuestionList = answerQuestionRepository.findAll();
        assertThat(answerQuestionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAnswerQuestionWithPatch() throws Exception {
        // Initialize the database
        answerQuestionRepository.saveAndFlush(answerQuestion);

        int databaseSizeBeforeUpdate = answerQuestionRepository.findAll().size();

        // Update the answerQuestion using partial update
        AnswerQuestion partialUpdatedAnswerQuestion = new AnswerQuestion();
        partialUpdatedAnswerQuestion.setId(answerQuestion.getId());

        partialUpdatedAnswerQuestion.answerC(UPDATED_ANSWER_C).answerD(UPDATED_ANSWER_D).updatedAt(UPDATED_UPDATED_AT);

        restAnswerQuestionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAnswerQuestion.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAnswerQuestion))
            )
            .andExpect(status().isOk());

        // Validate the AnswerQuestion in the database
        List<AnswerQuestion> answerQuestionList = answerQuestionRepository.findAll();
        assertThat(answerQuestionList).hasSize(databaseSizeBeforeUpdate);
        AnswerQuestion testAnswerQuestion = answerQuestionList.get(answerQuestionList.size() - 1);
        assertThat(testAnswerQuestion.getAnswerA()).isEqualTo(DEFAULT_ANSWER_A);
        assertThat(testAnswerQuestion.getAnswerB()).isEqualTo(DEFAULT_ANSWER_B);
        assertThat(testAnswerQuestion.getAnswerC()).isEqualTo(UPDATED_ANSWER_C);
        assertThat(testAnswerQuestion.getAnswerD()).isEqualTo(UPDATED_ANSWER_D);
        assertThat(testAnswerQuestion.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testAnswerQuestion.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
    }

    @Test
    @Transactional
    void fullUpdateAnswerQuestionWithPatch() throws Exception {
        // Initialize the database
        answerQuestionRepository.saveAndFlush(answerQuestion);

        int databaseSizeBeforeUpdate = answerQuestionRepository.findAll().size();

        // Update the answerQuestion using partial update
        AnswerQuestion partialUpdatedAnswerQuestion = new AnswerQuestion();
        partialUpdatedAnswerQuestion.setId(answerQuestion.getId());

        partialUpdatedAnswerQuestion
            .answerA(UPDATED_ANSWER_A)
            .answerB(UPDATED_ANSWER_B)
            .answerC(UPDATED_ANSWER_C)
            .answerD(UPDATED_ANSWER_D)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);

        restAnswerQuestionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAnswerQuestion.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAnswerQuestion))
            )
            .andExpect(status().isOk());

        // Validate the AnswerQuestion in the database
        List<AnswerQuestion> answerQuestionList = answerQuestionRepository.findAll();
        assertThat(answerQuestionList).hasSize(databaseSizeBeforeUpdate);
        AnswerQuestion testAnswerQuestion = answerQuestionList.get(answerQuestionList.size() - 1);
        assertThat(testAnswerQuestion.getAnswerA()).isEqualTo(UPDATED_ANSWER_A);
        assertThat(testAnswerQuestion.getAnswerB()).isEqualTo(UPDATED_ANSWER_B);
        assertThat(testAnswerQuestion.getAnswerC()).isEqualTo(UPDATED_ANSWER_C);
        assertThat(testAnswerQuestion.getAnswerD()).isEqualTo(UPDATED_ANSWER_D);
        assertThat(testAnswerQuestion.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testAnswerQuestion.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
    }

    @Test
    @Transactional
    void patchNonExistingAnswerQuestion() throws Exception {
        int databaseSizeBeforeUpdate = answerQuestionRepository.findAll().size();
        answerQuestion.setId(count.incrementAndGet());

        // Create the AnswerQuestion
        AnswerQuestionDTO answerQuestionDTO = answerQuestionMapper.toDto(answerQuestion);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAnswerQuestionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, answerQuestionDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(answerQuestionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AnswerQuestion in the database
        List<AnswerQuestion> answerQuestionList = answerQuestionRepository.findAll();
        assertThat(answerQuestionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAnswerQuestion() throws Exception {
        int databaseSizeBeforeUpdate = answerQuestionRepository.findAll().size();
        answerQuestion.setId(count.incrementAndGet());

        // Create the AnswerQuestion
        AnswerQuestionDTO answerQuestionDTO = answerQuestionMapper.toDto(answerQuestion);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAnswerQuestionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(answerQuestionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AnswerQuestion in the database
        List<AnswerQuestion> answerQuestionList = answerQuestionRepository.findAll();
        assertThat(answerQuestionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAnswerQuestion() throws Exception {
        int databaseSizeBeforeUpdate = answerQuestionRepository.findAll().size();
        answerQuestion.setId(count.incrementAndGet());

        // Create the AnswerQuestion
        AnswerQuestionDTO answerQuestionDTO = answerQuestionMapper.toDto(answerQuestion);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAnswerQuestionMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(answerQuestionDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the AnswerQuestion in the database
        List<AnswerQuestion> answerQuestionList = answerQuestionRepository.findAll();
        assertThat(answerQuestionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAnswerQuestion() throws Exception {
        // Initialize the database
        answerQuestionRepository.saveAndFlush(answerQuestion);

        int databaseSizeBeforeDelete = answerQuestionRepository.findAll().size();

        // Delete the answerQuestion
        restAnswerQuestionMockMvc
            .perform(delete(ENTITY_API_URL_ID, answerQuestion.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AnswerQuestion> answerQuestionList = answerQuestionRepository.findAll();
        assertThat(answerQuestionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
