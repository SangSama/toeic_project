package com.sangtt.toeic_test.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sangtt.toeic_test.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class GrammarUserDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(GrammarUserDTO.class);
        GrammarUserDTO grammarUserDTO1 = new GrammarUserDTO();
        grammarUserDTO1.setId(1L);
        GrammarUserDTO grammarUserDTO2 = new GrammarUserDTO();
        assertThat(grammarUserDTO1).isNotEqualTo(grammarUserDTO2);
        grammarUserDTO2.setId(grammarUserDTO1.getId());
        assertThat(grammarUserDTO1).isEqualTo(grammarUserDTO2);
        grammarUserDTO2.setId(2L);
        assertThat(grammarUserDTO1).isNotEqualTo(grammarUserDTO2);
        grammarUserDTO1.setId(null);
        assertThat(grammarUserDTO1).isNotEqualTo(grammarUserDTO2);
    }
}
