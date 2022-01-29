package com.sangtt.toeic_test.service.mapper;

import com.sangtt.toeic_test.domain.VocabularyUser;
import com.sangtt.toeic_test.service.dto.VocabularyUserDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link VocabularyUser} and its DTO {@link VocabularyUserDTO}.
 */
@Mapper(componentModel = "spring", uses = { VocabularyTopicMapper.class })
public interface VocabularyUserMapper extends EntityMapper<VocabularyUserDTO, VocabularyUser> {
    @Mapping(target = "vocabularyTopic", source = "vocabularyTopic", qualifiedByName = "id")
    VocabularyUserDTO toDto(VocabularyUser s);

    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    VocabularyUserDTO toDtoId(VocabularyUser vocabularyUser);
}
