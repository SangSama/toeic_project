package com.sangtt.toeic_test.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sangtt.toeic_test.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PartsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Parts.class);
        Parts parts1 = new Parts();
        parts1.setId(1L);
        Parts parts2 = new Parts();
        parts2.setId(parts1.getId());
        assertThat(parts1).isEqualTo(parts2);
        parts2.setId(2L);
        assertThat(parts1).isNotEqualTo(parts2);
        parts1.setId(null);
        assertThat(parts1).isNotEqualTo(parts2);
    }
}
