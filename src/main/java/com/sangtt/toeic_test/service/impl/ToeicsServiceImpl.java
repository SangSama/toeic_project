package com.sangtt.toeic_test.service.impl;

import com.sangtt.toeic_test.domain.Toeics;
import com.sangtt.toeic_test.repository.ToeicsRepository;
import com.sangtt.toeic_test.service.ToeicsService;
import com.sangtt.toeic_test.service.dto.ToeicsDTO;
import com.sangtt.toeic_test.service.mapper.ToeicsMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Toeics}.
 */
@Service
@Transactional
public class ToeicsServiceImpl implements ToeicsService {

    private final Logger log = LoggerFactory.getLogger(ToeicsServiceImpl.class);

    private final ToeicsRepository toeicsRepository;

    private final ToeicsMapper toeicsMapper;

    public ToeicsServiceImpl(ToeicsRepository toeicsRepository, ToeicsMapper toeicsMapper) {
        this.toeicsRepository = toeicsRepository;
        this.toeicsMapper = toeicsMapper;
    }

    @Override
    public ToeicsDTO save(ToeicsDTO toeicsDTO) {
        log.debug("Request to save Toeics : {}", toeicsDTO);
        Toeics toeics = toeicsMapper.toEntity(toeicsDTO);
        toeics = toeicsRepository.save(toeics);
        return toeicsMapper.toDto(toeics);
    }

    @Override
    public Optional<ToeicsDTO> partialUpdate(ToeicsDTO toeicsDTO) {
        log.debug("Request to partially update Toeics : {}", toeicsDTO);

        return toeicsRepository
            .findById(toeicsDTO.getId())
            .map(existingToeics -> {
                toeicsMapper.partialUpdate(existingToeics, toeicsDTO);

                return existingToeics;
            })
            .map(toeicsRepository::save)
            .map(toeicsMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ToeicsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Toeics");
        return toeicsRepository.findAll(pageable).map(toeicsMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ToeicsDTO> findOne(Long id) {
        log.debug("Request to get Toeics : {}", id);
        return toeicsRepository.findById(id).map(toeicsMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Toeics : {}", id);
        toeicsRepository.deleteById(id);
    }
}
