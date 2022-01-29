package com.sangtt.toeic_test.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sangtt.toeic_test.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class VocabularyUserTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(VocabularyUser.class);
        VocabularyUser vocabularyUser1 = new VocabularyUser();
        vocabularyUser1.setId(1L);
        VocabularyUser vocabularyUser2 = new VocabularyUser();
        vocabularyUser2.setId(vocabularyUser1.getId());
        assertThat(vocabularyUser1).isEqualTo(vocabularyUser2);
        vocabularyUser2.setId(2L);
        assertThat(vocabularyUser1).isNotEqualTo(vocabularyUser2);
        vocabularyUser1.setId(null);
        assertThat(vocabularyUser1).isNotEqualTo(vocabularyUser2);
    }
}
