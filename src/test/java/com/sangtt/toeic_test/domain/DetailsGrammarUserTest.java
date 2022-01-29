package com.sangtt.toeic_test.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sangtt.toeic_test.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DetailsGrammarUserTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DetailsGrammarUser.class);
        DetailsGrammarUser detailsGrammarUser1 = new DetailsGrammarUser();
        detailsGrammarUser1.setId(1L);
        DetailsGrammarUser detailsGrammarUser2 = new DetailsGrammarUser();
        detailsGrammarUser2.setId(detailsGrammarUser1.getId());
        assertThat(detailsGrammarUser1).isEqualTo(detailsGrammarUser2);
        detailsGrammarUser2.setId(2L);
        assertThat(detailsGrammarUser1).isNotEqualTo(detailsGrammarUser2);
        detailsGrammarUser1.setId(null);
        assertThat(detailsGrammarUser1).isNotEqualTo(detailsGrammarUser2);
    }
}
