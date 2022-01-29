package com.sangtt.toeic_test.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sangtt.toeic_test.IntegrationTest;
import com.sangtt.toeic_test.domain.DetailsVocabularyUser;
import com.sangtt.toeic_test.repository.DetailsVocabularyUserRepository;
import com.sangtt.toeic_test.service.dto.DetailsVocabularyUserDTO;
import com.sangtt.toeic_test.service.mapper.DetailsVocabularyUserMapper;
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
 * Integration tests for the {@link DetailsVocabularyUserResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DetailsVocabularyUserResourceIT {

    private static final Long DEFAULT_VOCABULARY_ID = 1L;
    private static final Long UPDATED_VOCABULARY_ID = 2L;

    private static final Long DEFAULT_STATUS = 1L;
    private static final Long UPDATED_STATUS = 2L;

    private static final LocalDate DEFAULT_CREATED_AT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_AT = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_UPDATED_AT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_AT = LocalDate.now(ZoneId.systemDefault());

    private static final String ENTITY_API_URL = "/api/details-vocabulary-users";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private DetailsVocabularyUserRepository detailsVocabularyUserRepository;

    @Autowired
    private DetailsVocabularyUserMapper detailsVocabularyUserMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDetailsVocabularyUserMockMvc;

    private DetailsVocabularyUser detailsVocabularyUser;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DetailsVocabularyUser createEntity(EntityManager em) {
        DetailsVocabularyUser detailsVocabularyUser = new DetailsVocabularyUser()
            .vocabularyId(DEFAULT_VOCABULARY_ID)
            .status(DEFAULT_STATUS)
            .createdAt(DEFAULT_CREATED_AT)
            .updatedAt(DEFAULT_UPDATED_AT);
        return detailsVocabularyUser;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DetailsVocabularyUser createUpdatedEntity(EntityManager em) {
        DetailsVocabularyUser detailsVocabularyUser = new DetailsVocabularyUser()
            .vocabularyId(UPDATED_VOCABULARY_ID)
            .status(UPDATED_STATUS)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);
        return detailsVocabularyUser;
    }

    @BeforeEach
    public void initTest() {
        detailsVocabularyUser = createEntity(em);
    }

    @Test
    @Transactional
    void createDetailsVocabularyUser() throws Exception {
        int databaseSizeBeforeCreate = detailsVocabularyUserRepository.findAll().size();
        // Create the DetailsVocabularyUser
        DetailsVocabularyUserDTO detailsVocabularyUserDTO = detailsVocabularyUserMapper.toDto(detailsVocabularyUser);
        restDetailsVocabularyUserMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(detailsVocabularyUserDTO))
            )
            .andExpect(status().isCreated());

        // Validate the DetailsVocabularyUser in the database
        List<DetailsVocabularyUser> detailsVocabularyUserList = detailsVocabularyUserRepository.findAll();
        assertThat(detailsVocabularyUserList).hasSize(databaseSizeBeforeCreate + 1);
        DetailsVocabularyUser testDetailsVocabularyUser = detailsVocabularyUserList.get(detailsVocabularyUserList.size() - 1);
        assertThat(testDetailsVocabularyUser.getVocabularyId()).isEqualTo(DEFAULT_VOCABULARY_ID);
        assertThat(testDetailsVocabularyUser.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testDetailsVocabularyUser.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testDetailsVocabularyUser.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
    }

    @Test
    @Transactional
    void createDetailsVocabularyUserWithExistingId() throws Exception {
        // Create the DetailsVocabularyUser with an existing ID
        detailsVocabularyUser.setId(1L);
        DetailsVocabularyUserDTO detailsVocabularyUserDTO = detailsVocabularyUserMapper.toDto(detailsVocabularyUser);

        int databaseSizeBeforeCreate = detailsVocabularyUserRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDetailsVocabularyUserMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(detailsVocabularyUserDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DetailsVocabularyUser in the database
        List<DetailsVocabularyUser> detailsVocabularyUserList = detailsVocabularyUserRepository.findAll();
        assertThat(detailsVocabularyUserList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkVocabularyIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = detailsVocabularyUserRepository.findAll().size();
        // set the field null
        detailsVocabularyUser.setVocabularyId(null);

        // Create the DetailsVocabularyUser, which fails.
        DetailsVocabularyUserDTO detailsVocabularyUserDTO = detailsVocabularyUserMapper.toDto(detailsVocabularyUser);

        restDetailsVocabularyUserMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(detailsVocabularyUserDTO))
            )
            .andExpect(status().isBadRequest());

        List<DetailsVocabularyUser> detailsVocabularyUserList = detailsVocabularyUserRepository.findAll();
        assertThat(detailsVocabularyUserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = detailsVocabularyUserRepository.findAll().size();
        // set the field null
        detailsVocabularyUser.setStatus(null);

        // Create the DetailsVocabularyUser, which fails.
        DetailsVocabularyUserDTO detailsVocabularyUserDTO = detailsVocabularyUserMapper.toDto(detailsVocabularyUser);

        restDetailsVocabularyUserMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(detailsVocabularyUserDTO))
            )
            .andExpect(status().isBadRequest());

        List<DetailsVocabularyUser> detailsVocabularyUserList = detailsVocabularyUserRepository.findAll();
        assertThat(detailsVocabularyUserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = detailsVocabularyUserRepository.findAll().size();
        // set the field null
        detailsVocabularyUser.setCreatedAt(null);

        // Create the DetailsVocabularyUser, which fails.
        DetailsVocabularyUserDTO detailsVocabularyUserDTO = detailsVocabularyUserMapper.toDto(detailsVocabularyUser);

        restDetailsVocabularyUserMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(detailsVocabularyUserDTO))
            )
            .andExpect(status().isBadRequest());

        List<DetailsVocabularyUser> detailsVocabularyUserList = detailsVocabularyUserRepository.findAll();
        assertThat(detailsVocabularyUserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkUpdatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = detailsVocabularyUserRepository.findAll().size();
        // set the field null
        detailsVocabularyUser.setUpdatedAt(null);

        // Create the DetailsVocabularyUser, which fails.
        DetailsVocabularyUserDTO detailsVocabularyUserDTO = detailsVocabularyUserMapper.toDto(detailsVocabularyUser);

        restDetailsVocabularyUserMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(detailsVocabularyUserDTO))
            )
            .andExpect(status().isBadRequest());

        List<DetailsVocabularyUser> detailsVocabularyUserList = detailsVocabularyUserRepository.findAll();
        assertThat(detailsVocabularyUserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllDetailsVocabularyUsers() throws Exception {
        // Initialize the database
        detailsVocabularyUserRepository.saveAndFlush(detailsVocabularyUser);

        // Get all the detailsVocabularyUserList
        restDetailsVocabularyUserMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(detailsVocabularyUser.getId().intValue())))
            .andExpect(jsonPath("$.[*].vocabularyId").value(hasItem(DEFAULT_VOCABULARY_ID.intValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.intValue())))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(DEFAULT_UPDATED_AT.toString())));
    }

    @Test
    @Transactional
    void getDetailsVocabularyUser() throws Exception {
        // Initialize the database
        detailsVocabularyUserRepository.saveAndFlush(detailsVocabularyUser);

        // Get the detailsVocabularyUser
        restDetailsVocabularyUserMockMvc
            .perform(get(ENTITY_API_URL_ID, detailsVocabularyUser.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(detailsVocabularyUser.getId().intValue()))
            .andExpect(jsonPath("$.vocabularyId").value(DEFAULT_VOCABULARY_ID.intValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.intValue()))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()))
            .andExpect(jsonPath("$.updatedAt").value(DEFAULT_UPDATED_AT.toString()));
    }

    @Test
    @Transactional
    void getNonExistingDetailsVocabularyUser() throws Exception {
        // Get the detailsVocabularyUser
        restDetailsVocabularyUserMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewDetailsVocabularyUser() throws Exception {
        // Initialize the database
        detailsVocabularyUserRepository.saveAndFlush(detailsVocabularyUser);

        int databaseSizeBeforeUpdate = detailsVocabularyUserRepository.findAll().size();

        // Update the detailsVocabularyUser
        DetailsVocabularyUser updatedDetailsVocabularyUser = detailsVocabularyUserRepository.findById(detailsVocabularyUser.getId()).get();
        // Disconnect from session so that the updates on updatedDetailsVocabularyUser are not directly saved in db
        em.detach(updatedDetailsVocabularyUser);
        updatedDetailsVocabularyUser
            .vocabularyId(UPDATED_VOCABULARY_ID)
            .status(UPDATED_STATUS)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);
        DetailsVocabularyUserDTO detailsVocabularyUserDTO = detailsVocabularyUserMapper.toDto(updatedDetailsVocabularyUser);

        restDetailsVocabularyUserMockMvc
            .perform(
                put(ENTITY_API_URL_ID, detailsVocabularyUserDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(detailsVocabularyUserDTO))
            )
            .andExpect(status().isOk());

        // Validate the DetailsVocabularyUser in the database
        List<DetailsVocabularyUser> detailsVocabularyUserList = detailsVocabularyUserRepository.findAll();
        assertThat(detailsVocabularyUserList).hasSize(databaseSizeBeforeUpdate);
        DetailsVocabularyUser testDetailsVocabularyUser = detailsVocabularyUserList.get(detailsVocabularyUserList.size() - 1);
        assertThat(testDetailsVocabularyUser.getVocabularyId()).isEqualTo(UPDATED_VOCABULARY_ID);
        assertThat(testDetailsVocabularyUser.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testDetailsVocabularyUser.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testDetailsVocabularyUser.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
    }

    @Test
    @Transactional
    void putNonExistingDetailsVocabularyUser() throws Exception {
        int databaseSizeBeforeUpdate = detailsVocabularyUserRepository.findAll().size();
        detailsVocabularyUser.setId(count.incrementAndGet());

        // Create the DetailsVocabularyUser
        DetailsVocabularyUserDTO detailsVocabularyUserDTO = detailsVocabularyUserMapper.toDto(detailsVocabularyUser);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDetailsVocabularyUserMockMvc
            .perform(
                put(ENTITY_API_URL_ID, detailsVocabularyUserDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(detailsVocabularyUserDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DetailsVocabularyUser in the database
        List<DetailsVocabularyUser> detailsVocabularyUserList = detailsVocabularyUserRepository.findAll();
        assertThat(detailsVocabularyUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDetailsVocabularyUser() throws Exception {
        int databaseSizeBeforeUpdate = detailsVocabularyUserRepository.findAll().size();
        detailsVocabularyUser.setId(count.incrementAndGet());

        // Create the DetailsVocabularyUser
        DetailsVocabularyUserDTO detailsVocabularyUserDTO = detailsVocabularyUserMapper.toDto(detailsVocabularyUser);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDetailsVocabularyUserMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(detailsVocabularyUserDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DetailsVocabularyUser in the database
        List<DetailsVocabularyUser> detailsVocabularyUserList = detailsVocabularyUserRepository.findAll();
        assertThat(detailsVocabularyUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDetailsVocabularyUser() throws Exception {
        int databaseSizeBeforeUpdate = detailsVocabularyUserRepository.findAll().size();
        detailsVocabularyUser.setId(count.incrementAndGet());

        // Create the DetailsVocabularyUser
        DetailsVocabularyUserDTO detailsVocabularyUserDTO = detailsVocabularyUserMapper.toDto(detailsVocabularyUser);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDetailsVocabularyUserMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(detailsVocabularyUserDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the DetailsVocabularyUser in the database
        List<DetailsVocabularyUser> detailsVocabularyUserList = detailsVocabularyUserRepository.findAll();
        assertThat(detailsVocabularyUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDetailsVocabularyUserWithPatch() throws Exception {
        // Initialize the database
        detailsVocabularyUserRepository.saveAndFlush(detailsVocabularyUser);

        int databaseSizeBeforeUpdate = detailsVocabularyUserRepository.findAll().size();

        // Update the detailsVocabularyUser using partial update
        DetailsVocabularyUser partialUpdatedDetailsVocabularyUser = new DetailsVocabularyUser();
        partialUpdatedDetailsVocabularyUser.setId(detailsVocabularyUser.getId());

        partialUpdatedDetailsVocabularyUser.vocabularyId(UPDATED_VOCABULARY_ID).updatedAt(UPDATED_UPDATED_AT);

        restDetailsVocabularyUserMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDetailsVocabularyUser.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDetailsVocabularyUser))
            )
            .andExpect(status().isOk());

        // Validate the DetailsVocabularyUser in the database
        List<DetailsVocabularyUser> detailsVocabularyUserList = detailsVocabularyUserRepository.findAll();
        assertThat(detailsVocabularyUserList).hasSize(databaseSizeBeforeUpdate);
        DetailsVocabularyUser testDetailsVocabularyUser = detailsVocabularyUserList.get(detailsVocabularyUserList.size() - 1);
        assertThat(testDetailsVocabularyUser.getVocabularyId()).isEqualTo(UPDATED_VOCABULARY_ID);
        assertThat(testDetailsVocabularyUser.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testDetailsVocabularyUser.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testDetailsVocabularyUser.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
    }

    @Test
    @Transactional
    void fullUpdateDetailsVocabularyUserWithPatch() throws Exception {
        // Initialize the database
        detailsVocabularyUserRepository.saveAndFlush(detailsVocabularyUser);

        int databaseSizeBeforeUpdate = detailsVocabularyUserRepository.findAll().size();

        // Update the detailsVocabularyUser using partial update
        DetailsVocabularyUser partialUpdatedDetailsVocabularyUser = new DetailsVocabularyUser();
        partialUpdatedDetailsVocabularyUser.setId(detailsVocabularyUser.getId());

        partialUpdatedDetailsVocabularyUser
            .vocabularyId(UPDATED_VOCABULARY_ID)
            .status(UPDATED_STATUS)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);

        restDetailsVocabularyUserMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDetailsVocabularyUser.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDetailsVocabularyUser))
            )
            .andExpect(status().isOk());

        // Validate the DetailsVocabularyUser in the database
        List<DetailsVocabularyUser> detailsVocabularyUserList = detailsVocabularyUserRepository.findAll();
        assertThat(detailsVocabularyUserList).hasSize(databaseSizeBeforeUpdate);
        DetailsVocabularyUser testDetailsVocabularyUser = detailsVocabularyUserList.get(detailsVocabularyUserList.size() - 1);
        assertThat(testDetailsVocabularyUser.getVocabularyId()).isEqualTo(UPDATED_VOCABULARY_ID);
        assertThat(testDetailsVocabularyUser.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testDetailsVocabularyUser.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testDetailsVocabularyUser.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
    }

    @Test
    @Transactional
    void patchNonExistingDetailsVocabularyUser() throws Exception {
        int databaseSizeBeforeUpdate = detailsVocabularyUserRepository.findAll().size();
        detailsVocabularyUser.setId(count.incrementAndGet());

        // Create the DetailsVocabularyUser
        DetailsVocabularyUserDTO detailsVocabularyUserDTO = detailsVocabularyUserMapper.toDto(detailsVocabularyUser);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDetailsVocabularyUserMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, detailsVocabularyUserDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(detailsVocabularyUserDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DetailsVocabularyUser in the database
        List<DetailsVocabularyUser> detailsVocabularyUserList = detailsVocabularyUserRepository.findAll();
        assertThat(detailsVocabularyUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDetailsVocabularyUser() throws Exception {
        int databaseSizeBeforeUpdate = detailsVocabularyUserRepository.findAll().size();
        detailsVocabularyUser.setId(count.incrementAndGet());

        // Create the DetailsVocabularyUser
        DetailsVocabularyUserDTO detailsVocabularyUserDTO = detailsVocabularyUserMapper.toDto(detailsVocabularyUser);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDetailsVocabularyUserMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(detailsVocabularyUserDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DetailsVocabularyUser in the database
        List<DetailsVocabularyUser> detailsVocabularyUserList = detailsVocabularyUserRepository.findAll();
        assertThat(detailsVocabularyUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDetailsVocabularyUser() throws Exception {
        int databaseSizeBeforeUpdate = detailsVocabularyUserRepository.findAll().size();
        detailsVocabularyUser.setId(count.incrementAndGet());

        // Create the DetailsVocabularyUser
        DetailsVocabularyUserDTO detailsVocabularyUserDTO = detailsVocabularyUserMapper.toDto(detailsVocabularyUser);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDetailsVocabularyUserMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(detailsVocabularyUserDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the DetailsVocabularyUser in the database
        List<DetailsVocabularyUser> detailsVocabularyUserList = detailsVocabularyUserRepository.findAll();
        assertThat(detailsVocabularyUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDetailsVocabularyUser() throws Exception {
        // Initialize the database
        detailsVocabularyUserRepository.saveAndFlush(detailsVocabularyUser);

        int databaseSizeBeforeDelete = detailsVocabularyUserRepository.findAll().size();

        // Delete the detailsVocabularyUser
        restDetailsVocabularyUserMockMvc
            .perform(delete(ENTITY_API_URL_ID, detailsVocabularyUser.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DetailsVocabularyUser> detailsVocabularyUserList = detailsVocabularyUserRepository.findAll();
        assertThat(detailsVocabularyUserList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
