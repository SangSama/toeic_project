package com.sangtt.toeic_test.web.rest;

import com.sangtt.toeic_test.repository.DetailsToeicUserRepository;
import com.sangtt.toeic_test.service.DetailsToeicUserService;
import com.sangtt.toeic_test.service.dto.DetailsToeicUserDTO;
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
 * REST controller for managing {@link com.sangtt.toeic_test.domain.DetailsToeicUser}.
 */
@RestController
@RequestMapping("/api")
public class DetailsToeicUserResource {

    private final Logger log = LoggerFactory.getLogger(DetailsToeicUserResource.class);

    private static final String ENTITY_NAME = "detailsToeicUser";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DetailsToeicUserService detailsToeicUserService;

    private final DetailsToeicUserRepository detailsToeicUserRepository;

    public DetailsToeicUserResource(
        DetailsToeicUserService detailsToeicUserService,
        DetailsToeicUserRepository detailsToeicUserRepository
    ) {
        this.detailsToeicUserService = detailsToeicUserService;
        this.detailsToeicUserRepository = detailsToeicUserRepository;
    }

    /**
     * {@code POST  /details-toeic-users} : Create a new detailsToeicUser.
     *
     * @param detailsToeicUserDTO the detailsToeicUserDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new detailsToeicUserDTO, or with status {@code 400 (Bad Request)} if the detailsToeicUser has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/details-toeic-users")
    public ResponseEntity<DetailsToeicUserDTO> createDetailsToeicUser(@Valid @RequestBody DetailsToeicUserDTO detailsToeicUserDTO)
        throws URISyntaxException {
        log.debug("REST request to save DetailsToeicUser : {}", detailsToeicUserDTO);
        if (detailsToeicUserDTO.getId() != null) {
            throw new BadRequestAlertException("A new detailsToeicUser cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DetailsToeicUserDTO result = detailsToeicUserService.save(detailsToeicUserDTO);
        return ResponseEntity
            .created(new URI("/api/details-toeic-users/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /details-toeic-users/:id} : Updates an existing detailsToeicUser.
     *
     * @param id the id of the detailsToeicUserDTO to save.
     * @param detailsToeicUserDTO the detailsToeicUserDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated detailsToeicUserDTO,
     * or with status {@code 400 (Bad Request)} if the detailsToeicUserDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the detailsToeicUserDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/details-toeic-users/{id}")
    public ResponseEntity<DetailsToeicUserDTO> updateDetailsToeicUser(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody DetailsToeicUserDTO detailsToeicUserDTO
    ) throws URISyntaxException {
        log.debug("REST request to update DetailsToeicUser : {}, {}", id, detailsToeicUserDTO);
        if (detailsToeicUserDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, detailsToeicUserDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!detailsToeicUserRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        DetailsToeicUserDTO result = detailsToeicUserService.save(detailsToeicUserDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, detailsToeicUserDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /details-toeic-users/:id} : Partial updates given fields of an existing detailsToeicUser, field will ignore if it is null
     *
     * @param id the id of the detailsToeicUserDTO to save.
     * @param detailsToeicUserDTO the detailsToeicUserDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated detailsToeicUserDTO,
     * or with status {@code 400 (Bad Request)} if the detailsToeicUserDTO is not valid,
     * or with status {@code 404 (Not Found)} if the detailsToeicUserDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the detailsToeicUserDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/details-toeic-users/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DetailsToeicUserDTO> partialUpdateDetailsToeicUser(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody DetailsToeicUserDTO detailsToeicUserDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update DetailsToeicUser partially : {}, {}", id, detailsToeicUserDTO);
        if (detailsToeicUserDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, detailsToeicUserDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!detailsToeicUserRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DetailsToeicUserDTO> result = detailsToeicUserService.partialUpdate(detailsToeicUserDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, detailsToeicUserDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /details-toeic-users} : get all the detailsToeicUsers.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of detailsToeicUsers in body.
     */
    @GetMapping("/details-toeic-users")
    public ResponseEntity<List<DetailsToeicUserDTO>> getAllDetailsToeicUsers(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of DetailsToeicUsers");
        Page<DetailsToeicUserDTO> page = detailsToeicUserService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /details-toeic-users/:id} : get the "id" detailsToeicUser.
     *
     * @param id the id of the detailsToeicUserDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the detailsToeicUserDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/details-toeic-users/{id}")
    public ResponseEntity<DetailsToeicUserDTO> getDetailsToeicUser(@PathVariable Long id) {
        log.debug("REST request to get DetailsToeicUser : {}", id);
        Optional<DetailsToeicUserDTO> detailsToeicUserDTO = detailsToeicUserService.findOne(id);
        return ResponseUtil.wrapOrNotFound(detailsToeicUserDTO);
    }

    /**
     * {@code DELETE  /details-toeic-users/:id} : delete the "id" detailsToeicUser.
     *
     * @param id the id of the detailsToeicUserDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/details-toeic-users/{id}")
    public ResponseEntity<Void> deleteDetailsToeicUser(@PathVariable Long id) {
        log.debug("REST request to delete DetailsToeicUser : {}", id);
        detailsToeicUserService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
