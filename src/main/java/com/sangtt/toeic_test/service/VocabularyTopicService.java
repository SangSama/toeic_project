package com.sangtt.toeic_test.service;

import com.sangtt.toeic_test.service.dto.VocabularyTopicDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sangtt.toeic_test.domain.VocabularyTopic}.
 */
public interface VocabularyTopicService {
    /**
     * Save a vocabularyTopic.
     *
     * @param vocabularyTopicDTO the entity to save.
     * @return the persisted entity.
     */
    VocabularyTopicDTO save(VocabularyTopicDTO vocabularyTopicDTO);

    /**
     * Partially updates a vocabularyTopic.
     *
     * @param vocabularyTopicDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<VocabularyTopicDTO> partialUpdate(VocabularyTopicDTO vocabularyTopicDTO);

    /**
     * Get all the vocabularyTopics.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<VocabularyTopicDTO> findAll(Pageable pageable);

    /**
     * Get the "id" vocabularyTopic.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<VocabularyTopicDTO> findOne(Long id);

    /**
     * Delete the "id" vocabularyTopic.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
