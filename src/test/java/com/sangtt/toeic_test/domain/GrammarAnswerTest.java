package com.sangtt.toeic_test.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sangtt.toeic_test.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class GrammarAnswerTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GrammarAnswer.class);
        GrammarAnswer grammarAnswer1 = new GrammarAnswer();
        grammarAnswer1.setId(1L);
        GrammarAnswer grammarAnswer2 = new GrammarAnswer();
        grammarAnswer2.setId(grammarAnswer1.getId());
        assertThat(grammarAnswer1).isEqualTo(grammarAnswer2);
        grammarAnswer2.setId(2L);
        assertThat(grammarAnswer1).isNotEqualTo(grammarAnswer2);
        grammarAnswer1.setId(null);
        assertThat(grammarAnswer1).isNotEqualTo(grammarAnswer2);
    }
}
