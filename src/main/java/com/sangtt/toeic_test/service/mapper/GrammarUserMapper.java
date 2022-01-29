package com.sangtt.toeic_test.service.mapper;

import com.sangtt.toeic_test.domain.GrammarUser;
import com.sangtt.toeic_test.service.dto.GrammarUserDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link GrammarUser} and its DTO {@link GrammarUserDTO}.
 */
@Mapper(componentModel = "spring", uses = { GrammarTopicMapper.class })
public interface GrammarUserMapper extends EntityMapper<GrammarUserDTO, GrammarUser> {
    @Mapping(target = "grammarTopic", source = "grammarTopic", qualifiedByName = "id")
    GrammarUserDTO toDto(GrammarUser s);

    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    GrammarUserDTO toDtoId(GrammarUser grammarUser);
}
