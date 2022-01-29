package com.sangtt.toeic_test.service;

import com.sangtt.toeic_test.service.dto.GrammarAnswerDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sangtt.toeic_test.domain.GrammarAnswer}.
 */
public interface GrammarAnswerService {
    /**
     * Save a grammarAnswer.
     *
     * @param grammarAnswerDTO the entity to save.
     * @return the persisted entity.
     */
    GrammarAnswerDTO save(GrammarAnswerDTO grammarAnswerDTO);

    /**
     * Partially updates a grammarAnswer.
     *
     * @param grammarAnswerDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<GrammarAnswerDTO> partialUpdate(GrammarAnswerDTO grammarAnswerDTO);

    /**
     * Get all the grammarAnswers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<GrammarAnswerDTO> findAll(Pageable pageable);

    /**
     * Get the "id" grammarAnswer.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<GrammarAnswerDTO> findOne(Long id);

    /**
     * Delete the "id" grammarAnswer.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
