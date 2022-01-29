package com.sangtt.toeic_test.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sangtt.toeic_test.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DetailsToeicUserDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DetailsToeicUserDTO.class);
        DetailsToeicUserDTO detailsToeicUserDTO1 = new DetailsToeicUserDTO();
        detailsToeicUserDTO1.setId(1L);
        DetailsToeicUserDTO detailsToeicUserDTO2 = new DetailsToeicUserDTO();
        assertThat(detailsToeicUserDTO1).isNotEqualTo(detailsToeicUserDTO2);
        detailsToeicUserDTO2.setId(detailsToeicUserDTO1.getId());
        assertThat(detailsToeicUserDTO1).isEqualTo(detailsToeicUserDTO2);
        detailsToeicUserDTO2.setId(2L);
        assertThat(detailsToeicUserDTO1).isNotEqualTo(detailsToeicUserDTO2);
        detailsToeicUserDTO1.setId(null);
        assertThat(detailsToeicUserDTO1).isNotEqualTo(detailsToeicUserDTO2);
    }
}
