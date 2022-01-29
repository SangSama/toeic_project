package com.sangtt.toeic_test.service.mapper;

import com.sangtt.toeic_test.domain.DetailsVocabularyUser;
import com.sangtt.toeic_test.service.dto.DetailsVocabularyUserDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link DetailsVocabularyUser} and its DTO {@link DetailsVocabularyUserDTO}.
 */
@Mapper(componentModel = "spring", uses = { VocabularyUserMapper.class })
public interface DetailsVocabularyUserMapper extends EntityMapper<DetailsVocabularyUserDTO, DetailsVocabularyUser> {
    @Mapping(target = "vocabularyUser", source = "vocabularyUser", qualifiedByName = "id")
    DetailsVocabularyUserDTO toDto(DetailsVocabularyUser s);
}
