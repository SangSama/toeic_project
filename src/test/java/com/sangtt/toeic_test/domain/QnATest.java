package com.sangtt.toeic_test.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sangtt.toeic_test.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class QnATest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(QnA.class);
        QnA qnA1 = new QnA();
        qnA1.setId(1L);
        QnA qnA2 = new QnA();
        qnA2.setId(qnA1.getId());
        assertThat(qnA1).isEqualTo(qnA2);
        qnA2.setId(2L);
        assertThat(qnA1).isNotEqualTo(qnA2);
        qnA1.setId(null);
        assertThat(qnA1).isNotEqualTo(qnA2);
    }
}
