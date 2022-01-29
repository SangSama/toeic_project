package com.sangtt.toeic_test.service.impl;

import com.sangtt.toeic_test.domain.GrammarUser;
import com.sangtt.toeic_test.repository.GrammarUserRepository;
import com.sangtt.toeic_test.service.GrammarUserService;
import com.sangtt.toeic_test.service.dto.GrammarUserDTO;
import com.sangtt.toeic_test.service.mapper.GrammarUserMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link GrammarUser}.
 */
@Service
@Transactional
public class GrammarUserServiceImpl implements GrammarUserService {

    private final Logger log = LoggerFactory.getLogger(GrammarUserServiceImpl.class);

    private final GrammarUserRepository grammarUserRepository;

    private final GrammarUserMapper grammarUserMapper;

    public GrammarUserServiceImpl(GrammarUserRepository grammarUserRepository, GrammarUserMapper grammarUserMapper) {
        this.grammarUserRepository = grammarUserRepository;
        this.grammarUserMapper = grammarUserMapper;
    }

    @Override
    public GrammarUserDTO save(GrammarUserDTO grammarUserDTO) {
        log.debug("Request to save GrammarUser : {}", grammarUserDTO);
        GrammarUser grammarUser = grammarUserMapper.toEntity(grammarUserDTO);
        grammarUser = grammarUserRepository.save(grammarUser);
        return grammarUserMapper.toDto(grammarUser);
    }

    @Override
    public Optional<GrammarUserDTO> partialUpdate(GrammarUserDTO grammarUserDTO) {
        log.debug("Request to partially update GrammarUser : {}", grammarUserDTO);

        return grammarUserRepository
            .findById(grammarUserDTO.getId())
            .map(existingGrammarUser -> {
                grammarUserMapper.partialUpdate(existingGrammarUser, grammarUserDTO);

                return existingGrammarUser;
            })
            .map(grammarUserRepository::save)
            .map(grammarUserMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<GrammarUserDTO> findAll(Pageable pageable) {
        log.debug("Request to get all GrammarUsers");
        return grammarUserRepository.findAll(pageable).map(grammarUserMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<GrammarUserDTO> findOne(Long id) {
        log.debug("Request to get GrammarUser : {}", id);
        return grammarUserRepository.findById(id).map(grammarUserMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete GrammarUser : {}", id);
        grammarUserRepository.deleteById(id);
    }
}
