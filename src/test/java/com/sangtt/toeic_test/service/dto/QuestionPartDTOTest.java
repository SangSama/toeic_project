package com.sangtt.toeic_test.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sangtt.toeic_test.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class QuestionPartDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(QuestionPartDTO.class);
        QuestionPartDTO questionPartDTO1 = new QuestionPartDTO();
        questionPartDTO1.setId(1L);
        QuestionPartDTO questionPartDTO2 = new QuestionPartDTO();
        assertThat(questionPartDTO1).isNotEqualTo(questionPartDTO2);
        questionPartDTO2.setId(questionPartDTO1.getId());
        assertThat(questionPartDTO1).isEqualTo(questionPartDTO2);
        questionPartDTO2.setId(2L);
        assertThat(questionPartDTO1).isNotEqualTo(questionPartDTO2);
        questionPartDTO1.setId(null);
        assertThat(questionPartDTO1).isNotEqualTo(questionPartDTO2);
    }
}
