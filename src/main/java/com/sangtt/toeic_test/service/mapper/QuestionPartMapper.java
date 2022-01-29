package com.sangtt.toeic_test.service.mapper;

import com.sangtt.toeic_test.domain.QuestionPart;
import com.sangtt.toeic_test.service.dto.QuestionPartDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link QuestionPart} and its DTO {@link QuestionPartDTO}.
 */
@Mapper(componentModel = "spring", uses = { AnswerQuestionMapper.class, PartsMapper.class, ExtendQuestionMapper.class })
public interface QuestionPartMapper extends EntityMapper<QuestionPartDTO, QuestionPart> {
    @Mapping(target = "answerQuestion", source = "answerQuestion", qualifiedByName = "id")
    @Mapping(target = "parts", source = "parts", qualifiedByName = "id")
    @Mapping(target = "extendQuestion", source = "extendQuestion", qualifiedByName = "id")
    QuestionPartDTO toDto(QuestionPart s);
}
