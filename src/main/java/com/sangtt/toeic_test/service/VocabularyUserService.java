package com.sangtt.toeic_test.service;

import com.sangtt.toeic_test.service.dto.VocabularyUserDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sangtt.toeic_test.domain.VocabularyUser}.
 */
public interface VocabularyUserService {
    /**
     * Save a vocabularyUser.
     *
     * @param vocabularyUserDTO the entity to save.
     * @return the persisted entity.
     */
    VocabularyUserDTO save(VocabularyUserDTO vocabularyUserDTO);

    /**
     * Partially updates a vocabularyUser.
     *
     * @param vocabularyUserDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<VocabularyUserDTO> partialUpdate(VocabularyUserDTO vocabularyUserDTO);

    /**
     * Get all the vocabularyUsers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<VocabularyUserDTO> findAll(Pageable pageable);

    /**
     * Get the "id" vocabularyUser.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<VocabularyUserDTO> findOne(Long id);

    /**
     * Delete the "id" vocabularyUser.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
