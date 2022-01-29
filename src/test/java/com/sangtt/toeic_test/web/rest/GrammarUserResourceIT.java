package com.sangtt.toeic_test.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sangtt.toeic_test.IntegrationTest;
import com.sangtt.toeic_test.domain.GrammarUser;
import com.sangtt.toeic_test.repository.GrammarUserRepository;
import com.sangtt.toeic_test.service.dto.GrammarUserDTO;
import com.sangtt.toeic_test.service.mapper.GrammarUserMapper;
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
 * Integration tests for the {@link GrammarUserResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class GrammarUserResourceIT {

    private static final Long DEFAULT_USER_ID = 1L;
    private static final Long UPDATED_USER_ID = 2L;

    private static final Long DEFAULT_SCORE = 1L;
    private static final Long UPDATED_SCORE = 2L;

    private static final LocalDate DEFAULT_CREATED_AT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_AT = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_UPDATED_AT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_AT = LocalDate.now(ZoneId.systemDefault());

    private static final String ENTITY_API_URL = "/api/grammar-users";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private GrammarUserRepository grammarUserRepository;

    @Autowired
    private GrammarUserMapper grammarUserMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restGrammarUserMockMvc;

    private GrammarUser grammarUser;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GrammarUser createEntity(EntityManager em) {
        GrammarUser grammarUser = new GrammarUser()
            .userId(DEFAULT_USER_ID)
            .score(DEFAULT_SCORE)
            .createdAt(DEFAULT_CREATED_AT)
            .updatedAt(DEFAULT_UPDATED_AT);
        return grammarUser;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GrammarUser createUpdatedEntity(EntityManager em) {
        GrammarUser grammarUser = new GrammarUser()
            .userId(UPDATED_USER_ID)
            .score(UPDATED_SCORE)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);
        return grammarUser;
    }

    @BeforeEach
    public void initTest() {
        grammarUser = createEntity(em);
    }

    @Test
    @Transactional
    void createGrammarUser() throws Exception {
        int databaseSizeBeforeCreate = grammarUserRepository.findAll().size();
        // Create the GrammarUser
        GrammarUserDTO grammarUserDTO = grammarUserMapper.toDto(grammarUser);
        restGrammarUserMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(grammarUserDTO))
            )
            .andExpect(status().isCreated());

        // Validate the GrammarUser in the database
        List<GrammarUser> grammarUserList = grammarUserRepository.findAll();
        assertThat(grammarUserList).hasSize(databaseSizeBeforeCreate + 1);
        GrammarUser testGrammarUser = grammarUserList.get(grammarUserList.size() - 1);
        assertThat(testGrammarUser.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testGrammarUser.getScore()).isEqualTo(DEFAULT_SCORE);
        assertThat(testGrammarUser.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testGrammarUser.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
    }

    @Test
    @Transactional
    void createGrammarUserWithExistingId() throws Exception {
        // Create the GrammarUser with an existing ID
        grammarUser.setId(1L);
        GrammarUserDTO grammarUserDTO = grammarUserMapper.toDto(grammarUser);

        int databaseSizeBeforeCreate = grammarUserRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restGrammarUserMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(grammarUserDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the GrammarUser in the database
        List<GrammarUser> grammarUserList = grammarUserRepository.findAll();
        assertThat(grammarUserList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkUserIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = grammarUserRepository.findAll().size();
        // set the field null
        grammarUser.setUserId(null);

        // Create the GrammarUser, which fails.
        GrammarUserDTO grammarUserDTO = grammarUserMapper.toDto(grammarUser);

        restGrammarUserMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(grammarUserDTO))
            )
            .andExpect(status().isBadRequest());

        List<GrammarUser> grammarUserList = grammarUserRepository.findAll();
        assertThat(grammarUserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = grammarUserRepository.findAll().size();
        // set the field null
        grammarUser.setCreatedAt(null);

        // Create the GrammarUser, which fails.
        GrammarUserDTO grammarUserDTO = grammarUserMapper.toDto(grammarUser);

        restGrammarUserMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(grammarUserDTO))
            )
            .andExpect(status().isBadRequest());

        List<GrammarUser> grammarUserList = grammarUserRepository.findAll();
        assertThat(grammarUserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkUpdatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = grammarUserRepository.findAll().size();
        // set the field null
        grammarUser.setUpdatedAt(null);

        // Create the GrammarUser, which fails.
        GrammarUserDTO grammarUserDTO = grammarUserMapper.toDto(grammarUser);

        restGrammarUserMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(grammarUserDTO))
            )
            .andExpect(status().isBadRequest());

        List<GrammarUser> grammarUserList = grammarUserRepository.findAll();
        assertThat(grammarUserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllGrammarUsers() throws Exception {
        // Initialize the database
        grammarUserRepository.saveAndFlush(grammarUser);

        // Get all the grammarUserList
        restGrammarUserMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(grammarUser.getId().intValue())))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID.intValue())))
            .andExpect(jsonPath("$.[*].score").value(hasItem(DEFAULT_SCORE.intValue())))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(DEFAULT_UPDATED_AT.toString())));
    }

    @Test
    @Transactional
    void getGrammarUser() throws Exception {
        // Initialize the database
        grammarUserRepository.saveAndFlush(grammarUser);

        // Get the grammarUser
        restGrammarUserMockMvc
            .perform(get(ENTITY_API_URL_ID, grammarUser.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(grammarUser.getId().intValue()))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID.intValue()))
            .andExpect(jsonPath("$.score").value(DEFAULT_SCORE.intValue()))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()))
            .andExpect(jsonPath("$.updatedAt").value(DEFAULT_UPDATED_AT.toString()));
    }

    @Test
    @Transactional
    void getNonExistingGrammarUser() throws Exception {
        // Get the grammarUser
        restGrammarUserMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewGrammarUser() throws Exception {
        // Initialize the database
        grammarUserRepository.saveAndFlush(grammarUser);

        int databaseSizeBeforeUpdate = grammarUserRepository.findAll().size();

        // Update the grammarUser
        GrammarUser updatedGrammarUser = grammarUserRepository.findById(grammarUser.getId()).get();
        // Disconnect from session so that the updates on updatedGrammarUser are not directly saved in db
        em.detach(updatedGrammarUser);
        updatedGrammarUser.userId(UPDATED_USER_ID).score(UPDATED_SCORE).createdAt(UPDATED_CREATED_AT).updatedAt(UPDATED_UPDATED_AT);
        GrammarUserDTO grammarUserDTO = grammarUserMapper.toDto(updatedGrammarUser);

        restGrammarUserMockMvc
            .perform(
                put(ENTITY_API_URL_ID, grammarUserDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(grammarUserDTO))
            )
            .andExpect(status().isOk());

        // Validate the GrammarUser in the database
        List<GrammarUser> grammarUserList = grammarUserRepository.findAll();
        assertThat(grammarUserList).hasSize(databaseSizeBeforeUpdate);
        GrammarUser testGrammarUser = grammarUserList.get(grammarUserList.size() - 1);
        assertThat(testGrammarUser.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testGrammarUser.getScore()).isEqualTo(UPDATED_SCORE);
        assertThat(testGrammarUser.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testGrammarUser.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
    }

    @Test
    @Transactional
    void putNonExistingGrammarUser() throws Exception {
        int databaseSizeBeforeUpdate = grammarUserRepository.findAll().size();
        grammarUser.setId(count.incrementAndGet());

        // Create the GrammarUser
        GrammarUserDTO grammarUserDTO = grammarUserMapper.toDto(grammarUser);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGrammarUserMockMvc
            .perform(
                put(ENTITY_API_URL_ID, grammarUserDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(grammarUserDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the GrammarUser in the database
        List<GrammarUser> grammarUserList = grammarUserRepository.findAll();
        assertThat(grammarUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchGrammarUser() throws Exception {
        int databaseSizeBeforeUpdate = grammarUserRepository.findAll().size();
        grammarUser.setId(count.incrementAndGet());

        // Create the GrammarUser
        GrammarUserDTO grammarUserDTO = grammarUserMapper.toDto(grammarUser);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restGrammarUserMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(grammarUserDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the GrammarUser in the database
        List<GrammarUser> grammarUserList = grammarUserRepository.findAll();
        assertThat(grammarUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamGrammarUser() throws Exception {
        int databaseSizeBeforeUpdate = grammarUserRepository.findAll().size();
        grammarUser.setId(count.incrementAndGet());

        // Create the GrammarUser
        GrammarUserDTO grammarUserDTO = grammarUserMapper.toDto(grammarUser);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restGrammarUserMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(grammarUserDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the GrammarUser in the database
        List<GrammarUser> grammarUserList = grammarUserRepository.findAll();
        assertThat(grammarUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateGrammarUserWithPatch() throws Exception {
        // Initialize the database
        grammarUserRepository.saveAndFlush(grammarUser);

        int databaseSizeBeforeUpdate = grammarUserRepository.findAll().size();

        // Update the grammarUser using partial update
        GrammarUser partialUpdatedGrammarUser = new GrammarUser();
        partialUpdatedGrammarUser.setId(grammarUser.getId());

        partialUpdatedGrammarUser.userId(UPDATED_USER_ID).score(UPDATED_SCORE);

        restGrammarUserMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedGrammarUser.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedGrammarUser))
            )
            .andExpect(status().isOk());

        // Validate the GrammarUser in the database
        List<GrammarUser> grammarUserList = grammarUserRepository.findAll();
        assertThat(grammarUserList).hasSize(databaseSizeBeforeUpdate);
        GrammarUser testGrammarUser = grammarUserList.get(grammarUserList.size() - 1);
        assertThat(testGrammarUser.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testGrammarUser.getScore()).isEqualTo(UPDATED_SCORE);
        assertThat(testGrammarUser.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testGrammarUser.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
    }

    @Test
    @Transactional
    void fullUpdateGrammarUserWithPatch() throws Exception {
        // Initialize the database
        grammarUserRepository.saveAndFlush(grammarUser);

        int databaseSizeBeforeUpdate = grammarUserRepository.findAll().size();

        // Update the grammarUser using partial update
        GrammarUser partialUpdatedGrammarUser = new GrammarUser();
        partialUpdatedGrammarUser.setId(grammarUser.getId());

        partialUpdatedGrammarUser.userId(UPDATED_USER_ID).score(UPDATED_SCORE).createdAt(UPDATED_CREATED_AT).updatedAt(UPDATED_UPDATED_AT);

        restGrammarUserMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedGrammarUser.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedGrammarUser))
            )
            .andExpect(status().isOk());

        // Validate the GrammarUser in the database
        List<GrammarUser> grammarUserList = grammarUserRepository.findAll();
        assertThat(grammarUserList).hasSize(databaseSizeBeforeUpdate);
        GrammarUser testGrammarUser = grammarUserList.get(grammarUserList.size() - 1);
        assertThat(testGrammarUser.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testGrammarUser.getScore()).isEqualTo(UPDATED_SCORE);
        assertThat(testGrammarUser.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testGrammarUser.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
    }

    @Test
    @Transactional
    void patchNonExistingGrammarUser() throws Exception {
        int databaseSizeBeforeUpdate = grammarUserRepository.findAll().size();
        grammarUser.setId(count.incrementAndGet());

        // Create the GrammarUser
        GrammarUserDTO grammarUserDTO = grammarUserMapper.toDto(grammarUser);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGrammarUserMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, grammarUserDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(grammarUserDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the GrammarUser in the database
        List<GrammarUser> grammarUserList = grammarUserRepository.findAll();
        assertThat(grammarUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchGrammarUser() throws Exception {
        int databaseSizeBeforeUpdate = grammarUserRepository.findAll().size();
        grammarUser.setId(count.incrementAndGet());

        // Create the GrammarUser
        GrammarUserDTO grammarUserDTO = grammarUserMapper.toDto(grammarUser);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restGrammarUserMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(grammarUserDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the GrammarUser in the database
        List<GrammarUser> grammarUserList = grammarUserRepository.findAll();
        assertThat(grammarUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamGrammarUser() throws Exception {
        int databaseSizeBeforeUpdate = grammarUserRepository.findAll().size();
        grammarUser.setId(count.incrementAndGet());

        // Create the GrammarUser
        GrammarUserDTO grammarUserDTO = grammarUserMapper.toDto(grammarUser);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restGrammarUserMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(grammarUserDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the GrammarUser in the database
        List<GrammarUser> grammarUserList = grammarUserRepository.findAll();
        assertThat(grammarUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteGrammarUser() throws Exception {
        // Initialize the database
        grammarUserRepository.saveAndFlush(grammarUser);

        int databaseSizeBeforeDelete = grammarUserRepository.findAll().size();

        // Delete the grammarUser
        restGrammarUserMockMvc
            .perform(delete(ENTITY_API_URL_ID, grammarUser.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<GrammarUser> grammarUserList = grammarUserRepository.findAll();
        assertThat(grammarUserList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
