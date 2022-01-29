package com.sangtt.toeic_test.service.impl;

import com.sangtt.toeic_test.domain.ToeicUser;
import com.sangtt.toeic_test.repository.ToeicUserRepository;
import com.sangtt.toeic_test.service.ToeicUserService;
import com.sangtt.toeic_test.service.dto.ToeicUserDTO;
import com.sangtt.toeic_test.service.mapper.ToeicUserMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ToeicUser}.
 */
@Service
@Transactional
public class ToeicUserServiceImpl implements ToeicUserService {

    private final Logger log = LoggerFactory.getLogger(ToeicUserServiceImpl.class);

    private final ToeicUserRepository toeicUserRepository;

    private final ToeicUserMapper toeicUserMapper;

    public ToeicUserServiceImpl(ToeicUserRepository toeicUserRepository, ToeicUserMapper toeicUserMapper) {
        this.toeicUserRepository = toeicUserRepository;
        this.toeicUserMapper = toeicUserMapper;
    }

    @Override
    public ToeicUserDTO save(ToeicUserDTO toeicUserDTO) {
        log.debug("Request to save ToeicUser : {}", toeicUserDTO);
        ToeicUser toeicUser = toeicUserMapper.toEntity(toeicUserDTO);
        toeicUser = toeicUserRepository.save(toeicUser);
        return toeicUserMapper.toDto(toeicUser);
    }

    @Override
    public Optional<ToeicUserDTO> partialUpdate(ToeicUserDTO toeicUserDTO) {
        log.debug("Request to partially update ToeicUser : {}", toeicUserDTO);

        return toeicUserRepository
            .findById(toeicUserDTO.getId())
            .map(existingToeicUser -> {
                toeicUserMapper.partialUpdate(existingToeicUser, toeicUserDTO);

                return existingToeicUser;
            })
            .map(toeicUserRepository::save)
            .map(toeicUserMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ToeicUserDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ToeicUsers");
        return toeicUserRepository.findAll(pageable).map(toeicUserMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ToeicUserDTO> findOne(Long id) {
        log.debug("Request to get ToeicUser : {}", id);
        return toeicUserRepository.findById(id).map(toeicUserMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ToeicUser : {}", id);
        toeicUserRepository.deleteById(id);
    }
}
