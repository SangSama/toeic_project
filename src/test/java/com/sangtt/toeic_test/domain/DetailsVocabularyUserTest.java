package com.sangtt.toeic_test.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sangtt.toeic_test.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DetailsVocabularyUserTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DetailsVocabularyUser.class);
        DetailsVocabularyUser detailsVocabularyUser1 = new DetailsVocabularyUser();
        detailsVocabularyUser1.setId(1L);
        DetailsVocabularyUser detailsVocabularyUser2 = new DetailsVocabularyUser();
        detailsVocabularyUser2.setId(detailsVocabularyUser1.getId());
        assertThat(detailsVocabularyUser1).isEqualTo(detailsVocabularyUser2);
        detailsVocabularyUser2.setId(2L);
        assertThat(detailsVocabularyUser1).isNotEqualTo(detailsVocabularyUser2);
        detailsVocabularyUser1.setId(null);
        assertThat(detailsVocabularyUser1).isNotEqualTo(detailsVocabularyUser2);
    }
}
