package com.sangtt.toeic_test.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sangtt.toeic_test.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ToeicUserTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ToeicUser.class);
        ToeicUser toeicUser1 = new ToeicUser();
        toeicUser1.setId(1L);
        ToeicUser toeicUser2 = new ToeicUser();
        toeicUser2.setId(toeicUser1.getId());
        assertThat(toeicUser1).isEqualTo(toeicUser2);
        toeicUser2.setId(2L);
        assertThat(toeicUser1).isNotEqualTo(toeicUser2);
        toeicUser1.setId(null);
        assertThat(toeicUser1).isNotEqualTo(toeicUser2);
    }
}
