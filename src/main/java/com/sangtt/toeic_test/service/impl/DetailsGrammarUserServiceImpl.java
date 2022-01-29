package com.sangtt.toeic_test.service.impl;

import com.sangtt.toeic_test.domain.DetailsGrammarUser;
import com.sangtt.toeic_test.repository.DetailsGrammarUserRepository;
import com.sangtt.toeic_test.service.DetailsGrammarUserService;
import com.sangtt.toeic_test.service.dto.DetailsGrammarUserDTO;
import com.sangtt.toeic_test.service.mapper.DetailsGrammarUserMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link DetailsGrammarUser}.
 */
@Service
@Transactional
public class DetailsGrammarUserServiceImpl implements DetailsGrammarUserService {

    private final Logger log = LoggerFactory.getLogger(DetailsGrammarUserServiceImpl.class);

    private final DetailsGrammarUserRepository detailsGrammarUserRepository;

    private final DetailsGrammarUserMapper detailsGrammarUserMapper;

    public DetailsGrammarUserServiceImpl(
        DetailsGrammarUserRepository detailsGrammarUserRepository,
        DetailsGrammarUserMapper detailsGrammarUserMapper
    ) {
        this.detailsGrammarUserRepository = detailsGrammarUserRepository;
        this.detailsGrammarUserMapper = detailsGrammarUserMapper;
    }

    @Override
    public DetailsGrammarUserDTO save(DetailsGrammarUserDTO detailsGrammarUserDTO) {
        log.debug("Request to save DetailsGrammarUser : {}", detailsGrammarUserDTO);
        DetailsGrammarUser detailsGrammarUser = detailsGrammarUserMapper.toEntity(detailsGrammarUserDTO);
        detailsGrammarUser = detailsGrammarUserRepository.save(detailsGrammarUser);
        return detailsGrammarUserMapper.toDto(detailsGrammarUser);
    }

    @Override
    public Optional<DetailsGrammarUserDTO> partialUpdate(DetailsGrammarUserDTO detailsGrammarUserDTO) {
        log.debug("Request to partially update DetailsGrammarUser : {}", detailsGrammarUserDTO);

        return detailsGrammarUserRepository
            .findById(detailsGrammarUserDTO.getId())
            .map(existingDetailsGrammarUser -> {
                detailsGrammarUserMapper.partialUpdate(existingDetailsGrammarUser, detailsGrammarUserDTO);

                return existingDetailsGrammarUser;
            })
            .map(detailsGrammarUserRepository::save)
            .map(detailsGrammarUserMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DetailsGrammarUserDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DetailsGrammarUsers");
        return detailsGrammarUserRepository.findAll(pageable).map(detailsGrammarUserMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DetailsGrammarUserDTO> findOne(Long id) {
        log.debug("Request to get DetailsGrammarUser : {}", id);
        return detailsGrammarUserRepository.findById(id).map(detailsGrammarUserMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete DetailsGrammarUser : {}", id);
        detailsGrammarUserRepository.deleteById(id);
    }
}
