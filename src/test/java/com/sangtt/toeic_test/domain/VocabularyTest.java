package com.sangtt.toeic_test.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sangtt.toeic_test.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class VocabularyTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Vocabulary.class);
        Vocabulary vocabulary1 = new Vocabulary();
        vocabulary1.setId(1L);
        Vocabulary vocabulary2 = new Vocabulary();
        vocabulary2.setId(vocabulary1.getId());
        assertThat(vocabulary1).isEqualTo(vocabulary2);
        vocabulary2.setId(2L);
        assertThat(vocabulary1).isNotEqualTo(vocabulary2);
        vocabulary1.setId(null);
        assertThat(vocabulary1).isNotEqualTo(vocabulary2);
    }
}
