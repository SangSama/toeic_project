package com.sangtt.toeic_test.service.impl;

import com.sangtt.toeic_test.domain.Parts;
import com.sangtt.toeic_test.repository.PartsRepository;
import com.sangtt.toeic_test.service.PartsService;
import com.sangtt.toeic_test.service.dto.PartsDTO;
import com.sangtt.toeic_test.service.mapper.PartsMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Parts}.
 */
@Service
@Transactional
public class PartsServiceImpl implements PartsService {

    private final Logger log = LoggerFactory.getLogger(PartsServiceImpl.class);

    private final PartsRepository partsRepository;

    private final PartsMapper partsMapper;

    public PartsServiceImpl(PartsRepository partsRepository, PartsMapper partsMapper) {
        this.partsRepository = partsRepository;
        this.partsMapper = partsMapper;
    }

    @Override
    public PartsDTO save(PartsDTO partsDTO) {
        log.debug("Request to save Parts : {}", partsDTO);
        Parts parts = partsMapper.toEntity(partsDTO);
        parts = partsRepository.save(parts);
        return partsMapper.toDto(parts);
    }

    @Override
    public Optional<PartsDTO> partialUpdate(PartsDTO partsDTO) {
        log.debug("Request to partially update Parts : {}", partsDTO);

        return partsRepository
            .findById(partsDTO.getId())
            .map(existingParts -> {
                partsMapper.partialUpdate(existingParts, partsDTO);

                return existingParts;
            })
            .map(partsRepository::save)
            .map(partsMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PartsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Parts");
        return partsRepository.findAll(pageable).map(partsMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PartsDTO> findOne(Long id) {
        log.debug("Request to get Parts : {}", id);
        return partsRepository.findById(id).map(partsMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Parts : {}", id);
        partsRepository.deleteById(id);
    }
}
