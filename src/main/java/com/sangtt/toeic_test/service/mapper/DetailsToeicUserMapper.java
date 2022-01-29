package com.sangtt.toeic_test.service.mapper;

import com.sangtt.toeic_test.domain.DetailsToeicUser;
import com.sangtt.toeic_test.service.dto.DetailsToeicUserDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link DetailsToeicUser} and its DTO {@link DetailsToeicUserDTO}.
 */
@Mapper(componentModel = "spring", uses = { ToeicUserMapper.class })
public interface DetailsToeicUserMapper extends EntityMapper<DetailsToeicUserDTO, DetailsToeicUser> {
    @Mapping(target = "toeicUser", source = "toeicUser", qualifiedByName = "id")
    DetailsToeicUserDTO toDto(DetailsToeicUser s);
}
