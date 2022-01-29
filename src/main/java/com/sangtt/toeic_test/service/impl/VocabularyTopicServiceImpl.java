package com.sangtt.toeic_test.service.impl;

import com.sangtt.toeic_test.domain.VocabularyTopic;
import com.sangtt.toeic_test.repository.VocabularyTopicRepository;
import com.sangtt.toeic_test.service.VocabularyTopicService;
import com.sangtt.toeic_test.service.dto.VocabularyTopicDTO;
import com.sangtt.toeic_test.service.mapper.VocabularyTopicMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link VocabularyTopic}.
 */
@Service
@Transactional
public class VocabularyTopicServiceImpl implements VocabularyTopicService {

    private final Logger log = LoggerFactory.getLogger(VocabularyTopicServiceImpl.class);

    private final VocabularyTopicRepository vocabularyTopicRepository;

    private final VocabularyTopicMapper vocabularyTopicMapper;

    public VocabularyTopicServiceImpl(VocabularyTopicRepository vocabularyTopicRepository, VocabularyTopicMapper vocabularyTopicMapper) {
        this.vocabularyTopicRepository = vocabularyTopicRepository;
        this.vocabularyTopicMapper = vocabularyTopicMapper;
    }

    @Override
    public VocabularyTopicDTO save(VocabularyTopicDTO vocabularyTopicDTO) {
        log.debug("Request to save VocabularyTopic : {}", vocabularyTopicDTO);
        VocabularyTopic vocabularyTopic = vocabularyTopicMapper.toEntity(vocabularyTopicDTO);
        vocabularyTopic = vocabularyTopicRepository.save(vocabularyTopic);
        return vocabularyTopicMapper.toDto(vocabularyTopic);
    }

    @Override
    public Optional<VocabularyTopicDTO> partialUpdate(VocabularyTopicDTO vocabularyTopicDTO) {
        log.debug("Request to partially update VocabularyTopic : {}", vocabularyTopicDTO);

        return vocabularyTopicRepository
            .findById(vocabularyTopicDTO.getId())
            .map(existingVocabularyTopic -> {
                vocabularyTopicMapper.partialUpdate(existingVocabularyTopic, vocabularyTopicDTO);

                return existingVocabularyTopic;
            })
            .map(vocabularyTopicRepository::save)
            .map(vocabularyTopicMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<VocabularyTopicDTO> findAll(Pageable pageable) {
        log.debug("Request to get all VocabularyTopics");
        return vocabularyTopicRepository.findAll(pageable).map(vocabularyTopicMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<VocabularyTopicDTO> findOne(Long id) {
        log.debug("Request to get VocabularyTopic : {}", id);
        return vocabularyTopicRepository.findById(id).map(vocabularyTopicMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete VocabularyTopic : {}", id);
        vocabularyTopicRepository.deleteById(id);
    }
}
