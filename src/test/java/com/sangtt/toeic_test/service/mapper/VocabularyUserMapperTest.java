package com.sangtt.toeic_test.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class VocabularyUserMapperTest {

    private VocabularyUserMapper vocabularyUserMapper;

    @BeforeEach
    public void setUp() {
        vocabularyUserMapper = new VocabularyUserMapperImpl();
    }
}
