package com.sangtt.toeic_test.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GrammarAnswerMapperTest {

    private GrammarAnswerMapper grammarAnswerMapper;

    @BeforeEach
    public void setUp() {
        grammarAnswerMapper = new GrammarAnswerMapperImpl();
    }
}
