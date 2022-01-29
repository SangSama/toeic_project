package com.sangtt.toeic_test.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sangtt.toeic_test.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class QnADTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(QnADTO.class);
        QnADTO qnADTO1 = new QnADTO();
        qnADTO1.setId(1L);
        QnADTO qnADTO2 = new QnADTO();
        assertThat(qnADTO1).isNotEqualTo(qnADTO2);
        qnADTO2.setId(qnADTO1.getId());
        assertThat(qnADTO1).isEqualTo(qnADTO2);
        qnADTO2.setId(2L);
        assertThat(qnADTO1).isNotEqualTo(qnADTO2);
        qnADTO1.setId(null);
        assertThat(qnADTO1).isNotEqualTo(qnADTO2);
    }
}
