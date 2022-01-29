package com.sangtt.toeic_test.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sangtt.toeic_test.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class GrammarUserTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GrammarUser.class);
        GrammarUser grammarUser1 = new GrammarUser();
        grammarUser1.setId(1L);
        GrammarUser grammarUser2 = new GrammarUser();
        grammarUser2.setId(grammarUser1.getId());
        assertThat(grammarUser1).isEqualTo(grammarUser2);
        grammarUser2.setId(2L);
        assertThat(grammarUser1).isNotEqualTo(grammarUser2);
        grammarUser1.setId(null);
        assertThat(grammarUser1).isNotEqualTo(grammarUser2);
    }
}
