package com.sangtt.toeic_test.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sangtt.toeic_test.IntegrationTest;
import com.sangtt.toeic_test.domain.VocabularyUser;
import com.sangtt.toeic_test.repository.VocabularyUserRepository;
import com.sangtt.toeic_test.service.dto.VocabularyUserDTO;
import com.sangtt.toeic_test.service.mapper.VocabularyUserMapper;
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
 * Integration tests for the {@link VocabularyUserResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class VocabularyUserResourceIT {

    private static final Long DEFAULT_USER_ID = 1L;
    private static final Long UPDATED_USER_ID = 2L;

    private static final Long DEFAULT_SCORE = 1L;
    private static final Long UPDATED_SCORE = 2L;

    private static final LocalDate DEFAULT_CREATED_AT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_AT = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_UPDATED_AT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_AT = LocalDate.now(ZoneId.systemDefault());

    private static final String ENTITY_API_URL = "/api/vocabulary-users";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private VocabularyUserRepository vocabularyUserRepository;

    @Autowired
    private VocabularyUserMapper vocabularyUserMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restVocabularyUserMockMvc;

    private VocabularyUser vocabularyUser;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static VocabularyUser createEntity(EntityManager em) {
        VocabularyUser vocabularyUser = new VocabularyUser()
            .userId(DEFAULT_USER_ID)
            .score(DEFAULT_SCORE)
            .createdAt(DEFAULT_CREATED_AT)
            .updatedAt(DEFAULT_UPDATED_AT);
        return vocabularyUser;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static VocabularyUser createUpdatedEntity(EntityManager em) {
        VocabularyUser vocabularyUser = new VocabularyUser()
            .userId(UPDATED_USER_ID)
            .score(UPDATED_SCORE)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);
        return vocabularyUser;
    }

    @BeforeEach
    public void initTest() {
        vocabularyUser = createEntity(em);
    }

    @Test
    @Transactional
    void createVocabularyUser() throws Exception {
        int databaseSizeBeforeCreate = vocabularyUserRepository.findAll().size();
        // Create the VocabularyUser
        VocabularyUserDTO vocabularyUserDTO = vocabularyUserMapper.toDto(vocabularyUser);
        restVocabularyUserMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(vocabularyUserDTO))
            )
            .andExpect(status().isCreated());

        // Validate the VocabularyUser in the database
        List<VocabularyUser> vocabularyUserList = vocabularyUserRepository.findAll();
        assertThat(vocabularyUserList).hasSize(databaseSizeBeforeCreate + 1);
        VocabularyUser testVocabularyUser = vocabularyUserList.get(vocabularyUserList.size() - 1);
        assertThat(testVocabularyUser.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testVocabularyUser.getScore()).isEqualTo(DEFAULT_SCORE);
        assertThat(testVocabularyUser.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testVocabularyUser.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
    }

    @Test
    @Transactional
    void createVocabularyUserWithExistingId() throws Exception {
        // Create the VocabularyUser with an existing ID
        vocabularyUser.setId(1L);
        VocabularyUserDTO vocabularyUserDTO = vocabularyUserMapper.toDto(vocabularyUser);

        int databaseSizeBeforeCreate = vocabularyUserRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restVocabularyUserMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(vocabularyUserDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the VocabularyUser in the database
        List<VocabularyUser> vocabularyUserList = vocabularyUserRepository.findAll();
        assertThat(vocabularyUserList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkUserIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = vocabularyUserRepository.findAll().size();
        // set the field null
        vocabularyUser.setUserId(null);

        // Create the VocabularyUser, which fails.
        VocabularyUserDTO vocabularyUserDTO = vocabularyUserMapper.toDto(vocabularyUser);

        restVocabularyUserMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(vocabularyUserDTO))
            )
            .andExpect(status().isBadRequest());

        List<VocabularyUser> vocabularyUserList = vocabularyUserRepository.findAll();
        assertThat(vocabularyUserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = vocabularyUserRepository.findAll().size();
        // set the field null
        vocabularyUser.setCreatedAt(null);

        // Create the VocabularyUser, which fails.
        VocabularyUserDTO vocabularyUserDTO = vocabularyUserMapper.toDto(vocabularyUser);

        restVocabularyUserMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(vocabularyUserDTO))
            )
            .andExpect(status().isBadRequest());

        List<VocabularyUser> vocabularyUserList = vocabularyUserRepository.findAll();
        assertThat(vocabularyUserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkUpdatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = vocabularyUserRepository.findAll().size();
        // set the field null
        vocabularyUser.setUpdatedAt(null);

        // Create the VocabularyUser, which fails.
        VocabularyUserDTO vocabularyUserDTO = vocabularyUserMapper.toDto(vocabularyUser);

        restVocabularyUserMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(vocabularyUserDTO))
            )
            .andExpect(status().isBadRequest());

        List<VocabularyUser> vocabularyUserList = vocabularyUserRepository.findAll();
        assertThat(vocabularyUserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllVocabularyUsers() throws Exception {
        // Initialize the database
        vocabularyUserRepository.saveAndFlush(vocabularyUser);

        // Get all the vocabularyUserList
        restVocabularyUserMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(vocabularyUser.getId().intValue())))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID.intValue())))
            .andExpect(jsonPath("$.[*].score").value(hasItem(DEFAULT_SCORE.intValue())))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(DEFAULT_UPDATED_AT.toString())));
    }

    @Test
    @Transactional
    void getVocabularyUser() throws Exception {
        // Initialize the database
        vocabularyUserRepository.saveAndFlush(vocabularyUser);

        // Get the vocabularyUser
        restVocabularyUserMockMvc
            .perform(get(ENTITY_API_URL_ID, vocabularyUser.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(vocabularyUser.getId().intValue()))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID.intValue()))
            .andExpect(jsonPath("$.score").value(DEFAULT_SCORE.intValue()))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()))
            .andExpect(jsonPath("$.updatedAt").value(DEFAULT_UPDATED_AT.toString()));
    }

    @Test
    @Transactional
    void getNonExistingVocabularyUser() throws Exception {
        // Get the vocabularyUser
        restVocabularyUserMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewVocabularyUser() throws Exception {
        // Initialize the database
        vocabularyUserRepository.saveAndFlush(vocabularyUser);

        int databaseSizeBeforeUpdate = vocabularyUserRepository.findAll().size();

        // Update the vocabularyUser
        VocabularyUser updatedVocabularyUser = vocabularyUserRepository.findById(vocabularyUser.getId()).get();
        // Disconnect from session so that the updates on updatedVocabularyUser are not directly saved in db
        em.detach(updatedVocabularyUser);
        updatedVocabularyUser.userId(UPDATED_USER_ID).score(UPDATED_SCORE).createdAt(UPDATED_CREATED_AT).updatedAt(UPDATED_UPDATED_AT);
        VocabularyUserDTO vocabularyUserDTO = vocabularyUserMapper.toDto(updatedVocabularyUser);

        restVocabularyUserMockMvc
            .perform(
                put(ENTITY_API_URL_ID, vocabularyUserDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(vocabularyUserDTO))
            )
            .andExpect(status().isOk());

        // Validate the VocabularyUser in the database
        List<VocabularyUser> vocabularyUserList = vocabularyUserRepository.findAll();
        assertThat(vocabularyUserList).hasSize(databaseSizeBeforeUpdate);
        VocabularyUser testVocabularyUser = vocabularyUserList.get(vocabularyUserList.size() - 1);
        assertThat(testVocabularyUser.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testVocabularyUser.getScore()).isEqualTo(UPDATED_SCORE);
        assertThat(testVocabularyUser.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testVocabularyUser.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
    }

    @Test
    @Transactional
    void putNonExistingVocabularyUser() throws Exception {
        int databaseSizeBeforeUpdate = vocabularyUserRepository.findAll().size();
        vocabularyUser.setId(count.incrementAndGet());

        // Create the VocabularyUser
        VocabularyUserDTO vocabularyUserDTO = vocabularyUserMapper.toDto(vocabularyUser);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVocabularyUserMockMvc
            .perform(
                put(ENTITY_API_URL_ID, vocabularyUserDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(vocabularyUserDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the VocabularyUser in the database
        List<VocabularyUser> vocabularyUserList = vocabularyUserRepository.findAll();
        assertThat(vocabularyUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchVocabularyUser() throws Exception {
        int databaseSizeBeforeUpdate = vocabularyUserRepository.findAll().size();
        vocabularyUser.setId(count.incrementAndGet());

        // Create the VocabularyUser
        VocabularyUserDTO vocabularyUserDTO = vocabularyUserMapper.toDto(vocabularyUser);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVocabularyUserMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(vocabularyUserDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the VocabularyUser in the database
        List<VocabularyUser> vocabularyUserList = vocabularyUserRepository.findAll();
        assertThat(vocabularyUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamVocabularyUser() throws Exception {
        int databaseSizeBeforeUpdate = vocabularyUserRepository.findAll().size();
        vocabularyUser.setId(count.incrementAndGet());

        // Create the VocabularyUser
        VocabularyUserDTO vocabularyUserDTO = vocabularyUserMapper.toDto(vocabularyUser);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVocabularyUserMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(vocabularyUserDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the VocabularyUser in the database
        List<VocabularyUser> vocabularyUserList = vocabularyUserRepository.findAll();
        assertThat(vocabularyUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateVocabularyUserWithPatch() throws Exception {
        // Initialize the database
        vocabularyUserRepository.saveAndFlush(vocabularyUser);

        int databaseSizeBeforeUpdate = vocabularyUserRepository.findAll().size();

        // Update the vocabularyUser using partial update
        VocabularyUser partialUpdatedVocabularyUser = new VocabularyUser();
        partialUpdatedVocabularyUser.setId(vocabularyUser.getId());

        partialUpdatedVocabularyUser.userId(UPDATED_USER_ID).score(UPDATED_SCORE).createdAt(UPDATED_CREATED_AT);

        restVocabularyUserMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedVocabularyUser.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedVocabularyUser))
            )
            .andExpect(status().isOk());

        // Validate the VocabularyUser in the database
        List<VocabularyUser> vocabularyUserList = vocabularyUserRepository.findAll();
        assertThat(vocabularyUserList).hasSize(databaseSizeBeforeUpdate);
        VocabularyUser testVocabularyUser = vocabularyUserList.get(vocabularyUserList.size() - 1);
        assertThat(testVocabularyUser.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testVocabularyUser.getScore()).isEqualTo(UPDATED_SCORE);
        assertThat(testVocabularyUser.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testVocabularyUser.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
    }

    @Test
    @Transactional
    void fullUpdateVocabularyUserWithPatch() throws Exception {
        // Initialize the database
        vocabularyUserRepository.saveAndFlush(vocabularyUser);

        int databaseSizeBeforeUpdate = vocabularyUserRepository.findAll().size();

        // Update the vocabularyUser using partial update
        VocabularyUser partialUpdatedVocabularyUser = new VocabularyUser();
        partialUpdatedVocabularyUser.setId(vocabularyUser.getId());

        partialUpdatedVocabularyUser
            .userId(UPDATED_USER_ID)
            .score(UPDATED_SCORE)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);

        restVocabularyUserMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedVocabularyUser.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedVocabularyUser))
            )
            .andExpect(status().isOk());

        // Validate the VocabularyUser in the database
        List<VocabularyUser> vocabularyUserList = vocabularyUserRepository.findAll();
        assertThat(vocabularyUserList).hasSize(databaseSizeBeforeUpdate);
        VocabularyUser testVocabularyUser = vocabularyUserList.get(vocabularyUserList.size() - 1);
        assertThat(testVocabularyUser.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testVocabularyUser.getScore()).isEqualTo(UPDATED_SCORE);
        assertThat(testVocabularyUser.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testVocabularyUser.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
    }

    @Test
    @Transactional
    void patchNonExistingVocabularyUser() throws Exception {
        int databaseSizeBeforeUpdate = vocabularyUserRepository.findAll().size();
        vocabularyUser.setId(count.incrementAndGet());

        // Create the VocabularyUser
        VocabularyUserDTO vocabularyUserDTO = vocabularyUserMapper.toDto(vocabularyUser);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVocabularyUserMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, vocabularyUserDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(vocabularyUserDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the VocabularyUser in the database
        List<VocabularyUser> vocabularyUserList = vocabularyUserRepository.findAll();
        assertThat(vocabularyUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchVocabularyUser() throws Exception {
        int databaseSizeBeforeUpdate = vocabularyUserRepository.findAll().size();
        vocabularyUser.setId(count.incrementAndGet());

        // Create the VocabularyUser
        VocabularyUserDTO vocabularyUserDTO = vocabularyUserMapper.toDto(vocabularyUser);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVocabularyUserMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(vocabularyUserDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the VocabularyUser in the database
        List<VocabularyUser> vocabularyUserList = vocabularyUserRepository.findAll();
        assertThat(vocabularyUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamVocabularyUser() throws Exception {
        int databaseSizeBeforeUpdate = vocabularyUserRepository.findAll().size();
        vocabularyUser.setId(count.incrementAndGet());

        // Create the VocabularyUser
        VocabularyUserDTO vocabularyUserDTO = vocabularyUserMapper.toDto(vocabularyUser);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVocabularyUserMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(vocabularyUserDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the VocabularyUser in the database
        List<VocabularyUser> vocabularyUserList = vocabularyUserRepository.findAll();
        assertThat(vocabularyUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteVocabularyUser() throws Exception {
        // Initialize the database
        vocabularyUserRepository.saveAndFlush(vocabularyUser);

        int databaseSizeBeforeDelete = vocabularyUserRepository.findAll().size();

        // Delete the vocabularyUser
        restVocabularyUserMockMvc
            .perform(delete(ENTITY_API_URL_ID, vocabularyUser.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<VocabularyUser> vocabularyUserList = vocabularyUserRepository.findAll();
        assertThat(vocabularyUserList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
