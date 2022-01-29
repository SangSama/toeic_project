package com.sangtt.toeic_test.service;

import com.sangtt.toeic_test.service.dto.GrammarUserDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sangtt.toeic_test.domain.GrammarUser}.
 */
public interface GrammarUserService {
    /**
     * Save a grammarUser.
     *
     * @param grammarUserDTO the entity to save.
     * @return the persisted entity.
     */
    GrammarUserDTO save(GrammarUserDTO grammarUserDTO);

    /**
     * Partially updates a grammarUser.
     *
     * @param grammarUserDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<GrammarUserDTO> partialUpdate(GrammarUserDTO grammarUserDTO);

    /**
     * Get all the grammarUsers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<GrammarUserDTO> findAll(Pageable pageable);

    /**
     * Get the "id" grammarUser.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<GrammarUserDTO> findOne(Long id);

    /**
     * Delete the "id" grammarUser.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
