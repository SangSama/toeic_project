package com.sangtt.toeic_test.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sangtt.toeic_test.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ToeicUserDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ToeicUserDTO.class);
        ToeicUserDTO toeicUserDTO1 = new ToeicUserDTO();
        toeicUserDTO1.setId(1L);
        ToeicUserDTO toeicUserDTO2 = new ToeicUserDTO();
        assertThat(toeicUserDTO1).isNotEqualTo(toeicUserDTO2);
        toeicUserDTO2.setId(toeicUserDTO1.getId());
        assertThat(toeicUserDTO1).isEqualTo(toeicUserDTO2);
        toeicUserDTO2.setId(2L);
        assertThat(toeicUserDTO1).isNotEqualTo(toeicUserDTO2);
        toeicUserDTO1.setId(null);
        assertThat(toeicUserDTO1).isNotEqualTo(toeicUserDTO2);
    }
}
