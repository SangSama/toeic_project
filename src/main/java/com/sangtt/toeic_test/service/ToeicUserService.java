package com.sangtt.toeic_test.service;

import com.sangtt.toeic_test.service.dto.ToeicUserDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sangtt.toeic_test.domain.ToeicUser}.
 */
public interface ToeicUserService {
    /**
     * Save a toeicUser.
     *
     * @param toeicUserDTO the entity to save.
     * @return the persisted entity.
     */
    ToeicUserDTO save(ToeicUserDTO toeicUserDTO);

    /**
     * Partially updates a toeicUser.
     *
     * @param toeicUserDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ToeicUserDTO> partialUpdate(ToeicUserDTO toeicUserDTO);

    /**
     * Get all the toeicUsers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ToeicUserDTO> findAll(Pageable pageable);

    /**
     * Get the "id" toeicUser.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ToeicUserDTO> findOne(Long id);

    /**
     * Delete the "id" toeicUser.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
