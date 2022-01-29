package com.sangtt.toeic_test.web.rest;

import com.sangtt.toeic_test.repository.ToeicsRepository;
import com.sangtt.toeic_test.service.ToeicsService;
import com.sangtt.toeic_test.service.dto.ToeicsDTO;
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
 * REST controller for managing {@link com.sangtt.toeic_test.domain.Toeics}.
 */
@RestController
@RequestMapping("/api")
public class ToeicsResource {

    private final Logger log = LoggerFactory.getLogger(ToeicsResource.class);

    private static final String ENTITY_NAME = "toeics";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ToeicsService toeicsService;

    private final ToeicsRepository toeicsRepository;

    public ToeicsResource(ToeicsService toeicsService, ToeicsRepository toeicsRepository) {
        this.toeicsService = toeicsService;
        this.toeicsRepository = toeicsRepository;
    }

    /**
     * {@code POST  /toeics} : Create a new toeics.
     *
     * @param toeicsDTO the toeicsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new toeicsDTO, or with status {@code 400 (Bad Request)} if the toeics has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/toeics")
    public ResponseEntity<ToeicsDTO> createToeics(@Valid @RequestBody ToeicsDTO toeicsDTO) throws URISyntaxException {
        log.debug("REST request to save Toeics : {}", toeicsDTO);
        if (toeicsDTO.getId() != null) {
            throw new BadRequestAlertException("A new toeics cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ToeicsDTO result = toeicsService.save(toeicsDTO);
        return ResponseEntity
            .created(new URI("/api/toeics/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /toeics/:id} : Updates an existing toeics.
     *
     * @param id the id of the toeicsDTO to save.
     * @param toeicsDTO the toeicsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated toeicsDTO,
     * or with status {@code 400 (Bad Request)} if the toeicsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the toeicsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/toeics/{id}")
    public ResponseEntity<ToeicsDTO> updateToeics(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody ToeicsDTO toeicsDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Toeics : {}, {}", id, toeicsDTO);
        if (toeicsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, toeicsDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!toeicsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ToeicsDTO result = toeicsService.save(toeicsDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, toeicsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /toeics/:id} : Partial updates given fields of an existing toeics, field will ignore if it is null
     *
     * @param id the id of the toeicsDTO to save.
     * @param toeicsDTO the toeicsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated toeicsDTO,
     * or with status {@code 400 (Bad Request)} if the toeicsDTO is not valid,
     * or with status {@code 404 (Not Found)} if the toeicsDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the toeicsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/toeics/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ToeicsDTO> partialUpdateToeics(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody ToeicsDTO toeicsDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Toeics partially : {}, {}", id, toeicsDTO);
        if (toeicsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, toeicsDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!toeicsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ToeicsDTO> result = toeicsService.partialUpdate(toeicsDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, toeicsDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /toeics} : get all the toeics.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of toeics in body.
     */
    @GetMapping("/toeics")
    public ResponseEntity<List<ToeicsDTO>> getAllToeics(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Toeics");
        Page<ToeicsDTO> page = toeicsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /toeics/:id} : get the "id" toeics.
     *
     * @param id the id of the toeicsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the toeicsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/toeics/{id}")
    public ResponseEntity<ToeicsDTO> getToeics(@PathVariable Long id) {
        log.debug("REST request to get Toeics : {}", id);
        Optional<ToeicsDTO> toeicsDTO = toeicsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(toeicsDTO);
    }

    /**
     * {@code DELETE  /toeics/:id} : delete the "id" toeics.
     *
     * @param id the id of the toeicsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/toeics/{id}")
    public ResponseEntity<Void> deleteToeics(@PathVariable Long id) {
        log.debug("REST request to delete Toeics : {}", id);
        toeicsService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
