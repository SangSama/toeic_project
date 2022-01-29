package com.sangtt.toeic_test.service.mapper;

import com.sangtt.toeic_test.domain.Parts;
import com.sangtt.toeic_test.service.dto.PartsDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Parts} and its DTO {@link PartsDTO}.
 */
@Mapper(componentModel = "spring", uses = { ToeicsMapper.class })
public interface PartsMapper extends EntityMapper<PartsDTO, Parts> {
    @Mapping(target = "toeics", source = "toeics", qualifiedByName = "id")
    PartsDTO toDto(Parts s);

    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    PartsDTO toDtoId(Parts parts);
}
