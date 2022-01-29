package com.sangtt.toeic_test.web.rest;

import com.sangtt.toeic_test.repository.QnARepository;
import com.sangtt.toeic_test.service.QnAService;
import com.sangtt.toeic_test.service.dto.QnADTO;
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
 * REST controller for managing {@link com.sangtt.toeic_test.domain.QnA}.
 */
@RestController
@RequestMapping("/api")
public class QnAResource {

    private final Logger log = LoggerFactory.getLogger(QnAResource.class);

    private static final String ENTITY_NAME = "qnA";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final QnAService qnAService;

    private final QnARepository qnARepository;

    public QnAResource(QnAService qnAService, QnARepository qnARepository) {
        this.qnAService = qnAService;
        this.qnARepository = qnARepository;
    }

    /**
     * {@code POST  /qn-as} : Create a new qnA.
     *
     * @param qnADTO the qnADTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new qnADTO, or with status {@code 400 (Bad Request)} if the qnA has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/qn-as")
    public ResponseEntity<QnADTO> createQnA(@Valid @RequestBody QnADTO qnADTO) throws URISyntaxException {
        log.debug("REST request to save QnA : {}", qnADTO);
        if (qnADTO.getId() != null) {
            throw new BadRequestAlertException("A new qnA cannot already have an ID", ENTITY_NAME, "idexists");
        }
        QnADTO result = qnAService.save(qnADTO);
        return ResponseEntity
            .created(new URI("/api/qn-as/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /qn-as/:id} : Updates an existing qnA.
     *
     * @param id the id of the qnADTO to save.
     * @param qnADTO the qnADTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated qnADTO,
     * or with status {@code 400 (Bad Request)} if the qnADTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the qnADTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/qn-as/{id}")
    public ResponseEntity<QnADTO> updateQnA(@PathVariable(value = "id", required = false) final Long id, @Valid @RequestBody QnADTO qnADTO)
        throws URISyntaxException {
        log.debug("REST request to update QnA : {}, {}", id, qnADTO);
        if (qnADTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, qnADTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!qnARepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        QnADTO result = qnAService.save(qnADTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, qnADTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /qn-as/:id} : Partial updates given fields of an existing qnA, field will ignore if it is null
     *
     * @param id the id of the qnADTO to save.
     * @param qnADTO the qnADTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated qnADTO,
     * or with status {@code 400 (Bad Request)} if the qnADTO is not valid,
     * or with status {@code 404 (Not Found)} if the qnADTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the qnADTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/qn-as/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<QnADTO> partialUpdateQnA(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody QnADTO qnADTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update QnA partially : {}, {}", id, qnADTO);
        if (qnADTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, qnADTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!qnARepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<QnADTO> result = qnAService.partialUpdate(qnADTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, qnADTO.getId().toString())
        );
    }

    /**
     * {@code GET  /qn-as} : get all the qnAS.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of qnAS in body.
     */
    @GetMapping("/qn-as")
    public ResponseEntity<List<QnADTO>> getAllQnAS(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of QnAS");
        Page<QnADTO> page = qnAService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /qn-as/:id} : get the "id" qnA.
     *
     * @param id the id of the qnADTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the qnADTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/qn-as/{id}")
    public ResponseEntity<QnADTO> getQnA(@PathVariable Long id) {
        log.debug("REST request to get QnA : {}", id);
        Optional<QnADTO> qnADTO = qnAService.findOne(id);
        return ResponseUtil.wrapOrNotFound(qnADTO);
    }

    /**
     * {@code DELETE  /qn-as/:id} : delete the "id" qnA.
     *
     * @param id the id of the qnADTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/qn-as/{id}")
    public ResponseEntity<Void> deleteQnA(@PathVariable Long id) {
        log.debug("REST request to delete QnA : {}", id);
        qnAService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
