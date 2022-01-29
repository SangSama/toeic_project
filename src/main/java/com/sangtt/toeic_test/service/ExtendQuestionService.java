package com.sangtt.toeic_test.service;

import com.sangtt.toeic_test.service.dto.ExtendQuestionDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sangtt.toeic_test.domain.ExtendQuestion}.
 */
public interface ExtendQuestionService {
    /**
     * Save a extendQuestion.
     *
     * @param extendQuestionDTO the entity to save.
     * @return the persisted entity.
     */
    ExtendQuestionDTO save(ExtendQuestionDTO extendQuestionDTO);

    /**
     * Partially updates a extendQuestion.
     *
     * @param extendQuestionDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ExtendQuestionDTO> partialUpdate(ExtendQuestionDTO extendQuestionDTO);

    /**
     * Get all the extendQuestions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ExtendQuestionDTO> findAll(Pageable pageable);

    /**
     * Get the "id" extendQuestion.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ExtendQuestionDTO> findOne(Long id);

    /**
     * Delete the "id" extendQuestion.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
