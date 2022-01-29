package com.sangtt.toeic_test.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sangtt.toeic_test.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ExtendQuestionDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ExtendQuestionDTO.class);
        ExtendQuestionDTO extendQuestionDTO1 = new ExtendQuestionDTO();
        extendQuestionDTO1.setId(1L);
        ExtendQuestionDTO extendQuestionDTO2 = new ExtendQuestionDTO();
        assertThat(extendQuestionDTO1).isNotEqualTo(extendQuestionDTO2);
        extendQuestionDTO2.setId(extendQuestionDTO1.getId());
        assertThat(extendQuestionDTO1).isEqualTo(extendQuestionDTO2);
        extendQuestionDTO2.setId(2L);
        assertThat(extendQuestionDTO1).isNotEqualTo(extendQuestionDTO2);
        extendQuestionDTO1.setId(null);
        assertThat(extendQuestionDTO1).isNotEqualTo(extendQuestionDTO2);
    }
}
