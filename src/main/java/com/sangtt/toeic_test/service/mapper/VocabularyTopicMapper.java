package com.sangtt.toeic_test.service.mapper;

import com.sangtt.toeic_test.domain.VocabularyTopic;
import com.sangtt.toeic_test.service.dto.VocabularyTopicDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link VocabularyTopic} and its DTO {@link VocabularyTopicDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface VocabularyTopicMapper extends EntityMapper<VocabularyTopicDTO, VocabularyTopic> {
    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    VocabularyTopicDTO toDtoId(VocabularyTopic vocabularyTopic);
}
