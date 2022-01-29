package com.sangtt.toeic_test.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sangtt.toeic_test.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PartsDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PartsDTO.class);
        PartsDTO partsDTO1 = new PartsDTO();
        partsDTO1.setId(1L);
        PartsDTO partsDTO2 = new PartsDTO();
        assertThat(partsDTO1).isNotEqualTo(partsDTO2);
        partsDTO2.setId(partsDTO1.getId());
        assertThat(partsDTO1).isEqualTo(partsDTO2);
        partsDTO2.setId(2L);
        assertThat(partsDTO1).isNotEqualTo(partsDTO2);
        partsDTO1.setId(null);
        assertThat(partsDTO1).isNotEqualTo(partsDTO2);
    }
}
