package com.sangtt.toeic_test.service;

import com.sangtt.toeic_test.service.dto.AnswerQuestionDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sangtt.toeic_test.domain.AnswerQuestion}.
 */
public interface AnswerQuestionService {
    /**
     * Save a answerQuestion.
     *
     * @param answerQuestionDTO the entity to save.
     * @return the persisted entity.
     */
    AnswerQuestionDTO save(AnswerQuestionDTO answerQuestionDTO);

    /**
     * Partially updates a answerQuestion.
     *
     * @param answerQuestionDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<AnswerQuestionDTO> partialUpdate(AnswerQuestionDTO answerQuestionDTO);

    /**
     * Get all the answerQuestions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AnswerQuestionDTO> findAll(Pageable pageable);

    /**
     * Get the "id" answerQuestion.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AnswerQuestionDTO> findOne(Long id);

    /**
     * Delete the "id" answerQuestion.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
