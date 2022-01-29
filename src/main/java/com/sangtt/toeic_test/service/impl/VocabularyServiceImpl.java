package com.sangtt.toeic_test.service.impl;

import com.sangtt.toeic_test.domain.Vocabulary;
import com.sangtt.toeic_test.repository.VocabularyRepository;
import com.sangtt.toeic_test.service.VocabularyService;
import com.sangtt.toeic_test.service.dto.VocabularyDTO;
import com.sangtt.toeic_test.service.mapper.VocabularyMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Vocabulary}.
 */
@Service
@Transactional
public class VocabularyServiceImpl implements VocabularyService {

    private final Logger log = LoggerFactory.getLogger(VocabularyServiceImpl.class);

    private final VocabularyRepository vocabularyRepository;

    private final VocabularyMapper vocabularyMapper;

    public VocabularyServiceImpl(VocabularyRepository vocabularyRepository, VocabularyMapper vocabularyMapper) {
        this.vocabularyRepository = vocabularyRepository;
        this.vocabularyMapper = vocabularyMapper;
    }

    @Override
    public VocabularyDTO save(VocabularyDTO vocabularyDTO) {
        log.debug("Request to save Vocabulary : {}", vocabularyDTO);
        Vocabulary vocabulary = vocabularyMapper.toEntity(vocabularyDTO);
        vocabulary = vocabularyRepository.save(vocabulary);
        return vocabularyMapper.toDto(vocabulary);
    }

    @Override
    public Optional<VocabularyDTO> partialUpdate(VocabularyDTO vocabularyDTO) {
        log.debug("Request to partially update Vocabulary : {}", vocabularyDTO);

        return vocabularyRepository
            .findById(vocabularyDTO.getId())
            .map(existingVocabulary -> {
                vocabularyMapper.partialUpdate(existingVocabulary, vocabularyDTO);

                return existingVocabulary;
            })
            .map(vocabularyRepository::save)
            .map(vocabularyMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<VocabularyDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Vocabularies");
        return vocabularyRepository.findAll(pageable).map(vocabularyMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<VocabularyDTO> findOne(Long id) {
        log.debug("Request to get Vocabulary : {}", id);
        return vocabularyRepository.findById(id).map(vocabularyMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Vocabulary : {}", id);
        vocabularyRepository.deleteById(id);
    }
}
