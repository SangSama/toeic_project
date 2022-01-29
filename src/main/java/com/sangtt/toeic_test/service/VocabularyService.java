package com.sangtt.toeic_test.service;

import com.sangtt.toeic_test.service.dto.VocabularyDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sangtt.toeic_test.domain.Vocabulary}.
 */
public interface VocabularyService {
    /**
     * Save a vocabulary.
     *
     * @param vocabularyDTO the entity to save.
     * @return the persisted entity.
     */
    VocabularyDTO save(VocabularyDTO vocabularyDTO);

    /**
     * Partially updates a vocabulary.
     *
     * @param vocabularyDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<VocabularyDTO> partialUpdate(VocabularyDTO vocabularyDTO);

    /**
     * Get all the vocabularies.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<VocabularyDTO> findAll(Pageable pageable);

    /**
     * Get the "id" vocabulary.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<VocabularyDTO> findOne(Long id);

    /**
     * Delete the "id" vocabulary.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
