package com.sangtt.toeic_test.service.impl;

import com.sangtt.toeic_test.domain.QuestionPart;
import com.sangtt.toeic_test.repository.QuestionPartRepository;
import com.sangtt.toeic_test.service.QuestionPartService;
import com.sangtt.toeic_test.service.dto.QuestionPartDTO;
import com.sangtt.toeic_test.service.mapper.QuestionPartMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link QuestionPart}.
 */
@Service
@Transactional
public class QuestionPartServiceImpl implements QuestionPartService {

    private final Logger log = LoggerFactory.getLogger(QuestionPartServiceImpl.class);

    private final QuestionPartRepository questionPartRepository;

    private final QuestionPartMapper questionPartMapper;

    public QuestionPartServiceImpl(QuestionPartRepository questionPartRepository, QuestionPartMapper questionPartMapper) {
        this.questionPartRepository = questionPartRepository;
        this.questionPartMapper = questionPartMapper;
    }

    @Override
    public QuestionPartDTO save(QuestionPartDTO questionPartDTO) {
        log.debug("Request to save QuestionPart : {}", questionPartDTO);
        QuestionPart questionPart = questionPartMapper.toEntity(questionPartDTO);
        questionPart = questionPartRepository.save(questionPart);
        return questionPartMapper.toDto(questionPart);
    }

    @Override
    public Optional<QuestionPartDTO> partialUpdate(QuestionPartDTO questionPartDTO) {
        log.debug("Request to partially update QuestionPart : {}", questionPartDTO);

        return questionPartRepository
            .findById(questionPartDTO.getId())
            .map(existingQuestionPart -> {
                questionPartMapper.partialUpdate(existingQuestionPart, questionPartDTO);

                return existingQuestionPart;
            })
            .map(questionPartRepository::save)
            .map(questionPartMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<QuestionPartDTO> findAll(Pageable pageable) {
        log.debug("Request to get all QuestionParts");
        return questionPartRepository.findAll(pageable).map(questionPartMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<QuestionPartDTO> findOne(Long id) {
        log.debug("Request to get QuestionPart : {}", id);
        return questionPartRepository.findById(id).map(questionPartMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete QuestionPart : {}", id);
        questionPartRepository.deleteById(id);
    }
}
