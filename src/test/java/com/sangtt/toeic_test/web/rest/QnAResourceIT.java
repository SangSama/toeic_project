package com.sangtt.toeic_test.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sangtt.toeic_test.IntegrationTest;
import com.sangtt.toeic_test.domain.QnA;
import com.sangtt.toeic_test.repository.QnARepository;
import com.sangtt.toeic_test.service.dto.QnADTO;
import com.sangtt.toeic_test.service.mapper.QnAMapper;
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
 * Integration tests for the {@link QnAResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class QnAResourceIT {

    private static final Long DEFAULT_USER_ID = 1L;
    private static final Long UPDATED_USER_ID = 2L;

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final Long DEFAULT_STATUS = 1L;
    private static final Long UPDATED_STATUS = 2L;

    private static final LocalDate DEFAULT_CREATED_AT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_AT = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_UPDATED_AT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_AT = LocalDate.now(ZoneId.systemDefault());

    private static final String ENTITY_API_URL = "/api/qn-as";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private QnARepository qnARepository;

    @Autowired
    private QnAMapper qnAMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restQnAMockMvc;

    private QnA qnA;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QnA createEntity(EntityManager em) {
        QnA qnA = new QnA()
            .userId(DEFAULT_USER_ID)
            .email(DEFAULT_EMAIL)
            .status(DEFAULT_STATUS)
            .createdAt(DEFAULT_CREATED_AT)
            .updatedAt(DEFAULT_UPDATED_AT);
        return qnA;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QnA createUpdatedEntity(EntityManager em) {
        QnA qnA = new QnA()
            .userId(UPDATED_USER_ID)
            .email(UPDATED_EMAIL)
            .status(UPDATED_STATUS)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);
        return qnA;
    }

    @BeforeEach
    public void initTest() {
        qnA = createEntity(em);
    }

    @Test
    @Transactional
    void createQnA() throws Exception {
        int databaseSizeBeforeCreate = qnARepository.findAll().size();
        // Create the QnA
        QnADTO qnADTO = qnAMapper.toDto(qnA);
        restQnAMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(qnADTO)))
            .andExpect(status().isCreated());

        // Validate the QnA in the database
        List<QnA> qnAList = qnARepository.findAll();
        assertThat(qnAList).hasSize(databaseSizeBeforeCreate + 1);
        QnA testQnA = qnAList.get(qnAList.size() - 1);
        assertThat(testQnA.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testQnA.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testQnA.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testQnA.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testQnA.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
    }

    @Test
    @Transactional
    void createQnAWithExistingId() throws Exception {
        // Create the QnA with an existing ID
        qnA.setId(1L);
        QnADTO qnADTO = qnAMapper.toDto(qnA);

        int databaseSizeBeforeCreate = qnARepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restQnAMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(qnADTO)))
            .andExpect(status().isBadRequest());

        // Validate the QnA in the database
        List<QnA> qnAList = qnARepository.findAll();
        assertThat(qnAList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkUserIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = qnARepository.findAll().size();
        // set the field null
        qnA.setUserId(null);

        // Create the QnA, which fails.
        QnADTO qnADTO = qnAMapper.toDto(qnA);

        restQnAMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(qnADTO)))
            .andExpect(status().isBadRequest());

        List<QnA> qnAList = qnARepository.findAll();
        assertThat(qnAList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkEmailIsRequired() throws Exception {
        int databaseSizeBeforeTest = qnARepository.findAll().size();
        // set the field null
        qnA.setEmail(null);

        // Create the QnA, which fails.
        QnADTO qnADTO = qnAMapper.toDto(qnA);

        restQnAMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(qnADTO)))
            .andExpect(status().isBadRequest());

        List<QnA> qnAList = qnARepository.findAll();
        assertThat(qnAList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = qnARepository.findAll().size();
        // set the field null
        qnA.setStatus(null);

        // Create the QnA, which fails.
        QnADTO qnADTO = qnAMapper.toDto(qnA);

        restQnAMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(qnADTO)))
            .andExpect(status().isBadRequest());

        List<QnA> qnAList = qnARepository.findAll();
        assertThat(qnAList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = qnARepository.findAll().size();
        // set the field null
        qnA.setCreatedAt(null);

        // Create the QnA, which fails.
        QnADTO qnADTO = qnAMapper.toDto(qnA);

        restQnAMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(qnADTO)))
            .andExpect(status().isBadRequest());

        List<QnA> qnAList = qnARepository.findAll();
        assertThat(qnAList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkUpdatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = qnARepository.findAll().size();
        // set the field null
        qnA.setUpdatedAt(null);

        // Create the QnA, which fails.
        QnADTO qnADTO = qnAMapper.toDto(qnA);

        restQnAMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(qnADTO)))
            .andExpect(status().isBadRequest());

        List<QnA> qnAList = qnARepository.findAll();
        assertThat(qnAList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllQnAS() throws Exception {
        // Initialize the database
        qnARepository.saveAndFlush(qnA);

        // Get all the qnAList
        restQnAMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(qnA.getId().intValue())))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID.intValue())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.intValue())))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(DEFAULT_UPDATED_AT.toString())));
    }

    @Test
    @Transactional
    void getQnA() throws Exception {
        // Initialize the database
        qnARepository.saveAndFlush(qnA);

        // Get the qnA
        restQnAMockMvc
            .perform(get(ENTITY_API_URL_ID, qnA.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(qnA.getId().intValue()))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID.intValue()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.intValue()))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()))
            .andExpect(jsonPath("$.updatedAt").value(DEFAULT_UPDATED_AT.toString()));
    }

    @Test
    @Transactional
    void getNonExistingQnA() throws Exception {
        // Get the qnA
        restQnAMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewQnA() throws Exception {
        // Initialize the database
        qnARepository.saveAndFlush(qnA);

        int databaseSizeBeforeUpdate = qnARepository.findAll().size();

        // Update the qnA
        QnA updatedQnA = qnARepository.findById(qnA.getId()).get();
        // Disconnect from session so that the updates on updatedQnA are not directly saved in db
        em.detach(updatedQnA);
        updatedQnA
            .userId(UPDATED_USER_ID)
            .email(UPDATED_EMAIL)
            .status(UPDATED_STATUS)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);
        QnADTO qnADTO = qnAMapper.toDto(updatedQnA);

        restQnAMockMvc
            .perform(
                put(ENTITY_API_URL_ID, qnADTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(qnADTO))
            )
            .andExpect(status().isOk());

        // Validate the QnA in the database
        List<QnA> qnAList = qnARepository.findAll();
        assertThat(qnAList).hasSize(databaseSizeBeforeUpdate);
        QnA testQnA = qnAList.get(qnAList.size() - 1);
        assertThat(testQnA.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testQnA.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testQnA.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testQnA.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testQnA.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
    }

    @Test
    @Transactional
    void putNonExistingQnA() throws Exception {
        int databaseSizeBeforeUpdate = qnARepository.findAll().size();
        qnA.setId(count.incrementAndGet());

        // Create the QnA
        QnADTO qnADTO = qnAMapper.toDto(qnA);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQnAMockMvc
            .perform(
                put(ENTITY_API_URL_ID, qnADTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(qnADTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the QnA in the database
        List<QnA> qnAList = qnARepository.findAll();
        assertThat(qnAList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchQnA() throws Exception {
        int databaseSizeBeforeUpdate = qnARepository.findAll().size();
        qnA.setId(count.incrementAndGet());

        // Create the QnA
        QnADTO qnADTO = qnAMapper.toDto(qnA);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQnAMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(qnADTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the QnA in the database
        List<QnA> qnAList = qnARepository.findAll();
        assertThat(qnAList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamQnA() throws Exception {
        int databaseSizeBeforeUpdate = qnARepository.findAll().size();
        qnA.setId(count.incrementAndGet());

        // Create the QnA
        QnADTO qnADTO = qnAMapper.toDto(qnA);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQnAMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(qnADTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the QnA in the database
        List<QnA> qnAList = qnARepository.findAll();
        assertThat(qnAList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateQnAWithPatch() throws Exception {
        // Initialize the database
        qnARepository.saveAndFlush(qnA);

        int databaseSizeBeforeUpdate = qnARepository.findAll().size();

        // Update the qnA using partial update
        QnA partialUpdatedQnA = new QnA();
        partialUpdatedQnA.setId(qnA.getId());

        partialUpdatedQnA.email(UPDATED_EMAIL).status(UPDATED_STATUS).updatedAt(UPDATED_UPDATED_AT);

        restQnAMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedQnA.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedQnA))
            )
            .andExpect(status().isOk());

        // Validate the QnA in the database
        List<QnA> qnAList = qnARepository.findAll();
        assertThat(qnAList).hasSize(databaseSizeBeforeUpdate);
        QnA testQnA = qnAList.get(qnAList.size() - 1);
        assertThat(testQnA.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testQnA.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testQnA.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testQnA.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testQnA.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
    }

    @Test
    @Transactional
    void fullUpdateQnAWithPatch() throws Exception {
        // Initialize the database
        qnARepository.saveAndFlush(qnA);

        int databaseSizeBeforeUpdate = qnARepository.findAll().size();

        // Update the qnA using partial update
        QnA partialUpdatedQnA = new QnA();
        partialUpdatedQnA.setId(qnA.getId());

        partialUpdatedQnA
            .userId(UPDATED_USER_ID)
            .email(UPDATED_EMAIL)
            .status(UPDATED_STATUS)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);

        restQnAMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedQnA.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedQnA))
            )
            .andExpect(status().isOk());

        // Validate the QnA in the database
        List<QnA> qnAList = qnARepository.findAll();
        assertThat(qnAList).hasSize(databaseSizeBeforeUpdate);
        QnA testQnA = qnAList.get(qnAList.size() - 1);
        assertThat(testQnA.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testQnA.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testQnA.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testQnA.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testQnA.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
    }

    @Test
    @Transactional
    void patchNonExistingQnA() throws Exception {
        int databaseSizeBeforeUpdate = qnARepository.findAll().size();
        qnA.setId(count.incrementAndGet());

        // Create the QnA
        QnADTO qnADTO = qnAMapper.toDto(qnA);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQnAMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, qnADTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(qnADTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the QnA in the database
        List<QnA> qnAList = qnARepository.findAll();
        assertThat(qnAList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchQnA() throws Exception {
        int databaseSizeBeforeUpdate = qnARepository.findAll().size();
        qnA.setId(count.incrementAndGet());

        // Create the QnA
        QnADTO qnADTO = qnAMapper.toDto(qnA);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQnAMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(qnADTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the QnA in the database
        List<QnA> qnAList = qnARepository.findAll();
        assertThat(qnAList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamQnA() throws Exception {
        int databaseSizeBeforeUpdate = qnARepository.findAll().size();
        qnA.setId(count.incrementAndGet());

        // Create the QnA
        QnADTO qnADTO = qnAMapper.toDto(qnA);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQnAMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(qnADTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the QnA in the database
        List<QnA> qnAList = qnARepository.findAll();
        assertThat(qnAList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteQnA() throws Exception {
        // Initialize the database
        qnARepository.saveAndFlush(qnA);

        int databaseSizeBeforeDelete = qnARepository.findAll().size();

        // Delete the qnA
        restQnAMockMvc.perform(delete(ENTITY_API_URL_ID, qnA.getId()).accept(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<QnA> qnAList = qnARepository.findAll();
        assertThat(qnAList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
