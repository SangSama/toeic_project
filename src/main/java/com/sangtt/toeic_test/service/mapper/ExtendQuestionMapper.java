package com.sangtt.toeic_test.service.mapper;

import com.sangtt.toeic_test.domain.ExtendQuestion;
import com.sangtt.toeic_test.service.dto.ExtendQuestionDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ExtendQuestion} and its DTO {@link ExtendQuestionDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ExtendQuestionMapper extends EntityMapper<ExtendQuestionDTO, ExtendQuestion> {
    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ExtendQuestionDTO toDtoId(ExtendQuestion extendQuestion);
}
