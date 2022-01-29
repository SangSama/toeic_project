package com.sangtt.toeic_test.service.impl;

import com.sangtt.toeic_test.domain.GrammarTopic;
import com.sangtt.toeic_test.repository.GrammarTopicRepository;
import com.sangtt.toeic_test.service.GrammarTopicService;
import com.sangtt.toeic_test.service.dto.GrammarTopicDTO;
import com.sangtt.toeic_test.service.mapper.GrammarTopicMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link GrammarTopic}.
 */
@Service
@Transactional
public class GrammarTopicServiceImpl implements GrammarTopicService {

    private final Logger log = LoggerFactory.getLogger(GrammarTopicServiceImpl.class);

    private final GrammarTopicRepository grammarTopicRepository;

    private final GrammarTopicMapper grammarTopicMapper;

    public GrammarTopicServiceImpl(GrammarTopicRepository grammarTopicRepository, GrammarTopicMapper grammarTopicMapper) {
        this.grammarTopicRepository = grammarTopicRepository;
        this.grammarTopicMapper = grammarTopicMapper;
    }

    @Override
    public GrammarTopicDTO save(GrammarTopicDTO grammarTopicDTO) {
        log.debug("Request to save GrammarTopic : {}", grammarTopicDTO);
        GrammarTopic grammarTopic = grammarTopicMapper.toEntity(grammarTopicDTO);
        grammarTopic = grammarTopicRepository.save(grammarTopic);
        return grammarTopicMapper.toDto(grammarTopic);
    }

    @Override
    public Optional<GrammarTopicDTO> partialUpdate(GrammarTopicDTO grammarTopicDTO) {
        log.debug("Request to partially update GrammarTopic : {}", grammarTopicDTO);

        return grammarTopicRepository
            .findById(grammarTopicDTO.getId())
            .map(existingGrammarTopic -> {
                grammarTopicMapper.partialUpdate(existingGrammarTopic, grammarTopicDTO);

                return existingGrammarTopic;
            })
            .map(grammarTopicRepository::save)
            .map(grammarTopicMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<GrammarTopicDTO> findAll(Pageable pageable) {
        log.debug("Request to get all GrammarTopics");
        return grammarTopicRepository.findAll(pageable).map(grammarTopicMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<GrammarTopicDTO> findOne(Long id) {
        log.debug("Request to get GrammarTopic : {}", id);
        return grammarTopicRepository.findById(id).map(grammarTopicMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete GrammarTopic : {}", id);
        grammarTopicRepository.deleteById(id);
    }
}
