package com.sangtt.toeic_test.service.mapper;

import com.sangtt.toeic_test.domain.GrammarQuestion;
import com.sangtt.toeic_test.service.dto.GrammarQuestionDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link GrammarQuestion} and its DTO {@link GrammarQuestionDTO}.
 */
@Mapper(componentModel = "spring", uses = { GrammarAnswerMapper.class, GrammarTopicMapper.class })
public interface GrammarQuestionMapper extends EntityMapper<GrammarQuestionDTO, GrammarQuestion> {
    @Mapping(target = "grammarAnswer", source = "grammarAnswer", qualifiedByName = "id")
    @Mapping(target = "grammarTopic", source = "grammarTopic", qualifiedByName = "id")
    GrammarQuestionDTO toDto(GrammarQuestion s);
}
