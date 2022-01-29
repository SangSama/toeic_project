package com.sangtt.toeic_test.web.rest;

import com.sangtt.toeic_test.repository.VocabularyRepository;
import com.sangtt.toeic_test.service.VocabularyService;
import com.sangtt.toeic_test.service.dto.VocabularyDTO;
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
 * REST controller for managing {@link com.sangtt.toeic_test.domain.Vocabulary}.
 */
@RestController
@RequestMapping("/api")
public class VocabularyResource {

    private final Logger log = LoggerFactory.getLogger(VocabularyResource.class);

    private static final String ENTITY_NAME = "vocabulary";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final VocabularyService vocabularyService;

    private final VocabularyRepository vocabularyRepository;

    public VocabularyResource(VocabularyService vocabularyService, VocabularyRepository vocabularyRepository) {
        this.vocabularyService = vocabularyService;
        this.vocabularyRepository = vocabularyRepository;
    }

    /**
     * {@code POST  /vocabularies} : Create a new vocabulary.
     *
     * @param vocabularyDTO the vocabularyDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new vocabularyDTO, or with status {@code 400 (Bad Request)} if the vocabulary has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/vocabularies")
    public ResponseEntity<VocabularyDTO> createVocabulary(@Valid @RequestBody VocabularyDTO vocabularyDTO) throws URISyntaxException {
        log.debug("REST request to save Vocabulary : {}", vocabularyDTO);
        if (vocabularyDTO.getId() != null) {
            throw new BadRequestAlertException("A new vocabulary cannot already have an ID", ENTITY_NAME, "idexists");
        }
        VocabularyDTO result = vocabularyService.save(vocabularyDTO);
        return ResponseEntity
            .created(new URI("/api/vocabularies/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /vocabularies/:id} : Updates an existing vocabulary.
     *
     * @param id the id of the vocabularyDTO to save.
     * @param vocabularyDTO the vocabularyDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated vocabularyDTO,
     * or with status {@code 400 (Bad Request)} if the vocabularyDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the vocabularyDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/vocabularies/{id}")
    public ResponseEntity<VocabularyDTO> updateVocabulary(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody VocabularyDTO vocabularyDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Vocabulary : {}, {}", id, vocabularyDTO);
        if (vocabularyDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, vocabularyDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!vocabularyRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        VocabularyDTO result = vocabularyService.save(vocabularyDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, vocabularyDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /vocabularies/:id} : Partial updates given fields of an existing vocabulary, field will ignore if it is null
     *
     * @param id the id of the vocabularyDTO to save.
     * @param vocabularyDTO the vocabularyDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated vocabularyDTO,
     * or with status {@code 400 (Bad Request)} if the vocabularyDTO is not valid,
     * or with status {@code 404 (Not Found)} if the vocabularyDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the vocabularyDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/vocabularies/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<VocabularyDTO> partialUpdateVocabulary(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody VocabularyDTO vocabularyDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Vocabulary partially : {}, {}", id, vocabularyDTO);
        if (vocabularyDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, vocabularyDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!vocabularyRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<VocabularyDTO> result = vocabularyService.partialUpdate(vocabularyDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, vocabularyDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /vocabularies} : get all the vocabularies.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of vocabularies in body.
     */
    @GetMapping("/vocabularies")
    public ResponseEntity<List<VocabularyDTO>> getAllVocabularies(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Vocabularies");
        Page<VocabularyDTO> page = vocabularyService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /vocabularies/:id} : get the "id" vocabulary.
     *
     * @param id the id of the vocabularyDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the vocabularyDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/vocabularies/{id}")
    public ResponseEntity<VocabularyDTO> getVocabulary(@PathVariable Long id) {
        log.debug("REST request to get Vocabulary : {}", id);
        Optional<VocabularyDTO> vocabularyDTO = vocabularyService.findOne(id);
        return ResponseUtil.wrapOrNotFound(vocabularyDTO);
    }

    /**
     * {@code DELETE  /vocabularies/:id} : delete the "id" vocabulary.
     *
     * @param id the id of the vocabularyDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/vocabularies/{id}")
    public ResponseEntity<Void> deleteVocabulary(@PathVariable Long id) {
        log.debug("REST request to delete Vocabulary : {}", id);
        vocabularyService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
