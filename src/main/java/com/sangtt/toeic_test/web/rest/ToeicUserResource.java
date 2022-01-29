package com.sangtt.toeic_test.web.rest;

import com.sangtt.toeic_test.repository.ToeicUserRepository;
import com.sangtt.toeic_test.service.ToeicUserService;
import com.sangtt.toeic_test.service.dto.ToeicUserDTO;
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
 * REST controller for managing {@link com.sangtt.toeic_test.domain.ToeicUser}.
 */
@RestController
@RequestMapping("/api")
public class ToeicUserResource {

    private final Logger log = LoggerFactory.getLogger(ToeicUserResource.class);

    private static final String ENTITY_NAME = "toeicUser";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ToeicUserService toeicUserService;

    private final ToeicUserRepository toeicUserRepository;

    public ToeicUserResource(ToeicUserService toeicUserService, ToeicUserRepository toeicUserRepository) {
        this.toeicUserService = toeicUserService;
        this.toeicUserRepository = toeicUserRepository;
    }

    /**
     * {@code POST  /toeic-users} : Create a new toeicUser.
     *
     * @param toeicUserDTO the toeicUserDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new toeicUserDTO, or with status {@code 400 (Bad Request)} if the toeicUser has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/toeic-users")
    public ResponseEntity<ToeicUserDTO> createToeicUser(@Valid @RequestBody ToeicUserDTO toeicUserDTO) throws URISyntaxException {
        log.debug("REST request to save ToeicUser : {}", toeicUserDTO);
        if (toeicUserDTO.getId() != null) {
            throw new BadRequestAlertException("A new toeicUser cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ToeicUserDTO result = toeicUserService.save(toeicUserDTO);
        return ResponseEntity
            .created(new URI("/api/toeic-users/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /toeic-users/:id} : Updates an existing toeicUser.
     *
     * @param id the id of the toeicUserDTO to save.
     * @param toeicUserDTO the toeicUserDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated toeicUserDTO,
     * or with status {@code 400 (Bad Request)} if the toeicUserDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the toeicUserDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/toeic-users/{id}")
    public ResponseEntity<ToeicUserDTO> updateToeicUser(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody ToeicUserDTO toeicUserDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ToeicUser : {}, {}", id, toeicUserDTO);
        if (toeicUserDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, toeicUserDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!toeicUserRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ToeicUserDTO result = toeicUserService.save(toeicUserDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, toeicUserDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /toeic-users/:id} : Partial updates given fields of an existing toeicUser, field will ignore if it is null
     *
     * @param id the id of the toeicUserDTO to save.
     * @param toeicUserDTO the toeicUserDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated toeicUserDTO,
     * or with status {@code 400 (Bad Request)} if the toeicUserDTO is not valid,
     * or with status {@code 404 (Not Found)} if the toeicUserDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the toeicUserDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/toeic-users/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ToeicUserDTO> partialUpdateToeicUser(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody ToeicUserDTO toeicUserDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ToeicUser partially : {}, {}", id, toeicUserDTO);
        if (toeicUserDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, toeicUserDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!toeicUserRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ToeicUserDTO> result = toeicUserService.partialUpdate(toeicUserDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, toeicUserDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /toeic-users} : get all the toeicUsers.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of toeicUsers in body.
     */
    @GetMapping("/toeic-users")
    public ResponseEntity<List<ToeicUserDTO>> getAllToeicUsers(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of ToeicUsers");
        Page<ToeicUserDTO> page = toeicUserService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /toeic-users/:id} : get the "id" toeicUser.
     *
     * @param id the id of the toeicUserDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the toeicUserDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/toeic-users/{id}")
    public ResponseEntity<ToeicUserDTO> getToeicUser(@PathVariable Long id) {
        log.debug("REST request to get ToeicUser : {}", id);
        Optional<ToeicUserDTO> toeicUserDTO = toeicUserService.findOne(id);
        return ResponseUtil.wrapOrNotFound(toeicUserDTO);
    }

    /**
     * {@code DELETE  /toeic-users/:id} : delete the "id" toeicUser.
     *
     * @param id the id of the toeicUserDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/toeic-users/{id}")
    public ResponseEntity<Void> deleteToeicUser(@PathVariable Long id) {
        log.debug("REST request to delete ToeicUser : {}", id);
        toeicUserService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
