package com.sangtt.toeic_test.web.rest;

import com.sangtt.toeic_test.repository.GrammarAnswerRepository;
import com.sangtt.toeic_test.service.GrammarAnswerService;
import com.sangtt.toeic_test.service.dto.GrammarAnswerDTO;
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
 * REST controller for managing {@link com.sangtt.toeic_test.domain.GrammarAnswer}.
 */
@RestController
@RequestMapping("/api")
public class GrammarAnswerResource {

    private final Logger log = LoggerFactory.getLogger(GrammarAnswerResource.class);

    private static final String ENTITY_NAME = "grammarAnswer";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GrammarAnswerService grammarAnswerService;

    private final GrammarAnswerRepository grammarAnswerRepository;

    public GrammarAnswerResource(GrammarAnswerService grammarAnswerService, GrammarAnswerRepository grammarAnswerRepository) {
        this.grammarAnswerService = grammarAnswerService;
        this.grammarAnswerRepository = grammarAnswerRepository;
    }

    /**
     * {@code POST  /grammar-answers} : Create a new grammarAnswer.
     *
     * @param grammarAnswerDTO the grammarAnswerDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new grammarAnswerDTO, or with status {@code 400 (Bad Request)} if the grammarAnswer has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/grammar-answers")
    public ResponseEntity<GrammarAnswerDTO> createGrammarAnswer(@Valid @RequestBody GrammarAnswerDTO grammarAnswerDTO)
        throws URISyntaxException {
        log.debug("REST request to save GrammarAnswer : {}", grammarAnswerDTO);
        if (grammarAnswerDTO.getId() != null) {
            throw new BadRequestAlertException("A new grammarAnswer cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GrammarAnswerDTO result = grammarAnswerService.save(grammarAnswerDTO);
        return ResponseEntity
            .created(new URI("/api/grammar-answers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /grammar-answers/:id} : Updates an existing grammarAnswer.
     *
     * @param id the id of the grammarAnswerDTO to save.
     * @param grammarAnswerDTO the grammarAnswerDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated grammarAnswerDTO,
     * or with status {@code 400 (Bad Request)} if the grammarAnswerDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the grammarAnswerDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/grammar-answers/{id}")
    public ResponseEntity<GrammarAnswerDTO> updateGrammarAnswer(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody GrammarAnswerDTO grammarAnswerDTO
    ) throws URISyntaxException {
        log.debug("REST request to update GrammarAnswer : {}, {}", id, grammarAnswerDTO);
        if (grammarAnswerDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, grammarAnswerDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!grammarAnswerRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        GrammarAnswerDTO result = grammarAnswerService.save(grammarAnswerDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, grammarAnswerDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /grammar-answers/:id} : Partial updates given fields of an existing grammarAnswer, field will ignore if it is null
     *
     * @param id the id of the grammarAnswerDTO to save.
     * @param grammarAnswerDTO the grammarAnswerDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated grammarAnswerDTO,
     * or with status {@code 400 (Bad Request)} if the grammarAnswerDTO is not valid,
     * or with status {@code 404 (Not Found)} if the grammarAnswerDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the grammarAnswerDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/grammar-answers/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<GrammarAnswerDTO> partialUpdateGrammarAnswer(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody GrammarAnswerDTO grammarAnswerDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update GrammarAnswer partially : {}, {}", id, grammarAnswerDTO);
        if (grammarAnswerDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, grammarAnswerDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!grammarAnswerRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<GrammarAnswerDTO> result = grammarAnswerService.partialUpdate(grammarAnswerDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, grammarAnswerDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /grammar-answers} : get all the grammarAnswers.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of grammarAnswers in body.
     */
    @GetMapping("/grammar-answers")
    public ResponseEntity<List<GrammarAnswerDTO>> getAllGrammarAnswers(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of GrammarAnswers");
        Page<GrammarAnswerDTO> page = grammarAnswerService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /grammar-answers/:id} : get the "id" grammarAnswer.
     *
     * @param id the id of the grammarAnswerDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the grammarAnswerDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/grammar-answers/{id}")
    public ResponseEntity<GrammarAnswerDTO> getGrammarAnswer(@PathVariable Long id) {
        log.debug("REST request to get GrammarAnswer : {}", id);
        Optional<GrammarAnswerDTO> grammarAnswerDTO = grammarAnswerService.findOne(id);
        return ResponseUtil.wrapOrNotFound(grammarAnswerDTO);
    }

    /**
     * {@code DELETE  /grammar-answers/:id} : delete the "id" grammarAnswer.
     *
     * @param id the id of the grammarAnswerDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/grammar-answers/{id}")
    public ResponseEntity<Void> deleteGrammarAnswer(@PathVariable Long id) {
        log.debug("REST request to delete GrammarAnswer : {}", id);
        grammarAnswerService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
