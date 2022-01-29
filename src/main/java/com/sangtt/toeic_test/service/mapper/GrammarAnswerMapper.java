package com.sangtt.toeic_test.service.mapper;

import com.sangtt.toeic_test.domain.GrammarAnswer;
import com.sangtt.toeic_test.service.dto.GrammarAnswerDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link GrammarAnswer} and its DTO {@link GrammarAnswerDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface GrammarAnswerMapper extends EntityMapper<GrammarAnswerDTO, GrammarAnswer> {
    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    GrammarAnswerDTO toDtoId(GrammarAnswer grammarAnswer);
}
