package com.sangtt.toeic_test.web.rest;

import com.sangtt.toeic_test.repository.PartsRepository;
import com.sangtt.toeic_test.service.PartsService;
import com.sangtt.toeic_test.service.dto.PartsDTO;
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
 * REST controller for managing {@link com.sangtt.toeic_test.domain.Parts}.
 */
@RestController
@RequestMapping("/api")
public class PartsResource {

    private final Logger log = LoggerFactory.getLogger(PartsResource.class);

    private static final String ENTITY_NAME = "parts";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PartsService partsService;

    private final PartsRepository partsRepository;

    public PartsResource(PartsService partsService, PartsRepository partsRepository) {
        this.partsService = partsService;
        this.partsRepository = partsRepository;
    }

    /**
     * {@code POST  /parts} : Create a new parts.
     *
     * @param partsDTO the partsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new partsDTO, or with status {@code 400 (Bad Request)} if the parts has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/parts")
    public ResponseEntity<PartsDTO> createParts(@Valid @RequestBody PartsDTO partsDTO) throws URISyntaxException {
        log.debug("REST request to save Parts : {}", partsDTO);
        if (partsDTO.getId() != null) {
            throw new BadRequestAlertException("A new parts cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PartsDTO result = partsService.save(partsDTO);
        return ResponseEntity
            .created(new URI("/api/parts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /parts/:id} : Updates an existing parts.
     *
     * @param id the id of the partsDTO to save.
     * @param partsDTO the partsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated partsDTO,
     * or with status {@code 400 (Bad Request)} if the partsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the partsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/parts/{id}")
    public ResponseEntity<PartsDTO> updateParts(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody PartsDTO partsDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Parts : {}, {}", id, partsDTO);
        if (partsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, partsDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!partsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        PartsDTO result = partsService.save(partsDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, partsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /parts/:id} : Partial updates given fields of an existing parts, field will ignore if it is null
     *
     * @param id the id of the partsDTO to save.
     * @param partsDTO the partsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated partsDTO,
     * or with status {@code 400 (Bad Request)} if the partsDTO is not valid,
     * or with status {@code 404 (Not Found)} if the partsDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the partsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/parts/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<PartsDTO> partialUpdateParts(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody PartsDTO partsDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Parts partially : {}, {}", id, partsDTO);
        if (partsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, partsDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!partsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<PartsDTO> result = partsService.partialUpdate(partsDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, partsDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /parts} : get all the parts.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of parts in body.
     */
    @GetMapping("/parts")
    public ResponseEntity<List<PartsDTO>> getAllParts(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Parts");
        Page<PartsDTO> page = partsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /parts/:id} : get the "id" parts.
     *
     * @param id the id of the partsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the partsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/parts/{id}")
    public ResponseEntity<PartsDTO> getParts(@PathVariable Long id) {
        log.debug("REST request to get Parts : {}", id);
        Optional<PartsDTO> partsDTO = partsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(partsDTO);
    }

    /**
     * {@code DELETE  /parts/:id} : delete the "id" parts.
     *
     * @param id the id of the partsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/parts/{id}")
    public ResponseEntity<Void> deleteParts(@PathVariable Long id) {
        log.debug("REST request to delete Parts : {}", id);
        partsService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
