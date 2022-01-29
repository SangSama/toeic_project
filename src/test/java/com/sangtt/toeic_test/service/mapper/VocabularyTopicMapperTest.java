package com.sangtt.toeic_test.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class VocabularyTopicMapperTest {

    private VocabularyTopicMapper vocabularyTopicMapper;

    @BeforeEach
    public void setUp() {
        vocabularyTopicMapper = new VocabularyTopicMapperImpl();
    }
}
