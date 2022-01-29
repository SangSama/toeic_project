package com.sangtt.toeic_test.service.mapper;

import com.sangtt.toeic_test.domain.AnswerQuestion;
import com.sangtt.toeic_test.service.dto.AnswerQuestionDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link AnswerQuestion} and its DTO {@link AnswerQuestionDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AnswerQuestionMapper extends EntityMapper<AnswerQuestionDTO, AnswerQuestion> {
    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    AnswerQuestionDTO toDtoId(AnswerQuestion answerQuestion);
}
