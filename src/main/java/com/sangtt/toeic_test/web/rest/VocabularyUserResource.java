package com.sangtt.toeic_test.web.rest;

import com.sangtt.toeic_test.repository.VocabularyUserRepository;
import com.sangtt.toeic_test.service.VocabularyUserService;
import com.sangtt.toeic_test.service.dto.VocabularyUserDTO;
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
 * REST controller for managing {@link com.sangtt.toeic_test.domain.VocabularyUser}.
 */
@RestController
@RequestMapping("/api")
public class VocabularyUserResource {

    private final Logger log = LoggerFactory.getLogger(VocabularyUserResource.class);

    private static final String ENTITY_NAME = "vocabularyUser";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final VocabularyUserService vocabularyUserService;

    private final VocabularyUserRepository vocabularyUserRepository;

    public VocabularyUserResource(VocabularyUserService vocabularyUserService, VocabularyUserRepository vocabularyUserRepository) {
        this.vocabularyUserService = vocabularyUserService;
        this.vocabularyUserRepository = vocabularyUserRepository;
    }

    /**
     * {@code POST  /vocabulary-users} : Create a new vocabularyUser.
     *
     * @param vocabularyUserDTO the vocabularyUserDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new vocabularyUserDTO, or with status {@code 400 (Bad Request)} if the vocabularyUser has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/vocabulary-users")
    public ResponseEntity<VocabularyUserDTO> createVocabularyUser(@Valid @RequestBody VocabularyUserDTO vocabularyUserDTO)
        throws URISyntaxException {
        log.debug("REST request to save VocabularyUser : {}", vocabularyUserDTO);
        if (vocabularyUserDTO.getId() != null) {
            throw new BadRequestAlertException("A new vocabularyUser cannot already have an ID", ENTITY_NAME, "idexists");
        }
        VocabularyUserDTO result = vocabularyUserService.save(vocabularyUserDTO);
        return ResponseEntity
            .created(new URI("/api/vocabulary-users/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /vocabulary-users/:id} : Updates an existing vocabularyUser.
     *
     * @param id the id of the vocabularyUserDTO to save.
     * @param vocabularyUserDTO the vocabularyUserDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated vocabularyUserDTO,
     * or with status {@code 400 (Bad Request)} if the vocabularyUserDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the vocabularyUserDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/vocabulary-users/{id}")
    public ResponseEntity<VocabularyUserDTO> updateVocabularyUser(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody VocabularyUserDTO vocabularyUserDTO
    ) throws URISyntaxException {
        log.debug("REST request to update VocabularyUser : {}, {}", id, vocabularyUserDTO);
        if (vocabularyUserDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, vocabularyUserDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!vocabularyUserRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        VocabularyUserDTO result = vocabularyUserService.save(vocabularyUserDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, vocabularyUserDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /vocabulary-users/:id} : Partial updates given fields of an existing vocabularyUser, field will ignore if it is null
     *
     * @param id the id of the vocabularyUserDTO to save.
     * @param vocabularyUserDTO the vocabularyUserDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated vocabularyUserDTO,
     * or with status {@code 400 (Bad Request)} if the vocabularyUserDTO is not valid,
     * or with status {@code 404 (Not Found)} if the vocabularyUserDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the vocabularyUserDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/vocabulary-users/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<VocabularyUserDTO> partialUpdateVocabularyUser(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody VocabularyUserDTO vocabularyUserDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update VocabularyUser partially : {}, {}", id, vocabularyUserDTO);
        if (vocabularyUserDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, vocabularyUserDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!vocabularyUserRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<VocabularyUserDTO> result = vocabularyUserService.partialUpdate(vocabularyUserDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, vocabularyUserDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /vocabulary-users} : get all the vocabularyUsers.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of vocabularyUsers in body.
     */
    @GetMapping("/vocabulary-users")
    public ResponseEntity<List<VocabularyUserDTO>> getAllVocabularyUsers(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of VocabularyUsers");
        Page<VocabularyUserDTO> page = vocabularyUserService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /vocabulary-users/:id} : get the "id" vocabularyUser.
     *
     * @param id the id of the vocabularyUserDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the vocabularyUserDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/vocabulary-users/{id}")
    public ResponseEntity<VocabularyUserDTO> getVocabularyUser(@PathVariable Long id) {
        log.debug("REST request to get VocabularyUser : {}", id);
        Optional<VocabularyUserDTO> vocabularyUserDTO = vocabularyUserService.findOne(id);
        return ResponseUtil.wrapOrNotFound(vocabularyUserDTO);
    }

    /**
     * {@code DELETE  /vocabulary-users/:id} : delete the "id" vocabularyUser.
     *
     * @param id the id of the vocabularyUserDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/vocabulary-users/{id}")
    public ResponseEntity<Void> deleteVocabularyUser(@PathVariable Long id) {
        log.debug("REST request to delete VocabularyUser : {}", id);
        vocabularyUserService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
