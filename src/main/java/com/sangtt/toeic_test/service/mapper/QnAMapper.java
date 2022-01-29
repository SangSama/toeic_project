package com.sangtt.toeic_test.service.mapper;

import com.sangtt.toeic_test.domain.QnA;
import com.sangtt.toeic_test.service.dto.QnADTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link QnA} and its DTO {@link QnADTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface QnAMapper extends EntityMapper<QnADTO, QnA> {}
