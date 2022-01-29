package com.sangtt.toeic_test.service.impl;

import com.sangtt.toeic_test.domain.VocabularyUser;
import com.sangtt.toeic_test.repository.VocabularyUserRepository;
import com.sangtt.toeic_test.service.VocabularyUserService;
import com.sangtt.toeic_test.service.dto.VocabularyUserDTO;
import com.sangtt.toeic_test.service.mapper.VocabularyUserMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link VocabularyUser}.
 */
@Service
@Transactional
public class VocabularyUserServiceImpl implements VocabularyUserService {

    private final Logger log = LoggerFactory.getLogger(VocabularyUserServiceImpl.class);

    private final VocabularyUserRepository vocabularyUserRepository;

    private final VocabularyUserMapper vocabularyUserMapper;

    public VocabularyUserServiceImpl(VocabularyUserRepository vocabularyUserRepository, VocabularyUserMapper vocabularyUserMapper) {
        this.vocabularyUserRepository = vocabularyUserRepository;
        this.vocabularyUserMapper = vocabularyUserMapper;
    }

    @Override
    public VocabularyUserDTO save(VocabularyUserDTO vocabularyUserDTO) {
        log.debug("Request to save VocabularyUser : {}", vocabularyUserDTO);
        VocabularyUser vocabularyUser = vocabularyUserMapper.toEntity(vocabularyUserDTO);
        vocabularyUser = vocabularyUserRepository.save(vocabularyUser);
        return vocabularyUserMapper.toDto(vocabularyUser);
    }

    @Override
    public Optional<VocabularyUserDTO> partialUpdate(VocabularyUserDTO vocabularyUserDTO) {
        log.debug("Request to partially update VocabularyUser : {}", vocabularyUserDTO);

        return vocabularyUserRepository
            .findById(vocabularyUserDTO.getId())
            .map(existingVocabularyUser -> {
                vocabularyUserMapper.partialUpdate(existingVocabularyUser, vocabularyUserDTO);

                return existingVocabularyUser;
            })
            .map(vocabularyUserRepository::save)
            .map(vocabularyUserMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<VocabularyUserDTO> findAll(Pageable pageable) {
        log.debug("Request to get all VocabularyUsers");
        return vocabularyUserRepository.findAll(pageable).map(vocabularyUserMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<VocabularyUserDTO> findOne(Long id) {
        log.debug("Request to get VocabularyUser : {}", id);
        return vocabularyUserRepository.findById(id).map(vocabularyUserMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete VocabularyUser : {}", id);
        vocabularyUserRepository.deleteById(id);
    }
}
