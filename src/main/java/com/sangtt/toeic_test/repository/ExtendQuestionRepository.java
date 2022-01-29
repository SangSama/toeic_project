package com.sangtt.toeic_test.repository;

import com.sangtt.toeic_test.domain.ExtendQuestion;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the ExtendQuestion entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ExtendQuestionRepository extends JpaRepository<ExtendQuestion, Long> {}
