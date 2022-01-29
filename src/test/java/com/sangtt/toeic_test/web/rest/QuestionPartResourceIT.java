package com.sangtt.toeic_test.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sangtt.toeic_test.IntegrationTest;
import com.sangtt.toeic_test.domain.QuestionPart;
import com.sangtt.toeic_test.repository.QuestionPartRepository;
import com.sangtt.toeic_test.service.dto.QuestionPartDTO;
import com.sangtt.toeic_test.service.mapper.QuestionPartMapper;
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
 * Integration tests for the {@link QuestionPartResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class QuestionPartResourceIT {

    private static final String DEFAULT_QUESTION = "AAAAAAAAAA";
    private static final String UPDATED_QUESTION = "BBBBBBBBBB";

    private static final String DEFAULT_CORRECT = "AAAAAAAAAA";
    private static final String UPDATED_CORRECT = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CREATED_AT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_AT = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_UPDATED_AT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_AT = LocalDate.now(ZoneId.systemDefault());

    private static final String ENTITY_API_URL = "/api/question-parts";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private QuestionPartRepository questionPartRepository;

    @Autowired
    private QuestionPartMapper questionPartMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restQuestionPartMockMvc;

    private QuestionPart questionPart;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QuestionPart createEntity(EntityManager em) {
        QuestionPart questionPart = new QuestionPart()
            .question(DEFAULT_QUESTION)
            .correct(DEFAULT_CORRECT)
            .createdAt(DEFAULT_CREATED_AT)
            .updatedAt(DEFAULT_UPDATED_AT);
        return questionPart;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QuestionPart createUpdatedEntity(EntityManager em) {
        QuestionPart questionPart = new QuestionPart()
            .question(UPDATED_QUESTION)
            .correct(UPDATED_CORRECT)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);
        return questionPart;
    }

    @BeforeEach
    public void initTest() {
        questionPart = createEntity(em);
    }

    @Test
    @Transactional
    void createQuestionPart() throws Exception {
        int databaseSizeBeforeCreate = questionPartRepository.findAll().size();
        // Create the QuestionPart
        QuestionPartDTO questionPartDTO = questionPartMapper.toDto(questionPart);
        restQuestionPartMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(questionPartDTO))
            )
            .andExpect(status().isCreated());

        // Validate the QuestionPart in the database
        List<QuestionPart> questionPartList = questionPartRepository.findAll();
        assertThat(questionPartList).hasSize(databaseSizeBeforeCreate + 1);
        QuestionPart testQuestionPart = questionPartList.get(questionPartList.size() - 1);
        assertThat(testQuestionPart.getQuestion()).isEqualTo(DEFAULT_QUESTION);
        assertThat(testQuestionPart.getCorrect()).isEqualTo(DEFAULT_CORRECT);
        assertThat(testQuestionPart.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testQuestionPart.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
    }

    @Test
    @Transactional
    void createQuestionPartWithExistingId() throws Exception {
        // Create the QuestionPart with an existing ID
        questionPart.setId(1L);
        QuestionPartDTO questionPartDTO = questionPartMapper.toDto(questionPart);

        int databaseSizeBeforeCreate = questionPartRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restQuestionPartMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(questionPartDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the QuestionPart in the database
        List<QuestionPart> questionPartList = questionPartRepository.findAll();
        assertThat(questionPartList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkQuestionIsRequired() throws Exception {
        int databaseSizeBeforeTest = questionPartRepository.findAll().size();
        // set the field null
        questionPart.setQuestion(null);

        // Create the QuestionPart, which fails.
        QuestionPartDTO questionPartDTO = questionPartMapper.toDto(questionPart);

        restQuestionPartMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(questionPartDTO))
            )
            .andExpect(status().isBadRequest());

        List<QuestionPart> questionPartList = questionPartRepository.findAll();
        assertThat(questionPartList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCorrectIsRequired() throws Exception {
        int databaseSizeBeforeTest = questionPartRepository.findAll().size();
        // set the field null
        questionPart.setCorrect(null);

        // Create the QuestionPart, which fails.
        QuestionPartDTO questionPartDTO = questionPartMapper.toDto(questionPart);

        restQuestionPartMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(questionPartDTO))
            )
            .andExpect(status().isBadRequest());

        List<QuestionPart> questionPartList = questionPartRepository.findAll();
        assertThat(questionPartList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = questionPartRepository.findAll().size();
        // set the field null
        questionPart.setCreatedAt(null);

        // Create the QuestionPart, which fails.
        QuestionPartDTO questionPartDTO = questionPartMapper.toDto(questionPart);

        restQuestionPartMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(questionPartDTO))
            )
            .andExpect(status().isBadRequest());

        List<QuestionPart> questionPartList = questionPartRepository.findAll();
        assertThat(questionPartList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkUpdatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = questionPartRepository.findAll().size();
        // set the field null
        questionPart.setUpdatedAt(null);

        // Create the QuestionPart, which fails.
        QuestionPartDTO questionPartDTO = questionPartMapper.toDto(questionPart);

        restQuestionPartMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(questionPartDTO))
            )
            .andExpect(status().isBadRequest());

        List<QuestionPart> questionPartList = questionPartRepository.findAll();
        assertThat(questionPartList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllQuestionParts() throws Exception {
        // Initialize the database
        questionPartRepository.saveAndFlush(questionPart);

        // Get all the questionPartList
        restQuestionPartMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(questionPart.getId().intValue())))
            .andExpect(jsonPath("$.[*].question").value(hasItem(DEFAULT_QUESTION)))
            .andExpect(jsonPath("$.[*].correct").value(hasItem(DEFAULT_CORRECT)))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(DEFAULT_UPDATED_AT.toString())));
    }

    @Test
    @Transactional
    void getQuestionPart() throws Exception {
        // Initialize the database
        questionPartRepository.saveAndFlush(questionPart);

        // Get the questionPart
        restQuestionPartMockMvc
            .perform(get(ENTITY_API_URL_ID, questionPart.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(questionPart.getId().intValue()))
            .andExpect(jsonPath("$.question").value(DEFAULT_QUESTION))
            .andExpect(jsonPath("$.correct").value(DEFAULT_CORRECT))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()))
            .andExpect(jsonPath("$.updatedAt").value(DEFAULT_UPDATED_AT.toString()));
    }

    @Test
    @Transactional
    void getNonExistingQuestionPart() throws Exception {
        // Get the questionPart
        restQuestionPartMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewQuestionPart() throws Exception {
        // Initialize the database
        questionPartRepository.saveAndFlush(questionPart);

        int databaseSizeBeforeUpdate = questionPartRepository.findAll().size();

        // Update the questionPart
        QuestionPart updatedQuestionPart = questionPartRepository.findById(questionPart.getId()).get();
        // Disconnect from session so that the updates on updatedQuestionPart are not directly saved in db
        em.detach(updatedQuestionPart);
        updatedQuestionPart.question(UPDATED_QUESTION).correct(UPDATED_CORRECT).createdAt(UPDATED_CREATED_AT).updatedAt(UPDATED_UPDATED_AT);
        QuestionPartDTO questionPartDTO = questionPartMapper.toDto(updatedQuestionPart);

        restQuestionPartMockMvc
            .perform(
                put(ENTITY_API_URL_ID, questionPartDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(questionPartDTO))
            )
            .andExpect(status().isOk());

        // Validate the QuestionPart in the database
        List<QuestionPart> questionPartList = questionPartRepository.findAll();
        assertThat(questionPartList).hasSize(databaseSizeBeforeUpdate);
        QuestionPart testQuestionPart = questionPartList.get(questionPartList.size() - 1);
        assertThat(testQuestionPart.getQuestion()).isEqualTo(UPDATED_QUESTION);
        assertThat(testQuestionPart.getCorrect()).isEqualTo(UPDATED_CORRECT);
        assertThat(testQuestionPart.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testQuestionPart.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
    }

    @Test
    @Transactional
    void putNonExistingQuestionPart() throws Exception {
        int databaseSizeBeforeUpdate = questionPartRepository.findAll().size();
        questionPart.setId(count.incrementAndGet());

        // Create the QuestionPart
        QuestionPartDTO questionPartDTO = questionPartMapper.toDto(questionPart);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQuestionPartMockMvc
            .perform(
                put(ENTITY_API_URL_ID, questionPartDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(questionPartDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the QuestionPart in the database
        List<QuestionPart> questionPartList = questionPartRepository.findAll();
        assertThat(questionPartList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchQuestionPart() throws Exception {
        int databaseSizeBeforeUpdate = questionPartRepository.findAll().size();
        questionPart.setId(count.incrementAndGet());

        // Create the QuestionPart
        QuestionPartDTO questionPartDTO = questionPartMapper.toDto(questionPart);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQuestionPartMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(questionPartDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the QuestionPart in the database
        List<QuestionPart> questionPartList = questionPartRepository.findAll();
        assertThat(questionPartList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamQuestionPart() throws Exception {
        int databaseSizeBeforeUpdate = questionPartRepository.findAll().size();
        questionPart.setId(count.incrementAndGet());

        // Create the QuestionPart
        QuestionPartDTO questionPartDTO = questionPartMapper.toDto(questionPart);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQuestionPartMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(questionPartDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the QuestionPart in the database
        List<QuestionPart> questionPartList = questionPartRepository.findAll();
        assertThat(questionPartList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateQuestionPartWithPatch() throws Exception {
        // Initialize the database
        questionPartRepository.saveAndFlush(questionPart);

        int databaseSizeBeforeUpdate = questionPartRepository.findAll().size();

        // Update the questionPart using partial update
        QuestionPart partialUpdatedQuestionPart = new QuestionPart();
        partialUpdatedQuestionPart.setId(questionPart.getId());

        partialUpdatedQuestionPart.question(UPDATED_QUESTION).createdAt(UPDATED_CREATED_AT).updatedAt(UPDATED_UPDATED_AT);

        restQuestionPartMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedQuestionPart.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedQuestionPart))
            )
            .andExpect(status().isOk());

        // Validate the QuestionPart in the database
        List<QuestionPart> questionPartList = questionPartRepository.findAll();
        assertThat(questionPartList).hasSize(databaseSizeBeforeUpdate);
        QuestionPart testQuestionPart = questionPartList.get(questionPartList.size() - 1);
        assertThat(testQuestionPart.getQuestion()).isEqualTo(UPDATED_QUESTION);
        assertThat(testQuestionPart.getCorrect()).isEqualTo(DEFAULT_CORRECT);
        assertThat(testQuestionPart.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testQuestionPart.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
    }

    @Test
    @Transactional
    void fullUpdateQuestionPartWithPatch() throws Exception {
        // Initialize the database
        questionPartRepository.saveAndFlush(questionPart);

        int databaseSizeBeforeUpdate = questionPartRepository.findAll().size();

        // Update the questionPart using partial update
        QuestionPart partialUpdatedQuestionPart = new QuestionPart();
        partialUpdatedQuestionPart.setId(questionPart.getId());

        partialUpdatedQuestionPart
            .question(UPDATED_QUESTION)
            .correct(UPDATED_CORRECT)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);

        restQuestionPartMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedQuestionPart.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedQuestionPart))
            )
            .andExpect(status().isOk());

        // Validate the QuestionPart in the database
        List<QuestionPart> questionPartList = questionPartRepository.findAll();
        assertThat(questionPartList).hasSize(databaseSizeBeforeUpdate);
        QuestionPart testQuestionPart = questionPartList.get(questionPartList.size() - 1);
        assertThat(testQuestionPart.getQuestion()).isEqualTo(UPDATED_QUESTION);
        assertThat(testQuestionPart.getCorrect()).isEqualTo(UPDATED_CORRECT);
        assertThat(testQuestionPart.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testQuestionPart.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
    }

    @Test
    @Transactional
    void patchNonExistingQuestionPart() throws Exception {
        int databaseSizeBeforeUpdate = questionPartRepository.findAll().size();
        questionPart.setId(count.incrementAndGet());

        // Create the QuestionPart
        QuestionPartDTO questionPartDTO = questionPartMapper.toDto(questionPart);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQuestionPartMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, questionPartDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(questionPartDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the QuestionPart in the database
        List<QuestionPart> questionPartList = questionPartRepository.findAll();
        assertThat(questionPartList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchQuestionPart() throws Exception {
        int databaseSizeBeforeUpdate = questionPartRepository.findAll().size();
        questionPart.setId(count.incrementAndGet());

        // Create the QuestionPart
        QuestionPartDTO questionPartDTO = questionPartMapper.toDto(questionPart);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQuestionPartMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(questionPartDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the QuestionPart in the database
        List<QuestionPart> questionPartList = questionPartRepository.findAll();
        assertThat(questionPartList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamQuestionPart() throws Exception {
        int databaseSizeBeforeUpdate = questionPartRepository.findAll().size();
        questionPart.setId(count.incrementAndGet());

        // Create the QuestionPart
        QuestionPartDTO questionPartDTO = questionPartMapper.toDto(questionPart);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQuestionPartMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(questionPartDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the QuestionPart in the database
        List<QuestionPart> questionPartList = questionPartRepository.findAll();
        assertThat(questionPartList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteQuestionPart() throws Exception {
        // Initialize the database
        questionPartRepository.saveAndFlush(questionPart);

        int databaseSizeBeforeDelete = questionPartRepository.findAll().size();

        // Delete the questionPart
        restQuestionPartMockMvc
            .perform(delete(ENTITY_API_URL_ID, questionPart.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<QuestionPart> questionPartList = questionPartRepository.findAll();
        assertThat(questionPartList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
