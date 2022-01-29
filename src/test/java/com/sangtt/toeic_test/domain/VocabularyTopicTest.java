package com.sangtt.toeic_test.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sangtt.toeic_test.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class VocabularyTopicTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(VocabularyTopic.class);
        VocabularyTopic vocabularyTopic1 = new VocabularyTopic();
        vocabularyTopic1.setId(1L);
        VocabularyTopic vocabularyTopic2 = new VocabularyTopic();
        vocabularyTopic2.setId(vocabularyTopic1.getId());
        assertThat(vocabularyTopic1).isEqualTo(vocabularyTopic2);
        vocabularyTopic2.setId(2L);
        assertThat(vocabularyTopic1).isNotEqualTo(vocabularyTopic2);
        vocabularyTopic1.setId(null);
        assertThat(vocabularyTopic1).isNotEqualTo(vocabularyTopic2);
    }
}
