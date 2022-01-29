package com.sangtt.toeic_test.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sangtt.toeic_test.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DetailsGrammarUserDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DetailsGrammarUserDTO.class);
        DetailsGrammarUserDTO detailsGrammarUserDTO1 = new DetailsGrammarUserDTO();
        detailsGrammarUserDTO1.setId(1L);
        DetailsGrammarUserDTO detailsGrammarUserDTO2 = new DetailsGrammarUserDTO();
        assertThat(detailsGrammarUserDTO1).isNotEqualTo(detailsGrammarUserDTO2);
        detailsGrammarUserDTO2.setId(detailsGrammarUserDTO1.getId());
        assertThat(detailsGrammarUserDTO1).isEqualTo(detailsGrammarUserDTO2);
        detailsGrammarUserDTO2.setId(2L);
        assertThat(detailsGrammarUserDTO1).isNotEqualTo(detailsGrammarUserDTO2);
        detailsGrammarUserDTO1.setId(null);
        assertThat(detailsGrammarUserDTO1).isNotEqualTo(detailsGrammarUserDTO2);
    }
}
