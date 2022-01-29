package com.sangtt.toeic_test.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class VocabularyMapperTest {

    private VocabularyMapper vocabularyMapper;

    @BeforeEach
    public void setUp() {
        vocabularyMapper = new VocabularyMapperImpl();
    }
}
