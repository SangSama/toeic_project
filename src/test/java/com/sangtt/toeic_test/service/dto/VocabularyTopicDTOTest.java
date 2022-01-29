package com.sangtt.toeic_test.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sangtt.toeic_test.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class VocabularyTopicDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(VocabularyTopicDTO.class);
        VocabularyTopicDTO vocabularyTopicDTO1 = new VocabularyTopicDTO();
        vocabularyTopicDTO1.setId(1L);
        VocabularyTopicDTO vocabularyTopicDTO2 = new VocabularyTopicDTO();
        assertThat(vocabularyTopicDTO1).isNotEqualTo(vocabularyTopicDTO2);
        vocabularyTopicDTO2.setId(vocabularyTopicDTO1.getId());
        assertThat(vocabularyTopicDTO1).isEqualTo(vocabularyTopicDTO2);
        vocabularyTopicDTO2.setId(2L);
        assertThat(vocabularyTopicDTO1).isNotEqualTo(vocabularyTopicDTO2);
        vocabularyTopicDTO1.setId(null);
        assertThat(vocabularyTopicDTO1).isNotEqualTo(vocabularyTopicDTO2);
    }
}
