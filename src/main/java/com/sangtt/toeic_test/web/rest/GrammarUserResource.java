package com.sangtt.toeic_test.web.rest;

import com.sangtt.toeic_test.repository.GrammarUserRepository;
import com.sangtt.toeic_test.service.GrammarUserService;
import com.sangtt.toeic_test.service.dto.GrammarUserDTO;
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
 * REST controller for managing {@link com.sangtt.toeic_test.domain.GrammarUser}.
 */
@RestController
@RequestMapping("/api")
public class GrammarUserResource {

    private final Logger log = LoggerFactory.getLogger(GrammarUserResource.class);

    private static final String ENTITY_NAME = "grammarUser";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GrammarUserService grammarUserService;

    private final GrammarUserRepository grammarUserRepository;

    public GrammarUserResource(GrammarUserService grammarUserService, GrammarUserRepository grammarUserRepository) {
        this.grammarUserService = grammarUserService;
        this.grammarUserRepository = grammarUserRepository;
    }

    /**
     * {@code POST  /grammar-users} : Create a new grammarUser.
     *
     * @param grammarUserDTO the grammarUserDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new grammarUserDTO, or with status {@code 400 (Bad Request)} if the grammarUser has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/grammar-users")
    public ResponseEntity<GrammarUserDTO> createGrammarUser(@Valid @RequestBody GrammarUserDTO grammarUserDTO) throws URISyntaxException {
        log.debug("REST request to save GrammarUser : {}", grammarUserDTO);
        if (grammarUserDTO.getId() != null) {
            throw new BadRequestAlertException("A new grammarUser cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GrammarUserDTO result = grammarUserService.save(grammarUserDTO);
        return ResponseEntity
            .created(new URI("/api/grammar-users/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /grammar-users/:id} : Updates an existing grammarUser.
     *
     * @param id the id of the grammarUserDTO to save.
     * @param grammarUserDTO the grammarUserDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated grammarUserDTO,
     * or with status {@code 400 (Bad Request)} if the grammarUserDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the grammarUserDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/grammar-users/{id}")
    public ResponseEntity<GrammarUserDTO> updateGrammarUser(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody GrammarUserDTO grammarUserDTO
    ) throws URISyntaxException {
        log.debug("REST request to update GrammarUser : {}, {}", id, grammarUserDTO);
        if (grammarUserDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, grammarUserDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!grammarUserRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        GrammarUserDTO result = grammarUserService.save(grammarUserDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, grammarUserDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /grammar-users/:id} : Partial updates given fields of an existing grammarUser, field will ignore if it is null
     *
     * @param id the id of the grammarUserDTO to save.
     * @param grammarUserDTO the grammarUserDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated grammarUserDTO,
     * or with status {@code 400 (Bad Request)} if the grammarUserDTO is not valid,
     * or with status {@code 404 (Not Found)} if the grammarUserDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the grammarUserDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/grammar-users/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<GrammarUserDTO> partialUpdateGrammarUser(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody GrammarUserDTO grammarUserDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update GrammarUser partially : {}, {}", id, grammarUserDTO);
        if (grammarUserDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, grammarUserDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!grammarUserRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<GrammarUserDTO> result = grammarUserService.partialUpdate(grammarUserDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, grammarUserDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /grammar-users} : get all the grammarUsers.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of grammarUsers in body.
     */
    @GetMapping("/grammar-users")
    public ResponseEntity<List<GrammarUserDTO>> getAllGrammarUsers(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of GrammarUsers");
        Page<GrammarUserDTO> page = grammarUserService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /grammar-users/:id} : get the "id" grammarUser.
     *
     * @param id the id of the grammarUserDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the grammarUserDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/grammar-users/{id}")
    public ResponseEntity<GrammarUserDTO> getGrammarUser(@PathVariable Long id) {
        log.debug("REST request to get GrammarUser : {}", id);
        Optional<GrammarUserDTO> grammarUserDTO = grammarUserService.findOne(id);
        return ResponseUtil.wrapOrNotFound(grammarUserDTO);
    }

    /**
     * {@code DELETE  /grammar-users/:id} : delete the "id" grammarUser.
     *
     * @param id the id of the grammarUserDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/grammar-users/{id}")
    public ResponseEntity<Void> deleteGrammarUser(@PathVariable Long id) {
        log.debug("REST request to delete GrammarUser : {}", id);
        grammarUserService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
