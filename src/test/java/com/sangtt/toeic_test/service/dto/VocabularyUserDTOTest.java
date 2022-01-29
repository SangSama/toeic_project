package com.sangtt.toeic_test.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sangtt.toeic_test.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class VocabularyUserDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(VocabularyUserDTO.class);
        VocabularyUserDTO vocabularyUserDTO1 = new VocabularyUserDTO();
        vocabularyUserDTO1.setId(1L);
        VocabularyUserDTO vocabularyUserDTO2 = new VocabularyUserDTO();
        assertThat(vocabularyUserDTO1).isNotEqualTo(vocabularyUserDTO2);
        vocabularyUserDTO2.setId(vocabularyUserDTO1.getId());
        assertThat(vocabularyUserDTO1).isEqualTo(vocabularyUserDTO2);
        vocabularyUserDTO2.setId(2L);
        assertThat(vocabularyUserDTO1).isNotEqualTo(vocabularyUserDTO2);
        vocabularyUserDTO1.setId(null);
        assertThat(vocabularyUserDTO1).isNotEqualTo(vocabularyUserDTO2);
    }
}
