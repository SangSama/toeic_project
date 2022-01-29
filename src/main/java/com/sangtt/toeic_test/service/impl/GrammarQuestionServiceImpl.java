package com.sangtt.toeic_test.service.impl;

import com.sangtt.toeic_test.domain.GrammarQuestion;
import com.sangtt.toeic_test.repository.GrammarQuestionRepository;
import com.sangtt.toeic_test.service.GrammarQuestionService;
import com.sangtt.toeic_test.service.dto.GrammarQuestionDTO;
import com.sangtt.toeic_test.service.mapper.GrammarQuestionMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link GrammarQuestion}.
 */
@Service
@Transactional
public class GrammarQuestionServiceImpl implements GrammarQuestionService {

    private final Logger log = LoggerFactory.getLogger(GrammarQuestionServiceImpl.class);

    private final GrammarQuestionRepository grammarQuestionRepository;

    private final GrammarQuestionMapper grammarQuestionMapper;

    public GrammarQuestionServiceImpl(GrammarQuestionRepository grammarQuestionRepository, GrammarQuestionMapper grammarQuestionMapper) {
        this.grammarQuestionRepository = grammarQuestionRepository;
        this.grammarQuestionMapper = grammarQuestionMapper;
    }

    @Override
    public GrammarQuestionDTO save(GrammarQuestionDTO grammarQuestionDTO) {
        log.debug("Request to save GrammarQuestion : {}", grammarQuestionDTO);
        GrammarQuestion grammarQuestion = grammarQuestionMapper.toEntity(grammarQuestionDTO);
        grammarQuestion = grammarQuestionRepository.save(grammarQuestion);
        return grammarQuestionMapper.toDto(grammarQuestion);
    }

    @Override
    public Optional<GrammarQuestionDTO> partialUpdate(GrammarQuestionDTO grammarQuestionDTO) {
        log.debug("Request to partially update GrammarQuestion : {}", grammarQuestionDTO);

        return grammarQuestionRepository
            .findById(grammarQuestionDTO.getId())
            .map(existingGrammarQuestion -> {
                grammarQuestionMapper.partialUpdate(existingGrammarQuestion, grammarQuestionDTO);

                return existingGrammarQuestion;
            })
            .map(grammarQuestionRepository::save)
            .map(grammarQuestionMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<GrammarQuestionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all GrammarQuestions");
        return grammarQuestionRepository.findAll(pageable).map(grammarQuestionMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<GrammarQuestionDTO> findOne(Long id) {
        log.debug("Request to get GrammarQuestion : {}", id);
        return grammarQuestionRepository.findById(id).map(grammarQuestionMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete GrammarQuestion : {}", id);
        grammarQuestionRepository.deleteById(id);
    }
}
