package com.sangtt.toeic_test.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sangtt.toeic_test.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class GrammarTopicTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GrammarTopic.class);
        GrammarTopic grammarTopic1 = new GrammarTopic();
        grammarTopic1.setId(1L);
        GrammarTopic grammarTopic2 = new GrammarTopic();
        grammarTopic2.setId(grammarTopic1.getId());
        assertThat(grammarTopic1).isEqualTo(grammarTopic2);
        grammarTopic2.setId(2L);
        assertThat(grammarTopic1).isNotEqualTo(grammarTopic2);
        grammarTopic1.setId(null);
        assertThat(grammarTopic1).isNotEqualTo(grammarTopic2);
    }
}
