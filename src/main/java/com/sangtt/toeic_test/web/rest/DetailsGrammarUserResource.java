package com.sangtt.toeic_test.web.rest;

import com.sangtt.toeic_test.repository.DetailsGrammarUserRepository;
import com.sangtt.toeic_test.service.DetailsGrammarUserService;
import com.sangtt.toeic_test.service.dto.DetailsGrammarUserDTO;
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
 * REST controller for managing {@link com.sangtt.toeic_test.domain.DetailsGrammarUser}.
 */
@RestController
@RequestMapping("/api")
public class DetailsGrammarUserResource {

    private final Logger log = LoggerFactory.getLogger(DetailsGrammarUserResource.class);

    private static final String ENTITY_NAME = "detailsGrammarUser";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DetailsGrammarUserService detailsGrammarUserService;

    private final DetailsGrammarUserRepository detailsGrammarUserRepository;

    public DetailsGrammarUserResource(
        DetailsGrammarUserService detailsGrammarUserService,
        DetailsGrammarUserRepository detailsGrammarUserRepository
    ) {
        this.detailsGrammarUserService = detailsGrammarUserService;
        this.detailsGrammarUserRepository = detailsGrammarUserRepository;
    }

    /**
     * {@code POST  /details-grammar-users} : Create a new detailsGrammarUser.
     *
     * @param detailsGrammarUserDTO the detailsGrammarUserDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new detailsGrammarUserDTO, or with status {@code 400 (Bad Request)} if the detailsGrammarUser has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/details-grammar-users")
    public ResponseEntity<DetailsGrammarUserDTO> createDetailsGrammarUser(@Valid @RequestBody DetailsGrammarUserDTO detailsGrammarUserDTO)
        throws URISyntaxException {
        log.debug("REST request to save DetailsGrammarUser : {}", detailsGrammarUserDTO);
        if (detailsGrammarUserDTO.getId() != null) {
            throw new BadRequestAlertException("A new detailsGrammarUser cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DetailsGrammarUserDTO result = detailsGrammarUserService.save(detailsGrammarUserDTO);
        return ResponseEntity
            .created(new URI("/api/details-grammar-users/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /details-grammar-users/:id} : Updates an existing detailsGrammarUser.
     *
     * @param id the id of the detailsGrammarUserDTO to save.
     * @param detailsGrammarUserDTO the detailsGrammarUserDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated detailsGrammarUserDTO,
     * or with status {@code 400 (Bad Request)} if the detailsGrammarUserDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the detailsGrammarUserDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/details-grammar-users/{id}")
    public ResponseEntity<DetailsGrammarUserDTO> updateDetailsGrammarUser(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody DetailsGrammarUserDTO detailsGrammarUserDTO
    ) throws URISyntaxException {
        log.debug("REST request to update DetailsGrammarUser : {}, {}", id, detailsGrammarUserDTO);
        if (detailsGrammarUserDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, detailsGrammarUserDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!detailsGrammarUserRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        DetailsGrammarUserDTO result = detailsGrammarUserService.save(detailsGrammarUserDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, detailsGrammarUserDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /details-grammar-users/:id} : Partial updates given fields of an existing detailsGrammarUser, field will ignore if it is null
     *
     * @param id the id of the detailsGrammarUserDTO to save.
     * @param detailsGrammarUserDTO the detailsGrammarUserDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated detailsGrammarUserDTO,
     * or with status {@code 400 (Bad Request)} if the detailsGrammarUserDTO is not valid,
     * or with status {@code 404 (Not Found)} if the detailsGrammarUserDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the detailsGrammarUserDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/details-grammar-users/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DetailsGrammarUserDTO> partialUpdateDetailsGrammarUser(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody DetailsGrammarUserDTO detailsGrammarUserDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update DetailsGrammarUser partially : {}, {}", id, detailsGrammarUserDTO);
        if (detailsGrammarUserDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, detailsGrammarUserDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!detailsGrammarUserRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DetailsGrammarUserDTO> result = detailsGrammarUserService.partialUpdate(detailsGrammarUserDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, detailsGrammarUserDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /details-grammar-users} : get all the detailsGrammarUsers.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of detailsGrammarUsers in body.
     */
    @GetMapping("/details-grammar-users")
    public ResponseEntity<List<DetailsGrammarUserDTO>> getAllDetailsGrammarUsers(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of DetailsGrammarUsers");
        Page<DetailsGrammarUserDTO> page = detailsGrammarUserService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /details-grammar-users/:id} : get the "id" detailsGrammarUser.
     *
     * @param id the id of the detailsGrammarUserDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the detailsGrammarUserDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/details-grammar-users/{id}")
    public ResponseEntity<DetailsGrammarUserDTO> getDetailsGrammarUser(@PathVariable Long id) {
        log.debug("REST request to get DetailsGrammarUser : {}", id);
        Optional<DetailsGrammarUserDTO> detailsGrammarUserDTO = detailsGrammarUserService.findOne(id);
        return ResponseUtil.wrapOrNotFound(detailsGrammarUserDTO);
    }

    /**
     * {@code DELETE  /details-grammar-users/:id} : delete the "id" detailsGrammarUser.
     *
     * @param id the id of the detailsGrammarUserDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/details-grammar-users/{id}")
    public ResponseEntity<Void> deleteDetailsGrammarUser(@PathVariable Long id) {
        log.debug("REST request to delete DetailsGrammarUser : {}", id);
        detailsGrammarUserService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
