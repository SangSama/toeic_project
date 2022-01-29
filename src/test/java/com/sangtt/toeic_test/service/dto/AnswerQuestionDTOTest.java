package com.sangtt.toeic_test.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sangtt.toeic_test.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AnswerQuestionDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AnswerQuestionDTO.class);
        AnswerQuestionDTO answerQuestionDTO1 = new AnswerQuestionDTO();
        answerQuestionDTO1.setId(1L);
        AnswerQuestionDTO answerQuestionDTO2 = new AnswerQuestionDTO();
        assertThat(answerQuestionDTO1).isNotEqualTo(answerQuestionDTO2);
        answerQuestionDTO2.setId(answerQuestionDTO1.getId());
        assertThat(answerQuestionDTO1).isEqualTo(answerQuestionDTO2);
        answerQuestionDTO2.setId(2L);
        assertThat(answerQuestionDTO1).isNotEqualTo(answerQuestionDTO2);
        answerQuestionDTO1.setId(null);
        assertThat(answerQuestionDTO1).isNotEqualTo(answerQuestionDTO2);
    }
}
