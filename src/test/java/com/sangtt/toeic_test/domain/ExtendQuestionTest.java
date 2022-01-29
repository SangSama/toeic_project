package com.sangtt.toeic_test.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sangtt.toeic_test.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ExtendQuestionTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ExtendQuestion.class);
        ExtendQuestion extendQuestion1 = new ExtendQuestion();
        extendQuestion1.setId(1L);
        ExtendQuestion extendQuestion2 = new ExtendQuestion();
        extendQuestion2.setId(extendQuestion1.getId());
        assertThat(extendQuestion1).isEqualTo(extendQuestion2);
        extendQuestion2.setId(2L);
        assertThat(extendQuestion1).isNotEqualTo(extendQuestion2);
        extendQuestion1.setId(null);
        assertThat(extendQuestion1).isNotEqualTo(extendQuestion2);
    }
}
