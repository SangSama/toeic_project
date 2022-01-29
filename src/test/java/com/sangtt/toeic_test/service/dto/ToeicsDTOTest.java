package com.sangtt.toeic_test.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sangtt.toeic_test.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ToeicsDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ToeicsDTO.class);
        ToeicsDTO toeicsDTO1 = new ToeicsDTO();
        toeicsDTO1.setId(1L);
        ToeicsDTO toeicsDTO2 = new ToeicsDTO();
        assertThat(toeicsDTO1).isNotEqualTo(toeicsDTO2);
        toeicsDTO2.setId(toeicsDTO1.getId());
        assertThat(toeicsDTO1).isEqualTo(toeicsDTO2);
        toeicsDTO2.setId(2L);
        assertThat(toeicsDTO1).isNotEqualTo(toeicsDTO2);
        toeicsDTO1.setId(null);
        assertThat(toeicsDTO1).isNotEqualTo(toeicsDTO2);
    }
}
