package com.sangtt.toeic_test.service.impl;

import com.sangtt.toeic_test.domain.QnA;
import com.sangtt.toeic_test.repository.QnARepository;
import com.sangtt.toeic_test.service.QnAService;
import com.sangtt.toeic_test.service.dto.QnADTO;
import com.sangtt.toeic_test.service.mapper.QnAMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link QnA}.
 */
@Service
@Transactional
public class QnAServiceImpl implements QnAService {

    private final Logger log = LoggerFactory.getLogger(QnAServiceImpl.class);

    private final QnARepository qnARepository;

    private final QnAMapper qnAMapper;

    public QnAServiceImpl(QnARepository qnARepository, QnAMapper qnAMapper) {
        this.qnARepository = qnARepository;
        this.qnAMapper = qnAMapper;
    }

    @Override
    public QnADTO save(QnADTO qnADTO) {
        log.debug("Request to save QnA : {}", qnADTO);
        QnA qnA = qnAMapper.toEntity(qnADTO);
        qnA = qnARepository.save(qnA);
        return qnAMapper.toDto(qnA);
    }

    @Override
    public Optional<QnADTO> partialUpdate(QnADTO qnADTO) {
        log.debug("Request to partially update QnA : {}", qnADTO);

        return qnARepository
            .findById(qnADTO.getId())
            .map(existingQnA -> {
                qnAMapper.partialUpdate(existingQnA, qnADTO);

                return existingQnA;
            })
            .map(qnARepository::save)
            .map(qnAMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<QnADTO> findAll(Pageable pageable) {
        log.debug("Request to get all QnAS");
        return qnARepository.findAll(pageable).map(qnAMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<QnADTO> findOne(Long id) {
        log.debug("Request to get QnA : {}", id);
        return qnARepository.findById(id).map(qnAMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete QnA : {}", id);
        qnARepository.deleteById(id);
    }
}
