package com.sangtt.toeic_test.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sangtt.toeic_test.IntegrationTest;
import com.sangtt.toeic_test.domain.DetailsGrammarUser;
import com.sangtt.toeic_test.repository.DetailsGrammarUserRepository;
import com.sangtt.toeic_test.service.dto.DetailsGrammarUserDTO;
import com.sangtt.toeic_test.service.mapper.DetailsGrammarUserMapper;
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
 * Integration tests for the {@link DetailsGrammarUserResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DetailsGrammarUserResourceIT {

    private static final Long DEFAULT_GRAMMAR_QUESTION_ID = 1L;
    private static final Long UPDATED_GRAMMAR_QUESTION_ID = 2L;

    private static final Long DEFAULT_STATUS = 1L;
    private static final Long UPDATED_STATUS = 2L;

    private static final LocalDate DEFAULT_CREATED_AT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_AT = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_UPDATED_AT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_AT = LocalDate.now(ZoneId.systemDefault());

    private static final String ENTITY_API_URL = "/api/details-grammar-users";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private DetailsGrammarUserRepository detailsGrammarUserRepository;

    @Autowired
    private DetailsGrammarUserMapper detailsGrammarUserMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDetailsGrammarUserMockMvc;

    private DetailsGrammarUser detailsGrammarUser;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DetailsGrammarUser createEntity(EntityManager em) {
        DetailsGrammarUser detailsGrammarUser = new DetailsGrammarUser()
            .grammarQuestionId(DEFAULT_GRAMMAR_QUESTION_ID)
            .status(DEFAULT_STATUS)
            .createdAt(DEFAULT_CREATED_AT)
            .updatedAt(DEFAULT_UPDATED_AT);
        return detailsGrammarUser;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DetailsGrammarUser createUpdatedEntity(EntityManager em) {
        DetailsGrammarUser detailsGrammarUser = new DetailsGrammarUser()
            .grammarQuestionId(UPDATED_GRAMMAR_QUESTION_ID)
            .status(UPDATED_STATUS)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);
        return detailsGrammarUser;
    }

    @BeforeEach
    public void initTest() {
        detailsGrammarUser = createEntity(em);
    }

    @Test
    @Transactional
    void createDetailsGrammarUser() throws Exception {
        int databaseSizeBeforeCreate = detailsGrammarUserRepository.findAll().size();
        // Create the DetailsGrammarUser
        DetailsGrammarUserDTO detailsGrammarUserDTO = detailsGrammarUserMapper.toDto(detailsGrammarUser);
        restDetailsGrammarUserMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(detailsGrammarUserDTO))
            )
            .andExpect(status().isCreated());

        // Validate the DetailsGrammarUser in the database
        List<DetailsGrammarUser> detailsGrammarUserList = detailsGrammarUserRepository.findAll();
        assertThat(detailsGrammarUserList).hasSize(databaseSizeBeforeCreate + 1);
        DetailsGrammarUser testDetailsGrammarUser = detailsGrammarUserList.get(detailsGrammarUserList.size() - 1);
        assertThat(testDetailsGrammarUser.getGrammarQuestionId()).isEqualTo(DEFAULT_GRAMMAR_QUESTION_ID);
        assertThat(testDetailsGrammarUser.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testDetailsGrammarUser.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testDetailsGrammarUser.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
    }

    @Test
    @Transactional
    void createDetailsGrammarUserWithExistingId() throws Exception {
        // Create the DetailsGrammarUser with an existing ID
        detailsGrammarUser.setId(1L);
        DetailsGrammarUserDTO detailsGrammarUserDTO = detailsGrammarUserMapper.toDto(detailsGrammarUser);

        int databaseSizeBeforeCreate = detailsGrammarUserRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDetailsGrammarUserMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(detailsGrammarUserDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DetailsGrammarUser in the database
        List<DetailsGrammarUser> detailsGrammarUserList = detailsGrammarUserRepository.findAll();
        assertThat(detailsGrammarUserList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkGrammarQuestionIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = detailsGrammarUserRepository.findAll().size();
        // set the field null
        detailsGrammarUser.setGrammarQuestionId(null);

        // Create the DetailsGrammarUser, which fails.
        DetailsGrammarUserDTO detailsGrammarUserDTO = detailsGrammarUserMapper.toDto(detailsGrammarUser);

        restDetailsGrammarUserMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(detailsGrammarUserDTO))
            )
            .andExpect(status().isBadRequest());

        List<DetailsGrammarUser> detailsGrammarUserList = detailsGrammarUserRepository.findAll();
        assertThat(detailsGrammarUserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = detailsGrammarUserRepository.findAll().size();
        // set the field null
        detailsGrammarUser.setStatus(null);

        // Create the DetailsGrammarUser, which fails.
        DetailsGrammarUserDTO detailsGrammarUserDTO = detailsGrammarUserMapper.toDto(detailsGrammarUser);

        restDetailsGrammarUserMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(detailsGrammarUserDTO))
            )
            .andExpect(status().isBadRequest());

        List<DetailsGrammarUser> detailsGrammarUserList = detailsGrammarUserRepository.findAll();
        assertThat(detailsGrammarUserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = detailsGrammarUserRepository.findAll().size();
        // set the field null
        detailsGrammarUser.setCreatedAt(null);

        // Create the DetailsGrammarUser, which fails.
        DetailsGrammarUserDTO detailsGrammarUserDTO = detailsGrammarUserMapper.toDto(detailsGrammarUser);

        restDetailsGrammarUserMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(detailsGrammarUserDTO))
            )
            .andExpect(status().isBadRequest());

        List<DetailsGrammarUser> detailsGrammarUserList = detailsGrammarUserRepository.findAll();
        assertThat(detailsGrammarUserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkUpdatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = detailsGrammarUserRepository.findAll().size();
        // set the field null
        detailsGrammarUser.setUpdatedAt(null);

        // Create the DetailsGrammarUser, which fails.
        DetailsGrammarUserDTO detailsGrammarUserDTO = detailsGrammarUserMapper.toDto(detailsGrammarUser);

        restDetailsGrammarUserMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(detailsGrammarUserDTO))
            )
            .andExpect(status().isBadRequest());

        List<DetailsGrammarUser> detailsGrammarUserList = detailsGrammarUserRepository.findAll();
        assertThat(detailsGrammarUserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllDetailsGrammarUsers() throws Exception {
        // Initialize the database
        detailsGrammarUserRepository.saveAndFlush(detailsGrammarUser);

        // Get all the detailsGrammarUserList
        restDetailsGrammarUserMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(detailsGrammarUser.getId().intValue())))
            .andExpect(jsonPath("$.[*].grammarQuestionId").value(hasItem(DEFAULT_GRAMMAR_QUESTION_ID.intValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.intValue())))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(DEFAULT_UPDATED_AT.toString())));
    }

    @Test
    @Transactional
    void getDetailsGrammarUser() throws Exception {
        // Initialize the database
        detailsGrammarUserRepository.saveAndFlush(detailsGrammarUser);

        // Get the detailsGrammarUser
        restDetailsGrammarUserMockMvc
            .perform(get(ENTITY_API_URL_ID, detailsGrammarUser.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(detailsGrammarUser.getId().intValue()))
            .andExpect(jsonPath("$.grammarQuestionId").value(DEFAULT_GRAMMAR_QUESTION_ID.intValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.intValue()))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()))
            .andExpect(jsonPath("$.updatedAt").value(DEFAULT_UPDATED_AT.toString()));
    }

    @Test
    @Transactional
    void getNonExistingDetailsGrammarUser() throws Exception {
        // Get the detailsGrammarUser
        restDetailsGrammarUserMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewDetailsGrammarUser() throws Exception {
        // Initialize the database
        detailsGrammarUserRepository.saveAndFlush(detailsGrammarUser);

        int databaseSizeBeforeUpdate = detailsGrammarUserRepository.findAll().size();

        // Update the detailsGrammarUser
        DetailsGrammarUser updatedDetailsGrammarUser = detailsGrammarUserRepository.findById(detailsGrammarUser.getId()).get();
        // Disconnect from session so that the updates on updatedDetailsGrammarUser are not directly saved in db
        em.detach(updatedDetailsGrammarUser);
        updatedDetailsGrammarUser
            .grammarQuestionId(UPDATED_GRAMMAR_QUESTION_ID)
            .status(UPDATED_STATUS)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);
        DetailsGrammarUserDTO detailsGrammarUserDTO = detailsGrammarUserMapper.toDto(updatedDetailsGrammarUser);

        restDetailsGrammarUserMockMvc
            .perform(
                put(ENTITY_API_URL_ID, detailsGrammarUserDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(detailsGrammarUserDTO))
            )
            .andExpect(status().isOk());

        // Validate the DetailsGrammarUser in the database
        List<DetailsGrammarUser> detailsGrammarUserList = detailsGrammarUserRepository.findAll();
        assertThat(detailsGrammarUserList).hasSize(databaseSizeBeforeUpdate);
        DetailsGrammarUser testDetailsGrammarUser = detailsGrammarUserList.get(detailsGrammarUserList.size() - 1);
        assertThat(testDetailsGrammarUser.getGrammarQuestionId()).isEqualTo(UPDATED_GRAMMAR_QUESTION_ID);
        assertThat(testDetailsGrammarUser.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testDetailsGrammarUser.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testDetailsGrammarUser.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
    }

    @Test
    @Transactional
    void putNonExistingDetailsGrammarUser() throws Exception {
        int databaseSizeBeforeUpdate = detailsGrammarUserRepository.findAll().size();
        detailsGrammarUser.setId(count.incrementAndGet());

        // Create the DetailsGrammarUser
        DetailsGrammarUserDTO detailsGrammarUserDTO = detailsGrammarUserMapper.toDto(detailsGrammarUser);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDetailsGrammarUserMockMvc
            .perform(
                put(ENTITY_API_URL_ID, detailsGrammarUserDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(detailsGrammarUserDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DetailsGrammarUser in the database
        List<DetailsGrammarUser> detailsGrammarUserList = detailsGrammarUserRepository.findAll();
        assertThat(detailsGrammarUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDetailsGrammarUser() throws Exception {
        int databaseSizeBeforeUpdate = detailsGrammarUserRepository.findAll().size();
        detailsGrammarUser.setId(count.incrementAndGet());

        // Create the DetailsGrammarUser
        DetailsGrammarUserDTO detailsGrammarUserDTO = detailsGrammarUserMapper.toDto(detailsGrammarUser);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDetailsGrammarUserMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(detailsGrammarUserDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DetailsGrammarUser in the database
        List<DetailsGrammarUser> detailsGrammarUserList = detailsGrammarUserRepository.findAll();
        assertThat(detailsGrammarUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDetailsGrammarUser() throws Exception {
        int databaseSizeBeforeUpdate = detailsGrammarUserRepository.findAll().size();
        detailsGrammarUser.setId(count.incrementAndGet());

        // Create the DetailsGrammarUser
        DetailsGrammarUserDTO detailsGrammarUserDTO = detailsGrammarUserMapper.toDto(detailsGrammarUser);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDetailsGrammarUserMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(detailsGrammarUserDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the DetailsGrammarUser in the database
        List<DetailsGrammarUser> detailsGrammarUserList = detailsGrammarUserRepository.findAll();
        assertThat(detailsGrammarUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDetailsGrammarUserWithPatch() throws Exception {
        // Initialize the database
        detailsGrammarUserRepository.saveAndFlush(detailsGrammarUser);

        int databaseSizeBeforeUpdate = detailsGrammarUserRepository.findAll().size();

        // Update the detailsGrammarUser using partial update
        DetailsGrammarUser partialUpdatedDetailsGrammarUser = new DetailsGrammarUser();
        partialUpdatedDetailsGrammarUser.setId(detailsGrammarUser.getId());

        partialUpdatedDetailsGrammarUser.grammarQuestionId(UPDATED_GRAMMAR_QUESTION_ID).updatedAt(UPDATED_UPDATED_AT);

        restDetailsGrammarUserMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDetailsGrammarUser.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDetailsGrammarUser))
            )
            .andExpect(status().isOk());

        // Validate the DetailsGrammarUser in the database
        List<DetailsGrammarUser> detailsGrammarUserList = detailsGrammarUserRepository.findAll();
        assertThat(detailsGrammarUserList).hasSize(databaseSizeBeforeUpdate);
        DetailsGrammarUser testDetailsGrammarUser = detailsGrammarUserList.get(detailsGrammarUserList.size() - 1);
        assertThat(testDetailsGrammarUser.getGrammarQuestionId()).isEqualTo(UPDATED_GRAMMAR_QUESTION_ID);
        assertThat(testDetailsGrammarUser.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testDetailsGrammarUser.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testDetailsGrammarUser.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
    }

    @Test
    @Transactional
    void fullUpdateDetailsGrammarUserWithPatch() throws Exception {
        // Initialize the database
        detailsGrammarUserRepository.saveAndFlush(detailsGrammarUser);

        int databaseSizeBeforeUpdate = detailsGrammarUserRepository.findAll().size();

        // Update the detailsGrammarUser using partial update
        DetailsGrammarUser partialUpdatedDetailsGrammarUser = new DetailsGrammarUser();
        partialUpdatedDetailsGrammarUser.setId(detailsGrammarUser.getId());

        partialUpdatedDetailsGrammarUser
            .grammarQuestionId(UPDATED_GRAMMAR_QUESTION_ID)
            .status(UPDATED_STATUS)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);

        restDetailsGrammarUserMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDetailsGrammarUser.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDetailsGrammarUser))
            )
            .andExpect(status().isOk());

        // Validate the DetailsGrammarUser in the database
        List<DetailsGrammarUser> detailsGrammarUserList = detailsGrammarUserRepository.findAll();
        assertThat(detailsGrammarUserList).hasSize(databaseSizeBeforeUpdate);
        DetailsGrammarUser testDetailsGrammarUser = detailsGrammarUserList.get(detailsGrammarUserList.size() - 1);
        assertThat(testDetailsGrammarUser.getGrammarQuestionId()).isEqualTo(UPDATED_GRAMMAR_QUESTION_ID);
        assertThat(testDetailsGrammarUser.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testDetailsGrammarUser.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testDetailsGrammarUser.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
    }

    @Test
    @Transactional
    void patchNonExistingDetailsGrammarUser() throws Exception {
        int databaseSizeBeforeUpdate = detailsGrammarUserRepository.findAll().size();
        detailsGrammarUser.setId(count.incrementAndGet());

        // Create the DetailsGrammarUser
        DetailsGrammarUserDTO detailsGrammarUserDTO = detailsGrammarUserMapper.toDto(detailsGrammarUser);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDetailsGrammarUserMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, detailsGrammarUserDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(detailsGrammarUserDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DetailsGrammarUser in the database
        List<DetailsGrammarUser> detailsGrammarUserList = detailsGrammarUserRepository.findAll();
        assertThat(detailsGrammarUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDetailsGrammarUser() throws Exception {
        int databaseSizeBeforeUpdate = detailsGrammarUserRepository.findAll().size();
        detailsGrammarUser.setId(count.incrementAndGet());

        // Create the DetailsGrammarUser
        DetailsGrammarUserDTO detailsGrammarUserDTO = detailsGrammarUserMapper.toDto(detailsGrammarUser);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDetailsGrammarUserMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(detailsGrammarUserDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DetailsGrammarUser in the database
        List<DetailsGrammarUser> detailsGrammarUserList = detailsGrammarUserRepository.findAll();
        assertThat(detailsGrammarUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDetailsGrammarUser() throws Exception {
        int databaseSizeBeforeUpdate = detailsGrammarUserRepository.findAll().size();
        detailsGrammarUser.setId(count.incrementAndGet());

        // Create the DetailsGrammarUser
        DetailsGrammarUserDTO detailsGrammarUserDTO = detailsGrammarUserMapper.toDto(detailsGrammarUser);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDetailsGrammarUserMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(detailsGrammarUserDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the DetailsGrammarUser in the database
        List<DetailsGrammarUser> detailsGrammarUserList = detailsGrammarUserRepository.findAll();
        assertThat(detailsGrammarUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDetailsGrammarUser() throws Exception {
        // Initialize the database
        detailsGrammarUserRepository.saveAndFlush(detailsGrammarUser);

        int databaseSizeBeforeDelete = detailsGrammarUserRepository.findAll().size();

        // Delete the detailsGrammarUser
        restDetailsGrammarUserMockMvc
            .perform(delete(ENTITY_API_URL_ID, detailsGrammarUser.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DetailsGrammarUser> detailsGrammarUserList = detailsGrammarUserRepository.findAll();
        assertThat(detailsGrammarUserList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
