package com.sangtt.toeic_test.service;

import com.sangtt.toeic_test.service.dto.GrammarTopicDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sangtt.toeic_test.domain.GrammarTopic}.
 */
public interface GrammarTopicService {
    /**
     * Save a grammarTopic.
     *
     * @param grammarTopicDTO the entity to save.
     * @return the persisted entity.
     */
    GrammarTopicDTO save(GrammarTopicDTO grammarTopicDTO);

    /**
     * Partially updates a grammarTopic.
     *
     * @param grammarTopicDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<GrammarTopicDTO> partialUpdate(GrammarTopicDTO grammarTopicDTO);

    /**
     * Get all the grammarTopics.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<GrammarTopicDTO> findAll(Pageable pageable);

    /**
     * Get the "id" grammarTopic.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<GrammarTopicDTO> findOne(Long id);

    /**
     * Delete the "id" grammarTopic.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
