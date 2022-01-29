package com.sangtt.toeic_test.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sangtt.toeic_test.IntegrationTest;
import com.sangtt.toeic_test.domain.ToeicUser;
import com.sangtt.toeic_test.repository.ToeicUserRepository;
import com.sangtt.toeic_test.service.dto.ToeicUserDTO;
import com.sangtt.toeic_test.service.mapper.ToeicUserMapper;
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
 * Integration tests for the {@link ToeicUserResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ToeicUserResourceIT {

    private static final Long DEFAULT_USER_ID = 1L;
    private static final Long UPDATED_USER_ID = 2L;

    private static final Long DEFAULT_SCORE = 1L;
    private static final Long UPDATED_SCORE = 2L;

    private static final String DEFAULT_READING = "AAAAAAAAAA";
    private static final String UPDATED_READING = "BBBBBBBBBB";

    private static final String DEFAULT_LISTENING = "AAAAAAAAAA";
    private static final String UPDATED_LISTENING = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CREATED_AT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_AT = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_UPDATED_AT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_AT = LocalDate.now(ZoneId.systemDefault());

    private static final String ENTITY_API_URL = "/api/toeic-users";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ToeicUserRepository toeicUserRepository;

    @Autowired
    private ToeicUserMapper toeicUserMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restToeicUserMockMvc;

    private ToeicUser toeicUser;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ToeicUser createEntity(EntityManager em) {
        ToeicUser toeicUser = new ToeicUser()
            .userId(DEFAULT_USER_ID)
            .score(DEFAULT_SCORE)
            .reading(DEFAULT_READING)
            .listening(DEFAULT_LISTENING)
            .createdAt(DEFAULT_CREATED_AT)
            .updatedAt(DEFAULT_UPDATED_AT);
        return toeicUser;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ToeicUser createUpdatedEntity(EntityManager em) {
        ToeicUser toeicUser = new ToeicUser()
            .userId(UPDATED_USER_ID)
            .score(UPDATED_SCORE)
            .reading(UPDATED_READING)
            .listening(UPDATED_LISTENING)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);
        return toeicUser;
    }

    @BeforeEach
    public void initTest() {
        toeicUser = createEntity(em);
    }

    @Test
    @Transactional
    void createToeicUser() throws Exception {
        int databaseSizeBeforeCreate = toeicUserRepository.findAll().size();
        // Create the ToeicUser
        ToeicUserDTO toeicUserDTO = toeicUserMapper.toDto(toeicUser);
        restToeicUserMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(toeicUserDTO)))
            .andExpect(status().isCreated());

        // Validate the ToeicUser in the database
        List<ToeicUser> toeicUserList = toeicUserRepository.findAll();
        assertThat(toeicUserList).hasSize(databaseSizeBeforeCreate + 1);
        ToeicUser testToeicUser = toeicUserList.get(toeicUserList.size() - 1);
        assertThat(testToeicUser.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testToeicUser.getScore()).isEqualTo(DEFAULT_SCORE);
        assertThat(testToeicUser.getReading()).isEqualTo(DEFAULT_READING);
        assertThat(testToeicUser.getListening()).isEqualTo(DEFAULT_LISTENING);
        assertThat(testToeicUser.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testToeicUser.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
    }

    @Test
    @Transactional
    void createToeicUserWithExistingId() throws Exception {
        // Create the ToeicUser with an existing ID
        toeicUser.setId(1L);
        ToeicUserDTO toeicUserDTO = toeicUserMapper.toDto(toeicUser);

        int databaseSizeBeforeCreate = toeicUserRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restToeicUserMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(toeicUserDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ToeicUser in the database
        List<ToeicUser> toeicUserList = toeicUserRepository.findAll();
        assertThat(toeicUserList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkUserIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = toeicUserRepository.findAll().size();
        // set the field null
        toeicUser.setUserId(null);

        // Create the ToeicUser, which fails.
        ToeicUserDTO toeicUserDTO = toeicUserMapper.toDto(toeicUser);

        restToeicUserMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(toeicUserDTO)))
            .andExpect(status().isBadRequest());

        List<ToeicUser> toeicUserList = toeicUserRepository.findAll();
        assertThat(toeicUserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkReadingIsRequired() throws Exception {
        int databaseSizeBeforeTest = toeicUserRepository.findAll().size();
        // set the field null
        toeicUser.setReading(null);

        // Create the ToeicUser, which fails.
        ToeicUserDTO toeicUserDTO = toeicUserMapper.toDto(toeicUser);

        restToeicUserMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(toeicUserDTO)))
            .andExpect(status().isBadRequest());

        List<ToeicUser> toeicUserList = toeicUserRepository.findAll();
        assertThat(toeicUserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkListeningIsRequired() throws Exception {
        int databaseSizeBeforeTest = toeicUserRepository.findAll().size();
        // set the field null
        toeicUser.setListening(null);

        // Create the ToeicUser, which fails.
        ToeicUserDTO toeicUserDTO = toeicUserMapper.toDto(toeicUser);

        restToeicUserMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(toeicUserDTO)))
            .andExpect(status().isBadRequest());

        List<ToeicUser> toeicUserList = toeicUserRepository.findAll();
        assertThat(toeicUserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = toeicUserRepository.findAll().size();
        // set the field null
        toeicUser.setCreatedAt(null);

        // Create the ToeicUser, which fails.
        ToeicUserDTO toeicUserDTO = toeicUserMapper.toDto(toeicUser);

        restToeicUserMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(toeicUserDTO)))
            .andExpect(status().isBadRequest());

        List<ToeicUser> toeicUserList = toeicUserRepository.findAll();
        assertThat(toeicUserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkUpdatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = toeicUserRepository.findAll().size();
        // set the field null
        toeicUser.setUpdatedAt(null);

        // Create the ToeicUser, which fails.
        ToeicUserDTO toeicUserDTO = toeicUserMapper.toDto(toeicUser);

        restToeicUserMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(toeicUserDTO)))
            .andExpect(status().isBadRequest());

        List<ToeicUser> toeicUserList = toeicUserRepository.findAll();
        assertThat(toeicUserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllToeicUsers() throws Exception {
        // Initialize the database
        toeicUserRepository.saveAndFlush(toeicUser);

        // Get all the toeicUserList
        restToeicUserMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(toeicUser.getId().intValue())))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID.intValue())))
            .andExpect(jsonPath("$.[*].score").value(hasItem(DEFAULT_SCORE.intValue())))
            .andExpect(jsonPath("$.[*].reading").value(hasItem(DEFAULT_READING)))
            .andExpect(jsonPath("$.[*].listening").value(hasItem(DEFAULT_LISTENING)))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(DEFAULT_UPDATED_AT.toString())));
    }

    @Test
    @Transactional
    void getToeicUser() throws Exception {
        // Initialize the database
        toeicUserRepository.saveAndFlush(toeicUser);

        // Get the toeicUser
        restToeicUserMockMvc
            .perform(get(ENTITY_API_URL_ID, toeicUser.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(toeicUser.getId().intValue()))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID.intValue()))
            .andExpect(jsonPath("$.score").value(DEFAULT_SCORE.intValue()))
            .andExpect(jsonPath("$.reading").value(DEFAULT_READING))
            .andExpect(jsonPath("$.listening").value(DEFAULT_LISTENING))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()))
            .andExpect(jsonPath("$.updatedAt").value(DEFAULT_UPDATED_AT.toString()));
    }

    @Test
    @Transactional
    void getNonExistingToeicUser() throws Exception {
        // Get the toeicUser
        restToeicUserMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewToeicUser() throws Exception {
        // Initialize the database
        toeicUserRepository.saveAndFlush(toeicUser);

        int databaseSizeBeforeUpdate = toeicUserRepository.findAll().size();

        // Update the toeicUser
        ToeicUser updatedToeicUser = toeicUserRepository.findById(toeicUser.getId()).get();
        // Disconnect from session so that the updates on updatedToeicUser are not directly saved in db
        em.detach(updatedToeicUser);
        updatedToeicUser
            .userId(UPDATED_USER_ID)
            .score(UPDATED_SCORE)
            .reading(UPDATED_READING)
            .listening(UPDATED_LISTENING)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);
        ToeicUserDTO toeicUserDTO = toeicUserMapper.toDto(updatedToeicUser);

        restToeicUserMockMvc
            .perform(
                put(ENTITY_API_URL_ID, toeicUserDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(toeicUserDTO))
            )
            .andExpect(status().isOk());

        // Validate the ToeicUser in the database
        List<ToeicUser> toeicUserList = toeicUserRepository.findAll();
        assertThat(toeicUserList).hasSize(databaseSizeBeforeUpdate);
        ToeicUser testToeicUser = toeicUserList.get(toeicUserList.size() - 1);
        assertThat(testToeicUser.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testToeicUser.getScore()).isEqualTo(UPDATED_SCORE);
        assertThat(testToeicUser.getReading()).isEqualTo(UPDATED_READING);
        assertThat(testToeicUser.getListening()).isEqualTo(UPDATED_LISTENING);
        assertThat(testToeicUser.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testToeicUser.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
    }

    @Test
    @Transactional
    void putNonExistingToeicUser() throws Exception {
        int databaseSizeBeforeUpdate = toeicUserRepository.findAll().size();
        toeicUser.setId(count.incrementAndGet());

        // Create the ToeicUser
        ToeicUserDTO toeicUserDTO = toeicUserMapper.toDto(toeicUser);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restToeicUserMockMvc
            .perform(
                put(ENTITY_API_URL_ID, toeicUserDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(toeicUserDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ToeicUser in the database
        List<ToeicUser> toeicUserList = toeicUserRepository.findAll();
        assertThat(toeicUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchToeicUser() throws Exception {
        int databaseSizeBeforeUpdate = toeicUserRepository.findAll().size();
        toeicUser.setId(count.incrementAndGet());

        // Create the ToeicUser
        ToeicUserDTO toeicUserDTO = toeicUserMapper.toDto(toeicUser);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restToeicUserMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(toeicUserDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ToeicUser in the database
        List<ToeicUser> toeicUserList = toeicUserRepository.findAll();
        assertThat(toeicUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamToeicUser() throws Exception {
        int databaseSizeBeforeUpdate = toeicUserRepository.findAll().size();
        toeicUser.setId(count.incrementAndGet());

        // Create the ToeicUser
        ToeicUserDTO toeicUserDTO = toeicUserMapper.toDto(toeicUser);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restToeicUserMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(toeicUserDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ToeicUser in the database
        List<ToeicUser> toeicUserList = toeicUserRepository.findAll();
        assertThat(toeicUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateToeicUserWithPatch() throws Exception {
        // Initialize the database
        toeicUserRepository.saveAndFlush(toeicUser);

        int databaseSizeBeforeUpdate = toeicUserRepository.findAll().size();

        // Update the toeicUser using partial update
        ToeicUser partialUpdatedToeicUser = new ToeicUser();
        partialUpdatedToeicUser.setId(toeicUser.getId());

        partialUpdatedToeicUser.score(UPDATED_SCORE).listening(UPDATED_LISTENING).updatedAt(UPDATED_UPDATED_AT);

        restToeicUserMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedToeicUser.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedToeicUser))
            )
            .andExpect(status().isOk());

        // Validate the ToeicUser in the database
        List<ToeicUser> toeicUserList = toeicUserRepository.findAll();
        assertThat(toeicUserList).hasSize(databaseSizeBeforeUpdate);
        ToeicUser testToeicUser = toeicUserList.get(toeicUserList.size() - 1);
        assertThat(testToeicUser.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testToeicUser.getScore()).isEqualTo(UPDATED_SCORE);
        assertThat(testToeicUser.getReading()).isEqualTo(DEFAULT_READING);
        assertThat(testToeicUser.getListening()).isEqualTo(UPDATED_LISTENING);
        assertThat(testToeicUser.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testToeicUser.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
    }

    @Test
    @Transactional
    void fullUpdateToeicUserWithPatch() throws Exception {
        // Initialize the database
        toeicUserRepository.saveAndFlush(toeicUser);

        int databaseSizeBeforeUpdate = toeicUserRepository.findAll().size();

        // Update the toeicUser using partial update
        ToeicUser partialUpdatedToeicUser = new ToeicUser();
        partialUpdatedToeicUser.setId(toeicUser.getId());

        partialUpdatedToeicUser
            .userId(UPDATED_USER_ID)
            .score(UPDATED_SCORE)
            .reading(UPDATED_READING)
            .listening(UPDATED_LISTENING)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);

        restToeicUserMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedToeicUser.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedToeicUser))
            )
            .andExpect(status().isOk());

        // Validate the ToeicUser in the database
        List<ToeicUser> toeicUserList = toeicUserRepository.findAll();
        assertThat(toeicUserList).hasSize(databaseSizeBeforeUpdate);
        ToeicUser testToeicUser = toeicUserList.get(toeicUserList.size() - 1);
        assertThat(testToeicUser.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testToeicUser.getScore()).isEqualTo(UPDATED_SCORE);
        assertThat(testToeicUser.getReading()).isEqualTo(UPDATED_READING);
        assertThat(testToeicUser.getListening()).isEqualTo(UPDATED_LISTENING);
        assertThat(testToeicUser.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testToeicUser.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
    }

    @Test
    @Transactional
    void patchNonExistingToeicUser() throws Exception {
        int databaseSizeBeforeUpdate = toeicUserRepository.findAll().size();
        toeicUser.setId(count.incrementAndGet());

        // Create the ToeicUser
        ToeicUserDTO toeicUserDTO = toeicUserMapper.toDto(toeicUser);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restToeicUserMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, toeicUserDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(toeicUserDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ToeicUser in the database
        List<ToeicUser> toeicUserList = toeicUserRepository.findAll();
        assertThat(toeicUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchToeicUser() throws Exception {
        int databaseSizeBeforeUpdate = toeicUserRepository.findAll().size();
        toeicUser.setId(count.incrementAndGet());

        // Create the ToeicUser
        ToeicUserDTO toeicUserDTO = toeicUserMapper.toDto(toeicUser);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restToeicUserMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(toeicUserDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ToeicUser in the database
        List<ToeicUser> toeicUserList = toeicUserRepository.findAll();
        assertThat(toeicUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamToeicUser() throws Exception {
        int databaseSizeBeforeUpdate = toeicUserRepository.findAll().size();
        toeicUser.setId(count.incrementAndGet());

        // Create the ToeicUser
        ToeicUserDTO toeicUserDTO = toeicUserMapper.toDto(toeicUser);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restToeicUserMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(toeicUserDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ToeicUser in the database
        List<ToeicUser> toeicUserList = toeicUserRepository.findAll();
        assertThat(toeicUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteToeicUser() throws Exception {
        // Initialize the database
        toeicUserRepository.saveAndFlush(toeicUser);

        int databaseSizeBeforeDelete = toeicUserRepository.findAll().size();

        // Delete the toeicUser
        restToeicUserMockMvc
            .perform(delete(ENTITY_API_URL_ID, toeicUser.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ToeicUser> toeicUserList = toeicUserRepository.findAll();
        assertThat(toeicUserList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
