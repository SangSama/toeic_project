package com.sangtt.toeic_test.service;

import com.sangtt.toeic_test.service.dto.PartsDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sangtt.toeic_test.domain.Parts}.
 */
public interface PartsService {
    /**
     * Save a parts.
     *
     * @param partsDTO the entity to save.
     * @return the persisted entity.
     */
    PartsDTO save(PartsDTO partsDTO);

    /**
     * Partially updates a parts.
     *
     * @param partsDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<PartsDTO> partialUpdate(PartsDTO partsDTO);

    /**
     * Get all the parts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PartsDTO> findAll(Pageable pageable);

    /**
     * Get the "id" parts.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PartsDTO> findOne(Long id);

    /**
     * Delete the "id" parts.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
