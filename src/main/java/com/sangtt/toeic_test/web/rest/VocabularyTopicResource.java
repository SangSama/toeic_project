package com.sangtt.toeic_test.web.rest;

import com.sangtt.toeic_test.repository.VocabularyTopicRepository;
import com.sangtt.toeic_test.service.VocabularyTopicService;
import com.sangtt.toeic_test.service.dto.VocabularyTopicDTO;
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
 * REST controller for managing {@link com.sangtt.toeic_test.domain.VocabularyTopic}.
 */
@RestController
@RequestMapping("/api")
public class VocabularyTopicResource {

    private final Logger log = LoggerFactory.getLogger(VocabularyTopicResource.class);

    private static final String ENTITY_NAME = "vocabularyTopic";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final VocabularyTopicService vocabularyTopicService;

    private final VocabularyTopicRepository vocabularyTopicRepository;

    public VocabularyTopicResource(VocabularyTopicService vocabularyTopicService, VocabularyTopicRepository vocabularyTopicRepository) {
        this.vocabularyTopicService = vocabularyTopicService;
        this.vocabularyTopicRepository = vocabularyTopicRepository;
    }

    /**
     * {@code POST  /vocabulary-topics} : Create a new vocabularyTopic.
     *
     * @param vocabularyTopicDTO the vocabularyTopicDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new vocabularyTopicDTO, or with status {@code 400 (Bad Request)} if the vocabularyTopic has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/vocabulary-topics")
    public ResponseEntity<VocabularyTopicDTO> createVocabularyTopic(@Valid @RequestBody VocabularyTopicDTO vocabularyTopicDTO)
        throws URISyntaxException {
        log.debug("REST request to save VocabularyTopic : {}", vocabularyTopicDTO);
        if (vocabularyTopicDTO.getId() != null) {
            throw new BadRequestAlertException("A new vocabularyTopic cannot already have an ID", ENTITY_NAME, "idexists");
        }
        VocabularyTopicDTO result = vocabularyTopicService.save(vocabularyTopicDTO);
        return ResponseEntity
            .created(new URI("/api/vocabulary-topics/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /vocabulary-topics/:id} : Updates an existing vocabularyTopic.
     *
     * @param id the id of the vocabularyTopicDTO to save.
     * @param vocabularyTopicDTO the vocabularyTopicDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated vocabularyTopicDTO,
     * or with status {@code 400 (Bad Request)} if the vocabularyTopicDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the vocabularyTopicDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/vocabulary-topics/{id}")
    public ResponseEntity<VocabularyTopicDTO> updateVocabularyTopic(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody VocabularyTopicDTO vocabularyTopicDTO
    ) throws URISyntaxException {
        log.debug("REST request to update VocabularyTopic : {}, {}", id, vocabularyTopicDTO);
        if (vocabularyTopicDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, vocabularyTopicDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!vocabularyTopicRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        VocabularyTopicDTO result = vocabularyTopicService.save(vocabularyTopicDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, vocabularyTopicDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /vocabulary-topics/:id} : Partial updates given fields of an existing vocabularyTopic, field will ignore if it is null
     *
     * @param id the id of the vocabularyTopicDTO to save.
     * @param vocabularyTopicDTO the vocabularyTopicDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated vocabularyTopicDTO,
     * or with status {@code 400 (Bad Request)} if the vocabularyTopicDTO is not valid,
     * or with status {@code 404 (Not Found)} if the vocabularyTopicDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the vocabularyTopicDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/vocabulary-topics/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<VocabularyTopicDTO> partialUpdateVocabularyTopic(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody VocabularyTopicDTO vocabularyTopicDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update VocabularyTopic partially : {}, {}", id, vocabularyTopicDTO);
        if (vocabularyTopicDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, vocabularyTopicDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!vocabularyTopicRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<VocabularyTopicDTO> result = vocabularyTopicService.partialUpdate(vocabularyTopicDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, vocabularyTopicDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /vocabulary-topics} : get all the vocabularyTopics.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of vocabularyTopics in body.
     */
    @GetMapping("/vocabulary-topics")
    public ResponseEntity<List<VocabularyTopicDTO>> getAllVocabularyTopics(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of VocabularyTopics");
        Page<VocabularyTopicDTO> page = vocabularyTopicService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /vocabulary-topics/:id} : get the "id" vocabularyTopic.
     *
     * @param id the id of the vocabularyTopicDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the vocabularyTopicDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/vocabulary-topics/{id}")
    public ResponseEntity<VocabularyTopicDTO> getVocabularyTopic(@PathVariable Long id) {
        log.debug("REST request to get VocabularyTopic : {}", id);
        Optional<VocabularyTopicDTO> vocabularyTopicDTO = vocabularyTopicService.findOne(id);
        return ResponseUtil.wrapOrNotFound(vocabularyTopicDTO);
    }

    /**
     * {@code DELETE  /vocabulary-topics/:id} : delete the "id" vocabularyTopic.
     *
     * @param id the id of the vocabularyTopicDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/vocabulary-topics/{id}")
    public ResponseEntity<Void> deleteVocabularyTopic(@PathVariable Long id) {
        log.debug("REST request to delete VocabularyTopic : {}", id);
        vocabularyTopicService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
