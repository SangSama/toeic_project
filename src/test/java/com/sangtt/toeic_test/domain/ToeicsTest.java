package com.sangtt.toeic_test.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sangtt.toeic_test.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ToeicsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Toeics.class);
        Toeics toeics1 = new Toeics();
        toeics1.setId(1L);
        Toeics toeics2 = new Toeics();
        toeics2.setId(toeics1.getId());
        assertThat(toeics1).isEqualTo(toeics2);
        toeics2.setId(2L);
        assertThat(toeics1).isNotEqualTo(toeics2);
        toeics1.setId(null);
        assertThat(toeics1).isNotEqualTo(toeics2);
    }
}
