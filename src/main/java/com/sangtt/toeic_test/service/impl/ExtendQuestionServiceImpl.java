package com.sangtt.toeic_test.service.impl;

import com.sangtt.toeic_test.domain.ExtendQuestion;
import com.sangtt.toeic_test.repository.ExtendQuestionRepository;
import com.sangtt.toeic_test.service.ExtendQuestionService;
import com.sangtt.toeic_test.service.dto.ExtendQuestionDTO;
import com.sangtt.toeic_test.service.mapper.ExtendQuestionMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ExtendQuestion}.
 */
@Service
@Transactional
public class ExtendQuestionServiceImpl implements ExtendQuestionService {

    private final Logger log = LoggerFactory.getLogger(ExtendQuestionServiceImpl.class);

    private final ExtendQuestionRepository extendQuestionRepository;

    private final ExtendQuestionMapper extendQuestionMapper;

    public ExtendQuestionServiceImpl(ExtendQuestionRepository extendQuestionRepository, ExtendQuestionMapper extendQuestionMapper) {
        this.extendQuestionRepository = extendQuestionRepository;
        this.extendQuestionMapper = extendQuestionMapper;
    }

    @Override
    public ExtendQuestionDTO save(ExtendQuestionDTO extendQuestionDTO) {
        log.debug("Request to save ExtendQuestion : {}", extendQuestionDTO);
        ExtendQuestion extendQuestion = extendQuestionMapper.toEntity(extendQuestionDTO);
        extendQuestion = extendQuestionRepository.save(extendQuestion);
        return extendQuestionMapper.toDto(extendQuestion);
    }

    @Override
    public Optional<ExtendQuestionDTO> partialUpdate(ExtendQuestionDTO extendQuestionDTO) {
        log.debug("Request to partially update ExtendQuestion : {}", extendQuestionDTO);

        return extendQuestionRepository
            .findById(extendQuestionDTO.getId())
            .map(existingExtendQuestion -> {
                extendQuestionMapper.partialUpdate(existingExtendQuestion, extendQuestionDTO);

                return existingExtendQuestion;
            })
            .map(extendQuestionRepository::save)
            .map(extendQuestionMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ExtendQuestionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ExtendQuestions");
        return extendQuestionRepository.findAll(pageable).map(extendQuestionMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ExtendQuestionDTO> findOne(Long id) {
        log.debug("Request to get ExtendQuestion : {}", id);
        return extendQuestionRepository.findById(id).map(extendQuestionMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ExtendQuestion : {}", id);
        extendQuestionRepository.deleteById(id);
    }
}
