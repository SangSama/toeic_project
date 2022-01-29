package com.sangtt.toeic_test.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sangtt.toeic_test.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DetailsToeicUserTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DetailsToeicUser.class);
        DetailsToeicUser detailsToeicUser1 = new DetailsToeicUser();
        detailsToeicUser1.setId(1L);
        DetailsToeicUser detailsToeicUser2 = new DetailsToeicUser();
        detailsToeicUser2.setId(detailsToeicUser1.getId());
        assertThat(detailsToeicUser1).isEqualTo(detailsToeicUser2);
        detailsToeicUser2.setId(2L);
        assertThat(detailsToeicUser1).isNotEqualTo(detailsToeicUser2);
        detailsToeicUser1.setId(null);
        assertThat(detailsToeicUser1).isNotEqualTo(detailsToeicUser2);
    }
}
