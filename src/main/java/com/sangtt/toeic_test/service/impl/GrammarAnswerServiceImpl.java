package com.sangtt.toeic_test.service.impl;

import com.sangtt.toeic_test.domain.GrammarAnswer;
import com.sangtt.toeic_test.repository.GrammarAnswerRepository;
import com.sangtt.toeic_test.service.GrammarAnswerService;
import com.sangtt.toeic_test.service.dto.GrammarAnswerDTO;
import com.sangtt.toeic_test.service.mapper.GrammarAnswerMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link GrammarAnswer}.
 */
@Service
@Transactional
public class GrammarAnswerServiceImpl implements GrammarAnswerService {

    private final Logger log = LoggerFactory.getLogger(GrammarAnswerServiceImpl.class);

    private final GrammarAnswerRepository grammarAnswerRepository;

    private final GrammarAnswerMapper grammarAnswerMapper;

    public GrammarAnswerServiceImpl(GrammarAnswerRepository grammarAnswerRepository, GrammarAnswerMapper grammarAnswerMapper) {
        this.grammarAnswerRepository = grammarAnswerRepository;
        this.grammarAnswerMapper = grammarAnswerMapper;
    }

    @Override
    public GrammarAnswerDTO save(GrammarAnswerDTO grammarAnswerDTO) {
        log.debug("Request to save GrammarAnswer : {}", grammarAnswerDTO);
        GrammarAnswer grammarAnswer = grammarAnswerMapper.toEntity(grammarAnswerDTO);
        grammarAnswer = grammarAnswerRepository.save(grammarAnswer);
        return grammarAnswerMapper.toDto(grammarAnswer);
    }

    @Override
    public Optional<GrammarAnswerDTO> partialUpdate(GrammarAnswerDTO grammarAnswerDTO) {
        log.debug("Request to partially update GrammarAnswer : {}", grammarAnswerDTO);

        return grammarAnswerRepository
            .findById(grammarAnswerDTO.getId())
            .map(existingGrammarAnswer -> {
                grammarAnswerMapper.partialUpdate(existingGrammarAnswer, grammarAnswerDTO);

                return existingGrammarAnswer;
            })
            .map(grammarAnswerRepository::save)
            .map(grammarAnswerMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<GrammarAnswerDTO> findAll(Pageable pageable) {
        log.debug("Request to get all GrammarAnswers");
        return grammarAnswerRepository.findAll(pageable).map(grammarAnswerMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<GrammarAnswerDTO> findOne(Long id) {
        log.debug("Request to get GrammarAnswer : {}", id);
        return grammarAnswerRepository.findById(id).map(grammarAnswerMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete GrammarAnswer : {}", id);
        grammarAnswerRepository.deleteById(id);
    }
}
