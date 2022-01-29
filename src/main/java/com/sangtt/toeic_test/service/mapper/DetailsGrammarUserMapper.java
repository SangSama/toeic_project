package com.sangtt.toeic_test.service.mapper;

import com.sangtt.toeic_test.domain.DetailsGrammarUser;
import com.sangtt.toeic_test.service.dto.DetailsGrammarUserDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link DetailsGrammarUser} and its DTO {@link DetailsGrammarUserDTO}.
 */
@Mapper(componentModel = "spring", uses = { GrammarUserMapper.class })
public interface DetailsGrammarUserMapper extends EntityMapper<DetailsGrammarUserDTO, DetailsGrammarUser> {
    @Mapping(target = "grammarUser", source = "grammarUser", qualifiedByName = "id")
    DetailsGrammarUserDTO toDto(DetailsGrammarUser s);
}
