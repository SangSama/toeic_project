package com.sangtt.toeic_test.web.rest;

import com.sangtt.toeic_test.repository.GrammarTopicRepository;
import com.sangtt.toeic_test.service.GrammarTopicService;
import com.sangtt.toeic_test.service.dto.GrammarTopicDTO;
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
 * REST controller for managing {@link com.sangtt.toeic_test.domain.GrammarTopic}.
 */
@RestController
@RequestMapping("/api")
public class GrammarTopicResource {

    private final Logger log = LoggerFactory.getLogger(GrammarTopicResource.class);

    private static final String ENTITY_NAME = "grammarTopic";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GrammarTopicService grammarTopicService;

    private final GrammarTopicRepository grammarTopicRepository;

    public GrammarTopicResource(GrammarTopicService grammarTopicService, GrammarTopicRepository grammarTopicRepository) {
        this.grammarTopicService = grammarTopicService;
        this.grammarTopicRepository = grammarTopicRepository;
    }

    /**
     * {@code POST  /grammar-topics} : Create a new grammarTopic.
     *
     * @param grammarTopicDTO the grammarTopicDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new grammarTopicDTO, or with status {@code 400 (Bad Request)} if the grammarTopic has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/grammar-topics")
    public ResponseEntity<GrammarTopicDTO> createGrammarTopic(@Valid @RequestBody GrammarTopicDTO grammarTopicDTO)
        throws URISyntaxException {
        log.debug("REST request to save GrammarTopic : {}", grammarTopicDTO);
        if (grammarTopicDTO.getId() != null) {
            throw new BadRequestAlertException("A new grammarTopic cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GrammarTopicDTO result = grammarTopicService.save(grammarTopicDTO);
        return ResponseEntity
            .created(new URI("/api/grammar-topics/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /grammar-topics/:id} : Updates an existing grammarTopic.
     *
     * @param id the id of the grammarTopicDTO to save.
     * @param grammarTopicDTO the grammarTopicDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated grammarTopicDTO,
     * or with status {@code 400 (Bad Request)} if the grammarTopicDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the grammarTopicDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/grammar-topics/{id}")
    public ResponseEntity<GrammarTopicDTO> updateGrammarTopic(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody GrammarTopicDTO grammarTopicDTO
    ) throws URISyntaxException {
        log.debug("REST request to update GrammarTopic : {}, {}", id, grammarTopicDTO);
        if (grammarTopicDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, grammarTopicDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!grammarTopicRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        GrammarTopicDTO result = grammarTopicService.save(grammarTopicDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, grammarTopicDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /grammar-topics/:id} : Partial updates given fields of an existing grammarTopic, field will ignore if it is null
     *
     * @param id the id of the grammarTopicDTO to save.
     * @param grammarTopicDTO the grammarTopicDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated grammarTopicDTO,
     * or with status {@code 400 (Bad Request)} if the grammarTopicDTO is not valid,
     * or with status {@code 404 (Not Found)} if the grammarTopicDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the grammarTopicDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/grammar-topics/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<GrammarTopicDTO> partialUpdateGrammarTopic(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody GrammarTopicDTO grammarTopicDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update GrammarTopic partially : {}, {}", id, grammarTopicDTO);
        if (grammarTopicDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, grammarTopicDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!grammarTopicRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<GrammarTopicDTO> result = grammarTopicService.partialUpdate(grammarTopicDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, grammarTopicDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /grammar-topics} : get all the grammarTopics.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of grammarTopics in body.
     */
    @GetMapping("/grammar-topics")
    public ResponseEntity<List<GrammarTopicDTO>> getAllGrammarTopics(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of GrammarTopics");
        Page<GrammarTopicDTO> page = grammarTopicService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /grammar-topics/:id} : get the "id" grammarTopic.
     *
     * @param id the id of the grammarTopicDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the grammarTopicDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/grammar-topics/{id}")
    public ResponseEntity<GrammarTopicDTO> getGrammarTopic(@PathVariable Long id) {
        log.debug("REST request to get GrammarTopic : {}", id);
        Optional<GrammarTopicDTO> grammarTopicDTO = grammarTopicService.findOne(id);
        return ResponseUtil.wrapOrNotFound(grammarTopicDTO);
    }

    /**
     * {@code DELETE  /grammar-topics/:id} : delete the "id" grammarTopic.
     *
     * @param id the id of the grammarTopicDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/grammar-topics/{id}")
    public ResponseEntity<Void> deleteGrammarTopic(@PathVariable Long id) {
        log.debug("REST request to delete GrammarTopic : {}", id);
        grammarTopicService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
