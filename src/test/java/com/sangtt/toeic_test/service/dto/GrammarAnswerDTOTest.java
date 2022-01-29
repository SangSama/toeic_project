package com.sangtt.toeic_test.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sangtt.toeic_test.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class GrammarAnswerDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(GrammarAnswerDTO.class);
        GrammarAnswerDTO grammarAnswerDTO1 = new GrammarAnswerDTO();
        grammarAnswerDTO1.setId(1L);
        GrammarAnswerDTO grammarAnswerDTO2 = new GrammarAnswerDTO();
        assertThat(grammarAnswerDTO1).isNotEqualTo(grammarAnswerDTO2);
        grammarAnswerDTO2.setId(grammarAnswerDTO1.getId());
        assertThat(grammarAnswerDTO1).isEqualTo(grammarAnswerDTO2);
        grammarAnswerDTO2.setId(2L);
        assertThat(grammarAnswerDTO1).isNotEqualTo(grammarAnswerDTO2);
        grammarAnswerDTO1.setId(null);
        assertThat(grammarAnswerDTO1).isNotEqualTo(grammarAnswerDTO2);
    }
}
