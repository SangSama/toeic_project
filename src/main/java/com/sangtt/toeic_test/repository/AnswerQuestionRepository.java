package com.sangtt.toeic_test.repository;

import com.sangtt.toeic_test.domain.AnswerQuestion;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the AnswerQuestion entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AnswerQuestionRepository extends JpaRepository<AnswerQuestion, Long> {}
