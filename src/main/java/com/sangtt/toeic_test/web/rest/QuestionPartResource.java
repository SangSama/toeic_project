package com.sangtt.toeic_test.web.rest;

import com.sangtt.toeic_test.repository.QuestionPartRepository;
import com.sangtt.toeic_test.service.QuestionPartService;
import com.sangtt.toeic_test.service.dto.QuestionPartDTO;
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
 * REST controller for managing {@link com.sangtt.toeic_test.domain.QuestionPart}.
 */
@RestController
@RequestMapping("/api")
public class QuestionPartResource {

    private final Logger log = LoggerFactory.getLogger(QuestionPartResource.class);

    private static final String ENTITY_NAME = "questionPart";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final QuestionPartService questionPartService;

    private final QuestionPartRepository questionPartRepository;

    public QuestionPartResource(QuestionPartService questionPartService, QuestionPartRepository questionPartRepository) {
        this.questionPartService = questionPartService;
        this.questionPartRepository = questionPartRepository;
    }

    /**
     * {@code POST  /question-parts} : Create a new questionPart.
     *
     * @param questionPartDTO the questionPartDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new questionPartDTO, or with status {@code 400 (Bad Request)} if the questionPart has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/question-parts")
    public ResponseEntity<QuestionPartDTO> createQuestionPart(@Valid @RequestBody QuestionPartDTO questionPartDTO)
        throws URISyntaxException {
        log.debug("REST request to save QuestionPart : {}", questionPartDTO);
        if (questionPartDTO.getId() != null) {
            throw new BadRequestAlertException("A new questionPart cannot already have an ID", ENTITY_NAME, "idexists");
        }
        QuestionPartDTO result = questionPartService.save(questionPartDTO);
        return ResponseEntity
            .created(new URI("/api/question-parts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /question-parts/:id} : Updates an existing questionPart.
     *
     * @param id the id of the questionPartDTO to save.
     * @param questionPartDTO the questionPartDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated questionPartDTO,
     * or with status {@code 400 (Bad Request)} if the questionPartDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the questionPartDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/question-parts/{id}")
    public ResponseEntity<QuestionPartDTO> updateQuestionPart(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody QuestionPartDTO questionPartDTO
    ) throws URISyntaxException {
        log.debug("REST request to update QuestionPart : {}, {}", id, questionPartDTO);
        if (questionPartDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, questionPartDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!questionPartRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        QuestionPartDTO result = questionPartService.save(questionPartDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, questionPartDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /question-parts/:id} : Partial updates given fields of an existing questionPart, field will ignore if it is null
     *
     * @param id the id of the questionPartDTO to save.
     * @param questionPartDTO the questionPartDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated questionPartDTO,
     * or with status {@code 400 (Bad Request)} if the questionPartDTO is not valid,
     * or with status {@code 404 (Not Found)} if the questionPartDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the questionPartDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/question-parts/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<QuestionPartDTO> partialUpdateQuestionPart(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody QuestionPartDTO questionPartDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update QuestionPart partially : {}, {}", id, questionPartDTO);
        if (questionPartDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, questionPartDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!questionPartRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<QuestionPartDTO> result = questionPartService.partialUpdate(questionPartDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, questionPartDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /question-parts} : get all the questionParts.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of questionParts in body.
     */
    @GetMapping("/question-parts")
    public ResponseEntity<List<QuestionPartDTO>> getAllQuestionParts(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of QuestionParts");
        Page<QuestionPartDTO> page = questionPartService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /question-parts/:id} : get the "id" questionPart.
     *
     * @param id the id of the questionPartDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the questionPartDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/question-parts/{id}")
    public ResponseEntity<QuestionPartDTO> getQuestionPart(@PathVariable Long id) {
        log.debug("REST request to get QuestionPart : {}", id);
        Optional<QuestionPartDTO> questionPartDTO = questionPartService.findOne(id);
        return ResponseUtil.wrapOrNotFound(questionPartDTO);
    }

    /**
     * {@code DELETE  /question-parts/:id} : delete the "id" questionPart.
     *
     * @param id the id of the questionPartDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/question-parts/{id}")
    public ResponseEntity<Void> deleteQuestionPart(@PathVariable Long id) {
        log.debug("REST request to delete QuestionPart : {}", id);
        questionPartService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
