package com.sangtt.toeic_test.repository;

import com.sangtt.toeic_test.domain.GrammarQuestion;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the GrammarQuestion entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GrammarQuestionRepository extends JpaRepository<GrammarQuestion, Long> {}
