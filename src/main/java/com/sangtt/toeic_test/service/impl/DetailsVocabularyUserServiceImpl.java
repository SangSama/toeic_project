package com.sangtt.toeic_test.service.impl;

import com.sangtt.toeic_test.domain.DetailsVocabularyUser;
import com.sangtt.toeic_test.repository.DetailsVocabularyUserRepository;
import com.sangtt.toeic_test.service.DetailsVocabularyUserService;
import com.sangtt.toeic_test.service.dto.DetailsVocabularyUserDTO;
import com.sangtt.toeic_test.service.mapper.DetailsVocabularyUserMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link DetailsVocabularyUser}.
 */
@Service
@Transactional
public class DetailsVocabularyUserServiceImpl implements DetailsVocabularyUserService {

    private final Logger log = LoggerFactory.getLogger(DetailsVocabularyUserServiceImpl.class);

    private final DetailsVocabularyUserRepository detailsVocabularyUserRepository;

    private final DetailsVocabularyUserMapper detailsVocabularyUserMapper;

    public DetailsVocabularyUserServiceImpl(
        DetailsVocabularyUserRepository detailsVocabularyUserRepository,
        DetailsVocabularyUserMapper detailsVocabularyUserMapper
    ) {
        this.detailsVocabularyUserRepository = detailsVocabularyUserRepository;
        this.detailsVocabularyUserMapper = detailsVocabularyUserMapper;
    }

    @Override
    public DetailsVocabularyUserDTO save(DetailsVocabularyUserDTO detailsVocabularyUserDTO) {
        log.debug("Request to save DetailsVocabularyUser : {}", detailsVocabularyUserDTO);
        DetailsVocabularyUser detailsVocabularyUser = detailsVocabularyUserMapper.toEntity(detailsVocabularyUserDTO);
        detailsVocabularyUser = detailsVocabularyUserRepository.save(detailsVocabularyUser);
        return detailsVocabularyUserMapper.toDto(detailsVocabularyUser);
    }

    @Override
    public Optional<DetailsVocabularyUserDTO> partialUpdate(DetailsVocabularyUserDTO detailsVocabularyUserDTO) {
        log.debug("Request to partially update DetailsVocabularyUser : {}", detailsVocabularyUserDTO);

        return detailsVocabularyUserRepository
            .findById(detailsVocabularyUserDTO.getId())
            .map(existingDetailsVocabularyUser -> {
                detailsVocabularyUserMapper.partialUpdate(existingDetailsVocabularyUser, detailsVocabularyUserDTO);

                return existingDetailsVocabularyUser;
            })
            .map(detailsVocabularyUserRepository::save)
            .map(detailsVocabularyUserMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DetailsVocabularyUserDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DetailsVocabularyUsers");
        return detailsVocabularyUserRepository.findAll(pageable).map(detailsVocabularyUserMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DetailsVocabularyUserDTO> findOne(Long id) {
        log.debug("Request to get DetailsVocabularyUser : {}", id);
        return detailsVocabularyUserRepository.findById(id).map(detailsVocabularyUserMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete DetailsVocabularyUser : {}", id);
        detailsVocabularyUserRepository.deleteById(id);
    }
}
