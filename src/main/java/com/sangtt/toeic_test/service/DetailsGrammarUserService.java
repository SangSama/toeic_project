package com.sangtt.toeic_test.service;

import com.sangtt.toeic_test.service.dto.DetailsGrammarUserDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sangtt.toeic_test.domain.DetailsGrammarUser}.
 */
public interface DetailsGrammarUserService {
    /**
     * Save a detailsGrammarUser.
     *
     * @param detailsGrammarUserDTO the entity to save.
     * @return the persisted entity.
     */
    DetailsGrammarUserDTO save(DetailsGrammarUserDTO detailsGrammarUserDTO);

    /**
     * Partially updates a detailsGrammarUser.
     *
     * @param detailsGrammarUserDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DetailsGrammarUserDTO> partialUpdate(DetailsGrammarUserDTO detailsGrammarUserDTO);

    /**
     * Get all the detailsGrammarUsers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DetailsGrammarUserDTO> findAll(Pageable pageable);

    /**
     * Get the "id" detailsGrammarUser.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DetailsGrammarUserDTO> findOne(Long id);

    /**
     * Delete the "id" detailsGrammarUser.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
