package com.sangtt.toeic_test.service.impl;

import com.sangtt.toeic_test.domain.DetailsToeicUser;
import com.sangtt.toeic_test.repository.DetailsToeicUserRepository;
import com.sangtt.toeic_test.service.DetailsToeicUserService;
import com.sangtt.toeic_test.service.dto.DetailsToeicUserDTO;
import com.sangtt.toeic_test.service.mapper.DetailsToeicUserMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link DetailsToeicUser}.
 */
@Service
@Transactional
public class DetailsToeicUserServiceImpl implements DetailsToeicUserService {

    private final Logger log = LoggerFactory.getLogger(DetailsToeicUserServiceImpl.class);

    private final DetailsToeicUserRepository detailsToeicUserRepository;

    private final DetailsToeicUserMapper detailsToeicUserMapper;

    public DetailsToeicUserServiceImpl(
        DetailsToeicUserRepository detailsToeicUserRepository,
        DetailsToeicUserMapper detailsToeicUserMapper
    ) {
        this.detailsToeicUserRepository = detailsToeicUserRepository;
        this.detailsToeicUserMapper = detailsToeicUserMapper;
    }

    @Override
    public DetailsToeicUserDTO save(DetailsToeicUserDTO detailsToeicUserDTO) {
        log.debug("Request to save DetailsToeicUser : {}", detailsToeicUserDTO);
        DetailsToeicUser detailsToeicUser = detailsToeicUserMapper.toEntity(detailsToeicUserDTO);
        detailsToeicUser = detailsToeicUserRepository.save(detailsToeicUser);
        return detailsToeicUserMapper.toDto(detailsToeicUser);
    }

    @Override
    public Optional<DetailsToeicUserDTO> partialUpdate(DetailsToeicUserDTO detailsToeicUserDTO) {
        log.debug("Request to partially update DetailsToeicUser : {}", detailsToeicUserDTO);

        return detailsToeicUserRepository
            .findById(detailsToeicUserDTO.getId())
            .map(existingDetailsToeicUser -> {
                detailsToeicUserMapper.partialUpdate(existingDetailsToeicUser, detailsToeicUserDTO);

                return existingDetailsToeicUser;
            })
            .map(detailsToeicUserRepository::save)
            .map(detailsToeicUserMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DetailsToeicUserDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DetailsToeicUsers");
        return detailsToeicUserRepository.findAll(pageable).map(detailsToeicUserMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DetailsToeicUserDTO> findOne(Long id) {
        log.debug("Request to get DetailsToeicUser : {}", id);
        return detailsToeicUserRepository.findById(id).map(detailsToeicUserMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete DetailsToeicUser : {}", id);
        detailsToeicUserRepository.deleteById(id);
    }
}
