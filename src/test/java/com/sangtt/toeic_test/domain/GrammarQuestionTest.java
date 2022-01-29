package com.sangtt.toeic_test.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sangtt.toeic_test.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class GrammarQuestionTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GrammarQuestion.class);
        GrammarQuestion grammarQuestion1 = new GrammarQuestion();
        grammarQuestion1.setId(1L);
        GrammarQuestion grammarQuestion2 = new GrammarQuestion();
        grammarQuestion2.setId(grammarQuestion1.getId());
        assertThat(grammarQuestion1).isEqualTo(grammarQuestion2);
        grammarQuestion2.setId(2L);
        assertThat(grammarQuestion1).isNotEqualTo(grammarQuestion2);
        grammarQuestion1.setId(null);
        assertThat(grammarQuestion1).isNotEqualTo(grammarQuestion2);
    }
}
