package com.sangtt.toeic_test.service;

import com.sangtt.toeic_test.service.dto.GrammarQuestionDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sangtt.toeic_test.domain.GrammarQuestion}.
 */
public interface GrammarQuestionService {
    /**
     * Save a grammarQuestion.
     *
     * @param grammarQuestionDTO the entity to save.
     * @return the persisted entity.
     */
    GrammarQuestionDTO save(GrammarQuestionDTO grammarQuestionDTO);

    /**
     * Partially updates a grammarQuestion.
     *
     * @param grammarQuestionDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<GrammarQuestionDTO> partialUpdate(GrammarQuestionDTO grammarQuestionDTO);

    /**
     * Get all the grammarQuestions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<GrammarQuestionDTO> findAll(Pageable pageable);

    /**
     * Get the "id" grammarQuestion.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<GrammarQuestionDTO> findOne(Long id);

    /**
     * Delete the "id" grammarQuestion.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
