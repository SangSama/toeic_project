package com.sangtt.toeic_test.web.rest;

import com.sangtt.toeic_test.repository.ExtendQuestionRepository;
import com.sangtt.toeic_test.service.ExtendQuestionService;
import com.sangtt.toeic_test.service.dto.ExtendQuestionDTO;
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
 * REST controller for managing {@link com.sangtt.toeic_test.domain.ExtendQuestion}.
 */
@RestController
@RequestMapping("/api")
public class ExtendQuestionResource {

    private final Logger log = LoggerFactory.getLogger(ExtendQuestionResource.class);

    private static final String ENTITY_NAME = "extendQuestion";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ExtendQuestionService extendQuestionService;

    private final ExtendQuestionRepository extendQuestionRepository;

    public ExtendQuestionResource(ExtendQuestionService extendQuestionService, ExtendQuestionRepository extendQuestionRepository) {
        this.extendQuestionService = extendQuestionService;
        this.extendQuestionRepository = extendQuestionRepository;
    }

    /**
     * {@code POST  /extend-questions} : Create a new extendQuestion.
     *
     * @param extendQuestionDTO the extendQuestionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new extendQuestionDTO, or with status {@code 400 (Bad Request)} if the extendQuestion has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/extend-questions")
    public ResponseEntity<ExtendQuestionDTO> createExtendQuestion(@Valid @RequestBody ExtendQuestionDTO extendQuestionDTO)
        throws URISyntaxException {
        log.debug("REST request to save ExtendQuestion : {}", extendQuestionDTO);
        if (extendQuestionDTO.getId() != null) {
            throw new BadRequestAlertException("A new extendQuestion cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ExtendQuestionDTO result = extendQuestionService.save(extendQuestionDTO);
        return ResponseEntity
            .created(new URI("/api/extend-questions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /extend-questions/:id} : Updates an existing extendQuestion.
     *
     * @param id the id of the extendQuestionDTO to save.
     * @param extendQuestionDTO the extendQuestionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated extendQuestionDTO,
     * or with status {@code 400 (Bad Request)} if the extendQuestionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the extendQuestionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/extend-questions/{id}")
    public ResponseEntity<ExtendQuestionDTO> updateExtendQuestion(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody ExtendQuestionDTO extendQuestionDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ExtendQuestion : {}, {}", id, extendQuestionDTO);
        if (extendQuestionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, extendQuestionDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!extendQuestionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ExtendQuestionDTO result = extendQuestionService.save(extendQuestionDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, extendQuestionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /extend-questions/:id} : Partial updates given fields of an existing extendQuestion, field will ignore if it is null
     *
     * @param id the id of the extendQuestionDTO to save.
     * @param extendQuestionDTO the extendQuestionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated extendQuestionDTO,
     * or with status {@code 400 (Bad Request)} if the extendQuestionDTO is not valid,
     * or with status {@code 404 (Not Found)} if the extendQuestionDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the extendQuestionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/extend-questions/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ExtendQuestionDTO> partialUpdateExtendQuestion(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody ExtendQuestionDTO extendQuestionDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ExtendQuestion partially : {}, {}", id, extendQuestionDTO);
        if (extendQuestionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, extendQuestionDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!extendQuestionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ExtendQuestionDTO> result = extendQuestionService.partialUpdate(extendQuestionDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, extendQuestionDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /extend-questions} : get all the extendQuestions.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of extendQuestions in body.
     */
    @GetMapping("/extend-questions")
    public ResponseEntity<List<ExtendQuestionDTO>> getAllExtendQuestions(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of ExtendQuestions");
        Page<ExtendQuestionDTO> page = extendQuestionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /extend-questions/:id} : get the "id" extendQuestion.
     *
     * @param id the id of the extendQuestionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the extendQuestionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/extend-questions/{id}")
    public ResponseEntity<ExtendQuestionDTO> getExtendQuestion(@PathVariable Long id) {
        log.debug("REST request to get ExtendQuestion : {}", id);
        Optional<ExtendQuestionDTO> extendQuestionDTO = extendQuestionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(extendQuestionDTO);
    }

    /**
     * {@code DELETE  /extend-questions/:id} : delete the "id" extendQuestion.
     *
     * @param id the id of the extendQuestionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/extend-questions/{id}")
    public ResponseEntity<Void> deleteExtendQuestion(@PathVariable Long id) {
        log.debug("REST request to delete ExtendQuestion : {}", id);
        extendQuestionService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
