package com.sangtt.toeic_test.service.mapper;

import com.sangtt.toeic_test.domain.ToeicUser;
import com.sangtt.toeic_test.service.dto.ToeicUserDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ToeicUser} and its DTO {@link ToeicUserDTO}.
 */
@Mapper(componentModel = "spring", uses = { ToeicsMapper.class })
public interface ToeicUserMapper extends EntityMapper<ToeicUserDTO, ToeicUser> {
    @Mapping(target = "toeics", source = "toeics", qualifiedByName = "id")
    ToeicUserDTO toDto(ToeicUser s);

    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ToeicUserDTO toDtoId(ToeicUser toeicUser);
}
