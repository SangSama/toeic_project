package com.sangtt.toeic_test.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sangtt.toeic_test.IntegrationTest;
import com.sangtt.toeic_test.domain.DetailsToeicUser;
import com.sangtt.toeic_test.repository.DetailsToeicUserRepository;
import com.sangtt.toeic_test.service.dto.DetailsToeicUserDTO;
import com.sangtt.toeic_test.service.mapper.DetailsToeicUserMapper;
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
 * Integration tests for the {@link DetailsToeicUserResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DetailsToeicUserResourceIT {

    private static final Long DEFAULT_TOEIC_ID = 1L;
    private static final Long UPDATED_TOEIC_ID = 2L;

    private static final Long DEFAULT_PART_ID = 1L;
    private static final Long UPDATED_PART_ID = 2L;

    private static final Long DEFAULT_QUESTION_ID = 1L;
    private static final Long UPDATED_QUESTION_ID = 2L;

    private static final Long DEFAULT_STATUS = 1L;
    private static final Long UPDATED_STATUS = 2L;

    private static final LocalDate DEFAULT_CREATED_AT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_AT = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_UPDATED_AT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_AT = LocalDate.now(ZoneId.systemDefault());

    private static final String ENTITY_API_URL = "/api/details-toeic-users";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private DetailsToeicUserRepository detailsToeicUserRepository;

    @Autowired
    private DetailsToeicUserMapper detailsToeicUserMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDetailsToeicUserMockMvc;

    private DetailsToeicUser detailsToeicUser;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DetailsToeicUser createEntity(EntityManager em) {
        DetailsToeicUser detailsToeicUser = new DetailsToeicUser()
            .toeicId(DEFAULT_TOEIC_ID)
            .partId(DEFAULT_PART_ID)
            .questionId(DEFAULT_QUESTION_ID)
            .status(DEFAULT_STATUS)
            .createdAt(DEFAULT_CREATED_AT)
            .updatedAt(DEFAULT_UPDATED_AT);
        return detailsToeicUser;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DetailsToeicUser createUpdatedEntity(EntityManager em) {
        DetailsToeicUser detailsToeicUser = new DetailsToeicUser()
            .toeicId(UPDATED_TOEIC_ID)
            .partId(UPDATED_PART_ID)
            .questionId(UPDATED_QUESTION_ID)
            .status(UPDATED_STATUS)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);
        return detailsToeicUser;
    }

    @BeforeEach
    public void initTest() {
        detailsToeicUser = createEntity(em);
    }

    @Test
    @Transactional
    void createDetailsToeicUser() throws Exception {
        int databaseSizeBeforeCreate = detailsToeicUserRepository.findAll().size();
        // Create the DetailsToeicUser
        DetailsToeicUserDTO detailsToeicUserDTO = detailsToeicUserMapper.toDto(detailsToeicUser);
        restDetailsToeicUserMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(detailsToeicUserDTO))
            )
            .andExpect(status().isCreated());

        // Validate the DetailsToeicUser in the database
        List<DetailsToeicUser> detailsToeicUserList = detailsToeicUserRepository.findAll();
        assertThat(detailsToeicUserList).hasSize(databaseSizeBeforeCreate + 1);
        DetailsToeicUser testDetailsToeicUser = detailsToeicUserList.get(detailsToeicUserList.size() - 1);
        assertThat(testDetailsToeicUser.getToeicId()).isEqualTo(DEFAULT_TOEIC_ID);
        assertThat(testDetailsToeicUser.getPartId()).isEqualTo(DEFAULT_PART_ID);
        assertThat(testDetailsToeicUser.getQuestionId()).isEqualTo(DEFAULT_QUESTION_ID);
        assertThat(testDetailsToeicUser.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testDetailsToeicUser.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testDetailsToeicUser.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
    }

    @Test
    @Transactional
    void createDetailsToeicUserWithExistingId() throws Exception {
        // Create the DetailsToeicUser with an existing ID
        detailsToeicUser.setId(1L);
        DetailsToeicUserDTO detailsToeicUserDTO = detailsToeicUserMapper.toDto(detailsToeicUser);

        int databaseSizeBeforeCreate = detailsToeicUserRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDetailsToeicUserMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(detailsToeicUserDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DetailsToeicUser in the database
        List<DetailsToeicUser> detailsToeicUserList = detailsToeicUserRepository.findAll();
        assertThat(detailsToeicUserList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkToeicIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = detailsToeicUserRepository.findAll().size();
        // set the field null
        detailsToeicUser.setToeicId(null);

        // Create the DetailsToeicUser, which fails.
        DetailsToeicUserDTO detailsToeicUserDTO = detailsToeicUserMapper.toDto(detailsToeicUser);

        restDetailsToeicUserMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(detailsToeicUserDTO))
            )
            .andExpect(status().isBadRequest());

        List<DetailsToeicUser> detailsToeicUserList = detailsToeicUserRepository.findAll();
        assertThat(detailsToeicUserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPartIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = detailsToeicUserRepository.findAll().size();
        // set the field null
        detailsToeicUser.setPartId(null);

        // Create the DetailsToeicUser, which fails.
        DetailsToeicUserDTO detailsToeicUserDTO = detailsToeicUserMapper.toDto(detailsToeicUser);

        restDetailsToeicUserMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(detailsToeicUserDTO))
            )
            .andExpect(status().isBadRequest());

        List<DetailsToeicUser> detailsToeicUserList = detailsToeicUserRepository.findAll();
        assertThat(detailsToeicUserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkQuestionIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = detailsToeicUserRepository.findAll().size();
        // set the field null
        detailsToeicUser.setQuestionId(null);

        // Create the DetailsToeicUser, which fails.
        DetailsToeicUserDTO detailsToeicUserDTO = detailsToeicUserMapper.toDto(detailsToeicUser);

        restDetailsToeicUserMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(detailsToeicUserDTO))
            )
            .andExpect(status().isBadRequest());

        List<DetailsToeicUser> detailsToeicUserList = detailsToeicUserRepository.findAll();
        assertThat(detailsToeicUserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = detailsToeicUserRepository.findAll().size();
        // set the field null
        detailsToeicUser.setStatus(null);

        // Create the DetailsToeicUser, which fails.
        DetailsToeicUserDTO detailsToeicUserDTO = detailsToeicUserMapper.toDto(detailsToeicUser);

        restDetailsToeicUserMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(detailsToeicUserDTO))
            )
            .andExpect(status().isBadRequest());

        List<DetailsToeicUser> detailsToeicUserList = detailsToeicUserRepository.findAll();
        assertThat(detailsToeicUserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = detailsToeicUserRepository.findAll().size();
        // set the field null
        detailsToeicUser.setCreatedAt(null);

        // Create the DetailsToeicUser, which fails.
        DetailsToeicUserDTO detailsToeicUserDTO = detailsToeicUserMapper.toDto(detailsToeicUser);

        restDetailsToeicUserMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(detailsToeicUserDTO))
            )
            .andExpect(status().isBadRequest());

        List<DetailsToeicUser> detailsToeicUserList = detailsToeicUserRepository.findAll();
        assertThat(detailsToeicUserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkUpdatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = detailsToeicUserRepository.findAll().size();
        // set the field null
        detailsToeicUser.setUpdatedAt(null);

        // Create the DetailsToeicUser, which fails.
        DetailsToeicUserDTO detailsToeicUserDTO = detailsToeicUserMapper.toDto(detailsToeicUser);

        restDetailsToeicUserMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(detailsToeicUserDTO))
            )
            .andExpect(status().isBadRequest());

        List<DetailsToeicUser> detailsToeicUserList = detailsToeicUserRepository.findAll();
        assertThat(detailsToeicUserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllDetailsToeicUsers() throws Exception {
        // Initialize the database
        detailsToeicUserRepository.saveAndFlush(detailsToeicUser);

        // Get all the detailsToeicUserList
        restDetailsToeicUserMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(detailsToeicUser.getId().intValue())))
            .andExpect(jsonPath("$.[*].toeicId").value(hasItem(DEFAULT_TOEIC_ID.intValue())))
            .andExpect(jsonPath("$.[*].partId").value(hasItem(DEFAULT_PART_ID.intValue())))
            .andExpect(jsonPath("$.[*].questionId").value(hasItem(DEFAULT_QUESTION_ID.intValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.intValue())))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(DEFAULT_UPDATED_AT.toString())));
    }

    @Test
    @Transactional
    void getDetailsToeicUser() throws Exception {
        // Initialize the database
        detailsToeicUserRepository.saveAndFlush(detailsToeicUser);

        // Get the detailsToeicUser
        restDetailsToeicUserMockMvc
            .perform(get(ENTITY_API_URL_ID, detailsToeicUser.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(detailsToeicUser.getId().intValue()))
            .andExpect(jsonPath("$.toeicId").value(DEFAULT_TOEIC_ID.intValue()))
            .andExpect(jsonPath("$.partId").value(DEFAULT_PART_ID.intValue()))
            .andExpect(jsonPath("$.questionId").value(DEFAULT_QUESTION_ID.intValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.intValue()))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()))
            .andExpect(jsonPath("$.updatedAt").value(DEFAULT_UPDATED_AT.toString()));
    }

    @Test
    @Transactional
    void getNonExistingDetailsToeicUser() throws Exception {
        // Get the detailsToeicUser
        restDetailsToeicUserMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewDetailsToeicUser() throws Exception {
        // Initialize the database
        detailsToeicUserRepository.saveAndFlush(detailsToeicUser);

        int databaseSizeBeforeUpdate = detailsToeicUserRepository.findAll().size();

        // Update the detailsToeicUser
        DetailsToeicUser updatedDetailsToeicUser = detailsToeicUserRepository.findById(detailsToeicUser.getId()).get();
        // Disconnect from session so that the updates on updatedDetailsToeicUser are not directly saved in db
        em.detach(updatedDetailsToeicUser);
        updatedDetailsToeicUser
            .toeicId(UPDATED_TOEIC_ID)
            .partId(UPDATED_PART_ID)
            .questionId(UPDATED_QUESTION_ID)
            .status(UPDATED_STATUS)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);
        DetailsToeicUserDTO detailsToeicUserDTO = detailsToeicUserMapper.toDto(updatedDetailsToeicUser);

        restDetailsToeicUserMockMvc
            .perform(
                put(ENTITY_API_URL_ID, detailsToeicUserDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(detailsToeicUserDTO))
            )
            .andExpect(status().isOk());

        // Validate the DetailsToeicUser in the database
        List<DetailsToeicUser> detailsToeicUserList = detailsToeicUserRepository.findAll();
        assertThat(detailsToeicUserList).hasSize(databaseSizeBeforeUpdate);
        DetailsToeicUser testDetailsToeicUser = detailsToeicUserList.get(detailsToeicUserList.size() - 1);
        assertThat(testDetailsToeicUser.getToeicId()).isEqualTo(UPDATED_TOEIC_ID);
        assertThat(testDetailsToeicUser.getPartId()).isEqualTo(UPDATED_PART_ID);
        assertThat(testDetailsToeicUser.getQuestionId()).isEqualTo(UPDATED_QUESTION_ID);
        assertThat(testDetailsToeicUser.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testDetailsToeicUser.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testDetailsToeicUser.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
    }

    @Test
    @Transactional
    void putNonExistingDetailsToeicUser() throws Exception {
        int databaseSizeBeforeUpdate = detailsToeicUserRepository.findAll().size();
        detailsToeicUser.setId(count.incrementAndGet());

        // Create the DetailsToeicUser
        DetailsToeicUserDTO detailsToeicUserDTO = detailsToeicUserMapper.toDto(detailsToeicUser);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDetailsToeicUserMockMvc
            .perform(
                put(ENTITY_API_URL_ID, detailsToeicUserDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(detailsToeicUserDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DetailsToeicUser in the database
        List<DetailsToeicUser> detailsToeicUserList = detailsToeicUserRepository.findAll();
        assertThat(detailsToeicUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDetailsToeicUser() throws Exception {
        int databaseSizeBeforeUpdate = detailsToeicUserRepository.findAll().size();
        detailsToeicUser.setId(count.incrementAndGet());

        // Create the DetailsToeicUser
        DetailsToeicUserDTO detailsToeicUserDTO = detailsToeicUserMapper.toDto(detailsToeicUser);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDetailsToeicUserMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(detailsToeicUserDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DetailsToeicUser in the database
        List<DetailsToeicUser> detailsToeicUserList = detailsToeicUserRepository.findAll();
        assertThat(detailsToeicUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDetailsToeicUser() throws Exception {
        int databaseSizeBeforeUpdate = detailsToeicUserRepository.findAll().size();
        detailsToeicUser.setId(count.incrementAndGet());

        // Create the DetailsToeicUser
        DetailsToeicUserDTO detailsToeicUserDTO = detailsToeicUserMapper.toDto(detailsToeicUser);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDetailsToeicUserMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(detailsToeicUserDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the DetailsToeicUser in the database
        List<DetailsToeicUser> detailsToeicUserList = detailsToeicUserRepository.findAll();
        assertThat(detailsToeicUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDetailsToeicUserWithPatch() throws Exception {
        // Initialize the database
        detailsToeicUserRepository.saveAndFlush(detailsToeicUser);

        int databaseSizeBeforeUpdate = detailsToeicUserRepository.findAll().size();

        // Update the detailsToeicUser using partial update
        DetailsToeicUser partialUpdatedDetailsToeicUser = new DetailsToeicUser();
        partialUpdatedDetailsToeicUser.setId(detailsToeicUser.getId());

        partialUpdatedDetailsToeicUser.toeicId(UPDATED_TOEIC_ID).status(UPDATED_STATUS).createdAt(UPDATED_CREATED_AT);

        restDetailsToeicUserMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDetailsToeicUser.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDetailsToeicUser))
            )
            .andExpect(status().isOk());

        // Validate the DetailsToeicUser in the database
        List<DetailsToeicUser> detailsToeicUserList = detailsToeicUserRepository.findAll();
        assertThat(detailsToeicUserList).hasSize(databaseSizeBeforeUpdate);
        DetailsToeicUser testDetailsToeicUser = detailsToeicUserList.get(detailsToeicUserList.size() - 1);
        assertThat(testDetailsToeicUser.getToeicId()).isEqualTo(UPDATED_TOEIC_ID);
        assertThat(testDetailsToeicUser.getPartId()).isEqualTo(DEFAULT_PART_ID);
        assertThat(testDetailsToeicUser.getQuestionId()).isEqualTo(DEFAULT_QUESTION_ID);
        assertThat(testDetailsToeicUser.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testDetailsToeicUser.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testDetailsToeicUser.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
    }

    @Test
    @Transactional
    void fullUpdateDetailsToeicUserWithPatch() throws Exception {
        // Initialize the database
        detailsToeicUserRepository.saveAndFlush(detailsToeicUser);

        int databaseSizeBeforeUpdate = detailsToeicUserRepository.findAll().size();

        // Update the detailsToeicUser using partial update
        DetailsToeicUser partialUpdatedDetailsToeicUser = new DetailsToeicUser();
        partialUpdatedDetailsToeicUser.setId(detailsToeicUser.getId());

        partialUpdatedDetailsToeicUser
            .toeicId(UPDATED_TOEIC_ID)
            .partId(UPDATED_PART_ID)
            .questionId(UPDATED_QUESTION_ID)
            .status(UPDATED_STATUS)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);

        restDetailsToeicUserMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDetailsToeicUser.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDetailsToeicUser))
            )
            .andExpect(status().isOk());

        // Validate the DetailsToeicUser in the database
        List<DetailsToeicUser> detailsToeicUserList = detailsToeicUserRepository.findAll();
        assertThat(detailsToeicUserList).hasSize(databaseSizeBeforeUpdate);
        DetailsToeicUser testDetailsToeicUser = detailsToeicUserList.get(detailsToeicUserList.size() - 1);
        assertThat(testDetailsToeicUser.getToeicId()).isEqualTo(UPDATED_TOEIC_ID);
        assertThat(testDetailsToeicUser.getPartId()).isEqualTo(UPDATED_PART_ID);
        assertThat(testDetailsToeicUser.getQuestionId()).isEqualTo(UPDATED_QUESTION_ID);
        assertThat(testDetailsToeicUser.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testDetailsToeicUser.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testDetailsToeicUser.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
    }

    @Test
    @Transactional
    void patchNonExistingDetailsToeicUser() throws Exception {
        int databaseSizeBeforeUpdate = detailsToeicUserRepository.findAll().size();
        detailsToeicUser.setId(count.incrementAndGet());

        // Create the DetailsToeicUser
        DetailsToeicUserDTO detailsToeicUserDTO = detailsToeicUserMapper.toDto(detailsToeicUser);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDetailsToeicUserMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, detailsToeicUserDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(detailsToeicUserDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DetailsToeicUser in the database
        List<DetailsToeicUser> detailsToeicUserList = detailsToeicUserRepository.findAll();
        assertThat(detailsToeicUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDetailsToeicUser() throws Exception {
        int databaseSizeBeforeUpdate = detailsToeicUserRepository.findAll().size();
        detailsToeicUser.setId(count.incrementAndGet());

        // Create the DetailsToeicUser
        DetailsToeicUserDTO detailsToeicUserDTO = detailsToeicUserMapper.toDto(detailsToeicUser);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDetailsToeicUserMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(detailsToeicUserDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DetailsToeicUser in the database
        List<DetailsToeicUser> detailsToeicUserList = detailsToeicUserRepository.findAll();
        assertThat(detailsToeicUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDetailsToeicUser() throws Exception {
        int databaseSizeBeforeUpdate = detailsToeicUserRepository.findAll().size();
        detailsToeicUser.setId(count.incrementAndGet());

        // Create the DetailsToeicUser
        DetailsToeicUserDTO detailsToeicUserDTO = detailsToeicUserMapper.toDto(detailsToeicUser);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDetailsToeicUserMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(detailsToeicUserDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the DetailsToeicUser in the database
        List<DetailsToeicUser> detailsToeicUserList = detailsToeicUserRepository.findAll();
        assertThat(detailsToeicUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDetailsToeicUser() throws Exception {
        // Initialize the database
        detailsToeicUserRepository.saveAndFlush(detailsToeicUser);

        int databaseSizeBeforeDelete = detailsToeicUserRepository.findAll().size();

        // Delete the detailsToeicUser
        restDetailsToeicUserMockMvc
            .perform(delete(ENTITY_API_URL_ID, detailsToeicUser.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DetailsToeicUser> detailsToeicUserList = detailsToeicUserRepository.findAll();
        assertThat(detailsToeicUserList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
