package com.sangtt.toeic_test.service.impl;

import com.sangtt.toeic_test.domain.AnswerQuestion;
import com.sangtt.toeic_test.repository.AnswerQuestionRepository;
import com.sangtt.toeic_test.service.AnswerQuestionService;
import com.sangtt.toeic_test.service.dto.AnswerQuestionDTO;
import com.sangtt.toeic_test.service.mapper.AnswerQuestionMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link AnswerQuestion}.
 */
@Service
@Transactional
public class AnswerQuestionServiceImpl implements AnswerQuestionService {

    private final Logger log = LoggerFactory.getLogger(AnswerQuestionServiceImpl.class);

    private final AnswerQuestionRepository answerQuestionRepository;

    private final AnswerQuestionMapper answerQuestionMapper;

    public AnswerQuestionServiceImpl(AnswerQuestionRepository answerQuestionRepository, AnswerQuestionMapper answerQuestionMapper) {
        this.answerQuestionRepository = answerQuestionRepository;
        this.answerQuestionMapper = answerQuestionMapper;
    }

    @Override
    public AnswerQuestionDTO save(AnswerQuestionDTO answerQuestionDTO) {
        log.debug("Request to save AnswerQuestion : {}", answerQuestionDTO);
        AnswerQuestion answerQuestion = answerQuestionMapper.toEntity(answerQuestionDTO);
        answerQuestion = answerQuestionRepository.save(answerQuestion);
        return answerQuestionMapper.toDto(answerQuestion);
    }

    @Override
    public Optional<AnswerQuestionDTO> partialUpdate(AnswerQuestionDTO answerQuestionDTO) {
        log.debug("Request to partially update AnswerQuestion : {}", answerQuestionDTO);

        return answerQuestionRepository
            .findById(answerQuestionDTO.getId())
            .map(existingAnswerQuestion -> {
                answerQuestionMapper.partialUpdate(existingAnswerQuestion, answerQuestionDTO);

                return existingAnswerQuestion;
            })
            .map(answerQuestionRepository::save)
            .map(answerQuestionMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AnswerQuestionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AnswerQuestions");
        return answerQuestionRepository.findAll(pageable).map(answerQuestionMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AnswerQuestionDTO> findOne(Long id) {
        log.debug("Request to get AnswerQuestion : {}", id);
        return answerQuestionRepository.findById(id).map(answerQuestionMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete AnswerQuestion : {}", id);
        answerQuestionRepository.deleteById(id);
    }
}
