package com.sangtt.toeic_test.service.mapper;

import com.sangtt.toeic_test.domain.Toeics;
import com.sangtt.toeic_test.service.dto.ToeicsDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Toeics} and its DTO {@link ToeicsDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ToeicsMapper extends EntityMapper<ToeicsDTO, Toeics> {
    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ToeicsDTO toDtoId(Toeics toeics);
}
