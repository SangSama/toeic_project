package com.sangtt.toeic_test.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sangtt.toeic_test.IntegrationTest;
import com.sangtt.toeic_test.domain.GrammarAnswer;
import com.sangtt.toeic_test.repository.GrammarAnswerRepository;
import com.sangtt.toeic_test.service.dto.GrammarAnswerDTO;
import com.sangtt.toeic_test.service.mapper.GrammarAnswerMapper;
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
 * Integration tests for the {@link GrammarAnswerResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class GrammarAnswerResourceIT {

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

    private static final String ENTITY_API_URL = "/api/grammar-answers";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private GrammarAnswerRepository grammarAnswerRepository;

    @Autowired
    private GrammarAnswerMapper grammarAnswerMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restGrammarAnswerMockMvc;

    private GrammarAnswer grammarAnswer;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GrammarAnswer createEntity(EntityManager em) {
        GrammarAnswer grammarAnswer = new GrammarAnswer()
            .answerA(DEFAULT_ANSWER_A)
            .answerB(DEFAULT_ANSWER_B)
            .answerC(DEFAULT_ANSWER_C)
            .answerD(DEFAULT_ANSWER_D)
            .createdAt(DEFAULT_CREATED_AT)
            .updatedAt(DEFAULT_UPDATED_AT);
        return grammarAnswer;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GrammarAnswer createUpdatedEntity(EntityManager em) {
        GrammarAnswer grammarAnswer = new GrammarAnswer()
            .answerA(UPDATED_ANSWER_A)
            .answerB(UPDATED_ANSWER_B)
            .answerC(UPDATED_ANSWER_C)
            .answerD(UPDATED_ANSWER_D)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);
        return grammarAnswer;
    }

    @BeforeEach
    public void initTest() {
        grammarAnswer = createEntity(em);
    }

    @Test
    @Transactional
    void createGrammarAnswer() throws Exception {
        int databaseSizeBeforeCreate = grammarAnswerRepository.findAll().size();
        // Create the GrammarAnswer
        GrammarAnswerDTO grammarAnswerDTO = grammarAnswerMapper.toDto(grammarAnswer);
        restGrammarAnswerMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(grammarAnswerDTO))
            )
            .andExpect(status().isCreated());

        // Validate the GrammarAnswer in the database
        List<GrammarAnswer> grammarAnswerList = grammarAnswerRepository.findAll();
        assertThat(grammarAnswerList).hasSize(databaseSizeBeforeCreate + 1);
        GrammarAnswer testGrammarAnswer = grammarAnswerList.get(grammarAnswerList.size() - 1);
        assertThat(testGrammarAnswer.getAnswerA()).isEqualTo(DEFAULT_ANSWER_A);
        assertThat(testGrammarAnswer.getAnswerB()).isEqualTo(DEFAULT_ANSWER_B);
        assertThat(testGrammarAnswer.getAnswerC()).isEqualTo(DEFAULT_ANSWER_C);
        assertThat(testGrammarAnswer.getAnswerD()).isEqualTo(DEFAULT_ANSWER_D);
        assertThat(testGrammarAnswer.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testGrammarAnswer.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
    }

    @Test
    @Transactional
    void createGrammarAnswerWithExistingId() throws Exception {
        // Create the GrammarAnswer with an existing ID
        grammarAnswer.setId(1L);
        GrammarAnswerDTO grammarAnswerDTO = grammarAnswerMapper.toDto(grammarAnswer);

        int databaseSizeBeforeCreate = grammarAnswerRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restGrammarAnswerMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(grammarAnswerDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the GrammarAnswer in the database
        List<GrammarAnswer> grammarAnswerList = grammarAnswerRepository.findAll();
        assertThat(grammarAnswerList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkAnswerAIsRequired() throws Exception {
        int databaseSizeBeforeTest = grammarAnswerRepository.findAll().size();
        // set the field null
        grammarAnswer.setAnswerA(null);

        // Create the GrammarAnswer, which fails.
        GrammarAnswerDTO grammarAnswerDTO = grammarAnswerMapper.toDto(grammarAnswer);

        restGrammarAnswerMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(grammarAnswerDTO))
            )
            .andExpect(status().isBadRequest());

        List<GrammarAnswer> grammarAnswerList = grammarAnswerRepository.findAll();
        assertThat(grammarAnswerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkAnswerBIsRequired() throws Exception {
        int databaseSizeBeforeTest = grammarAnswerRepository.findAll().size();
        // set the field null
        grammarAnswer.setAnswerB(null);

        // Create the GrammarAnswer, which fails.
        GrammarAnswerDTO grammarAnswerDTO = grammarAnswerMapper.toDto(grammarAnswer);

        restGrammarAnswerMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(grammarAnswerDTO))
            )
            .andExpect(status().isBadRequest());

        List<GrammarAnswer> grammarAnswerList = grammarAnswerRepository.findAll();
        assertThat(grammarAnswerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkAnswerCIsRequired() throws Exception {
        int databaseSizeBeforeTest = grammarAnswerRepository.findAll().size();
        // set the field null
        grammarAnswer.setAnswerC(null);

        // Create the GrammarAnswer, which fails.
        GrammarAnswerDTO grammarAnswerDTO = grammarAnswerMapper.toDto(grammarAnswer);

        restGrammarAnswerMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(grammarAnswerDTO))
            )
            .andExpect(status().isBadRequest());

        List<GrammarAnswer> grammarAnswerList = grammarAnswerRepository.findAll();
        assertThat(grammarAnswerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkAnswerDIsRequired() throws Exception {
        int databaseSizeBeforeTest = grammarAnswerRepository.findAll().size();
        // set the field null
        grammarAnswer.setAnswerD(null);

        // Create the GrammarAnswer, which fails.
        GrammarAnswerDTO grammarAnswerDTO = grammarAnswerMapper.toDto(grammarAnswer);

        restGrammarAnswerMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(grammarAnswerDTO))
            )
            .andExpect(status().isBadRequest());

        List<GrammarAnswer> grammarAnswerList = grammarAnswerRepository.findAll();
        assertThat(grammarAnswerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = grammarAnswerRepository.findAll().size();
        // set the field null
        grammarAnswer.setCreatedAt(null);

        // Create the GrammarAnswer, which fails.
        GrammarAnswerDTO grammarAnswerDTO = grammarAnswerMapper.toDto(grammarAnswer);

        restGrammarAnswerMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(grammarAnswerDTO))
            )
            .andExpect(status().isBadRequest());

        List<GrammarAnswer> grammarAnswerList = grammarAnswerRepository.findAll();
        assertThat(grammarAnswerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkUpdatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = grammarAnswerRepository.findAll().size();
        // set the field null
        grammarAnswer.setUpdatedAt(null);

        // Create the GrammarAnswer, which fails.
        GrammarAnswerDTO grammarAnswerDTO = grammarAnswerMapper.toDto(grammarAnswer);

        restGrammarAnswerMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(grammarAnswerDTO))
            )
            .andExpect(status().isBadRequest());

        List<GrammarAnswer> grammarAnswerList = grammarAnswerRepository.findAll();
        assertThat(grammarAnswerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllGrammarAnswers() throws Exception {
        // Initialize the database
        grammarAnswerRepository.saveAndFlush(grammarAnswer);

        // Get all the grammarAnswerList
        restGrammarAnswerMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(grammarAnswer.getId().intValue())))
            .andExpect(jsonPath("$.[*].answerA").value(hasItem(DEFAULT_ANSWER_A)))
            .andExpect(jsonPath("$.[*].answerB").value(hasItem(DEFAULT_ANSWER_B)))
            .andExpect(jsonPath("$.[*].answerC").value(hasItem(DEFAULT_ANSWER_C)))
            .andExpect(jsonPath("$.[*].answerD").value(hasItem(DEFAULT_ANSWER_D)))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(DEFAULT_UPDATED_AT.toString())));
    }

    @Test
    @Transactional
    void getGrammarAnswer() throws Exception {
        // Initialize the database
        grammarAnswerRepository.saveAndFlush(grammarAnswer);

        // Get the grammarAnswer
        restGrammarAnswerMockMvc
            .perform(get(ENTITY_API_URL_ID, grammarAnswer.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(grammarAnswer.getId().intValue()))
            .andExpect(jsonPath("$.answerA").value(DEFAULT_ANSWER_A))
            .andExpect(jsonPath("$.answerB").value(DEFAULT_ANSWER_B))
            .andExpect(jsonPath("$.answerC").value(DEFAULT_ANSWER_C))
            .andExpect(jsonPath("$.answerD").value(DEFAULT_ANSWER_D))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()))
            .andExpect(jsonPath("$.updatedAt").value(DEFAULT_UPDATED_AT.toString()));
    }

    @Test
    @Transactional
    void getNonExistingGrammarAnswer() throws Exception {
        // Get the grammarAnswer
        restGrammarAnswerMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewGrammarAnswer() throws Exception {
        // Initialize the database
        grammarAnswerRepository.saveAndFlush(grammarAnswer);

        int databaseSizeBeforeUpdate = grammarAnswerRepository.findAll().size();

        // Update the grammarAnswer
        GrammarAnswer updatedGrammarAnswer = grammarAnswerRepository.findById(grammarAnswer.getId()).get();
        // Disconnect from session so that the updates on updatedGrammarAnswer are not directly saved in db
        em.detach(updatedGrammarAnswer);
        updatedGrammarAnswer
            .answerA(UPDATED_ANSWER_A)
            .answerB(UPDATED_ANSWER_B)
            .answerC(UPDATED_ANSWER_C)
            .answerD(UPDATED_ANSWER_D)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);
        GrammarAnswerDTO grammarAnswerDTO = grammarAnswerMapper.toDto(updatedGrammarAnswer);

        restGrammarAnswerMockMvc
            .perform(
                put(ENTITY_API_URL_ID, grammarAnswerDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(grammarAnswerDTO))
            )
            .andExpect(status().isOk());

        // Validate the GrammarAnswer in the database
        List<GrammarAnswer> grammarAnswerList = grammarAnswerRepository.findAll();
        assertThat(grammarAnswerList).hasSize(databaseSizeBeforeUpdate);
        GrammarAnswer testGrammarAnswer = grammarAnswerList.get(grammarAnswerList.size() - 1);
        assertThat(testGrammarAnswer.getAnswerA()).isEqualTo(UPDATED_ANSWER_A);
        assertThat(testGrammarAnswer.getAnswerB()).isEqualTo(UPDATED_ANSWER_B);
        assertThat(testGrammarAnswer.getAnswerC()).isEqualTo(UPDATED_ANSWER_C);
        assertThat(testGrammarAnswer.getAnswerD()).isEqualTo(UPDATED_ANSWER_D);
        assertThat(testGrammarAnswer.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testGrammarAnswer.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
    }

    @Test
    @Transactional
    void putNonExistingGrammarAnswer() throws Exception {
        int databaseSizeBeforeUpdate = grammarAnswerRepository.findAll().size();
        grammarAnswer.setId(count.incrementAndGet());

        // Create the GrammarAnswer
        GrammarAnswerDTO grammarAnswerDTO = grammarAnswerMapper.toDto(grammarAnswer);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGrammarAnswerMockMvc
            .perform(
                put(ENTITY_API_URL_ID, grammarAnswerDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(grammarAnswerDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the GrammarAnswer in the database
        List<GrammarAnswer> grammarAnswerList = grammarAnswerRepository.findAll();
        assertThat(grammarAnswerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchGrammarAnswer() throws Exception {
        int databaseSizeBeforeUpdate = grammarAnswerRepository.findAll().size();
        grammarAnswer.setId(count.incrementAndGet());

        // Create the GrammarAnswer
        GrammarAnswerDTO grammarAnswerDTO = grammarAnswerMapper.toDto(grammarAnswer);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restGrammarAnswerMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(grammarAnswerDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the GrammarAnswer in the database
        List<GrammarAnswer> grammarAnswerList = grammarAnswerRepository.findAll();
        assertThat(grammarAnswerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamGrammarAnswer() throws Exception {
        int databaseSizeBeforeUpdate = grammarAnswerRepository.findAll().size();
        grammarAnswer.setId(count.incrementAndGet());

        // Create the GrammarAnswer
        GrammarAnswerDTO grammarAnswerDTO = grammarAnswerMapper.toDto(grammarAnswer);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restGrammarAnswerMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(grammarAnswerDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the GrammarAnswer in the database
        List<GrammarAnswer> grammarAnswerList = grammarAnswerRepository.findAll();
        assertThat(grammarAnswerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateGrammarAnswerWithPatch() throws Exception {
        // Initialize the database
        grammarAnswerRepository.saveAndFlush(grammarAnswer);

        int databaseSizeBeforeUpdate = grammarAnswerRepository.findAll().size();

        // Update the grammarAnswer using partial update
        GrammarAnswer partialUpdatedGrammarAnswer = new GrammarAnswer();
        partialUpdatedGrammarAnswer.setId(grammarAnswer.getId());

        partialUpdatedGrammarAnswer.updatedAt(UPDATED_UPDATED_AT);

        restGrammarAnswerMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedGrammarAnswer.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedGrammarAnswer))
            )
            .andExpect(status().isOk());

        // Validate the GrammarAnswer in the database
        List<GrammarAnswer> grammarAnswerList = grammarAnswerRepository.findAll();
        assertThat(grammarAnswerList).hasSize(databaseSizeBeforeUpdate);
        GrammarAnswer testGrammarAnswer = grammarAnswerList.get(grammarAnswerList.size() - 1);
        assertThat(testGrammarAnswer.getAnswerA()).isEqualTo(DEFAULT_ANSWER_A);
        assertThat(testGrammarAnswer.getAnswerB()).isEqualTo(DEFAULT_ANSWER_B);
        assertThat(testGrammarAnswer.getAnswerC()).isEqualTo(DEFAULT_ANSWER_C);
        assertThat(testGrammarAnswer.getAnswerD()).isEqualTo(DEFAULT_ANSWER_D);
        assertThat(testGrammarAnswer.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testGrammarAnswer.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
    }

    @Test
    @Transactional
    void fullUpdateGrammarAnswerWithPatch() throws Exception {
        // Initialize the database
        grammarAnswerRepository.saveAndFlush(grammarAnswer);

        int databaseSizeBeforeUpdate = grammarAnswerRepository.findAll().size();

        // Update the grammarAnswer using partial update
        GrammarAnswer partialUpdatedGrammarAnswer = new GrammarAnswer();
        partialUpdatedGrammarAnswer.setId(grammarAnswer.getId());

        partialUpdatedGrammarAnswer
            .answerA(UPDATED_ANSWER_A)
            .answerB(UPDATED_ANSWER_B)
            .answerC(UPDATED_ANSWER_C)
            .answerD(UPDATED_ANSWER_D)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);

        restGrammarAnswerMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedGrammarAnswer.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedGrammarAnswer))
            )
            .andExpect(status().isOk());

        // Validate the GrammarAnswer in the database
        List<GrammarAnswer> grammarAnswerList = grammarAnswerRepository.findAll();
        assertThat(grammarAnswerList).hasSize(databaseSizeBeforeUpdate);
        GrammarAnswer testGrammarAnswer = grammarAnswerList.get(grammarAnswerList.size() - 1);
        assertThat(testGrammarAnswer.getAnswerA()).isEqualTo(UPDATED_ANSWER_A);
        assertThat(testGrammarAnswer.getAnswerB()).isEqualTo(UPDATED_ANSWER_B);
        assertThat(testGrammarAnswer.getAnswerC()).isEqualTo(UPDATED_ANSWER_C);
        assertThat(testGrammarAnswer.getAnswerD()).isEqualTo(UPDATED_ANSWER_D);
        assertThat(testGrammarAnswer.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testGrammarAnswer.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
    }

    @Test
    @Transactional
    void patchNonExistingGrammarAnswer() throws Exception {
        int databaseSizeBeforeUpdate = grammarAnswerRepository.findAll().size();
        grammarAnswer.setId(count.incrementAndGet());

        // Create the GrammarAnswer
        GrammarAnswerDTO grammarAnswerDTO = grammarAnswerMapper.toDto(grammarAnswer);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGrammarAnswerMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, grammarAnswerDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(grammarAnswerDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the GrammarAnswer in the database
        List<GrammarAnswer> grammarAnswerList = grammarAnswerRepository.findAll();
        assertThat(grammarAnswerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchGrammarAnswer() throws Exception {
        int databaseSizeBeforeUpdate = grammarAnswerRepository.findAll().size();
        grammarAnswer.setId(count.incrementAndGet());

        // Create the GrammarAnswer
        GrammarAnswerDTO grammarAnswerDTO = grammarAnswerMapper.toDto(grammarAnswer);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restGrammarAnswerMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(grammarAnswerDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the GrammarAnswer in the database
        List<GrammarAnswer> grammarAnswerList = grammarAnswerRepository.findAll();
        assertThat(grammarAnswerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamGrammarAnswer() throws Exception {
        int databaseSizeBeforeUpdate = grammarAnswerRepository.findAll().size();
        grammarAnswer.setId(count.incrementAndGet());

        // Create the GrammarAnswer
        GrammarAnswerDTO grammarAnswerDTO = grammarAnswerMapper.toDto(grammarAnswer);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restGrammarAnswerMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(grammarAnswerDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the GrammarAnswer in the database
        List<GrammarAnswer> grammarAnswerList = grammarAnswerRepository.findAll();
        assertThat(grammarAnswerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteGrammarAnswer() throws Exception {
        // Initialize the database
        grammarAnswerRepository.saveAndFlush(grammarAnswer);

        int databaseSizeBeforeDelete = grammarAnswerRepository.findAll().size();

        // Delete the grammarAnswer
        restGrammarAnswerMockMvc
            .perform(delete(ENTITY_API_URL_ID, grammarAnswer.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<GrammarAnswer> grammarAnswerList = grammarAnswerRepository.findAll();
        assertThat(grammarAnswerList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
