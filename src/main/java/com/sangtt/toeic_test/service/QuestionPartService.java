package com.sangtt.toeic_test.service;

import com.sangtt.toeic_test.service.dto.QuestionPartDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sangtt.toeic_test.domain.QuestionPart}.
 */
public interface QuestionPartService {
    /**
     * Save a questionPart.
     *
     * @param questionPartDTO the entity to save.
     * @return the persisted entity.
     */
    QuestionPartDTO save(QuestionPartDTO questionPartDTO);

    /**
     * Partially updates a questionPart.
     *
     * @param questionPartDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<QuestionPartDTO> partialUpdate(QuestionPartDTO questionPartDTO);

    /**
     * Get all the questionParts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<QuestionPartDTO> findAll(Pageable pageable);

    /**
     * Get the "id" questionPart.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<QuestionPartDTO> findOne(Long id);

    /**
     * Delete the "id" questionPart.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
