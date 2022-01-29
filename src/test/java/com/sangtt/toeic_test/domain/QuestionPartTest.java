package com.sangtt.toeic_test.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sangtt.toeic_test.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class QuestionPartTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(QuestionPart.class);
        QuestionPart questionPart1 = new QuestionPart();
        questionPart1.setId(1L);
        QuestionPart questionPart2 = new QuestionPart();
        questionPart2.setId(questionPart1.getId());
        assertThat(questionPart1).isEqualTo(questionPart2);
        questionPart2.setId(2L);
        assertThat(questionPart1).isNotEqualTo(questionPart2);
        questionPart1.setId(null);
        assertThat(questionPart1).isNotEqualTo(questionPart2);
    }
}
