package com.sangtt.toeic_test.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sangtt.toeic_test.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class GrammarTopicDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(GrammarTopicDTO.class);
        GrammarTopicDTO grammarTopicDTO1 = new GrammarTopicDTO();
        grammarTopicDTO1.setId(1L);
        GrammarTopicDTO grammarTopicDTO2 = new GrammarTopicDTO();
        assertThat(grammarTopicDTO1).isNotEqualTo(grammarTopicDTO2);
        grammarTopicDTO2.setId(grammarTopicDTO1.getId());
        assertThat(grammarTopicDTO1).isEqualTo(grammarTopicDTO2);
        grammarTopicDTO2.setId(2L);
        assertThat(grammarTopicDTO1).isNotEqualTo(grammarTopicDTO2);
        grammarTopicDTO1.setId(null);
        assertThat(grammarTopicDTO1).isNotEqualTo(grammarTopicDTO2);
    }
}
