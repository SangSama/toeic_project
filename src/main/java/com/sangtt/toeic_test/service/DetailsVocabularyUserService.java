package com.sangtt.toeic_test.service;

import com.sangtt.toeic_test.service.dto.DetailsVocabularyUserDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sangtt.toeic_test.domain.DetailsVocabularyUser}.
 */
public interface DetailsVocabularyUserService {
    /**
     * Save a detailsVocabularyUser.
     *
     * @param detailsVocabularyUserDTO the entity to save.
     * @return the persisted entity.
     */
    DetailsVocabularyUserDTO save(DetailsVocabularyUserDTO detailsVocabularyUserDTO);

    /**
     * Partially updates a detailsVocabularyUser.
     *
     * @param detailsVocabularyUserDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DetailsVocabularyUserDTO> partialUpdate(DetailsVocabularyUserDTO detailsVocabularyUserDTO);

    /**
     * Get all the detailsVocabularyUsers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DetailsVocabularyUserDTO> findAll(Pageable pageable);

    /**
     * Get the "id" detailsVocabularyUser.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DetailsVocabularyUserDTO> findOne(Long id);

    /**
     * Delete the "id" detailsVocabularyUser.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
