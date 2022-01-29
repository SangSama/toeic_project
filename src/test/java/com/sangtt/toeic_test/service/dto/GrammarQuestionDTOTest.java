package com.sangtt.toeic_test.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sangtt.toeic_test.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class GrammarQuestionDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(GrammarQuestionDTO.class);
        GrammarQuestionDTO grammarQuestionDTO1 = new GrammarQuestionDTO();
        grammarQuestionDTO1.setId(1L);
        GrammarQuestionDTO grammarQuestionDTO2 = new GrammarQuestionDTO();
        assertThat(grammarQuestionDTO1).isNotEqualTo(grammarQuestionDTO2);
        grammarQuestionDTO2.setId(grammarQuestionDTO1.getId());
        assertThat(grammarQuestionDTO1).isEqualTo(grammarQuestionDTO2);
        grammarQuestionDTO2.setId(2L);
        assertThat(grammarQuestionDTO1).isNotEqualTo(grammarQuestionDTO2);
        grammarQuestionDTO1.setId(null);
        assertThat(grammarQuestionDTO1).isNotEqualTo(grammarQuestionDTO2);
    }
}
