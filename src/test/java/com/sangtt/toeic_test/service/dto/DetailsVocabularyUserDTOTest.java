package com.sangtt.toeic_test.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sangtt.toeic_test.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DetailsVocabularyUserDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DetailsVocabularyUserDTO.class);
        DetailsVocabularyUserDTO detailsVocabularyUserDTO1 = new DetailsVocabularyUserDTO();
        detailsVocabularyUserDTO1.setId(1L);
        DetailsVocabularyUserDTO detailsVocabularyUserDTO2 = new DetailsVocabularyUserDTO();
        assertThat(detailsVocabularyUserDTO1).isNotEqualTo(detailsVocabularyUserDTO2);
        detailsVocabularyUserDTO2.setId(detailsVocabularyUserDTO1.getId());
        assertThat(detailsVocabularyUserDTO1).isEqualTo(detailsVocabularyUserDTO2);
        detailsVocabularyUserDTO2.setId(2L);
        assertThat(detailsVocabularyUserDTO1).isNotEqualTo(detailsVocabularyUserDTO2);
        detailsVocabularyUserDTO1.setId(null);
        assertThat(detailsVocabularyUserDTO1).isNotEqualTo(detailsVocabularyUserDTO2);
    }
}
