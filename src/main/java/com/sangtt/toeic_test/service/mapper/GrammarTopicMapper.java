package com.sangtt.toeic_test.service.mapper;

import com.sangtt.toeic_test.domain.GrammarTopic;
import com.sangtt.toeic_test.service.dto.GrammarTopicDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link GrammarTopic} and its DTO {@link GrammarTopicDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface GrammarTopicMapper extends EntityMapper<GrammarTopicDTO, GrammarTopic> {
    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    GrammarTopicDTO toDtoId(GrammarTopic grammarTopic);
}
