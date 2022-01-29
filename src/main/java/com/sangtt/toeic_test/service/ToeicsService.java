package com.sangtt.toeic_test.service;

import com.sangtt.toeic_test.service.dto.ToeicsDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sangtt.toeic_test.domain.Toeics}.
 */
public interface ToeicsService {
    /**
     * Save a toeics.
     *
     * @param toeicsDTO the entity to save.
     * @return the persisted entity.
     */
    ToeicsDTO save(ToeicsDTO toeicsDTO);

    /**
     * Partially updates a toeics.
     *
     * @param toeicsDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ToeicsDTO> partialUpdate(ToeicsDTO toeicsDTO);

    /**
     * Get all the toeics.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ToeicsDTO> findAll(Pageable pageable);

    /**
     * Get the "id" toeics.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ToeicsDTO> findOne(Long id);

    /**
     * Delete the "id" toeics.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
