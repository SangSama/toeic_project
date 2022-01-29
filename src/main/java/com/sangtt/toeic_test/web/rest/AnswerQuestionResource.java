package com.sangtt.toeic_test.web.rest;

import com.sangtt.toeic_test.repository.AnswerQuestionRepository;
import com.sangtt.toeic_test.service.AnswerQuestionService;
import com.sangtt.toeic_test.service.dto.AnswerQuestionDTO;
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
 * REST controller for managing {@link com.sangtt.toeic_test.domain.AnswerQuestion}.
 */
@RestController
@RequestMapping("/api")
public class AnswerQuestionResource {

    private final Logger log = LoggerFactory.getLogger(AnswerQuestionResource.class);

    private static final String ENTITY_NAME = "answerQuestion";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AnswerQuestionService answerQuestionService;

    private final AnswerQuestionRepository answerQuestionRepository;

    public AnswerQuestionResource(AnswerQuestionService answerQuestionService, AnswerQuestionRepository answerQuestionRepository) {
        this.answerQuestionService = answerQuestionService;
        this.answerQuestionRepository = answerQuestionRepository;
    }

    /**
     * {@code POST  /answer-questions} : Create a new answerQuestion.
     *
     * @param answerQuestionDTO the answerQuestionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new answerQuestionDTO, or with status {@code 400 (Bad Request)} if the answerQuestion has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/answer-questions")
    public ResponseEntity<AnswerQuestionDTO> createAnswerQuestion(@Valid @RequestBody AnswerQuestionDTO answerQuestionDTO)
        throws URISyntaxException {
        log.debug("REST request to save AnswerQuestion : {}", answerQuestionDTO);
        if (answerQuestionDTO.getId() != null) {
            throw new BadRequestAlertException("A new answerQuestion cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AnswerQuestionDTO result = answerQuestionService.save(answerQuestionDTO);
        return ResponseEntity
            .created(new URI("/api/answer-questions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /answer-questions/:id} : Updates an existing answerQuestion.
     *
     * @param id the id of the answerQuestionDTO to save.
     * @param answerQuestionDTO the answerQuestionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated answerQuestionDTO,
     * or with status {@code 400 (Bad Request)} if the answerQuestionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the answerQuestionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/answer-questions/{id}")
    public ResponseEntity<AnswerQuestionDTO> updateAnswerQuestion(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody AnswerQuestionDTO answerQuestionDTO
    ) throws URISyntaxException {
        log.debug("REST request to update AnswerQuestion : {}, {}", id, answerQuestionDTO);
        if (answerQuestionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, answerQuestionDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!answerQuestionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        AnswerQuestionDTO result = answerQuestionService.save(answerQuestionDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, answerQuestionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /answer-questions/:id} : Partial updates given fields of an existing answerQuestion, field will ignore if it is null
     *
     * @param id the id of the answerQuestionDTO to save.
     * @param answerQuestionDTO the answerQuestionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated answerQuestionDTO,
     * or with status {@code 400 (Bad Request)} if the answerQuestionDTO is not valid,
     * or with status {@code 404 (Not Found)} if the answerQuestionDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the answerQuestionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/answer-questions/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<AnswerQuestionDTO> partialUpdateAnswerQuestion(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody AnswerQuestionDTO answerQuestionDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update AnswerQuestion partially : {}, {}", id, answerQuestionDTO);
        if (answerQuestionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, answerQuestionDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!answerQuestionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<AnswerQuestionDTO> result = answerQuestionService.partialUpdate(answerQuestionDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, answerQuestionDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /answer-questions} : get all the answerQuestions.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of answerQuestions in body.
     */
    @GetMapping("/answer-questions")
    public ResponseEntity<List<AnswerQuestionDTO>> getAllAnswerQuestions(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of AnswerQuestions");
        Page<AnswerQuestionDTO> page = answerQuestionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /answer-questions/:id} : get the "id" answerQuestion.
     *
     * @param id the id of the answerQuestionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the answerQuestionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/answer-questions/{id}")
    public ResponseEntity<AnswerQuestionDTO> getAnswerQuestion(@PathVariable Long id) {
        log.debug("REST request to get AnswerQuestion : {}", id);
        Optional<AnswerQuestionDTO> answerQuestionDTO = answerQuestionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(answerQuestionDTO);
    }

    /**
     * {@code DELETE  /answer-questions/:id} : delete the "id" answerQuestion.
     *
     * @param id the id of the answerQuestionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/answer-questions/{id}")
    public ResponseEntity<Void> deleteAnswerQuestion(@PathVariable Long id) {
        log.debug("REST request to delete AnswerQuestion : {}", id);
        answerQuestionService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
