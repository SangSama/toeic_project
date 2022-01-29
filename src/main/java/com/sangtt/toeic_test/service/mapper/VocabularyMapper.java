package com.sangtt.toeic_test.service.mapper;

import com.sangtt.toeic_test.domain.Vocabulary;
import com.sangtt.toeic_test.service.dto.VocabularyDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Vocabulary} and its DTO {@link VocabularyDTO}.
 */
@Mapper(componentModel = "spring", uses = { VocabularyTopicMapper.class })
public interface VocabularyMapper extends EntityMapper<VocabularyDTO, Vocabulary> {
    @Mapping(target = "vocabularyTopic", source = "vocabularyTopic", qualifiedByName = "id")
    VocabularyDTO toDto(Vocabulary s);
}
