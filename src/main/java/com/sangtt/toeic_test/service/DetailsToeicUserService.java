package com.sangtt.toeic_test.service;

import com.sangtt.toeic_test.service.dto.DetailsToeicUserDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sangtt.toeic_test.domain.DetailsToeicUser}.
 */
public interface DetailsToeicUserService {
    /**
     * Save a detailsToeicUser.
     *
     * @param detailsToeicUserDTO the entity to save.
     * @return the persisted entity.
     */
    DetailsToeicUserDTO save(DetailsToeicUserDTO detailsToeicUserDTO);

    /**
     * Partially updates a detailsToeicUser.
     *
     * @param detailsToeicUserDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DetailsToeicUserDTO> partialUpdate(DetailsToeicUserDTO detailsToeicUserDTO);

    /**
     * Get all the detailsToeicUsers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DetailsToeicUserDTO> findAll(Pageable pageable);

    /**
     * Get the "id" detailsToeicUser.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DetailsToeicUserDTO> findOne(Long id);

    /**
     * Delete the "id" detailsToeicUser.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
