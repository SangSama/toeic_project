package com.sangtt.toeic_test.web.rest;

import com.sangtt.toeic_test.repository.DetailsVocabularyUserRepository;
import com.sangtt.toeic_test.service.DetailsVocabularyUserService;
import com.sangtt.toeic_test.service.dto.DetailsVocabularyUserDTO;
import com.sangtt.toeic_test.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.sangtt.toeic_test.domain.DetailsVocabularyUser}.
 */
@RestController
@RequestMapping("/api")
public class DetailsVocabularyUserResource {

    private final Logger log = LoggerFactory.getLogger(DetailsVocabularyUserResource.class);

    private static final String ENTITY_NAME = "detailsVocabularyUser";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DetailsVocabularyUserService detailsVocabularyUserService;

    private final DetailsVocabularyUserRepository detailsVocabularyUserRepository;

    public DetailsVocabularyUserResource(
        DetailsVocabularyUserService detailsVocabularyUserService,
        DetailsVocabularyUserRepository detailsVocabularyUserRepository
    ) {
        this.detailsVocabularyUserService = detailsVocabularyUserService;
        this.detailsVocabularyUserRepository = detailsVocabularyUserRepository;
    }

    /**
     * {@code POST  /details-vocabulary-users} : Create a new detailsVocabularyUser.
     *
     * @param detailsVocabularyUserDTO the detailsVocabularyUserDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new detailsVocabularyUserDTO, or with status {@code 400 (Bad Request)} if the detailsVocabularyUser has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/details-vocabulary-users")
    public ResponseEntity<DetailsVocabularyUserDTO> createDetailsVocabularyUser(
        @Valid @RequestBody DetailsVocabularyUserDTO detailsVocabularyUserDTO
    ) throws URISyntaxException {
        log.debug("REST request to save DetailsVocabularyUser : {}", detailsVocabularyUserDTO);
        if (detailsVocabularyUserDTO.getId() != null) {
            throw new BadRequestAlertException("A new detailsVocabularyUser cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DetailsVocabularyUserDTO result = detailsVocabularyUserService.save(detailsVocabularyUserDTO);
        return ResponseEntity
            .created(new URI("/api/details-vocabulary-users/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /details-vocabulary-users/:id} : Updates an existing detailsVocabularyUser.
     *
     * @param id the id of the detailsVocabularyUserDTO to save.
     * @param detailsVocabularyUserDTO the detailsVocabularyUserDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated detailsVocabularyUserDTO,
     * or with status {@code 400 (Bad Request)} if the detailsVocabularyUserDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the detailsVocabularyUserDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/details-vocabulary-users/{id}")
    public ResponseEntity<DetailsVocabularyUserDTO> updateDetailsVocabularyUser(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody DetailsVocabularyUserDTO detailsVocabularyUserDTO
    ) throws URISyntaxException {
        log.debug("REST request to update DetailsVocabularyUser : {}, {}", id, detailsVocabularyUserDTO);
        if (detailsVocabularyUserDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, detailsVocabularyUserDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!detailsVocabularyUserRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        DetailsVocabularyUserDTO result = detailsVocabularyUserService.save(detailsVocabularyUserDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, detailsVocabularyUserDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /details-vocabulary-users/:id} : Partial updates given fields of an existing detailsVocabularyUser, field will ignore if it is null
     *
     * @param id the id of the detailsVocabularyUserDTO to save.
     * @param detailsVocabularyUserDTO the detailsVocabularyUserDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated detailsVocabularyUserDTO,
     * or with status {@code 400 (Bad Request)} if the detailsVocabularyUserDTO is not valid,
     * or with status {@code 404 (Not Found)} if the detailsVocabularyUserDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the detailsVocabularyUserDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/details-vocabulary-users/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DetailsVocabularyUserDTO> partialUpdateDetailsVocabularyUser(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody DetailsVocabularyUserDTO detailsVocabularyUserDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update DetailsVocabularyUser partially : {}, {}", id, detailsVocabularyUserDTO);
        if (detailsVocabularyUserDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, detailsVocabularyUserDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!detailsVocabularyUserRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DetailsVocabularyUserDTO> result = detailsVocabularyUserService.partialUpdate(detailsVocabularyUserDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, detailsVocabularyUserDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /details-vocabulary-users} : get all the detailsVocabularyUsers.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of detailsVocabularyUsers in body.
     */
    @GetMapping("/details-vocabulary-users")
    public ResponseEntity<List<DetailsVocabularyUserDTO>> getAllDetailsVocabularyUsers(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of DetailsVocabularyUsers");
        Page<DetailsVocabularyUserDTO> page = detailsVocabularyUserService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /details-vocabulary-users/:id} : get the "id" detailsVocabularyUser.
     *
     * @param id the id of the detailsVocabularyUserDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the detailsVocabularyUserDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/details-vocabulary-users/{id}")
    public ResponseEntity<DetailsVocabularyUserDTO> getDetailsVocabularyUser(@PathVariable Long id) {
        log.debug("REST request to get DetailsVocabularyUser : {}", id);
        Optional<DetailsVocabularyUserDTO> detailsVocabularyUserDTO = detailsVocabularyUserService.findOne(id);
        return ResponseUtil.wrapOrNotFound(detailsVocabularyUserDTO);
    }

    /**
     * {@code DELETE  /details-vocabulary-users/:id} : delete the "id" detailsVocabularyUser.
     *
     * @param id the id of the detailsVocabularyUserDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/details-vocabulary-users/{id}")
    public ResponseEntity<Void> deleteDetailsVocabularyUser(@PathVariable Long id) {
        log.debug("REST request to delete DetailsVocabularyUser : {}", id);
        detailsVocabularyUserService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
