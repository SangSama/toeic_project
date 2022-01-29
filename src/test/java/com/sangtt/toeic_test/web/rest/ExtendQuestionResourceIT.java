package com.sangtt.toeic_test.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sangtt.toeic_test.IntegrationTest;
import com.sangtt.toeic_test.domain.ExtendQuestion;
import com.sangtt.toeic_test.repository.ExtendQuestionRepository;
import com.sangtt.toeic_test.service.dto.ExtendQuestionDTO;
import com.sangtt.toeic_test.service.mapper.ExtendQuestionMapper;
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
import org.springframework.util.Base64Utils;

/**
 * Integration tests for the {@link ExtendQuestionResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ExtendQuestionResourceIT {

    private static final String DEFAULT_PARAGRAPH = "AAAAAAAAAA";
    private static final String UPDATED_PARAGRAPH = "BBBBBBBBBB";

    private static final byte[] DEFAULT_IMAGE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_IMAGE = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_IMAGE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_IMAGE_CONTENT_TYPE = "image/png";

    private static final LocalDate DEFAULT_CREATED_AT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_AT = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_UPDATED_AT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_AT = LocalDate.now(ZoneId.systemDefault());

    private static final String ENTITY_API_URL = "/api/extend-questions";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ExtendQuestionRepository extendQuestionRepository;

    @Autowired
    private ExtendQuestionMapper extendQuestionMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restExtendQuestionMockMvc;

    private ExtendQuestion extendQuestion;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ExtendQuestion createEntity(EntityManager em) {
        ExtendQuestion extendQuestion = new ExtendQuestion()
            .paragraph(DEFAULT_PARAGRAPH)
            .image(DEFAULT_IMAGE)
            .imageContentType(DEFAULT_IMAGE_CONTENT_TYPE)
            .createdAt(DEFAULT_CREATED_AT)
            .updatedAt(DEFAULT_UPDATED_AT);
        return extendQuestion;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ExtendQuestion createUpdatedEntity(EntityManager em) {
        ExtendQuestion extendQuestion = new ExtendQuestion()
            .paragraph(UPDATED_PARAGRAPH)
            .image(UPDATED_IMAGE)
            .imageContentType(UPDATED_IMAGE_CONTENT_TYPE)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);
        return extendQuestion;
    }

    @BeforeEach
    public void initTest() {
        extendQuestion = createEntity(em);
    }

    @Test
    @Transactional
    void createExtendQuestion() throws Exception {
        int databaseSizeBeforeCreate = extendQuestionRepository.findAll().size();
        // Create the ExtendQuestion
        ExtendQuestionDTO extendQuestionDTO = extendQuestionMapper.toDto(extendQuestion);
        restExtendQuestionMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(extendQuestionDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ExtendQuestion in the database
        List<ExtendQuestion> extendQuestionList = extendQuestionRepository.findAll();
        assertThat(extendQuestionList).hasSize(databaseSizeBeforeCreate + 1);
        ExtendQuestion testExtendQuestion = extendQuestionList.get(extendQuestionList.size() - 1);
        assertThat(testExtendQuestion.getParagraph()).isEqualTo(DEFAULT_PARAGRAPH);
        assertThat(testExtendQuestion.getImage()).isEqualTo(DEFAULT_IMAGE);
        assertThat(testExtendQuestion.getImageContentType()).isEqualTo(DEFAULT_IMAGE_CONTENT_TYPE);
        assertThat(testExtendQuestion.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testExtendQuestion.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
    }

    @Test
    @Transactional
    void createExtendQuestionWithExistingId() throws Exception {
        // Create the ExtendQuestion with an existing ID
        extendQuestion.setId(1L);
        ExtendQuestionDTO extendQuestionDTO = extendQuestionMapper.toDto(extendQuestion);

        int databaseSizeBeforeCreate = extendQuestionRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restExtendQuestionMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(extendQuestionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ExtendQuestion in the database
        List<ExtendQuestion> extendQuestionList = extendQuestionRepository.findAll();
        assertThat(extendQuestionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkCreatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = extendQuestionRepository.findAll().size();
        // set the field null
        extendQuestion.setCreatedAt(null);

        // Create the ExtendQuestion, which fails.
        ExtendQuestionDTO extendQuestionDTO = extendQuestionMapper.toDto(extendQuestion);

        restExtendQuestionMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(extendQuestionDTO))
            )
            .andExpect(status().isBadRequest());

        List<ExtendQuestion> extendQuestionList = extendQuestionRepository.findAll();
        assertThat(extendQuestionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkUpdatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = extendQuestionRepository.findAll().size();
        // set the field null
        extendQuestion.setUpdatedAt(null);

        // Create the ExtendQuestion, which fails.
        ExtendQuestionDTO extendQuestionDTO = extendQuestionMapper.toDto(extendQuestion);

        restExtendQuestionMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(extendQuestionDTO))
            )
            .andExpect(status().isBadRequest());

        List<ExtendQuestion> extendQuestionList = extendQuestionRepository.findAll();
        assertThat(extendQuestionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllExtendQuestions() throws Exception {
        // Initialize the database
        extendQuestionRepository.saveAndFlush(extendQuestion);

        // Get all the extendQuestionList
        restExtendQuestionMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(extendQuestion.getId().intValue())))
            .andExpect(jsonPath("$.[*].paragraph").value(hasItem(DEFAULT_PARAGRAPH)))
            .andExpect(jsonPath("$.[*].imageContentType").value(hasItem(DEFAULT_IMAGE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].image").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGE))))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(DEFAULT_UPDATED_AT.toString())));
    }

    @Test
    @Transactional
    void getExtendQuestion() throws Exception {
        // Initialize the database
        extendQuestionRepository.saveAndFlush(extendQuestion);

        // Get the extendQuestion
        restExtendQuestionMockMvc
            .perform(get(ENTITY_API_URL_ID, extendQuestion.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(extendQuestion.getId().intValue()))
            .andExpect(jsonPath("$.paragraph").value(DEFAULT_PARAGRAPH))
            .andExpect(jsonPath("$.imageContentType").value(DEFAULT_IMAGE_CONTENT_TYPE))
            .andExpect(jsonPath("$.image").value(Base64Utils.encodeToString(DEFAULT_IMAGE)))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()))
            .andExpect(jsonPath("$.updatedAt").value(DEFAULT_UPDATED_AT.toString()));
    }

    @Test
    @Transactional
    void getNonExistingExtendQuestion() throws Exception {
        // Get the extendQuestion
        restExtendQuestionMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewExtendQuestion() throws Exception {
        // Initialize the database
        extendQuestionRepository.saveAndFlush(extendQuestion);

        int databaseSizeBeforeUpdate = extendQuestionRepository.findAll().size();

        // Update the extendQuestion
        ExtendQuestion updatedExtendQuestion = extendQuestionRepository.findById(extendQuestion.getId()).get();
        // Disconnect from session so that the updates on updatedExtendQuestion are not directly saved in db
        em.detach(updatedExtendQuestion);
        updatedExtendQuestion
            .paragraph(UPDATED_PARAGRAPH)
            .image(UPDATED_IMAGE)
            .imageContentType(UPDATED_IMAGE_CONTENT_TYPE)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);
        ExtendQuestionDTO extendQuestionDTO = extendQuestionMapper.toDto(updatedExtendQuestion);

        restExtendQuestionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, extendQuestionDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(extendQuestionDTO))
            )
            .andExpect(status().isOk());

        // Validate the ExtendQuestion in the database
        List<ExtendQuestion> extendQuestionList = extendQuestionRepository.findAll();
        assertThat(extendQuestionList).hasSize(databaseSizeBeforeUpdate);
        ExtendQuestion testExtendQuestion = extendQuestionList.get(extendQuestionList.size() - 1);
        assertThat(testExtendQuestion.getParagraph()).isEqualTo(UPDATED_PARAGRAPH);
        assertThat(testExtendQuestion.getImage()).isEqualTo(UPDATED_IMAGE);
        assertThat(testExtendQuestion.getImageContentType()).isEqualTo(UPDATED_IMAGE_CONTENT_TYPE);
        assertThat(testExtendQuestion.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testExtendQuestion.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
    }

    @Test
    @Transactional
    void putNonExistingExtendQuestion() throws Exception {
        int databaseSizeBeforeUpdate = extendQuestionRepository.findAll().size();
        extendQuestion.setId(count.incrementAndGet());

        // Create the ExtendQuestion
        ExtendQuestionDTO extendQuestionDTO = extendQuestionMapper.toDto(extendQuestion);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restExtendQuestionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, extendQuestionDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(extendQuestionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ExtendQuestion in the database
        List<ExtendQuestion> extendQuestionList = extendQuestionRepository.findAll();
        assertThat(extendQuestionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchExtendQuestion() throws Exception {
        int databaseSizeBeforeUpdate = extendQuestionRepository.findAll().size();
        extendQuestion.setId(count.incrementAndGet());

        // Create the ExtendQuestion
        ExtendQuestionDTO extendQuestionDTO = extendQuestionMapper.toDto(extendQuestion);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restExtendQuestionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(extendQuestionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ExtendQuestion in the database
        List<ExtendQuestion> extendQuestionList = extendQuestionRepository.findAll();
        assertThat(extendQuestionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamExtendQuestion() throws Exception {
        int databaseSizeBeforeUpdate = extendQuestionRepository.findAll().size();
        extendQuestion.setId(count.incrementAndGet());

        // Create the ExtendQuestion
        ExtendQuestionDTO extendQuestionDTO = extendQuestionMapper.toDto(extendQuestion);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restExtendQuestionMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(extendQuestionDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ExtendQuestion in the database
        List<ExtendQuestion> extendQuestionList = extendQuestionRepository.findAll();
        assertThat(extendQuestionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateExtendQuestionWithPatch() throws Exception {
        // Initialize the database
        extendQuestionRepository.saveAndFlush(extendQuestion);

        int databaseSizeBeforeUpdate = extendQuestionRepository.findAll().size();

        // Update the extendQuestion using partial update
        ExtendQuestion partialUpdatedExtendQuestion = new ExtendQuestion();
        partialUpdatedExtendQuestion.setId(extendQuestion.getId());

        partialUpdatedExtendQuestion.image(UPDATED_IMAGE).imageContentType(UPDATED_IMAGE_CONTENT_TYPE);

        restExtendQuestionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedExtendQuestion.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedExtendQuestion))
            )
            .andExpect(status().isOk());

        // Validate the ExtendQuestion in the database
        List<ExtendQuestion> extendQuestionList = extendQuestionRepository.findAll();
        assertThat(extendQuestionList).hasSize(databaseSizeBeforeUpdate);
        ExtendQuestion testExtendQuestion = extendQuestionList.get(extendQuestionList.size() - 1);
        assertThat(testExtendQuestion.getParagraph()).isEqualTo(DEFAULT_PARAGRAPH);
        assertThat(testExtendQuestion.getImage()).isEqualTo(UPDATED_IMAGE);
        assertThat(testExtendQuestion.getImageContentType()).isEqualTo(UPDATED_IMAGE_CONTENT_TYPE);
        assertThat(testExtendQuestion.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testExtendQuestion.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
    }

    @Test
    @Transactional
    void fullUpdateExtendQuestionWithPatch() throws Exception {
        // Initialize the database
        extendQuestionRepository.saveAndFlush(extendQuestion);

        int databaseSizeBeforeUpdate = extendQuestionRepository.findAll().size();

        // Update the extendQuestion using partial update
        ExtendQuestion partialUpdatedExtendQuestion = new ExtendQuestion();
        partialUpdatedExtendQuestion.setId(extendQuestion.getId());

        partialUpdatedExtendQuestion
            .paragraph(UPDATED_PARAGRAPH)
            .image(UPDATED_IMAGE)
            .imageContentType(UPDATED_IMAGE_CONTENT_TYPE)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);

        restExtendQuestionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedExtendQuestion.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedExtendQuestion))
            )
            .andExpect(status().isOk());

        // Validate the ExtendQuestion in the database
        List<ExtendQuestion> extendQuestionList = extendQuestionRepository.findAll();
        assertThat(extendQuestionList).hasSize(databaseSizeBeforeUpdate);
        ExtendQuestion testExtendQuestion = extendQuestionList.get(extendQuestionList.size() - 1);
        assertThat(testExtendQuestion.getParagraph()).isEqualTo(UPDATED_PARAGRAPH);
        assertThat(testExtendQuestion.getImage()).isEqualTo(UPDATED_IMAGE);
        assertThat(testExtendQuestion.getImageContentType()).isEqualTo(UPDATED_IMAGE_CONTENT_TYPE);
        assertThat(testExtendQuestion.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testExtendQuestion.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
    }

    @Test
    @Transactional
    void patchNonExistingExtendQuestion() throws Exception {
        int databaseSizeBeforeUpdate = extendQuestionRepository.findAll().size();
        extendQuestion.setId(count.incrementAndGet());

        // Create the ExtendQuestion
        ExtendQuestionDTO extendQuestionDTO = extendQuestionMapper.toDto(extendQuestion);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restExtendQuestionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, extendQuestionDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(extendQuestionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ExtendQuestion in the database
        List<ExtendQuestion> extendQuestionList = extendQuestionRepository.findAll();
        assertThat(extendQuestionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchExtendQuestion() throws Exception {
        int databaseSizeBeforeUpdate = extendQuestionRepository.findAll().size();
        extendQuestion.setId(count.incrementAndGet());

        // Create the ExtendQuestion
        ExtendQuestionDTO extendQuestionDTO = extendQuestionMapper.toDto(extendQuestion);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restExtendQuestionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(extendQuestionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ExtendQuestion in the database
        List<ExtendQuestion> extendQuestionList = extendQuestionRepository.findAll();
        assertThat(extendQuestionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamExtendQuestion() throws Exception {
        int databaseSizeBeforeUpdate = extendQuestionRepository.findAll().size();
        extendQuestion.setId(count.incrementAndGet());

        // Create the ExtendQuestion
        ExtendQuestionDTO extendQuestionDTO = extendQuestionMapper.toDto(extendQuestion);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restExtendQuestionMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(extendQuestionDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ExtendQuestion in the database
        List<ExtendQuestion> extendQuestionList = extendQuestionRepository.findAll();
        assertThat(extendQuestionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteExtendQuestion() throws Exception {
        // Initialize the database
        extendQuestionRepository.saveAndFlush(extendQuestion);

        int databaseSizeBeforeDelete = extendQuestionRepository.findAll().size();

        // Delete the extendQuestion
        restExtendQuestionMockMvc
            .perform(delete(ENTITY_API_URL_ID, extendQuestion.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ExtendQuestion> extendQuestionList = extendQuestionRepository.findAll();
        assertThat(extendQuestionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
