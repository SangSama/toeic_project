package com.sangtt.toeic_test.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sangtt.toeic_test.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AnswerQuestionTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AnswerQuestion.class);
        AnswerQuestion answerQuestion1 = new AnswerQuestion();
        answerQuestion1.setId(1L);
        AnswerQuestion answerQuestion2 = new AnswerQuestion();
        answerQuestion2.setId(answerQuestion1.getId());
        assertThat(answerQuestion1).isEqualTo(answerQuestion2);
        answerQuestion2.setId(2L);
        assertThat(answerQuestion1).isNotEqualTo(answerQuestion2);
        answerQuestion1.setId(null);
        assertThat(answerQuestion1).isNotEqualTo(answerQuestion2);
    }
}
